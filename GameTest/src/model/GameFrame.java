package model;

import controller.GameController;

public class GameFrame {
	private Snek snek;
	private Goal goal;
	private int score = 0;
	private GameMap map;
	private int difficulty;
	private int playerId;

	private boolean pausou;

	public GameFrame(Snek snek, Goal goal, GameMap map) {
		this.snek = snek;
		this.goal = goal;
		this.map = map;
	}

	public GameFrame() {
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public boolean isPausou() {
		return pausou;
	}

	public void setPausou(boolean pausou) {
		this.pausou = pausou;
	}

	public Snek getSnek() {
		return snek;
	}

	public void setSnek(Snek snek) {
		this.snek = snek;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

	public void checkDifficulty() {
		if (GameController.GAME_SPEED == 40)
			this.setDifficulty(0);

		if (GameController.GAME_SPEED == 70)
			this.setDifficulty(1);

		if (GameController.GAME_SPEED == 100)
			this.setDifficulty(2);

	}

	public int getGameDifficulty() {
		if (this.getDifficulty() == 0)
			return 40;

		if (this.getDifficulty() == 1)
			return 70;

		if (this.getDifficulty() == 2)
			return 100;
		return 300;

	}

}
