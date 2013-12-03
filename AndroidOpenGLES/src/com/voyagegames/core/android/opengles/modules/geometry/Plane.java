package com.voyagegames.core.android.opengles.modules.geometry;

public class Plane {

	public final Vector3D origin;
	public final Vector3D normal;
	
	public Plane(final Vector3D origin, final Vector3D normal) {
		this.origin = origin;
		this.normal = normal;
	}
	
}
