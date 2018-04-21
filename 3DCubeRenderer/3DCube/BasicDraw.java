
/**
 * BasicDraw.java
 *
 *
 * Template for beginning graphics programs.
 *
 */

import java.awt.*;
import javax.swing.*;

public class BasicDraw {

	// For this type of program, event handling determines the path of
	// execution. This main method "looks" like it sets up the frame
	// and then stops.

	public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setTitle("3D Cube");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
        // Initializing our objects for the frame
    	cube cb = new cube();
		SimpleCanvas myCanvas = new SimpleCanvas(cb);
		xyzRotations xyzPanel = new xyzRotations(cb, myCanvas);
		arbRotation arbPanel = new arbRotation(cb, myCanvas, xyzPanel);
		
		// Setting up the frame.
		Container cp = frame.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(myCanvas, BorderLayout.CENTER);
		cp.add(xyzPanel, BorderLayout.SOUTH);
		cp.add(arbPanel, BorderLayout.EAST);
		frame.setTitle("Basic Draw");
		frame.pack(); //resizes to preferred size for components.
		frame.setResizable(false);
		frame.setVisible(true);
		
    }
} // BasicDraw
