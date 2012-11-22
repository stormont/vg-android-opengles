package com.voyagegames.core.android.opengles.interfaces;

import android.opengl.GLSurfaceView.Renderer;

public interface IRenderingContext {
	
	// Gets the OpenGL ES version supported by this rendering context
	int openGLESVersion();
	
	// Gets the OpenGL ES renderer used by the context 
	Renderer renderer();

}
