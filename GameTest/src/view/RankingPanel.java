package view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

public class RankingPanel extends JPanel {
	private JTable playersTable;
	private JScrollPane jSP;
	private JButton backButton;

	public RankingPanel(AbstractTableModel tBM) {
		playersTable = new JTable();
		jSP = new JScrollPane(playersTable);
		add(jSP);
		backButton = new JButton("Back");
		Dimension d = new Dimension(180, 25);
		backButton.setPreferredSize(d);
		backButton.setBounds(200,500,180,25);
		add(backButton);
	}

	public JTable getPlayersTable() {
		return playersTable;
	}

	public void setPlayersTable(JTable playersTable) {
		this.playersTable = playersTable;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}
	}
