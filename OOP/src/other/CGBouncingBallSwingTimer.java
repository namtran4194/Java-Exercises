package other;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class CGBouncingBallSwingTimer extends JFrame {
	private static final long serialVersionUID = 1L;
	// Define named-constants
	private static final int CANVAS_WIDTH = 640;
	private static final int CANVAS_HEIGHT = 480;
	private static final int UPDATE_PERIOD = 50; // milliseconds

	private DrawCanvas canvas; // the drawing canvas (an inner class extends JPanel)

	// Attributes of moving object
	private int x = 100, y = 100; // top-left (x, y)
	private int size = 250; // width and height
	private int xSpeed = 3, ySpeed = 5; // displacement per step in x, y

	// Constructor to setup the GUI components and event handlers
	public CGBouncingBallSwingTimer() {
		canvas = new DrawCanvas();
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		this.setContentPane(canvas);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setTitle("Bouncing Ball");
		this.setVisible(true);

		// Define an ActionListener to perform update at regular interval
		ActionListener updateTask = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				update(); // update the (x, y) position
				repaint(); // Refresh the JFrame, callback paintComponent()
			}
		};
		// Allocate a Timer to run updateTask's actionPerformed() after every delay msec
		new Timer(UPDATE_PERIOD, updateTask).start();
	}

	// Update the (x, y) position of the moving object
	public void update() {
		x += xSpeed;
		y += ySpeed;
		if (x > CANVAS_WIDTH - size || x < 0) {
			xSpeed = -xSpeed;
		}
		if (y > CANVAS_HEIGHT - size || y < 0) {
			ySpeed = -ySpeed;
		}
	}

	// Define inner class DrawCanvas, which is a JPanel used for custom drawing
	private class DrawCanvas extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // paint parent's background
			setBackground(Color.BLACK);
			g.setColor(Color.BLUE);
			g.fillOval(x, y, size, size); // draw a circle
		}
	}

	// The entry main method
	public static void main(String[] args) {
		// Run GUI codes in Event-Dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CGBouncingBallSwingTimer(); // Let the constructor do the job
			}
		});
	}
}
