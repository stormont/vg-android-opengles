package com.voyagegames.core.android.opengles.interfaces;

import com.voyagegames.core.android.opengles.modules.geometry.Vector3D;

public interface IEntity {
	
	Vector3D position();
	void setPosition(Vector3D position);
	
	float rotationAngle();
	void setRotationAngle(float angle);
	
	Vector3D rotationAxis();
	void setRotationAxis(Vector3D axis);
	
	Vector3D scale();
	void setScale(Vector3D scale);
	
	float[] modelMatrix();
	float[] translationMatrix();
	float[] rotationMatrix();
	float[] scalingMatrix();
	float[] offsetModelMatrix(IEntity entity);
}
