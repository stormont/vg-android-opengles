package com.voyagegames.core.android.opengles.modules;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;

import android.opengl.GLES20;

import com.voyagegames.core.android.opengles.interfaces.IRenderableBuffer;

public abstract class AbstractTriangleBuffer implements IRenderableBuffer {
	
	private static final String TAG = AbstractTriangleBuffer.class.getName();
	
	public static final int POSITION_DATA_SIZE = 3;
	public static final int NORMAL_DATA_SIZE = 3;
	public static final int COLOR_DATA_SIZE = 4;
	public static final int TEX_COORD_DATA_SIZE = 2;
    
	protected final int mVertexCount;
	
	protected ShaderHandles mShaderHandles;
	protected FloatBuffer mVertices;
	protected boolean mUseVertexBufferObject;
	protected int mVertexBufferIndex;
	
	public AbstractTriangleBuffer(final float[] verticesData, final int dataElements) {
		if (verticesData.length % dataElements != 0) {
			throw new RuntimeException("Invalid vertex data length");
		}
		
		mVertices = ByteBuffer.allocateDirect(verticesData.length * Utility.FLOAT_SIZE_BYTES)
        		.order(ByteOrder.nativeOrder())
        		.asFloatBuffer();
		mVertices.put(verticesData).position(0);
		mVertexCount = verticesData.length / dataElements;
		
		mUseVertexBufferObject = false;
		mVertexBufferIndex = 0;
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
		mUseVertexBufferObject = true;
		mVertexBufferIndex = bufferIndex;
		
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, bufferIndex);
		
		// Transfer data from client memory to the buffer. We can release the client memory after this call.
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, mVertices.capacity() * Utility.FLOAT_SIZE_BYTES, mVertices, GLES20.GL_STATIC_DRAW);
		
		// IMPORTANT: Unbind from the buffer when we're done with it.
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		
		mVertices = null;
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
	
	protected void bindVertexBufferObject() {
        if (mUseVertexBufferObject) {
        	GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVertexBufferIndex);
        }
	}
	
	protected void unbindVertexBufferObject() {
        if (mUseVertexBufferObject) {
        	GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        }
	}
	
	protected void setVertexAttrib(final int index, final String var, final int stride, final int offset, final int dataSize) {
    	if (mUseVertexBufferObject) {
    		GLES20.glVertexAttribPointer(index, dataSize, GLES20.GL_FLOAT, false, stride, offset);
    	} else {
        	mVertices.position(offset);
    		GLES20.glVertexAttribPointer(index, dataSize, GLES20.GL_FLOAT, false, stride, mVertices);
    	}
    	
        Utility.checkGlError(TAG, "glVertexAttribPointer " + var);
        GLES20.glEnableVertexAttribArray(index);
        Utility.checkGlError(TAG, "glEnableVertexAttribArray " + var);
	}
	
	protected void draw() {
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, mVertexCount);
        Utility.checkGlError(TAG, "glDrawArrays");
	}

}
