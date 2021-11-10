package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class HardObstacle extends Obstacle {
	private Point direction;

	public HardObstacle(Point p) {

		this.setSize(5);
		this.setColor(Color.black);
		this.direction = p;

	}

	public HardObstacle(ArrayList<Segment> segments) {
		this.setSegments(new ArrayList());
	}

	public Point getDirection() {
		return direction;
	}

	public void setDirection(Point direction) {
		this.direction = direction;
	}
	
	
}
