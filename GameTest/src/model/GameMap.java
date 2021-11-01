package model;

import java.awt.Point;
import java.util.ArrayList;

public class GameMap {
	private int qtdCellsWidth;
	private int qtdCellsHeight;
	private Point spawnPoint;
	private ArrayList<Obstacle> obstacles;
	
	// pode add tb obstaculos...
	public GameMap(int cellsWidth, int cellsHeight, Point spawnPoint, ArrayList<Obstacle> obstacles) {
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

	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(ArrayList<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}
	
	
}
