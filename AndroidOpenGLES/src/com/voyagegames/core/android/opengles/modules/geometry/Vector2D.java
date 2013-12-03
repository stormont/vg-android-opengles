package com.voyagegames.core.android.opengles.modules.geometry;


public class Vector2D {
	
	public final float x;
	public final float y;
	
	public Vector2D() {
		this.x = 0f;
		this.y = 0f;
	}
	
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
	
	public float length() {
		return (float) Math.sqrt(lengthSquared());
	}
	
	public float lengthSquared() {
		return (x * x) + (y * y);
	}
	
	public boolean equals(final Vector2D rhs) {
		return equals(this, rhs);
	}
	
	public static boolean equals(final Vector2D lhs, final Vector2D rhs) {
		return (lhs.x == rhs.x)
				&& (lhs.y == rhs.y);
	}
	
	public boolean equals(final Vector2D rhs, final float delta) {
		return equals(this, rhs, delta);
	}
	
	public static boolean equals(final Vector2D lhs, final Vector2D rhs, final float delta) {
		return (Math.abs(lhs.x - rhs.x) <= delta)
				&& (Math.abs(lhs.y - rhs.y) <= delta);
	}
	
	public Vector2D normalize() {
		return normalize(this);
	}
	
	public static Vector2D normalize(final Vector2D vec) {
		final float len = vec.length();
		if (len == 0f) return null;
		return new Vector2D(vec.x / len, vec.y / len);
	}
	
	public Vector2D unit() {
		return normalize(this);
	}
	
	public static Vector2D unit(final Vector2D vec) {
		return normalize(vec);
	}
	
	public Vector2D add(final Vector2D rhs) {
		return add(this, rhs);
	}
	
	public static Vector2D add(final Vector2D lhs, final Vector2D rhs) {
		return new Vector2D(lhs.x + rhs.x, lhs.y + rhs.y);
	}
	
	public Vector2D subtract(final Vector2D rhs) {
		return subtract(this, rhs);
	}
	
	public static Vector2D subtract(final Vector2D lhs, final Vector2D rhs) {
		return new Vector2D(lhs.x - rhs.x, lhs.y - rhs.y);
	}
	
	public float dot(final Vector2D rhs) {
		return dot(this, rhs);
	}
	
	public static float dot(final Vector2D lhs, final Vector2D rhs) {
		return (lhs.x * rhs.x) + (lhs.y * rhs.y);
	}
	
	public float distance(final Vector2D rhs) {
		return distance(this, rhs);
	}
	
	public static float distance(final Vector2D lhs, final Vector2D rhs) {
		return Vector2D.subtract(lhs, rhs).length();
	}
	
	public float distanceSquared(final Vector2D rhs) {
		return distanceSquared(this, rhs);
	}
	
	public static float distanceSquared(final Vector2D lhs, final Vector2D rhs) {
		return Vector2D.subtract(lhs, rhs).lengthSquared();
	}
	
	public Vector2D scale(final float scale) {
		return scale(this, scale);
	}
	
	public static Vector2D scale(final Vector2D lhs, final float scale) {
		return new Vector2D(lhs.x * scale, lhs.y * scale);
	}
	
	public Vector2D multiply(final float length) {
		return scale(this, length);
	}
	
	public static Vector2D multiply(final Vector2D vec, final float length) {
		return scale(vec, length);
	}

}
