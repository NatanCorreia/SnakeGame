package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel {
private JButton playButton,userDataButton,loadButton, quitButton;

public ButtonsPanel() {
	setLayout(new GridLayout(4,4,0,50));
	Dimension d = new Dimension(180,25);
	playButton = new JButton("Play");
	playButton.setPreferredSize(d);
	
	add(playButton);
	userDataButton = new JButton("Data");
	userDataButton.setPreferredSize(d);
	add(userDataButton);
	loadButton = new JButton("Load");
	loadButton.setPreferredSize(d);
	add(loadButton);
	quitButton = new JButton("Quit");
	quitButton.setPreferredSize(d);
	add(quitButton);
}

public JButton getPlayButton() {
	return playButton;
}

public void setPlayButton(JButton playButton) {
	this.playButton = playButton;
}

public JButton getUserDataButton() {
	return userDataButton;
}

public void setUserDataButton(JButton userDataButton) {
	this.userDataButton = userDataButton;
}

public JButton getLoadButton() {
	return loadButton;
}

public void setLoadButton(JButton loadButton) {
	this.loadButton = loadButton;
}

public JButton getQuitButton() {
	return quitButton;
}

public void setQuitButton(JButton quitButton) {
	this.quitButton = quitButton;
}

}
