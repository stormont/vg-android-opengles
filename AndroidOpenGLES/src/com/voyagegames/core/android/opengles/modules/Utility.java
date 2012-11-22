package com.voyagegames.core.android.opengles.modules;

import android.opengl.GLES20;
import android.util.Log;

public class Utility {

    private static String TAG = Utility.class.getName();
	
    public static final int FLOAT_SIZE_BYTES = 4;

    public static void checkGlError(final String tag, final String op) {
        final int error = GLES20.glGetError();
        
        if (error != GLES20.GL_NO_ERROR) {
            Log.e(tag, op + ": glError " + error);
            throw new RuntimeException(op + ": glError " + error);
        }
    }

    public static int loadShader(final int shaderType, final String source) {
        final int shader = GLES20.glCreateShader(shaderType);
        
        if (shader == 0) {
        	throw new RuntimeException("Error creating shader");
        }
    
        final int[] compiled = new int[1];
        
        GLES20.glShaderSource(shader, source);
        GLES20.glCompileShader(shader);
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        
        if (compiled[0] != 0) {
        	return shader;
        }
    
        Log.e(TAG, "Could not compile shader " + shaderType + ":");
        Log.e(TAG, GLES20.glGetShaderInfoLog(shader));
        GLES20.glDeleteShader(shader);
        throw new RuntimeException("Error compiling shader");
    }

    public static int createProgram(final String vertexSource, final String fragmentSource) {
        final int vertexShader = Utility.loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
        
        if (vertexShader == 0) {
            return 0;
        }

        final int fragmentShader = Utility.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
        
        if (fragmentShader == 0) {
            return 0;
        }

        final int program = GLES20.glCreateProgram();
        
        if (program == 0) {
        	throw new RuntimeException("Error creating program");
        }
    
        GLES20.glAttachShader(program, vertexShader);
        Utility.checkGlError(TAG, "glAttachShader");
        
        GLES20.glAttachShader(program, fragmentShader);
        Utility.checkGlError(TAG, "glAttachShader");
        
        final int[] linkStatus = new int[1];
        
        GLES20.glLinkProgram(program);
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
        
        if (linkStatus[0] == GLES20.GL_TRUE) {
        	return program;
        }
    
        Log.e(TAG, "Could not link program:");
        Log.e(TAG, GLES20.glGetProgramInfoLog(program));
        GLES20.glDeleteProgram(program);
        throw new RuntimeException("Error linking program");
    }

}
