package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import adapter.HighScores;
import adapter.Player;

/**
 * Show High scores
 */
public class HSDialog extends JOptionPane {
	private static final long serialVersionUID = 1L;

	private static final Font FONT_TEXT = new Font("Verdana", Font.ROMAN_BASELINE, 12);
	public HighScores hScore = new HighScores();
	public Panel root;
	public JPanel dataPanel;

	/**
	 * Constructor to set up the data and the UI components
	 */
	public HSDialog() {
		root = new Panel(new BorderLayout());
		setTopPlayer();
		JPanel btnPanel = new JPanel(new GridBagLayout());
		JButton reset = new JButton("Reset High Scores");
		btnPanel.add(reset);
		reset.setMargin(new Insets(2, 2, 2, 2));
		reset.setToolTipText("Reset high scores");
		btnListener(reset);
		root.add(btnPanel, BorderLayout.SOUTH);
		showMessageDialog(this, root, "High Scores", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Add the players to the frame
	 */
	private void setTopPlayer() {
		dataPanel = new JPanel(new GridLayout(10, 1), true);
		JLabel row;
		String line;
		List<Player> list = hScore.getListTopPlayer();
		if (list.isEmpty()) {
			line = "No data!";
			row = new JLabel(line, SwingConstants.CENTER);
			row.setFont(FONT_TEXT);
			dataPanel.add(row);
		} else
			for (int i = 0; i < list.size(); i++) {
				Player player = list.get(i);
				line = (i + 1) + ". " + player.getName() + ": " + player.getScore();
				row = new JLabel(line);
				row.setFont(FONT_TEXT);
				dataPanel.add(row);
			}
		root.add(dataPanel, BorderLayout.CENTER);
	}

	private void btnListener(JButton button) {
		button.addActionListener(new ActionListener() {
			String message = "Are you sure you want to reset high scores?";

			@Override
			public void actionPerformed(ActionEvent e) {
				int resultDialog = showConfirmDialog(HSDialog.this, message, "Warning", YES_NO_OPTION, QUESTION_MESSAGE);
				if (resultDialog == YES_OPTION) {
					dataPanel.removeAll();
					hScore.removeAll();
					hScore.writeData();
					dataPanel.repaint();
				}
			}
		});
	}
}
