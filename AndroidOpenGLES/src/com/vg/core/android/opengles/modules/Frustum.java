package com.vg.core.android.opengles.modules;

public class Frustum {
	
	public final float top;
	public final float bottom;
	public final float near;
	public final float far;
	
	public Frustum(final float top, final float bottom, final float near, final float far) {
		this.top = top;
		this.bottom = bottom;
		this.near = near;
		this.far = far;
	}

}
