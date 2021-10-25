package model;

import java.awt.Point;

public class GameMap {
	private int qtdCellsWidth;
	private int qtdCellsHeight;
	private Point spawnPoint;
	
	// pode add tb obstaculos...
	public GameMap(int cellsWidth, int cellsHeight, Point spawnPoint) {
		super();
		this.qtdCellsWidth = cellsWidth;
		this.qtdCellsHeight = cellsHeight;
		this.spawnPoint = spawnPoint;
	}

	public int getQtdCellsWidth() {
		return qtdCellsWidth;
	}

	public void setQtdCellsWidth(int cellsWidth) {
		this.qtdCellsWidth = cellsWidth;
	}

	public int getQtdCellsHeight() {
		return qtdCellsHeight;
	}

	public void setQtdCellsHeight(int cellsHeight) {
		this.qtdCellsHeight = cellsHeight;
	}

	public Point getSpawnPoint() {
		return spawnPoint;
	}

	public void setSpawnPoint(Point spawnPoint) {
		this.spawnPoint = spawnPoint;
	}
	
	
}
