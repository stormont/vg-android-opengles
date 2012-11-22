package com.vg.core.android.opengles.modules;

import android.opengl.GLES20;

import com.vg.core.android.opengles.interfaces.ICamera;
import com.vg.core.android.opengles.interfaces.IScene;

public class Scene implements IScene {
	
	private final Vector4D mClearColor;
	private final int mClearMask;
	
	private ICamera mCamera;
	
	public Scene(final Vector4D clearColor, final int clearMask) {
		mClearColor = clearColor;
		mClearMask = clearMask;
	}
	
	@Override
	public ICamera camera() {
		return mCamera;
	}

	@Override
	public void setCamera(final ICamera camera) {
		mCamera = camera;
	}

	@Override
	public void updateViewport(final int width, final int height) {
        GLES20.glViewport(0, 0, width, height);
        mCamera.setFrustum(width, height);
	}

	@Override
	public void clear() {
        GLES20.glClearColor(mClearColor.x, mClearColor.y, mClearColor.z, mClearColor.w);
        GLES20.glClear(mClearMask);
	}

}
