package com.voyagegames.core.android.opengles.modules;

import com.voyagegames.core.android.opengles.modules.geometry.Vector3D;

public class LookAt {

	public final Vector3D eye;
	public final Vector3D at;
	public final Vector3D up;
	
	public LookAt(final Vector3D eye, final Vector3D at, final Vector3D up) {
		this.eye = eye;
		this.at = at;
		this.up = up;
	}

}
