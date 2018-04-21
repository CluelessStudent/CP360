import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

class xyzRotations extends JPanel implements ChangeListener {
	cube cb;
	SimpleCanvas sCanvas;
	JSlider xSlider, ySlider, zSlider;

	void reset() {
		xSlider.setValue(0);
		ySlider.setValue(0);
		zSlider.setValue(0);
	}

	public xyzRotations(cube _cb, SimpleCanvas _sCanvas) {
		cb = _cb;
		sCanvas = _sCanvas;

		setLayout(new GridLayout(3, 1, 0, 20));

		// Slider for rotation around the X-Axis.
		xSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
		xSlider.setMajorTickSpacing(30);
		xSlider.setMinorTickSpacing(15);
		xSlider.setPaintTicks(true);
		xSlider.setPaintLabels(true);
		xSlider.addChangeListener(this);
		JLabel xlabel = new JLabel("X-Axis Rotation");
		JPanel x = new JPanel();
		x.setLayout(new BoxLayout(x, BoxLayout.X_AXIS));
		x.add(xlabel);
		x.add(xSlider);
		add(x);

		// Slider for rotation around the Y-Axis.
		ySlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
		ySlider.addChangeListener(this);
		ySlider.setMajorTickSpacing(30);
		ySlider.setMinorTickSpacing(15);
		ySlider.setPaintTicks(true);
		ySlider.setPaintLabels(true);
		JLabel ylabel = new JLabel("Y-Axis Rotation");
		JPanel y = new JPanel();
		y.setLayout(new BoxLayout(y, BoxLayout.X_AXIS));
		y.add(ylabel);
		y.add(ySlider);
		add(y);

		// Slider for rotation around the Z-Axis.
		zSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
		zSlider.addChangeListener(this);
		zSlider.setMajorTickSpacing(30);
		zSlider.setMinorTickSpacing(15);
		zSlider.setPaintTicks(true);
		zSlider.setPaintLabels(true);
		JLabel zlabel = new JLabel("Z-Axis Rotation");
		JPanel z = new JPanel();
		z.setLayout(new BoxLayout(z, BoxLayout.X_AXIS));
		z.add(zlabel);
		z.add(zSlider);
		add(z);

	}// end contructor

	public void stateChanged(ChangeEvent ev) {
		// If xSlider moves, rotate the cube around x by the slider's value.
		if (xSlider.getValueIsAdjusting()) {
			cb.rotateX(xSlider.getValue());
			sCanvas.repaint();
		}
		// Ditto, but y.
		if (ySlider.getValueIsAdjusting()) {
			cb.rotateY(ySlider.getValue());
			sCanvas.repaint();
		}
		// Ditto, but zx.
		if (zSlider.getValueIsAdjusting()) {
			cb.rotateZ(zSlider.getValue());
			sCanvas.repaint();
		}
	}// end stateChanged

}