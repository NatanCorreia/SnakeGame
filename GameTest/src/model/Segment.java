package model;

import java.awt.Point;

public class Segment {
	private Point location;
	private int id;
	// pode definir formato, cor...

	public Segment(Point location) {
		super();
		this.location = location;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
	
	
}
