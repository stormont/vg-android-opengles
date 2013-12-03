package com.voyagegames.core.android.opengles.modules.geometry;

public class Intersection {

	public static Vector3D rayPlane(final Ray ray, final Plane plane) {
		final float denominator = Vector3D.dot(ray.direction, plane.normal);
		if (denominator == 0f) return null;
		
		final float numerator = -Vector3D.dot(ray.origin, plane.normal);
		final float t = numerator / denominator;
		if (t < 0f) return null;
		
		return ray.origin.add(ray.direction.scale(t));
	}
	
	public static boolean pointSphere(final Sphere sphere, final Vector3D point) {
		final float distanceSquared = point.distanceSquared(sphere.origin);
		return (distanceSquared <= (sphere.radius * sphere.radius));
	}
	
}
