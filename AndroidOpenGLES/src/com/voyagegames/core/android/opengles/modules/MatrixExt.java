package com.voyagegames.core.android.opengles.modules;

import android.opengl.Matrix;

public class MatrixExt {
    
    public static Vector3D unprojectRay(
    		final float x, final float y,
    		final float[] projectionMatrix, final float[] viewModelMatrix,
    		final float viewportWidth, final float viewportHeight) {
    	// via: http://www.antongerdelan.net/opengl/raycasting.html
    	// For a common camera-centered viewModelMatrix, invert the camera's view matrix
    	final float[] invertedProjection = new float[16];
    	if (!Matrix.invertM(invertedProjection, 0, projectionMatrix, 0)) return null;
    	
    	final float normalizedX = (2f * x) / viewportWidth - 1f;
    	final float normalizedY = 1f - (2f * y) / viewportHeight;
    	final Vector3D rayNDS = new Vector3D(normalizedX, normalizedY, 1f);
    	final float[] rayClip = new float[] { rayNDS.x, rayNDS.y, -1f, 1f };
    	
    	final float[] rayEye = new float[4];
    	Matrix.multiplyMV(rayEye, 0, invertedProjection, 0, rayClip, 0);
    	rayEye[2] = -1f;
    	rayEye[3] = 0f;
    	
    	final float[] rayWorld = new float[4];
    	Matrix.multiplyMV(rayWorld, 0, viewModelMatrix, 0, rayEye, 0);
    	
    	return new Vector3D(rayWorld).normalize();
    }
	
}
