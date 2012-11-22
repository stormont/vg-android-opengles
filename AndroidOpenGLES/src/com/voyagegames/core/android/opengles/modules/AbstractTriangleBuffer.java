package com.voyagegames.core.android.opengles.modules;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;

import com.voyagegames.core.android.opengles.interfaces.IRenderableBuffer;

public abstract class AbstractTriangleBuffer implements IRenderableBuffer {
    
	protected final FloatBuffer mVertices;
	protected final int mVertexCount;
	
	protected ShaderHandles mShaderHandles;
	
	public AbstractTriangleBuffer(final float[] verticesData, final int dataElements) {
		if (verticesData.length % dataElements != 0) {
			throw new RuntimeException("Invalid vertex data length");
		}
		
		mVertices = ByteBuffer.allocateDirect(verticesData.length * Utility.FLOAT_SIZE_BYTES)
        		.order(ByteOrder.nativeOrder())
        		.asFloatBuffer();
		mVertices.put(verticesData).position(0);
		mVertexCount = verticesData.length / dataElements;
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

}
