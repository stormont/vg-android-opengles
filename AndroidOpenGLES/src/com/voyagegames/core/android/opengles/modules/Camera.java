package com.voyagegames.core.android.opengles.modules;

import android.opengl.Matrix;

import com.voyagegames.core.android.opengles.interfaces.ICamera;
import com.voyagegames.core.android.opengles.modules.geometry.Vector3D;

public class Camera implements ICamera {
	
	public static Vector3D unprojectRay(final float x, final float y, final ICamera camera) {
		final float[] invertedView = new float[16];
		if (!Matrix.invertM(invertedView, 0, camera.viewMatrix(), 0)) return null;
		return MatrixExt.unprojectRay(
				x, y,
				camera.projectionMatrix(), invertedView,
				camera.viewportWidth(), camera.viewportHeight());
	}

	private final float[] mViewMatrix = new float[16];
	private final float[] mProjMatrix = new float[16];
	
	protected LookAt mLookAt;
	protected Frustum mFrustum;
	protected float mFieldOfView;
	protected float mViewportWidth;
	protected float mViewportHeight;
	
	public Camera(final LookAt lookAt, final Frustum frustum) {
		mFieldOfView = 60.0f;
		setLookAt(lookAt);
		setFrustum(frustum);
	}
	
	public Camera(final LookAt lookAt, final Frustum frustum, final float fieldOfView) {
		mFieldOfView = fieldOfView;
		setLookAt(lookAt);
		setFrustum(frustum);
	}

	@Override
	public LookAt lookAt() {
		return mLookAt;
	}

	@Override
	public Frustum frustum() {
		return mFrustum;
	}

	@Override
	public float fieldOfView() {
		return mFieldOfView;
	}
	
	@Override
	public float viewportWidth() {
		return mViewportWidth;
	}
	
	@Override
	public float viewportHeight() {
		return mViewportHeight;
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
	public void setLookAt(final LookAt lookAt) {
		mLookAt = lookAt;
		calculateViewMatrix();
	}

	@Override
	public void setFrustum(final Frustum frustum) {
		mFrustum = frustum;
        calculateProjectionMatrix();
	}
	
	@Override
	public void setFieldOfView(final float fieldOfView) {
		mFieldOfView = fieldOfView;
        calculateProjectionMatrix();
	}

	@Override
	public void setViewport(final float width, final float height) {
		mViewportWidth = width;
		mViewportHeight = height;
        mFieldOfView = width / height;
        calculateProjectionMatrix();
	}
	
	private void calculateViewMatrix() {
        Matrix.setLookAtM(
        		mViewMatrix, 0,
        		mLookAt.eye.x, mLookAt.eye.y, mLookAt.eye.z,
        		mLookAt.at.x, mLookAt.at.y, mLookAt.at.z,
        		mLookAt.up.x, mLookAt.up.y, mLookAt.up.z);
	}
	
	private void calculateProjectionMatrix() {
        Matrix.frustumM(mProjMatrix, 0, -mFieldOfView, mFieldOfView, mFrustum.bottom, mFrustum.top, mFrustum.near, mFrustum.far);
	}

}
