package Tetrominoes;

import java.awt.Color;
/*
 * @author Riley Peters
 * Form defines the set dimensions of the tetromino, with directly defining the specific shape and interactions
 */
public class Form {
	protected Segment a;
	protected Segment b;
	protected Segment c;
	protected Segment d;
	private String name;

	
	public Form(Segment a, Segment b, Segment c, Segment d, String name) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.name = name;
		Color shade = this.defineShade();
		a.setColor(shade);
		b.setColor(shade);
		c.setColor(shade);
		d.setColor(shade);
	}
	
	public Form(Segment a, Segment b, Segment c, Segment d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.name = null;
		Color shade = Color.darkGray;
		a.setColor(shade);
		b.setColor(shade);
		c.setColor(shade);
		d.setColor(shade);
	}
	
	public Color defineShade() {
		switch(name) {
		case "Square" :
			return Color.yellow;
		case "Line" :
			return Color.cyan;
		case "TBlock" :
			return Color.magenta;
		case "LBlock" :
			return Color.orange;
		case "JBlock" :
			return Color.blue;
		case "RSquigly" :
			return Color.green;
		case "LSquigly" :
			return Color.red;
		default :
			return Color.gray;
		}
	}
	
	public Segment getA() {
		return a;
	}
	
	public Segment getB() {
		return b;
	}
	
	public Segment getC() {
		return c;
	}
	
	public Segment getD() {
		return d;
	}

	public String getName() {
		return name;
	}
	

	/*
	 * method for border case where Form tries to move down but can not
	 */
	public void moveUp() {
		a.translate(0, -1);
		b.translate(0, -1);
		c.translate(0, -1);
		d.translate(0, -1);
	}

	public void moveDown() {
		a.translate(0, 1);
		b.translate(0, 1);
		c.translate(0, 1);
		d.translate(0, 1);
	}
		
	public void moveLeft() {
		a.translate(-1, 0);
		b.translate(-1, 0);
		c.translate(-1, 0);
		d.translate(-1, 0);
	}
	
	public void moveRight() {
		a.translate(1, 0);
		b.translate(1, 0);
		c.translate(1, 0);
		d.translate(1, 0);
	}
	
	/*
	 * Uses a Form class object and its segments to revert the implicit parameter back to the position of the explicit parameter
	 * Makes it easier to redraw the object if a rotation command is nonfunctional
	 */
	
	public void setStationary() {
		a.check = true;
		b.check = true;
		c.check = true;
		d.check = true;
	}
	
	public void setA(Segment piece) {
		a = piece;
	}
	
	public void setB(Segment piece) {
		b = piece;
	}
	
	public void setC(Segment piece) {
		c = piece;
	}
	
	public void setD(Segment piece) {
		d = piece;
	}

	public void rotate() {
		//defined by subclasses
		// b is the axis of rotation
	}
	
	public void undo() {
		//defined by subclasses
	}
	
}
