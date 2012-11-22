package com.vg.core.android.opengles.modules;

import java.util.List;

import android.opengl.GLES20;

public class ShaderHandles {
	
	private static final String TAG = ShaderHandles.class.getName();
	
	public final int program;
	
	private final List<String> attribVars;
	private final List<String> uniformVars;

	public ShaderHandles(final int program, final List<String> attribVars, final List<String> uniformVars) {
		this.program = program;
		this.attribVars = attribVars;
		this.uniformVars = uniformVars;
	}
	
	public int getAttribLocation(final String name) {
		if (attribVars == null) {
			return -1;
		}
		
		for (int i = 0; i < attribVars.size(); i++) {
			final String a = attribVars.get(i);
			
			if (a == name) {
        		final int location = GLES20.glGetAttribLocation(program, name);
        		Utility.checkGlError(TAG, "glGetAttribLocation " + name);
                
                if (location == -1) {
                    throw new RuntimeException("Could not get attribute location for " + name);
                }
                
                return location;
			}
		}
		
		return -1;
	}
	
	public int getUniformLocation(final String name) {
		if (uniformVars == null) {
			return -1;
		}
		
		for (int i = 0; i < uniformVars.size(); i++) {
			final String a = uniformVars.get(i);
			
			if (a == name) {
        		final int location = GLES20.glGetUniformLocation(program, name);
        		Utility.checkGlError(TAG, "glGetUniformLocation " + name);
                
                if (location == -1) {
                    throw new RuntimeException("Could not get uniform location for " + name);
                }
                
                return location;
			}
		}
		
		return -1;
	}

}
