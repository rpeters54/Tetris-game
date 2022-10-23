package Tetrominoes;

import java.awt.Color;

/*
 * Defines class segment which contains a coordinate and a boolean that determines
 * whether the segment is in motion or not
 */
public class Segment {
	protected boolean check;
	protected int x;
	protected int y;
	protected Color shade;
	
	public Segment(boolean check, int x, int y) {
		this.check = check;
		this.x = x;
		this.y = y;
		this.shade = null;
	}
	
	public Segment(int x, int y) {
		this.check = false;
		this.x = x;
		this.y = y;
		this.shade = null;
	}
	
	public boolean isStationary() {
		return check;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Color getShade() {
		return shade;
	}
	
	public void setColor(Color col) {
		shade = col;
	}
	/*
	 * translates the segment in any direction based on the parameter variables
	 * @param changeInX: the difference from the starting x value (- left, + right)
	 * @param changeInY: the difference from the starting y value (- up, + down)
	 */
	public void translate(int changeInX, int changeInY) {
		this.x += changeInX;
		this.y += changeInY;
	}
	
	public void stateChange() {
		if (check) {
			check = false;
		} else {
			check = true;
		}
	}
	
	public String toString() {
		String ret = "Sega";
		return ret;
	}
}
