package ui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Show instructions or about
 */
public class Infomation extends JOptionPane {
	private static final long serialVersionUID = 1L;
	private static final Font FONT_TEXT = new Font("Verdana", Font.ROMAN_BASELINE, 13);
	private static String instructions;
	private static String info;
	private static String email;
	private static String facebook;

	public Infomation() {
		if (instructions == null && info == null) {
			instructions = "";
			info = "";
			readData("instructions");
			readData("about");
		}
	}

	/**
	 * Get text in the file as string
	 */
	public void readData(String fileName) {
		int position = 0;
		String line;
		InputStream in = ClassLoader.getSystemResourceAsStream(fileName);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
			while ((line = reader.readLine()) != null) {
				if (fileName.equals("about")) {
					position++;
					if (position == 4)
						email = line;
					else if (position == 5)
						facebook = line;
					else
						info += line + "\n";
				} else {
					instructions += line + "\n";
				}
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Show instructions
	 */
	public void instructions() {
		JTextArea instr = new JTextArea(instructions);
		instr.setEditable(false);
		instr.setOpaque(false);
		instr.setFont(FONT_TEXT);
		showMessageDialog(getRootFrame(), instr, "Instructions", INFORMATION_MESSAGE);
	}

	/**
	 * Show about
	 */
	public void about() {
		JPanel root = new JPanel(new BorderLayout());
		JPanel contactsPanel = new JPanel(new GridLayout(2, 1));
		JPanel row;

		JTextArea infoLabel = new JTextArea(info.trim());
		infoLabel.setFont(FONT_TEXT);
		infoLabel.setEditable(false);
		infoLabel.setOpaque(false);
		// Email
		row = new JPanel(new GridBagLayout());
		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(FONT_TEXT);
		row.add(emailLabel);
		emailLabel = new JLabel(email);
		emailLabel.setFont(FONT_TEXT);
		emailLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		row.add(emailLabel);
		contactsPanel.add(row);
		// Facebook
		row = new JPanel(new GridBagLayout());
		JLabel fbLabel = new JLabel("Facebook: ");
		fbLabel.setFont(FONT_TEXT);
		row.add(fbLabel);
		fbLabel = new JLabel(facebook);
		fbLabel.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 12));
		fbLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		row.add(fbLabel);
		contactsPanel.add(row);
		// Handle event
		sendMail(emailLabel);
		goWebsite(fbLabel);

		root.add(infoLabel, BorderLayout.PAGE_START);
		root.add(contactsPanel, BorderLayout.LINE_START);
		showMessageDialog(getRootFrame(), root, "About MineSweeper", INFORMATION_MESSAGE);
	}

	/**
	 * Handle event when click to JLabel
	 */
	private static void sendMail(JLabel mail) {
		mail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().mail(new URI("mailto:namtran4194@gmail.com"));
					} catch (IOException | URISyntaxException ex) {
						handleException(ex.toString());
					}
				} else {
					handleException("A problem occurred");
				}
			}
		});
	}

	/**
	 * Handle event when click to JLabel
	 */
	private static void goWebsite(JLabel website) {
		website.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(new URI("http://fb.com/namtran4194"));
					} catch (IOException | URISyntaxException ex) {
						handleException(ex.toString());
					}
				} else {
					handleException("A problem occurred");
				}
			}
		});
	}

	/**
	 * Handle exception
	 */
	private static void handleException(String exception) {
		showMessageDialog(getRootFrame(), exception, "Error", ERROR_MESSAGE);
	}
}
