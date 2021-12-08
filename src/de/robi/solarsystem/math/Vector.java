package de.robi.solarsystem.math;

public record Vector(double x, double y) {
	public double dot(Vector b) {
		return this.x*b.x + this.y*b.y;
	}

	public double square() {
		return this.dot(this);
	}

	public double length() {
		return Math.sqrt(this.square());
	}

	public Vector add(Vector b) {
		return new Vector(this.x + b.x, this.y + b.y);
	}

	public Vector invert() {
		return new Vector(-this.x, -this.y);
	}

	public Vector subtract(Vector b) {
		return add(b.invert());
	}

	public Vector scale(double scalar) {
		return new Vector(scalar * this.x, scalar * this.y);
	}

	public Vector normalize() {
		return this.scale(1 / this.length());
	}
}
