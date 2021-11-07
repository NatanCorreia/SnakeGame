package model;

import java.awt.Color;
import java.awt.Point;

public class Banana extends Goal {

	public Banana() {
	
		this.setColor(Color.yellow);
		this.setReward(20);
	}
	public Banana(int x, int y) {
		this.setReward(20);
		this.setColor(Color.yellow);
		this.setLocation(new Point(x,y));
	}
}
