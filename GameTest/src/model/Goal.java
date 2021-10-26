package model;

import java.awt.Color;
import java.awt.Point;

public abstract class Goal {
	private Point location;
	private int reward;
	private Color color;
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
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
	
}
