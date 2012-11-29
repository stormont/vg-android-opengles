package com.voyagegames.core.android.opengles.interfaces;

import com.voyagegames.core.android.opengles.modules.Frustum;
import com.voyagegames.core.android.opengles.modules.LookAt;

public interface ICamera {
	
	LookAt lookAt();
	Frustum frustum();
	float[] viewMatrix();
	float[] projectionMatrix();
	void setLookAt();
	void setFrustum(float width, float height);

}
