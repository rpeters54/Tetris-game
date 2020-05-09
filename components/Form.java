package components;

import java.awt.Rectangle;
import java.awt.Color;
/*
 * @author Riley Peters
 * Form defines the set dimensions of the tetromino, with directly defining the specific shape and interactions
 */
public class Form {
	private Rectangle a;
	private Rectangle b;
	private Rectangle c;
	private Rectangle d;
	private String name;
	private Color shade;
	
	public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.name = name;
		shade = this.defineShade();
	}
	
	public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.name = null;
		shade = this.defineShade();
	}
	
	public Color defineShade() {
		switch(name) {
		case "Square" :
			return Color.yellow;
		case "Line" :
			return Color.cyan;
		case "T" :
			return Color.magenta;
		case "RL" :
			return Color.orange;
		case "LL" :
			return Color.blue;
		case "RS" :
			return Color.green;
		case "LS" :
			return Color.red;
		default :
			return Color.gray;
		}
	}
	
	public Color getShade() {
		return shade;
	}
	
	public Rectangle getA() {
		return a;
	}
	
	public Rectangle getB() {
		return b;
	}
	
	public Rectangle getC() {
		return c;
	}
	
	public Rectangle getD() {
		return d;
	}

	public String getName() {
		return name;
	}
	
	
}
