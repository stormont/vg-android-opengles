package com.vg.core.android.opengles.modules;

import java.util.List;

import android.opengl.GLES20;


public class TexturedNormalTriangleBuffer extends AbstractTriangleBuffer {
	
	public enum RequiredAttributes {
		POSITION,
		NORMAL,
		TEXTURE_COORD
	}

    private static String TAG = TexturedNormalTriangleBuffer.class.getName();

    private static final int DATA_ELEMENTS = 8;
    private static final int DATA_POSITION_OFFSET = 0;
    private static final int DATA_NORMAL_OFFSET = 3;
    private static final int DATA_TEX_COORD_OFFSET = 6;
    private static final int DATA_STRIDE_BYTES = DATA_ELEMENTS * Utility.FLOAT_SIZE_BYTES;
	
	public TexturedNormalTriangleBuffer(final float[] verticesData) {
		super(verticesData, DATA_ELEMENTS);
	}

	@Override
	public int requiredAttributeSize() {
		return RequiredAttributes.values().length;
	}

	@Override
	public void render(final List<String> attributes) {
		super.render(attributes);
		
		final String positionVar = attributes.get(RequiredAttributes.POSITION.ordinal());
        final int position = mShaderHandles.getAttribLocation(positionVar);
        
        if (position < 0) {
        	throw new RuntimeException(positionVar + " not found");
        }
		
		final String normalVar = attributes.get(RequiredAttributes.NORMAL.ordinal());
        final int normal = mShaderHandles.getAttribLocation(normalVar);
        
        if (normal < 0) {
        	throw new RuntimeException(normalVar + " not found");
        }

		final String texCoordVar = attributes.get(RequiredAttributes.TEXTURE_COORD.ordinal());
        final int texture = mShaderHandles.getAttribLocation(texCoordVar);
        
        if (texture < 0) {
        	throw new RuntimeException(texCoordVar + " not found");
        }
        
    	mVertices.position(DATA_POSITION_OFFSET);
        GLES20.glVertexAttribPointer(position, 3, GLES20.GL_FLOAT, false, DATA_STRIDE_BYTES, mVertices);
        Utility.checkGlError(TAG, "glVertexAttribPointer " + positionVar);
        
        GLES20.glEnableVertexAttribArray(position);
        Utility.checkGlError(TAG, "glEnableVertexAttribArray " + positionVar);
        
    	mVertices.position(DATA_NORMAL_OFFSET);
        GLES20.glVertexAttribPointer(normal, 3, GLES20.GL_FLOAT, false, DATA_STRIDE_BYTES, mVertices);
        Utility.checkGlError(TAG, "glVertexAttribPointer " + normalVar);
        
        GLES20.glEnableVertexAttribArray(normal);
        Utility.checkGlError(TAG, "glEnableVertexAttribArray " + normalVar);

        mVertices.position(DATA_TEX_COORD_OFFSET);
        GLES20.glVertexAttribPointer(texture, 2, GLES20.GL_FLOAT, false, DATA_STRIDE_BYTES, mVertices);
        Utility.checkGlError(TAG, "glVertexAttribPointer " + texCoordVar);
        
        GLES20.glEnableVertexAttribArray(texture);
        Utility.checkGlError(TAG, "glEnableVertexAttribArray " + texCoordVar);
        
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, mVertexCount);
        Utility.checkGlError(TAG, "glDrawArrays");
	}

}
