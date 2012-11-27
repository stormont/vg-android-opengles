package com.voyagegames.core.android.opengles.modules;

import java.util.List;


public class TexturedTriangleBuffer extends AbstractTriangleBuffer {
	
	public enum RequiredAttributes {
		POSITION,
		TEXTURE_COORD
	}
	
    private static final int DATA_ELEMENTS = POSITION_DATA_SIZE + TEX_COORD_DATA_SIZE;
    private static final int DATA_POSITION_OFFSET = 0;
    private static final int DATA_TEX_COORD_OFFSET = POSITION_DATA_SIZE;
    private static final int DATA_STRIDE_BYTES = DATA_ELEMENTS * Utility.FLOAT_SIZE_BYTES;
	
	public TexturedTriangleBuffer(final float[] verticesData) {
		super(verticesData, DATA_ELEMENTS);
	}

	@Override
	public int requiredAttributeSize() {
		return RequiredAttributes.values().length;
	}

	@Override
	public void render(final List<String> attributes) {
		super.render(attributes);
		
		final int[] indices = super.getIndices(attributes, RequiredAttributes.values().length);
        
        super.bindVertexBufferObject();
        super.setVertexAttrib(indices[0], attributes.get(0), DATA_STRIDE_BYTES, DATA_POSITION_OFFSET, POSITION_DATA_SIZE);
        super.setVertexAttrib(indices[1], attributes.get(1), DATA_STRIDE_BYTES, DATA_TEX_COORD_OFFSET, TEX_COORD_DATA_SIZE);
        super.draw();
        super.unbindVertexBufferObject();
	}

}
