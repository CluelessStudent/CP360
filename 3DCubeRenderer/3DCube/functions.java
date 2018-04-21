
public class functions {

	// Many of these functions are meant for vectors, but take in coordinates as
	// we represent vectors as a set of coordinates.

	// Produces a vector from 2 points via subtraction.
	coord vectorSubtraction(coord u, coord v) {
		double x = v.getX() - u.getX();
		double y = v.getY() - u.getY();
		double z = v.getZ() - u.getZ();
		return new coord(x, y, z);
	}

	// Produces a unit vector from a given coordinate.
	coord unitVector(coord u) {
		double magnitude = magnitude(u);
		double x = u.getX() / magnitude;
		double y = u.getY() / magnitude;
		double z = u.getZ() / magnitude;
		return new coord(x, y, z);
	}

	// Produces the cross-product of 2 coordinates.
	coord crossProduct(coord u, coord v) {
		double i = u.getY() * v.getZ() - v.getY() * u.getZ();
		double j = v.getX() * u.getZ() - u.getX() * v.getZ();
		double k = u.getX() * v.getY() - v.getX() * u.getY();
		return new coord(i, j, k);
	}

	// Gives the magnitude given a coordinate.
	double magnitude(coord u) {
		double newx = Math.pow(u.getX(), 2);
		double newy = Math.pow(u.getY(), 2);
		double newz = Math.pow(u.getZ(), 2);
		double magnitude = Math.pow(newx + newy + newz, .5);
		return magnitude;
	}

	// Returns the dot product of 2 coordinates.
	double dotProduct(coord u, coord v) {
		double x = u.getX() * v.getX();
		double y = u.getY() * v.getY();
		double z = u.getZ() * v.getZ();
		return x + y + z;
	}

	// Returns the angle between 2 coordinates.
	double getAngle(coord u, coord v) {
		double dot = dotProduct(u, v);
		double um = magnitude(u);
		double vm = magnitude(v);
		double result = (dot / (um * vm));
		result = Math.acos(result);
		return result;
	}

	// Given an x and z of a 3D point, calculates the x in 2D space that is length
	// e away from the perspective.
	double transform(double x, double z, double e) {
		return (x / (1 - (z / e)));
	}
}
