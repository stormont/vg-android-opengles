package com.vg.core.android.opengles.interfaces;

import com.vg.core.android.opengles.modules.ShaderHandles;

public interface IShaderSet {
	
	String vertexShader();
	String fragmentShader();
	ShaderHandles handles();

	void create();
	void prepareRender();

}
