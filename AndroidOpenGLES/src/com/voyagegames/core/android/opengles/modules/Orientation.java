package com.voyagegames.core.android.opengles.modules;

import android.opengl.Matrix;

import com.voyagegames.core.android.opengles.interfaces.IOrientation;
import com.voyagegames.core.android.opengles.modules.geometry.Vector3D;

public class Orientation implements IOrientation {
	
	protected float mAngle;
	protected Vector3D mAxis = new Vector3D(0, 0, 0);
	
	@Override
	public float angle() {
		return mAngle;
	}

	@Override
	public Vector3D axis() {
		return mAxis;
	}

	@Override
	public void setAngle(final float angle) {
		mAngle = angle;
	}

	@Override
	public void setAxis(final Vector3D axis) {
		mAxis = axis;
	}

	@Override
	public float[] modelMatrix(final float[] modelMatrix, final int offset) {
		Matrix.setRotateM(modelMatrix, offset, mAngle, mAxis.x, mAxis.y, mAxis.z);
		return modelMatrix;
	}

	@Override
	public float[] modelMatrix() {
        final float[] modelMatrix = new float[16];
        Matrix.setIdentityM(modelMatrix, 0);
		return modelMatrix(modelMatrix, 0);
	}

	@Override
	public void update(final long delta) {
		// no-op
	}
	
}
