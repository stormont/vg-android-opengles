package com.voyagegames.core.android.opengles.modules;

public class Vector4D extends Vector3D {
	
	public final float w;

	public Vector4D(final float x, final float y, final float z, final float w) {
		super(x, y, z);
		this.w = w;
	}
	
	public Vector4D(final float[] values) {
		super(values);
		
		if (values.length < 4) {
			throw new IllegalArgumentException("Invalid values");
		}
		
		this.w = values[3];
	}
	
	public float[] toArray() {
		final float[] result = new float[4];
		
		result[0] = x;
		result[1] = y;
		result[2] = z;
		result[3] = w;
		return result;
	}

}
