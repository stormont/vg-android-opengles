package com.vg.core.android.opengles.interfaces;

public interface ICamera {
	
	float[] viewMatrix();
	float[] projectionMatrix();
	void setLookAt();
	void setFrustum(int width, int height);

}
