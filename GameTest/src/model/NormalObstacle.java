package model;

import java.awt.Color;
import java.util.ArrayList;

public class NormalObstacle extends Obstacle {
	public NormalObstacle() {

		this.setSize(7);
		this.setColor(Color.blue);

	}

	public NormalObstacle(ArrayList<Segment> segments) {
		this.setSegments(new ArrayList());
	}
}
