package model;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Obstacle {
private ArrayList<Segment> segments;
private int size;
private Color color;


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
public Color getColor() {
	return color;
}
public void setColor(Color color) {
	this.color = color;
}


}
