package com.voyagegames.core.android.opengles.modules;

import android.opengl.Matrix;

import com.voyagegames.core.android.opengles.interfaces.ICamera;

public class Camera implements ICamera {

	private final LookAt mLookAt;
	private final Frustum mFrustum;
	private final float[] mViewMatrix = new float[16];
	private final float[] mProjMatrix = new float[16];
	
	public Camera(final LookAt lookAt, final Frustum frustum) {
		mLookAt = lookAt;
		mFrustum = frustum;
		setLookAt();
	}

	@Override
	public float[] viewMatrix() {
		return mViewMatrix;
	}

	@Override
	public float[] projectionMatrix() {
		return mProjMatrix;
	}

	@Override
	public void setLookAt() {
        Matrix.setLookAtM(
        		mViewMatrix, 0,
        		mLookAt.eye.x, mLookAt.eye.y, mLookAt.eye.z,
        		mLookAt.center.x, mLookAt.center.y, mLookAt.center.z,
        		mLookAt.up.x, mLookAt.up.y, mLookAt.up.z);
	}

	@Override
	public void setFrustum(final int width, final int height) {
        final float ratio = (float) width / height;
        Matrix.frustumM(mProjMatrix, 0, -ratio, ratio, mFrustum.bottom, mFrustum.top, mFrustum.near, mFrustum.far);
	}

}
