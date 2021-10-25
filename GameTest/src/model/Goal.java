package model;

import java.awt.Point;

public class  Goal {
	private Point location;
	private int reward;
	public Goal(Point location, int reward) {
		this.location = location;
		this.reward = reward;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public int getReward() {
		return reward;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}
	
	
}
