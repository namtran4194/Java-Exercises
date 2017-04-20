package graphic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/** Testing the full-screen mode */
@SuppressWarnings("serial")
public class FullScreenEscDemo extends JFrame {
	// Windowed mode settings
	private static String winModeTitle = "Switching between Full Screen Mode and Windowed Mode Demo";
	private static int winModeX, winModeY; // top-left corner (x, y)
	private static int winModeWidth, winModeHeight; // width and height

	private boolean inFullScreenMode; // in fullscreen or windowed mode?
	private boolean fullScreenSupported; // is fullscreen supported?

	/** Constructor to set up the GUI components */
	public FullScreenEscDemo() {
		// Get the screen width and height
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// Set the windowed mode initial width and height to about fullscreen
		winModeWidth = (int) dim.getWidth();
		winModeHeight = (int) dim.getHeight() - 35; // minus task bar
		winModeX = 0;
		winModeY = 0;

		// Check if full screen mode supported?
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final GraphicsDevice defaultScreen = env.getDefaultScreenDevice();
		fullScreenSupported = defaultScreen.isFullScreenSupported();

		if (fullScreenSupported) {
			setUndecorated(true);
			setResizable(false);
			defaultScreen.setFullScreenWindow(this); // full-screen mode
			inFullScreenMode = true;
		} else {
			setUndecorated(false);
			setResizable(true);
			defaultScreen.setFullScreenWindow(null); // windowed mode
			setBounds(winModeX, winModeY, winModeWidth, winModeHeight);
			inFullScreenMode = false;
		}

		// Use SPACE key to switch between Windowed and fullscreen modes
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (fullScreenSupported) {
						if (!inFullScreenMode) {
							// Switch to fullscreen mode
							setVisible(false);
							setResizable(false);
							dispose();
							setUndecorated(true);
							defaultScreen.setFullScreenWindow(FullScreenEscDemo.this);
							setVisible(true);
						} else {
							// Switch to windowed mode
							setVisible(false);
							dispose();
							setUndecorated(false);
							setResizable(true);
							defaultScreen.setFullScreenWindow(null);
							setBounds(winModeX, winModeY, winModeWidth, winModeHeight);
							setVisible(true);
						}
						inFullScreenMode = !inFullScreenMode;
						repaint();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});

		// To save the window width and height if the window has been resized.
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
				if (!inFullScreenMode) {
					winModeX = getX();
					winModeY = getY();
				}
			}

			@Override
			public void componentResized(ComponentEvent e) {
				if (!inFullScreenMode) {
					winModeWidth = getWidth();
					winModeHeight = getHeight();
				}
			}
		});

		setContentPane(new DrawCanvas());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(winModeTitle);
		setVisible(true);
	}

	/** DrawCanvas (inner class) is a JPanel used for custom drawing */
	private class DrawCanvas extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.BLACK);

			// Draw a box to indicate the borders
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(5));
			g2d.setColor(Color.RED);
			g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

			// Paint messages
			g.setColor(Color.YELLOW);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
			FontMetrics fm = g.getFontMetrics();
			String msg = inFullScreenMode ? "In Full-Screen mode" : "In Windowed mode";
			int msgWidth = fm.stringWidth(msg);
			int msgAscent = fm.getAscent();
			int msgX = getWidth() / 2 - msgWidth / 2;
			int msgY = getHeight() / 2 + msgAscent / 2;
			g.drawString(msg, msgX, msgY);

			g.setColor(Color.WHITE);
			g.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
			fm = g.getFontMetrics();
			msg = "Press SPACE to toggle between Full-screen/windowed modes, ESC to quit.";
			msgWidth = fm.stringWidth(msg);
			int msgHeight = fm.getHeight();
			msgX = getWidth() / 2 - msgWidth / 2;
			msgY += msgHeight * 1.5;
			g.drawString(msg, msgX, msgY);
		}
	}

	/** The Entry main method */
	public static void main(String[] args) {
		// Run the GUI codes on the Event-Dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FullScreenEscDemo();
			}
		});
	}
}
