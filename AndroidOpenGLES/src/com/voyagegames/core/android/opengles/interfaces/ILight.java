package com.voyagegames.core.android.opengles.interfaces;

public interface ILight {

	float[] positionInModelSpace();
	float[] positionInWorldSpace(float[] modelMatrix);
	float[] positionInEyeSpace(float[] viewMatrix, float[] worldSpacePosition);

}
