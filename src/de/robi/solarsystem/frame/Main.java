package de.robi.solarsystem.frame;

import de.robi.solarsystem.math.Vector;
import de.robi.solarsystem.system.Body;
import de.robi.solarsystem.system.SolarSystem;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		//SolarSystem system = new SolarSystem();
		SolarSystem system = SolarSystem.sol();



		//system.bodies.add(new Body(new Vector(-100E9, 0), new Vector(0, -12913), 2E30));
		/*system.bodies.add(new Body(new Vector(100E9, 0), new Vector(0, 12913), 2E30));

		system.bodies.add(new Body(new Vector(100E9 - 40E9, 0), new Vector(0, 12913 - 57749), 2E20));*/

		/*
		Random random = new Random();

		double posMax = 150E9;
		double speedMax = 50000;
		for(int i = 0; i < 100; i++) {
			Vector x = new Vector(random.nextDouble() * 2 * posMax - posMax, random.nextDouble() * 2 * posMax - posMax);
			Vector v = new Vector(random.nextDouble() * 2 * speedMax - speedMax, random.nextDouble() * 2 * speedMax - speedMax);
			double mass = random.nextDouble() * 1E30 + 1E25;
			system.bodies.add(new Body(x, v, mass));
		}*/

		new Frame(system);

		long last = System.nanoTime();
		while(true) {
			long time = System.nanoTime();
			system.update((time - last) * 2.592E-3);
			last = time;
		}
	}
}
