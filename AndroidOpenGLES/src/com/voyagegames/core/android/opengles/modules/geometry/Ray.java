package com.voyagegames.core.android.opengles.modules.geometry;

public class Ray {

	public final Vector3D origin;
	public final Vector3D direction;
	
	public Ray(final Vector3D origin, final Vector3D direction) {
		this.origin = origin;
		this.direction = direction;
	}
	
}
