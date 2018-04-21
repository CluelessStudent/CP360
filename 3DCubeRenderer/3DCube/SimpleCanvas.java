
/**
 * SimpleCanvas.java
 *
 * Part of the basic graphics Template.
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class SimpleCanvas extends JPanel {
	// Initializing values and objects.
	boolean wire_or_colors = false;
	double e = 300;
	Color myColor;
	Line2D.Double myLine;
	cube cb;

	// Constructor
	public SimpleCanvas(cube _cb) {
		setPreferredSize(new Dimension(1000, 1000));
		setBackground(Color.lightGray);
		cb = _cb;
	}

	// Method to toggle wireframe
	public void toggle() {
		if (wire_or_colors == false) {
			wire_or_colors = true;
		} else {
			wire_or_colors = false;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(getWidth() / 2, getHeight() / 2);

		// Creating a function object to use functions.
		functions func = new functions();

		// Scaling up the cube.
		g2d.scale(3, -3);

		// If we do have wireframes on.
		if (wire_or_colors == true) {
			// Looping through all faces
			for (int i = 0; i <= 5; i++) {
				face current_face = cb.getFace(i);
				// Applying the proper transform to get the correct 2D values
				// to represent the 3D image

				// All x points, need to case to int for polygon.
				int[] x = { (int) func.transform(current_face.getA().getX(), current_face.getA().getZ(), e),
							(int) func.transform(current_face.getB().getX(), current_face.getB().getZ(), e),
							(int) func.transform(current_face.getC().getX(), current_face.getC().getZ(), e),
							(int) func.transform(current_face.getD().getX(), current_face.getD().getZ(), e) };

				// All y points.
				int[] y = { (int) func.transform(current_face.getA().getY(), current_face.getA().getZ(), e),
							(int) func.transform(current_face.getB().getY(), current_face.getB().getZ(), e),
							(int) func.transform(current_face.getC().getY(), current_face.getC().getZ(), e),
							(int) func.transform(current_face.getD().getY(), current_face.getD().getZ(), e) };

				// New shape, applied with black color and drawn.
				Polygon face = new Polygon(x, y, 4);
				g2d.setPaint(Color.black);
				g2d.draw(face);
			}
		}

		// With wireframe off.
		else {
			// Looping through all faces.
			for (int i = 0; i <= 5; i++) {
				face current_face = cb.getFace(i);

				// Doing all of the necessary calculations so that we can
				// determine if we can see the face or not, depending on the
				// angle between out face and the outwards facing normal of the
				// face.
				coord ab = func.vectorSubtraction(current_face.getA(), current_face.getB());
				coord da = func.vectorSubtraction(current_face.getA(), current_face.getD());
				coord cross = func.crossProduct(da, ab);
				coord eye = new coord(0, 0, e);
				double angle = func.getAngle(cross, func.vectorSubtraction(eye, current_face.getA()));
				
				// If the face is in a 180 degree range of our eye, draw it.
				if (Math.toDegrees(angle) < 90 && Math.toDegrees(angle) > -90) {
					
					// All x points
					int[] x = { (int) func.transform(current_face.getA().getX(), current_face.getA().getZ(), e),
								(int) func.transform(current_face.getB().getX(), current_face.getB().getZ(), e),
								(int) func.transform(current_face.getC().getX(), current_face.getC().getZ(), e),
								(int) func.transform(current_face.getD().getX(), current_face.getD().getZ(), e) };
					
					// All y points
					int[] y = { (int) func.transform(current_face.getA().getY(), current_face.getA().getZ(), e),
								(int) func.transform(current_face.getB().getY(), current_face.getB().getZ(), e),
								(int) func.transform(current_face.getC().getY(), current_face.getC().getZ(), e),
								(int) func.transform(current_face.getD().getY(), current_face.getD().getZ(), e) };
					
					// New shape, we apply its unique color and fill it in. 
					Polygon face = new Polygon(x, y, 4);
					g2d.setPaint(current_face.color);
					g2d.fill(face);
				}
			}
		}
	}
}
