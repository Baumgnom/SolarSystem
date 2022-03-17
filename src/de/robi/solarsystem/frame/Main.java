package de.robi.solarsystem.frame;

import de.robi.solarsystem.math.Vector;
import de.robi.solarsystem.system.Body;
import de.robi.solarsystem.system.SolarSystem;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		SolarSystem system = new SolarSystem();
		//SolarSystem system = SolarSystem.sol();



		//Body sun = new Body(new Vector(0, 0), new Vector(0, 0), 2E30);
		//system.bodies.add(sun);
		//12913
		//system.bodies.add(new Body(new Vector(-10E9, 0), new Vector(0, -57749), 2E30));
		//system.bodies.add(new Body(new Vector(10E9, 0), new Vector(0, 57749), 2E30));

		/*
		Body body1 = new Body(new Vector(1E10, 0), Vector.nullVector, 2E30);
		Body body2 = new Body(new Vector(-1E10, 0), Vector.nullVector, 2E30);

		body1.v = body1.getCircularTwoBodyOrbit(body2.x, body2.mass);
		body2.v = body2.getCircularTwoBodyOrbit(body1.x, body1.mass);

		system.bodies.add(body1);
		system.bodies.add(body2);

		Body planet = new Body(new Vector(10E10, 0), Vector.nullVector, 2E20);
		planet.v = planet.getStableOrbit(Vector.nullVector, body1.mass * 2);

		system.bodies.add(planet);
		 */

		Body body1 = new Body(Vector.nullVector, Vector.nullVector, 8E25);
		Body body2 = new Body(new Vector(5E7, 0), Vector.nullVector, 3E24);
		body2.v = body2.getStableOrbit(body1.x, body1.mass);

		system.bodies.add(body1);
		system.bodies.add(body2);

		//system.bodies.add(new Body(new Vector(100E9 - 40E9, 0), new Vector(0, 12913 + 57749), 2E20));

		//system.bodies.add(new Body(new Vector(-30E9, -30E9), new Vector(0, 2000), 2E25));

		/*Random random = new Random();

		double posMax = 150E9;
		double mMax = 1E9;
		double speedMax = 50000;
		for(int i = 0; i < 30; i++) {
			Vector x = new Vector(random.nextDouble() * 2 * posMax - posMax, random.nextDouble() * 2 * posMax - posMax);
			double mass = (random.nextDouble() * 9 + 1) * Math.pow(10, random.nextInt(6) + 25);
			Vector v = new Vector(random.nextDouble() * 2 * speedMax - speedMax, random.nextDouble() * 2 * speedMax - speedMax);
			//Vector v = sun.getStableOrbit(x, sun.mass);
			Body body = new Body(x, v, mass);
			system.bodies.add(body);

			/*int moons = random.nextInt(5) + random.nextInt(5) - 4;
			for(int j = 0; j < moons; j++) {
				Vector xm = x.add(new Vector(random.nextDouble() * 2 * mMax - mMax, random.nextDouble() * 2 * mMax - mMax));
				double massM = random.nextDouble() * 1E25 + 1E22;
				Vector vm = body.getStableOrbit(xm, body.mass);
				system.bodies.add(new Body(xm, vm.add(v), massM));
			}
		}*/

		new Frame(system);

		long last = System.nanoTime();
		while(true) {
			long time = System.nanoTime();
			system.update((time - last) * 8.64E-5);//2.592E-3);
			last = time;
		}
	}
}
