package model;

import java.awt.Color;
import java.awt.Point;

public class Rock extends Goal {
private final int lifeTime = 7000;


	public int getLifeTime() {
	return lifeTime;
}


	public Rock() {
		this.setReward(-20);
		this.setColor(Color.gray);
	}

	public Rock(int x , int y) {
		this.setReward(-20);
		this.setColor(Color.gray);
		this.setLocation(new Point(x,y));
	}
	
}
