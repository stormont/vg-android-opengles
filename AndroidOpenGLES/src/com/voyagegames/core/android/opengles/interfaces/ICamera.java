package com.voyagegames.core.android.opengles.interfaces;

import com.voyagegames.core.android.opengles.modules.Frustum;
import com.voyagegames.core.android.opengles.modules.LookAt;

public interface ICamera {
	
	LookAt lookAt();
	Frustum frustum();
	float[] viewMatrix();
	float[] projectionMatrix();
	float fieldOfView();
	float viewportWidth();
	float viewportHeight();
	void setLookAt(LookAt lookAt);
	void setFrustum(Frustum frustum);
	void setFieldOfView(float fieldOfView);
	void setViewport(float width, float height);

}
