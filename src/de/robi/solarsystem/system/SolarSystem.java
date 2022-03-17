package de.robi.solarsystem.system;

import de.robi.solarsystem.math.Vector;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem {
	public final List<Body> bodies;

	public static final double G = 6.67E-11;

	public SolarSystem() {
		bodies = new ArrayList<>();
	}

	public void update(double t) {
		for (Body body : bodies) {
			body.a = new Vector(0, 0);
		}
		for(int i = 0; i < bodies.size(); i++) {
			for(int j = i + 1; j < bodies.size(); j++) {
				Body body1 = bodies.get(i);
				Body body2 = bodies.get(j);

				Vector r = body1.x.subtract(body2.x);

				Vector force = r.scale(G / (r.square() * r.length()));
				body1.a = body1.a.subtract(force.scale(body2.mass));
				body2.a = body2.a.add(force.scale(body1.mass));
			}
		}

		for (Body body : bodies) {
			body.x = body.x.add(body.v.scale(t));
			body.v = body.v.add(body.a.scale(t));
		}
	}

	public Vector centerOfWeight(Vector anchor, double fadeStart, double fadeEnd) {
		Vector sum = new Vector(0, 0);
		double mass = 0;
		for (Body body : bodies) {
			double weight = 1;
			if(anchor != null) {
				Vector r = body.x.subtract(anchor);
				double distance = r.length();


				weight = 1 - (distance - fadeStart) / (fadeEnd - fadeStart);

				if(weight < 0) weight = 0;
				else if(weight > 1) weight = 1;
			}


			sum = sum.add(body.x.scale(weight * body.mass));
			mass += weight * body.mass;
		}

		return sum.scale(1 / mass);
	}

	public double calculateEnergy() {
		double V = 0;
		double T = 0;

		for(int i = 0; i < bodies.size(); i++) {
			T += bodies.get(i).mass * bodies.get(i).v.square() / 2;
			for(int j = i + 1; j < bodies.size(); j++) {
				Body body1 = bodies.get(i);
				Body body2 = bodies.get(j);

				Vector r = body1.x.subtract(body2.x);

				V -= 2 * body1.mass * body2.mass * G / r.length();
			}
		}

		return V + T;
	}

	public static SolarSystem sol() {
		SolarSystem system = new SolarSystem();
		system.bodies.add(new Body(new Vector(0, 0), new Vector(0, 0), 2E30));
		system.bodies.add(new Body(new Vector(0, 150E9), new Vector(30000, 0), 6E24));
		system.bodies.add(new Body(new Vector(3.8E8, 150E9), new Vector(30000, 1000), 7E22));

		return system;
	}
}
