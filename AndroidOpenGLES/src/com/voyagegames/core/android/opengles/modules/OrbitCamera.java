package com.voyagegames.core.android.opengles.modules;


//
// This is a modification on spherical coordinates as defined at: http://en.wikipedia.org/wiki/Spherical_coordinate_system
//
// In OpenGL, rather than:
//     z
//     |
//     |
//     |
//     |______y
//    /
//   /
//  x
//
// We have:
//
//     y
//     |   x
//     |  /
//     | /
//     |/_____z
//
// Note that the Z-axis become the X-axis, the X-axis becomes the Y-axis, and the Y-axis becomes the Z-axis.
//
// The angles are still defined in their same respective positions: 
//   theta defined as the azimuth angle (between what is now the Z- and X-planes, zeroed at the Z-axis)
//   phi as the polar angle (between what is now the XZ- and Y- planes, zeroed at the XZ-axis)
//
public class OrbitCamera extends Camera {

	private static final float FULL_ROTATION_DEGREES = 360.0f;
	private static final float HALF_ROTATION_DEGREES = 180.0f;
	private static final float QUARTER_ROTATION_DEGREES = 90.0f;
	
	private float mRadius;
	private float mPhi;
	private float mTheta;
	
	public OrbitCamera(final LookAt lookAt, final Frustum frustum) {
		super(lookAt, frustum);
		calculateOrbit();
		addTheta(0f);
	}
	
	public OrbitCamera(final LookAt lookAt, final Frustum frustum, final float fieldOfView) {
		super(lookAt, frustum, fieldOfView);
		calculateOrbit();
		addTheta(0f);
	}
	
	private void calculateOrbit() {
		final float x = mLookAt.eye.x - mLookAt.at.x;
		final float y = mLookAt.eye.y - mLookAt.at.y;
		final float z = mLookAt.eye.z - mLookAt.at.z;
		
		mRadius = (float)Math.sqrt(x * x + y * y + z * z);
    	mTheta = (float)Math.acos(y / mRadius) + HALF_ROTATION_DEGREES;  // constrained from [0 .. pi/2)
    	mPhi = (float)Math.atan2(x, z) + QUARTER_ROTATION_DEGREES;  // constrained from [0 .. pi/2)
	}
	
	public float theta() {
		return mTheta;
	}
	
	public float phi() {
		return mPhi;
	}
	
	public void setTheta(final float theta) {
		mTheta = theta;
        updateLookAt();
	}
	
	public void setPhi(final float phi) {
		mPhi = phi;
        updateLookAt();
	}
	
	public void addTheta(final float theta) {
		mTheta += theta;
        
        while (mTheta < 0f) {
        	mTheta += FULL_ROTATION_DEGREES;
        }
        
        while (mTheta >= FULL_ROTATION_DEGREES) {
        	mTheta -= FULL_ROTATION_DEGREES;
        }
        
        updateLookAt();
	}
	
	public void addPhi(final float phi) {
		mPhi += phi;
        
        while (mPhi < 0f) {
        	mPhi += FULL_ROTATION_DEGREES;
        }
        
        while (mPhi >= FULL_ROTATION_DEGREES) {
        	mPhi -= FULL_ROTATION_DEGREES;
        }
        
        updateLookAt();
	}
	
	private void updateLookAt() {
        final float radTheta = (float)Math.toRadians(mTheta + QUARTER_ROTATION_DEGREES);
        final float radPhi = (float)Math.toRadians(mPhi);
        final float upDirection = mPhi < 180.0f ? 1.0f : -1.0f;
        
        final float sinAnglePhi = (float)Math.sin(radPhi);
        final float cosAnglePhi = (float)Math.cos(radPhi);
        final float sinAngleTheta = (float)Math.sin(radTheta);
        final float cosAngleTheta = (float)Math.cos(radTheta);
        
        final float offsetX = mRadius * sinAnglePhi * cosAngleTheta * -upDirection;
        final float offsetY = mRadius * cosAnglePhi;
        final float offsetZ = mRadius * sinAnglePhi * sinAngleTheta;
        
        super.setLookAt(new LookAt(
        		new Vector3D(offsetX, offsetY, offsetZ),
        		new Vector3D(mLookAt.at.x, mLookAt.at.y, mLookAt.at.z),
        		new Vector3D(0, upDirection * 1.0f, 0)));
	}

}
