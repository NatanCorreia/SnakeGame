package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RankingPanel extends JPanel {
	private JTable playersTable;
	private JScrollPane jSP;

	public RankingPanel() {
		playersTable = new JTable();
		jSP = new JScrollPane(playersTable);
		add(jSP);
	}

	public JTable getPlayersTable() {
		return playersTable;
	}

	public void setPlayersTable(JTable playersTable) {
		this.playersTable = playersTable;
	}

}
