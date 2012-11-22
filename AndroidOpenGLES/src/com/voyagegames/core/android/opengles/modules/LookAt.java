package com.voyagegames.core.android.opengles.modules;

public class LookAt {

	public final Vector3D eye;
	public final Vector3D center;
	public final Vector3D up;
	
	public LookAt(final Vector3D eye, final Vector3D center, final Vector3D up) {
		this.eye = eye;
		this.center = center;
		this.up = up;
	}

}
