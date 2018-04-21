import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

class arbRotation extends JPanel implements ChangeListener, ActionListener {
	cube cb;
	SimpleCanvas sCanvas;
	xyzRotations xyzR;
	JSlider arbSlider;
	JTextField xField, yField, zField;
	JButton b1, b2;

	public arbRotation(cube _cb, SimpleCanvas _sCanvas, xyzRotations _xyzR) {
		cb = _cb;
		sCanvas = _sCanvas;
		xyzR = _xyzR;

		setLayout(new GridLayout(1, 1, 0, 30));

		// Initializing our x, y, and z fields for our plane coordinates.
		xField = new JTextField("1", 1);
		xField.addActionListener(this);
		yField = new JTextField("1", 1);
		yField.addActionListener(this);
		zField = new JTextField("1", 1);
		zField.addActionListener(this);
		JLabel coordLabel = new JLabel("Arbitrary Coordinates");
		JPanel c = new JPanel();
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		c.add(coordLabel);
		c.add(xField);
		c.add(yField);
		c.add(zField);

		// Arbitrary Rotation slider.
		arbSlider = new JSlider(JSlider.VERTICAL, 0, 360, 0);
		arbSlider.addChangeListener(this);
		arbSlider.setMajorTickSpacing(30);
		arbSlider.setMinorTickSpacing(15);
		arbSlider.setPaintTicks(true);
		arbSlider.setPaintLabels(true);
		JLabel arblabel = new JLabel("Arbitrary Rotation");
		JPanel a = new JPanel();
		a.setLayout(new BoxLayout(a, BoxLayout.Y_AXIS));
		a.add(arblabel);
		a.add(arbSlider);

		// Our toggle and reset buttons
		b1 = new JButton("Toggle Wireframe");
		b1.addActionListener(this);
		b2 = new JButton("Reset Cube");
		b2.addActionListener(this);
		JPanel b = new JPanel();
		b.setLayout(new BoxLayout(b, BoxLayout.X_AXIS));
		b.add(b1);
		b.add(b2);

		// Putting everything together in a border panel.
		JPanel o = new JPanel();
		o.setLayout(new BorderLayout());
		o.add(c, BorderLayout.NORTH);
		o.add(a, BorderLayout.CENTER);
		o.add(b, BorderLayout.SOUTH);
		add(o);

	}// end contructor

	public void stateChanged(ChangeEvent ev) {
		// If we are using the slider, use the input values as the arbitrary
		// plane.
		if (arbSlider.getValueIsAdjusting()) {
			// Checking if the input is illegal (i.e. NaN)
			try {
				// Take textfield input, convert to double, make coordinates out
				// of it, use it as the input for abitrary rotation along with
				// the slider value, and repaint.
				double x = Double.parseDouble(xField.getText());
				double y = Double.parseDouble(yField.getText());
				double z = Double.parseDouble(zField.getText());
				coord arb = new coord(x, y, z);
				cb.rotateArb(arb, arbSlider.getValue());
				sCanvas.repaint();
			}

			// Catch and error message
			catch (java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid Input in Coordinate Field\n" + e.toString(), "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}// end
		// stateChanged

	public void actionPerformed(ActionEvent e) {
		// If we hit button 1, toggle the wireframe.
		if (e.getSource() == b1) {
			sCanvas.toggle();
			sCanvas.repaint();
		}
		// If we hit button 2, reset the cube and all associated values.
		if (e.getSource() == b2) {
			arbSlider.setValue(0);
			xyzR.reset();
			cb.reset();
			sCanvas.repaint();
		}
	}

}