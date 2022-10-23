package Tetrominoes;

public class TBlock extends Form{
	public TBlock() {
		super(new Segment(5, 0), new Segment(5, 1), new Segment(4, 1), new Segment(6, 1), "TBlock");
	}
	
	public void rotate() {
		int yDif = b.y - a.y;
		int xDif = b.x - a.x;
		switch(yDif) {
		case 1 :
			//a above b
			a.translate(1, 1);
			c.translate(1, -1);
			d.translate(-1, 1);
			break;
		case -1 :
			//a below b
			a.translate(-1, -1);
			c.translate(-1, 1);
			d.translate(1, -1);
			break;
		case 0 :
			switch(xDif) {
			case 1 :
				//a to the left
				a.translate(1, -1);
				c.translate(-1, -1);
				d.translate(1, 1);
				break;
			case -1 :
				//a to the right
				a.translate(-1, 1);
				c.translate(1, 1);
				d.translate(-1, -1);
				break;
			}
		}
	}
	
	public void undo() {
		int yDif = b.y - a.y;
		int xDif = b.x - a.x;
		switch(yDif) {
		case 1 :
			//a above b
			a.translate(-1, 1);
			c.translate(1, 1);
			d.translate(-1, -1);
			break;
		case -1 :
			//a below b
			a.translate(1, -1);
			c.translate(-1, -1);
			d.translate(1, 1);
			break;
		case 0 :
			switch(xDif) {
			case 1 :
				//a to the left
				a.translate(1, 1);
				c.translate(1, -1);
				d.translate(-1, 1);
				break;
			case -1 :
				a.translate(-1, -1);
				c.translate(-1, 1);
				d.translate(1, -1);
				break;
			}
		}
	}
}