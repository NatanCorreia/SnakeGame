package model;

import java.util.ArrayList;

public class Obstacle {
private ArrayList<Segment> segments;
private int size;

public Obstacle(int size) {
	this.size = size;
	this.segments = new ArrayList();
}
public Obstacle(ArrayList<Segment> segments) {
	this.segments = segments;
}
public ArrayList<Segment> getSegments() {
	return segments;
}
public void setSegments(ArrayList<Segment> segments) {
	this.segments = segments;
}
public int getSize() {
	return size;
}
public void setSize(int size) {
	this.size = size;
}

}
