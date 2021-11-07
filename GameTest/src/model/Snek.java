package model;

import java.util.ArrayList;
import java.awt.Point;

public class Snek {
	private ArrayList<Segment> segments;
	private Point direction;
	private boolean snekIsAlive = true;
	
	public Snek(ArrayList<Segment> segments, Point location) {
		this.segments = segments;
		this.direction = location;
	}
	

	public boolean isSnekIsAlive() {
		return snekIsAlive;
	}


	public void setSnekIsAlive(boolean snekIsAlive) {
		this.snekIsAlive = snekIsAlive;
	}


	public ArrayList<Segment> getSegments() {
		return segments;
	}

	public void setSegments(ArrayList<Segment> segments) {
		this.segments = segments;
	}

	public Point getDirection() {
		return direction;
	}

	public void setDirection(Point location) {
		this.direction = location;
	}
	public void setDirection(int x, int y) {
		this.direction = new Point(x,y);
	}
	
	
}
