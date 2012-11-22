package com.vg.core.android.opengles.modules;

import java.util.ArrayList;
import java.util.List;

import android.opengl.GLES20;
import android.util.Log;

import com.vg.core.android.opengles.interfaces.IShaderSet;


public abstract class ShaderSet implements IShaderSet {
	
	private static final String TAG = ShaderSet.class.getName();
	
	protected ShaderHandles mHandles;
	protected final List<String> mAttribVars = new ArrayList<String>();
	protected final List<String> mUniformVars = new ArrayList<String>();
	
	@Override
	public ShaderHandles handles() {
		return mHandles;
	}

	@Override
	public void create() {
        final int programHandle = Utility.createProgram(this.vertexShader(), this.fragmentShader());
        
        if (programHandle == 0) {
        	Log.e(TAG, "Failed to create shader program");
        	throw new RuntimeException("Failed to create shader program");
        }
        
		mHandles = new ShaderHandles(programHandle, mAttribVars, mUniformVars);
	}
	
	@Override
	public void prepareRender() {
        GLES20.glUseProgram(mHandles.program);
        Utility.checkGlError(TAG, "glUseProgram");
	}

}
