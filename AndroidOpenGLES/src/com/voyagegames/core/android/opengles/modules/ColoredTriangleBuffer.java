package com.voyagegames.core.android.opengles.modules;

import java.util.List;

import android.opengl.GLES20;


public class ColoredTriangleBuffer extends AbstractTriangleBuffer {
	
	public enum RequiredAttributes {
		POSITION,
		COLOR
	}

    private static String TAG = ColoredTriangleBuffer.class.getName();

    private static final int DATA_ELEMENTS = 7;
    private static final int DATA_POSITION_OFFSET = 0;
    private static final int DATA_COLOR_OFFSET = 3;
    private static final int DATA_STRIDE_BYTES = DATA_ELEMENTS * Utility.FLOAT_SIZE_BYTES;
	
	public ColoredTriangleBuffer(final float[] verticesData) {
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

		final String colorVar = attributes.get(RequiredAttributes.COLOR.ordinal());
        final int color = mShaderHandles.getAttribLocation(colorVar);
        
        if (color < 0) {
        	throw new RuntimeException(colorVar + " not found");
        }
        
    	mVertices.position(DATA_POSITION_OFFSET);
        GLES20.glVertexAttribPointer(position, 3, GLES20.GL_FLOAT, false, DATA_STRIDE_BYTES, mVertices);
        Utility.checkGlError(TAG, "glVertexAttribPointer " + positionVar);
        
        GLES20.glEnableVertexAttribArray(position);
        Utility.checkGlError(TAG, "glEnableVertexAttribArray " + positionVar);

        mVertices.position(DATA_COLOR_OFFSET);
        GLES20.glVertexAttribPointer(color, 4, GLES20.GL_FLOAT, false, DATA_STRIDE_BYTES, mVertices);
        Utility.checkGlError(TAG, "glVertexAttribPointer " + colorVar);
        
        GLES20.glEnableVertexAttribArray(color);
        Utility.checkGlError(TAG, "glEnableVertexAttribArray " + colorVar);
        
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, mVertexCount);
        Utility.checkGlError(TAG, "glDrawArrays");
	}

}
