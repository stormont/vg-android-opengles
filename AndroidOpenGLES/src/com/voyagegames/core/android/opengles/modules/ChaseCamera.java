package com.voyagegames.core.android.opengles.modules;

import com.voyagegames.core.android.opengles.modules.geometry.Vector3D;

public class ChaseCamera extends OrbitCamera {

	public ChaseCamera(final LookAt lookAt, final Frustum frustum) {
		super(lookAt, frustum);
	}

	public ChaseCamera(final LookAt lookAt, final Frustum frustum, final float fieldOfView) {
		super(lookAt, frustum, fieldOfView);
	}
	
	public Vector3D target() {
		return this.mLookAt.at;
	}
	
	public void setTarget(final Vector3D target) {
		final float x = target.x - this.mLookAt.at.x;
		final float y = target.y - this.mLookAt.at.y;
		final float z = target.z - this.mLookAt.at.z;
		
		final LookAt lookAt = new LookAt(
				new Vector3D(this.mLookAt.eye.x + x, this.mLookAt.eye.y + y, this.mLookAt.eye.z + z),
				new Vector3D(x, y, z),
				this.mLookAt.up);
		
		super.setLookAt(lookAt);
	}

}
