
public class coord {
	
	// Coordinates have an x, y, and z.
	double x;
	double y;
	double z;
	
	// Constructor
	public coord(double init_x, double init_y, double init_z) {
		x = init_x;
		y = init_y;
		z = init_z;
	}
	
	// Returns x
	double getX() {
		return x;
	}
	
	// Changes x
	void changeX(double new_x) {
		x = new_x;
	}
	
	// Returns y
	double getY() {
		return y;
	}
	
	// Changes y
	void changeY(double new_y) {
		y = new_y;
	}
	
	// Returns z
	double getZ() {
		return z;
	}
	
	// Changes z
	void changeZ(double new_z) {
		z = new_z;
	}
	
	
}
