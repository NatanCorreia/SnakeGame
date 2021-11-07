package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DifficultyPanel extends JPanel {
	private JButton easyButton,normalButton,hardButton;

	public DifficultyPanel() {
		setLayout(new GridLayout(6,4,50,50));
		Dimension d = new Dimension(180,25);
		easyButton = new JButton("Easy");
		easyButton.setPreferredSize(d);
		
		add(easyButton);
		normalButton = new JButton("Normal");
		normalButton.setPreferredSize(d);
		add(normalButton);
		hardButton = new JButton("Hard");
		hardButton.setPreferredSize(d);
		add(hardButton);
		
	}

	public JButton getEasyButton() {
		return easyButton;
	}

	public void setEasyButton(JButton easyButton) {
		this.easyButton = easyButton;
	}

	public JButton getNormalButton() {
		return normalButton;
	}

	public void setNormalButton(JButton normalButton) {
		this.normalButton = normalButton;
	}

	public JButton getHardButton() {
		return hardButton;
	}

	public void setHardButton(JButton hardButton) {
		this.hardButton = hardButton;
	}

	

}
