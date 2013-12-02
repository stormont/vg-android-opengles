package com.voyagegames.core.android.opengles.modules;

public class Vector4D extends Vector3D {
	
	public final float w;

	public Vector4D() {
		this.w = 0f;
	}

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
	
	public float length() {
		return (float) Math.sqrt(lengthSquared());
	}
	
	public float lengthSquared() {
		return (x * x) + (y * y) + (z * z) + (w * w);
	}
	
	public boolean equals(final Vector4D rhs) {
		return equals(this, rhs);
	}
	
	public static boolean equals(final Vector4D lhs, final Vector4D rhs) {
		return (lhs.x == rhs.x)
				&& (lhs.y == rhs.y)
				&& (lhs.z == rhs.z)
				&& (lhs.w == rhs.w);
	}
	
	public boolean equals(final Vector4D rhs, final float delta) {
		return equals(this, rhs, delta);
	}
	
	public static boolean equals(final Vector4D lhs, final Vector4D rhs, final float delta) {
		return (Math.abs(lhs.x - rhs.x) <= delta)
				&& (Math.abs(lhs.y - rhs.y) <= delta)
				&& (Math.abs(lhs.z - rhs.z) <= delta)
				&& (Math.abs(lhs.w - rhs.w) <= delta);
	}
	
	public Vector4D normalize() {
		return normalize(this);
	}
	
	public static Vector4D normalize(final Vector4D vec) {
		final float len = vec.length();
		if (len == 0f) return null;
		return new Vector4D(vec.x / len, vec.y / len, vec.z / len, vec.w / len);
	}
	
	public Vector4D add(final Vector4D rhs) {
		return add(this, rhs);
	}
	
	public static Vector4D add(final Vector4D lhs, final Vector4D rhs) {
		return new Vector4D(lhs.x + rhs.x, lhs.y + rhs.y, lhs.z + rhs.z, lhs.w + rhs.w);
	}
	
	public Vector4D subtract(final Vector4D rhs) {
		return subtract(this, rhs);
	}
	
	public static Vector4D subtract(final Vector4D lhs, final Vector4D rhs) {
		return new Vector4D(lhs.x - rhs.x, lhs.y - rhs.y, lhs.z - rhs.z, lhs.w - rhs.w);
	}
	
	public Vector4D multiply(final Vector4D rhs) {
		return multiply(this, rhs);
	}
	
	public static Vector4D multiply(final Vector4D lhs, final Vector4D rhs) {
		return new Vector4D(lhs.x * rhs.x, lhs.y * rhs.y, lhs.z * rhs.z, lhs.w * rhs.w);
	}
	
	public Vector4D divide(final Vector4D rhs) {
		return divide(this, rhs);
	}
	
	public static Vector4D divide(final Vector4D lhs, final Vector4D rhs) {
		if (rhs.x == 0f) return null;
		if (rhs.y == 0f) return null;
		if (rhs.z == 0f) return null;
		if (rhs.w == 0f) return null;
		return new Vector4D(lhs.x / rhs.x, lhs.y / rhs.y, lhs.z / rhs.z, lhs.w / rhs.w);
	}
	
	public float distance(final Vector4D rhs) {
		return distance(this, rhs);
	}
	
	public static float distance(final Vector4D lhs, final Vector4D rhs) {
		return Vector4D.subtract(lhs, rhs).length();
	}
	
	public float distanceSquared(final Vector4D rhs) {
		return distanceSquared(this, rhs);
	}
	
	public static float distanceSquared(final Vector4D lhs, final Vector4D rhs) {
		return Vector4D.subtract(lhs, rhs).lengthSquared();
	}
	
	public Vector4D scale(final float scale) {
		return scale(this, scale);
	}
	
	public static Vector4D scale(final Vector4D lhs, final float scale) {
		return new Vector4D(lhs.x * scale, lhs.y * scale, lhs.z * scale, lhs.w * scale);
	}

}
