package Tetrominoes;

public class LSquigly extends Form{
	public LSquigly() {
		super(new Segment(6, 1), new Segment(5, 1), new Segment(5, 0), new Segment(4, 0), "LSquigly");
	}
	
	public void rotate() {
		int yDif = b.y - a.y;
		int xDif = b.x - a.x;
		switch(yDif) {
		//b below a
		case 1 :
			a.translate(1, 1);
			c.translate(1, -1);
			d.translate(0, -2);
			break;
		//a below b
		case -1 :
			a.translate(-1, -1);
			c.translate(-1, 1);
			d.translate(0, 2);
			break;
		//horizontal cases
		case 0 :
			switch(xDif) {
			//a to the left
			case 1 :
				a.translate(1, -1);
				c.translate(-1, -1);
				d.translate(-2, 0);
				break;
			//a to the right
			case -1 :
				a.translate(-1, 1);
				c.translate(1, 1);
				d.translate(2, 0);
				break;
			}
		}
	}
	
	public void undo() {
		int yDif = b.y - a.y;
		int xDif = b.x - a.x;
		switch(yDif) {
		//b below a
		case 1 :
			a.translate(1, -1);
			c.translate(-1, -1);
			d.translate(-2, 0);
			break;
		//a below b
		case -1 :
			a.translate(-1, 1);
			c.translate(1, 1);
			d.translate(2, 0);
			break;
		//horizontal cases
		case 0 :
			switch(xDif) {
			//a to the left
			case 1 :
				a.translate(1, 1);
				c.translate(1, -1);
				d.translate(0, -2);
				break;
			//a to the right
			case -1 :
				a.translate(-1, -1);
				c.translate(-1, 1);
				d.translate(0, 2);
				break;
			}
		}
	}
}