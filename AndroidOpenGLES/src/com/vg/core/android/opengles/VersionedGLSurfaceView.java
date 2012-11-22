package com.vg.core.android.opengles;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class VersionedGLSurfaceView extends GLSurfaceView {

	// Private to prevent use... Only defined to remove lint warnings
	private VersionedGLSurfaceView(final Context context) {
		super(context);
	}

	public VersionedGLSurfaceView(final Context context, final int openGLVersion, final Renderer renderer) {
		super(context);
        setEGLContextClientVersion(openGLVersion);
        setRenderer(renderer);
	}

}
