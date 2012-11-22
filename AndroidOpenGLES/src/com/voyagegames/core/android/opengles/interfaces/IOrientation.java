package com.voyagegames.core.android.opengles.interfaces;

import com.voyagegames.core.android.opengles.modules.Vector3D;

public interface IOrientation {
	
	float angle();
	Vector3D axis();
	
	void setAngle(float angle);
	void setAxis(Vector3D axis);
	
	float[] modelMatrix(float[] modelMatrix, int offset);
	float[] modelMatrix();
	
	void update(long delta);

}
