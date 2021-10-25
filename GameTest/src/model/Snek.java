package model;

import java.util.ArrayList;
import java.awt.Point;

public class Snek {
	private ArrayList<Segment> segments;
	private Point velocity;
	private boolean snekIsAlive = true;
	
	public Snek(ArrayList<Segment> segments, Point velocity) {
		super();
		this.segments = segments;
		this.velocity = velocity;
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

	public Point getVelocity() {
		return velocity;
	}

	public void setVelocity(Point velocity) {
		this.velocity = velocity;
	}
	
	
}
