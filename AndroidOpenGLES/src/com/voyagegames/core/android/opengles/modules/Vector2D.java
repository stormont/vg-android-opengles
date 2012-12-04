package com.voyagegames.core.android.opengles.modules;

public class Vector2D {
	
	public final float x;
	public final float y;
	
	public Vector2D(final float x, final float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(final float[] values) {
		if (values == null || values.length < 2) {
			throw new IllegalArgumentException("Invalid values");
		}
		
		this.x = values[0];
		this.y = values[1];
	}
	
	public float[] toArray() {
		final float[] result = new float[2];
		
		result[0] = x;
		result[1] = y;
		return result;
	}

}
