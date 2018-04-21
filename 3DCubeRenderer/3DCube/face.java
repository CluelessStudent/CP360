import java.awt.Color;

public class face {
	// A face contains 4 coordinates and a color.
	coord a;
	coord b;
	coord c;
	coord d;
	Color color;
	
	// Constructor
	public face (coord _a, coord _b, coord _c, coord _d, Color _color) {
		a = _a;
		b = _b;
		c = _c;
		d = _d;
		color = _color;
	}
	
	// Return a
	coord getA() {
		return a;
	}
	
	// Return b
	coord getB() {
		return b;
	}
	
	// Return c
	coord getC() {
		return c;
	}
	
	// Return d
	coord getD() {
		return d;
	}
}
