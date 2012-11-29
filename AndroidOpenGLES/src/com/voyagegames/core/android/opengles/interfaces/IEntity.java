package com.voyagegames.core.android.opengles.interfaces;

import com.voyagegames.core.android.opengles.modules.Vector3D;

public interface IEntity {
	
	Vector3D position();
	void setPosition(Vector3D position);
	
	float rotationAngle();
	void setRotationAngle(float angle);
	
	Vector3D rotationAxis();
	void setRotationAxis(Vector3D axis);
	
	float[] modelMatrix();

}
