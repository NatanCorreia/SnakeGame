package controller;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import model.Player;

public class PlayerTM extends AbstractTableModel {
	private ArrayList<Player> linhas;
	private String[] colunas = null;
	private TableRowSorter tableSorter;
	public PlayerTM(ArrayList<Player> linhas, String[] colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		tableSorter = new TableRowSorter(this);
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex!=3)
		return String.class;
		else
			return int.class;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Player p = linhas.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return p.getName();
		case 1:
			return p.getLogin();
		case 2:
			return p.getEmail();
		case 3:
			return p.getScore();

		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

	@Override
	// modifica na linha e coluna especificada
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Player p = linhas.get(rowIndex); // Carrega o item da linha que deve ser modificado

		switch (columnIndex) { // Seta o valor do campo respectivo
		case 0:
			p.setName(aValue.toString());
			break;
		case 1:
			p.setLogin(aValue.toString());
			break;
		case 2:
			p.setEmail(aValue.toString());
			break;
		case 3:
			p.setScore((int)aValue);

		default:
			
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	
	public void setValueAt(Player aValue, int rowIndex) {
		Player p = linhas.get(rowIndex); 
		p.setName(aValue.getName());
		p.setLogin(aValue.getLogin());
		p.setEmail(aValue.getEmail());
		p.setScore(aValue.getScore());

		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Player getPlayer(int indiceLinha) {
		return linhas.get(indiceLinha);
	}

	public void addPlayer(Player p) {
		
		linhas.add(p);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	
	public void remove(int indiceLinha) {
		linhas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addLista(ArrayList<Player> p) {

		int tamanhoAntigo = getRowCount();

		
		linhas.addAll(p);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	
	public void limpar() {
		linhas.clear();
		fireTableDataChanged();
	}

	
	public boolean isEmpty() {
		return linhas.isEmpty();
	}

	public TableRowSorter getTableSorter() {
		
		return tableSorter;
	}
	

}
