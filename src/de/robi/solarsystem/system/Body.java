package de.robi.solarsystem.system;

import de.robi.solarsystem.math.Vector;

public class Body {
	public Vector x;
	public Vector v;
	public Vector a;
	public Vector aNext;
	public final double mass;

	public Body(Vector x, Vector v, double mass) {
		this.x = x;
		this.v = v;
		this.a = new Vector(0, 0);
		this.mass = mass;
		this.aNext = new Vector(0, 0);
	}

	public Vector getStableOrbit(Vector pos, double mass) {
		Vector r = pos.subtract(this.x);
		Vector direction = new Vector(r.y(), - r.x());
		double speed = Math.sqrt(SolarSystem.G * mass / r.length());

		return direction.scale(speed / direction.length());
	}
}
