package com.voyagegames.core.android.opengles.modules;

import android.opengl.GLES20;

import com.voyagegames.core.android.opengles.interfaces.ICamera;
import com.voyagegames.core.android.opengles.interfaces.IScene;

public class Scene implements IScene {
	
	private final Vector4D mClearColor;
	private final int mClearMask;
	
	private ICamera mCamera;
	private Vector2D mViewportSize;
	
	public Scene(final Vector4D clearColor, final int clearMask) {
		mClearColor = clearColor;
		mClearMask = clearMask;
		mViewportSize = new Vector2D(1.0f, 1.0f);
	}
	
	@Override
	public ICamera camera() {
		return mCamera;
	}

	@Override
	public Vector2D viewportSize() {
		return mViewportSize;
	}

	@Override
	public void setCamera(final ICamera camera) {
		mCamera = camera;
		mCamera.setViewport(mViewportSize.x, mViewportSize.y);
	}

	@Override
	public void updateViewport(final int width, final int height) {
        GLES20.glViewport(0, 0, width, height);
        mViewportSize = new Vector2D(width, height);
        
        if (mCamera != null) {
        	mCamera.setViewport(width, height);
        }
	}

	@Override
	public void clear() {
        GLES20.glClearColor(mClearColor.x, mClearColor.y, mClearColor.z, mClearColor.w);
        GLES20.glClear(mClearMask);
	}

}
