package com.voyagegames.core.android.opengles.buffers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.List;

import android.opengl.GLES20;

import com.voyagegames.core.android.opengles.interfaces.IRenderableBuffer;
import com.voyagegames.core.android.opengles.modules.ShaderHandles;
import com.voyagegames.core.android.opengles.modules.Utility;

public abstract class AbstractTriangleBuffer implements IRenderableBuffer {
	
	private static final String TAG = AbstractTriangleBuffer.class.getName();
	
	public static final int POSITION_DATA_SIZE = 3;
	public static final int NORMAL_DATA_SIZE = 3;
	public static final int COLOR_DATA_SIZE = 4;
	public static final int TEX_COORD_DATA_SIZE = 2;
    
	protected final int mVertexCount;
	protected final int mIndexCount;
	protected final int mTriangleType;
	
	protected ShaderHandles mShaderHandles;
	protected FloatBuffer mVertices;
	protected ShortBuffer mIndices;
	protected boolean mUseVertexBufferObject;
	protected boolean mUseIndexBufferObject;
	protected int mVertexBufferIndex;
	protected int mIndexBufferIndex;
	
	public AbstractTriangleBuffer(final float[] verticesData, final int dataElementSize, final int triangleType) {
		if (verticesData.length % dataElementSize != 0) {
			throw new RuntimeException("Invalid vertex data length");
		}
		
		mVertices = ByteBuffer.allocateDirect(verticesData.length * Utility.FLOAT_SIZE_BYTES)
        		.order(ByteOrder.nativeOrder())
        		.asFloatBuffer();
		mVertices.put(verticesData).position(0);
		mVertexCount = verticesData.length / dataElementSize;
		mIndexCount = 0;
		mTriangleType = triangleType;
		
		mUseVertexBufferObject = false;
		mUseIndexBufferObject = false;
		mVertexBufferIndex = 0;
		mIndexBufferIndex = 0;
	}
	
	public AbstractTriangleBuffer(final float[] verticesData, final int dataElementSize, final short[] indicesData, final int triangleType) {
		if (verticesData.length % dataElementSize != 0) {
			throw new RuntimeException("Invalid vertex data length");
		}
		
		mVertices = ByteBuffer.allocateDirect(verticesData.length * Utility.FLOAT_SIZE_BYTES)
        		.order(ByteOrder.nativeOrder())
        		.asFloatBuffer();
		mVertices.put(verticesData).position(0);
		
		mIndices = ByteBuffer.allocateDirect(indicesData.length * Utility.SHORT_SIZE_BYTES)
        		.order(ByteOrder.nativeOrder())
        		.asShortBuffer();
		mIndices.put(indicesData).position(0);
		
		mVertexCount = verticesData.length / dataElementSize;
		mIndexCount = indicesData.length;
		mTriangleType = triangleType;
		
		mUseVertexBufferObject = false;
		mUseIndexBufferObject = false;
		mVertexBufferIndex = 0;
		mIndexBufferIndex = 0;
	}

	@Override
	public void setHandles(final ShaderHandles handles) {
		mShaderHandles = handles;
	}
	
	@Override
	public void render(final List<String> attributes) {
		if (attributes == null || attributes.size() != this.requiredAttributeSize()) {
			throw new RuntimeException("Invalid attributes");
		}
	}
	
	@Override
	public void convertToVertexBufferObject(final int bufferIndex) {
		if (mVertices == null) {
			return;
		}
		
		mUseVertexBufferObject = true;
		mVertexBufferIndex = bufferIndex;
		
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, bufferIndex);
		
		// Transfer data from client memory to the buffer. We can release the client memory after this call.
		GLES20.glBufferData(
				GLES20.GL_ARRAY_BUFFER,
				mVertices.capacity() * Utility.FLOAT_SIZE_BYTES,
				mVertices,
				GLES20.GL_STATIC_DRAW);
		
		// IMPORTANT: Unbind from the buffer when we're done with it.
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		
		mVertices = null;
	}
	
	@Override
	public void convertToIndexBufferObject(final int bufferIndex) {
		if (mIndices == null) {
			return;
		}
		
		mUseIndexBufferObject = true;
		mIndexBufferIndex = bufferIndex;
		
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, bufferIndex);
		
		// Transfer data from client memory to the buffer. We can release the client memory after this call.
		GLES20.glBufferData(
				GLES20.GL_ELEMENT_ARRAY_BUFFER,
				mIndices.capacity() * Utility.SHORT_SIZE_BYTES,
				mIndices,
				GLES20.GL_STATIC_DRAW);
		
		// IMPORTANT: Unbind from the buffer when we're done with it.
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		mIndices = null;
	}
	
	protected int[] getIndices(final List<String> attributes, final int requiredSize) {
		if (attributes.size() != requiredSize) {
			throw new IllegalArgumentException("attributes must be of size " + requiredSize);
		}
		
		final int count = attributes.size();
		final int[] indices = new int[count];
		
		for (int i = 0; i < count; i++) {
			final String var = attributes.get(i);
			final int index = mShaderHandles.getAttribLocation(var);
        
			if (index < 0) {
				throw new RuntimeException(var + " not found");
			}
			
			indices[i] = index;
		}
        
        return indices;
	}
	
	protected void bindBufferObjects() {
        if (mUseVertexBufferObject) {
        	GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVertexBufferIndex);
        }
        
        if (mUseIndexBufferObject) {
        	GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, mIndexBufferIndex);
        }
	}
	
	protected void unbindBufferObjects() {
        if (mUseVertexBufferObject) {
        	GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        }
        
        if (mUseIndexBufferObject) {
        	GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
        }
	}
	
	protected void setVertexAttrib(final int index, final String var, final int stride, final int offset, final int dataSize) {
    	if (mUseVertexBufferObject) {
    		GLES20.glVertexAttribPointer(index, dataSize, GLES20.GL_FLOAT, false, stride, offset * Utility.FLOAT_SIZE_BYTES);
    	} else {
        	mVertices.position(offset);
    		GLES20.glVertexAttribPointer(index, dataSize, GLES20.GL_FLOAT, false, stride, mVertices);
    	}
    	
        Utility.checkGlError(TAG, "glVertexAttribPointer " + var);
        GLES20.glEnableVertexAttribArray(index);
        Utility.checkGlError(TAG, "glEnableVertexAttribArray " + var);
	}
	
	protected void draw() {
		if (mUseIndexBufferObject) {
			GLES20.glDrawElements(mTriangleType, mIndexCount, GLES20.GL_UNSIGNED_SHORT, 0);
			Utility.checkGlError(TAG, "glDrawElements");
		} else {
			GLES20.glDrawArrays(mTriangleType, 0, mVertexCount);
			Utility.checkGlError(TAG, "glDrawArrays");
		}
	}

}
