package de.robi.solarsystem.system;

import de.robi.solarsystem.math.Vector;

public class Body {
	public Vector x;
	public Vector v;
	public Vector a;
	//Vector a;
	public final double mass;

	public Body(Vector x, Vector v, double mass) {
		this.x = x;
		this.v = v;
		this.a = new Vector(0, 0);
		this.mass = mass;
	}
}
