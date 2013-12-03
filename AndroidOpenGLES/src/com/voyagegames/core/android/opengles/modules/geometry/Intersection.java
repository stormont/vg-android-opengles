package com.voyagegames.core.android.opengles.modules.geometry;

public class Intersection {

	public static Vector3D rayPlane(final Ray ray, final Plane plane) {
		final float denominator = Vector3D.dot(ray.direction, plane.normal);
		if (denominator == 0f) return null;  // undefined division; ray is parallel to plane
		
		final float numerator = -Vector3D.dot(ray.origin, plane.normal);
		final float t = numerator / denominator;
		if (t < 0f) return null;  // ray originates behind plane
		
		return ray.origin.add(ray.direction.scale(t));
	}
	
	public static Vector3D raySphere(final Ray ray, final Sphere sphere) {
		final Vector3D offset = ray.origin.subtract(sphere.origin);
		final float b = ray.direction.dot(offset);
		final float c = offset.dot(offset) - (sphere.radius * sphere.radius);
		final float quadratic = (b * b) - c;
		if (quadratic < 0) return null;  // ray misses sphere
		
		final float sqrt = (float)Math.sqrt(quadratic);
		final float t1 = -b + sqrt;
		final float t2 = -b - sqrt;
		if (t1 < 0 || t2 < 0) return null;  // ray originates from within sphere
		
		// return closest point to ray origin
		return ray.origin.add(ray.direction.scale(t1 < t2 ? t1 : t2));
	}
	
	public static boolean pointSphere(final Sphere sphere, final Vector3D point) {
		final float distanceSquared = point.distanceSquared(sphere.origin);
		return (distanceSquared <= (sphere.radius * sphere.radius));
	}
	
}
