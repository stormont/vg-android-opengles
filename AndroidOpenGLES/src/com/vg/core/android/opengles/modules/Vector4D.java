package com.vg.core.android.opengles.modules;

public class Vector4D extends Vector3D {
	
	public final float w;

	public Vector4D(final float x, final float y, final float z, final float w) {
		super(x, y, z);
		this.w = w;
	}

}
