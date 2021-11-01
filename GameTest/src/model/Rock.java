package model;

import java.awt.Color;

public class Rock extends Goal {
private final int lifeTime = 7000;


	public int getLifeTime() {
	return lifeTime;
}


	public Rock() {
		this.setReward(-20);
		this.setColor(Color.gray);
	}

	
	
}
