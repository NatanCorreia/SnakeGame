package model;

import java.awt.Color;
import java.util.ArrayList;

public class EasyObstacle extends Obstacle {

	public EasyObstacle() {
		
		this.setSize(5);
		this.setColor(Color.yellow);
		
	}
	
	public EasyObstacle(ArrayList<Segment> segments) {
		this.setSegments(new ArrayList());
	}
}
