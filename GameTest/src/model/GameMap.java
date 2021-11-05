package model;

import java.awt.Point;
import java.util.ArrayList;

public class GameMap {
	private int qtdCellsWidth;
	private int qtdCellsHeight;
	private Point spawnPoint;
	private Obstacle obstacles;
	
	// pode add tb obstaculos...
	public GameMap(int cellsWidth, int cellsHeight, Point spawnPoint, Obstacle obstacles) {
		this.qtdCellsWidth = cellsWidth;
		this.qtdCellsHeight = cellsHeight;
		this.spawnPoint = spawnPoint;
		this.obstacles = obstacles;
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

	public Obstacle getObstacles() {
		return obstacles;
	}

	public void setObstacles(Obstacle obstacles) {
		this.obstacles = obstacles;
	}

	
	
	
}
