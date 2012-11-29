package com.voyagegames.core.android.opengles.buffers;

import java.util.List;

import com.voyagegames.core.android.opengles.modules.Utility;

import android.opengl.GLES20;


public class TexturedNormalTriangleBuffer extends AbstractTriangleBuffer {
	
	public enum RequiredAttributes {
		POSITION,
		NORMAL,
		TEXTURE_COORD
	}

    private static final int DATA_ELEMENT_SIZE = POSITION_DATA_SIZE + NORMAL_DATA_SIZE + TEX_COORD_DATA_SIZE;
    private static final int DATA_POSITION_OFFSET = 0;
    private static final int DATA_NORMAL_OFFSET = POSITION_DATA_SIZE;
    private static final int DATA_TEX_COORD_OFFSET = POSITION_DATA_SIZE + NORMAL_DATA_SIZE;
    private static final int DATA_STRIDE_BYTES = DATA_ELEMENT_SIZE * Utility.FLOAT_SIZE_BYTES;
	
	public TexturedNormalTriangleBuffer(final float[] verticesData) {
		super(verticesData, DATA_ELEMENT_SIZE, GLES20.GL_TRIANGLES);
	}
	
	public TexturedNormalTriangleBuffer(final float[] verticesData, final int triangleType) {
		super(verticesData, DATA_ELEMENT_SIZE, triangleType);
	}
	
	public TexturedNormalTriangleBuffer(final float[] verticesData, final short[] indicesData) {
		super(verticesData, DATA_ELEMENT_SIZE, indicesData, GLES20.GL_TRIANGLES);
	}
	
	public TexturedNormalTriangleBuffer(final float[] verticesData, final short[] indicesData, final int triangleType) {
		super(verticesData, DATA_ELEMENT_SIZE, indicesData, triangleType);
	}

	@Override
	public int requiredAttributeSize() {
		return RequiredAttributes.values().length;
	}

	@Override
	public void render(final List<String> attributes) {
		super.render(attributes);

		final int[] indices = super.getIndices(attributes, RequiredAttributes.values().length);
        
        super.bindBufferObjects();
        super.setVertexAttrib(indices[0], attributes.get(0), DATA_STRIDE_BYTES, DATA_POSITION_OFFSET, POSITION_DATA_SIZE);
        super.setVertexAttrib(indices[1], attributes.get(1), DATA_STRIDE_BYTES, DATA_NORMAL_OFFSET, NORMAL_DATA_SIZE);
        super.setVertexAttrib(indices[2], attributes.get(2), DATA_STRIDE_BYTES, DATA_TEX_COORD_OFFSET, TEX_COORD_DATA_SIZE);
        super.draw();
        super.unbindBufferObjects();
	}

}
