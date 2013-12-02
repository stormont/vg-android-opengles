package com.voyagegames.core.android.opengles.modules;


public class Vector3D extends Vector2D {
	
	public final float z;
	
	public Vector3D() {
		this.z = 0f;
	}
	
	public Vector3D(final float x, final float y, final float z) {
		super(x, y);
		this.z = z;
	}
	
	public Vector3D(final float[] values) {
		super(values);
		
		if (values.length < 3) {
			throw new IllegalArgumentException("Invalid values");
		}
		
		this.z = values[2];
	}
	
	public float[] toArray() {
		final float[] result = new float[3];
		
		result[0] = x;
		result[1] = y;
		result[2] = z;
		return result;
	}
	
	public float length() {
		return (float) Math.sqrt(lengthSquared());
	}
	
	public float lengthSquared() {
		return (x * x) + (y * y) + (z * z);
	}
	
	public boolean equals(final Vector3D rhs) {
		return equals(this, rhs);
	}
	
	public static boolean equals(final Vector3D lhs, final Vector3D rhs) {
		return (lhs.x == rhs.x)
				&& (lhs.y == rhs.y)
				&& (lhs.z == rhs.z);
	}
	
	public boolean equals(final Vector3D rhs, final float delta) {
		return equals(this, rhs, delta);
	}
	
	public static boolean equals(final Vector3D lhs, final Vector3D rhs, final float delta) {
		return (Math.abs(lhs.x - rhs.x) <= delta)
				&& (Math.abs(lhs.y - rhs.y) <= delta)
				&& (Math.abs(lhs.z - rhs.z) <= delta);
	}
	
	public Vector3D normalize() {
		return normalize(this);
	}
	
	public static Vector3D normalize(final Vector3D vec) {
		final float len = vec.length();
		if (len == 0f) return null;
		return new Vector3D(vec.x / len, vec.y / len, vec.z / len);
	}
	
	public Vector3D add(final Vector3D rhs) {
		return add(this, rhs);
	}
	
	public static Vector3D add(final Vector3D lhs, final Vector3D rhs) {
		return new Vector3D(lhs.x + rhs.x, lhs.y + rhs.y, lhs.z + rhs.z);
	}
	
	public Vector3D subtract(final Vector3D rhs) {
		return subtract(this, rhs);
	}
	
	public static Vector3D subtract(final Vector3D lhs, final Vector3D rhs) {
		return new Vector3D(lhs.x - rhs.x, lhs.y - rhs.y, lhs.z - rhs.z);
	}
	
	public Vector3D multiply(final Vector3D rhs) {
		return multiply(this, rhs);
	}
	
	public static Vector3D multiply(final Vector3D lhs, final Vector3D rhs) {
		return new Vector3D(lhs.x * rhs.x, lhs.y * rhs.y, lhs.z * rhs.z);
	}
	
	public Vector3D divide(final Vector3D rhs) {
		return divide(this, rhs);
	}
	
	public static Vector3D divide(final Vector3D lhs, final Vector3D rhs) {
		if (rhs.x == 0f) return null;
		if (rhs.y == 0f) return null;
		if (rhs.z == 0f) return null;
		return new Vector3D(lhs.x / rhs.x, lhs.y / rhs.y, lhs.z / rhs.z);
	}
	
	public float distance(final Vector3D rhs) {
		return distance(this, rhs);
	}
	
	public static float distance(final Vector3D lhs, final Vector3D rhs) {
		return Vector3D.subtract(lhs, rhs).length();
	}
	
	public float distanceSquared(final Vector3D rhs) {
		return distanceSquared(this, rhs);
	}
	
	public static float distanceSquared(final Vector3D lhs, final Vector3D rhs) {
		return Vector3D.subtract(lhs, rhs).lengthSquared();
	}
	
	public Vector3D scale(final float scale) {
		return scale(this, scale);
	}
	
	public static Vector3D scale(final Vector3D lhs, final float scale) {
		return new Vector3D(lhs.x * scale, lhs.y * scale, lhs.z * scale);
	}

}
