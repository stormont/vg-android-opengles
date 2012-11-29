package com.voyagegames.core.android.opengles.modules;

import android.opengl.Matrix;

import com.voyagegames.core.android.opengles.interfaces.ILight;

public class Light extends Entity implements ILight {

	@Override
	public float[] positionInModelSpace() {
		return new float[] {0.0f, 0.0f, 0.0f, 1.0f};
	}

	@Override
	public float[] positionInWorldSpace(float[] modelMatrix) {
		final float[] worldSpacePosition = new float[4];
		
		Matrix.multiplyMV(worldSpacePosition, 0, modelMatrix, 0, positionInModelSpace(), 0);
		return worldSpacePosition;
	}

	@Override
	public float[] positionInEyeSpace(float[] viewMatrix, float[] worldSpacePosition) {
		final float[] eyeSpacePosition = new float[4];
		
		Matrix.multiplyMV(eyeSpacePosition, 0, viewMatrix, 0, worldSpacePosition, 0);
		return eyeSpacePosition;
	}

}
