import java.awt.Color;

public class cube {

	// Setting up our coordinates and putting them in a list to be easily
	// editted.
	coord a = new coord(-50, -50, 50);
	coord b = new coord(50, -50, 50);
	coord c = new coord(50, 50, 50);
	coord d = new coord(-50, 50, 50);
	coord e = new coord(-50, -50, -50);
	coord f = new coord(50, -50, -50);
	coord g = new coord(50, 50, -50);
	coord h = new coord(-50, 50, -50);
	coord[] coords = { a, b, c, d, e, f, g, h };

	// Making our faces from our coordinates and putting them in a list to be
	// easily retrieved.
	face face_1 = new face(a, b, c, d, Color.blue);
	face face_2 = new face(b, f, g, c, Color.RED);
	face face_3 = new face(f, e, h, g, Color.YELLOW);
	face face_4 = new face(e, a, d, h, Color.cyan);
	face face_5 = new face(c, g, h, d, Color.GREEN);
	face face_6 = new face(a, e, f, b, Color.MAGENTA);
	face[] faces = { face_1, face_2, face_3, face_4, face_5, face_6 };

	// Current rotation values.
	double x_rot = 0;
	double y_rot = 0;
	double z_rot = 0;
	double arb_rot = 0;

	// Function object to perform functions.
	functions func = new functions();

	// Resets the cube to an initial state.
	public void reset() {
		cube cb = new cube();
		coords = cb.coords;
		faces = cb.faces;
		x_rot = 0;
		y_rot = 0;
		z_rot = 0;
		arb_rot = 0;
	}

	// Rotates around the X-Axis.
	public void rotateX(double theta) {
		// We need to make our rotation the change in degrees/radians, so we use
		// (input - current rotation) to achieve this.
		double rot = theta - x_rot;
		rot = Math.toRadians(rot);

		// We change all coordinates by iterating through the list
		for (coord i : coords) {
			double x = i.getX();
			double y = i.getY();
			double z = i.getZ();

			// x does not change in rotations around its axis.
			double newx = x;
			double newy = y * Math.cos(rot) - z * Math.sin(rot);
			double newz = y * Math.sin(rot) + z * Math.cos(rot);

			i.changeX(newx);
			i.changeY(newy);
			i.changeZ(newz);
		}
		// Now change the rotation to the input, because it is now what the
		// rotation
		// of the original shape is. In order to keep rotating consistently, we
		// need
		// to preserve this.
		x_rot = theta;
	}

	public void rotateY(double theta) {
		// Ditto for rotateX, only with rotating around Y.
		double rot = theta - y_rot;
		rot = Math.toRadians(rot);
		for (coord i : coords) {
			double x = i.getX();
			double y = i.getY();
			double z = i.getZ();
			double newx = x * Math.cos(rot) + z * Math.sin(rot);
			double newy = y;
			double newz = -x * Math.sin(rot) + z * Math.cos(rot);

			i.changeX(newx);
			i.changeY(newy);
			i.changeZ(newz);
		}
		y_rot = theta;
	}

	public void rotateZ(double theta) {
		// Ditto with prior rotates, but for z.
		double rot = theta - z_rot;
		rot = Math.toRadians(rot);
		for (coord i : coords) {
			double x = i.getX();
			double y = i.getY();
			double z = i.getZ();
			double newx = x * Math.cos(rot) - y * Math.sin(rot);
			double newy = x * Math.sin(rot) + y * Math.cos(rot);
			double newz = z;

			i.changeX(newx);
			i.changeY(newy);
			i.changeZ(newz);
		}
		z_rot = theta;
	}

	public void rotateArb(coord arb, double theta) {
		double rot = theta - arb_rot;
		rot = Math.toRadians(rot);
		for (coord i : coords) {

			// Setting up our variables so the code is readable
			coord v = func.unitVector(arb);
			double x = v.getX();
			double y = v.getY();
			double z = v.getZ();
			double c = Math.cos(rot);
			double s = Math.sin(rot);

			// Calculating x applying matrix multiplication from page 100
			double x1 = i.getX() * (c + ((1 - c) * Math.pow(x, 2)));
			double x2 = i.getY() * (((1 - c) * x * y) - (s * z));
			double x3 = i.getZ() * (((1 - c) * x * z) + (s * y));
			double newx = x1 + x2 + x3;

			// Calculating y applying matrix multiplication from page 100
			double y1 = i.getX() * (((1 - c) * x * y) + (s * z));
			double y2 = i.getY() * (c + ((1 - c) * Math.pow(y, 2)));
			double y3 = i.getZ() * (((1 - c) * y * z) - (s * x));
			double newy = y1 + y2 + y3;

			// Calculating z applying matrix multiplication from page 100
			double z1 = i.getX() * (((1 - c) * x * z) - (s * y));
			double z2 = i.getY() * (((1 - c) * y * z) + (s * x));
			double z3 = i.getZ() * (c + ((1 - c) * Math.pow(z, 2)));
			double newz = z1 + z2 + z3;

			// Applying the changes
			i.changeX(newx);
			i.changeY(newy);
			i.changeZ(newz);
		}
		arb_rot = theta;
	}

	// Returns face from the list of faces at index i.
	public face getFace(int i) {
		return faces[i];
	}
}
