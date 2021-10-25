package model;

public class GameFrame{
	private Snek snek;
	private Goal goal;
	private int score = 0;
	private GameMap map;
	
	
	private boolean pausou;
	
	public GameFrame(Snek snek, Goal goal, GameMap map) {
		this.snek = snek;
		this.goal = goal;
		this.map = map;
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

	
	
	
}
