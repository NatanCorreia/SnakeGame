package model;

import java.awt.Color;
import java.awt.Point;

public class Apple extends Goal {

	public Apple() {
		this.setReward(10);
		this.setColor(Color.red);
		
	}
	public Apple(Point ponto) {
		this.setReward(10);
		this.setColor(Color.red);
		this.setLocation(ponto);
	}
}
