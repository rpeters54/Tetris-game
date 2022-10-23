package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;

import Tetrominoes.Form;
import Tetrominoes.Segment;

public class Background extends JComponent{
	private static final long serialVersionUID = 1L;
	private ArrayList<Segment> mesh = new ArrayList<Segment>();
	private int points;
	private int level;
	private Form nextPiece;
	
	/**
	 * Initialized in the start function of Shell
	 * Initializes the background of the main JFrame
	 */
	public Background(Segment[][] in, Form nextPiece) {
		mesh = new ArrayList<Segment>();
		for (int x = 0; x < Shell.XMAX; x++) {
			for (int y = 0; y < Shell.YMAX; y++) {
				mesh.add(in[x][y]);
			}
		}
		points = 0;
		level = 0;
		this.nextPiece = nextPiece;
	}
	
	/**
	 * Updates the values of the Background whenever movement takes place, points are scored, etc.
	 */
	public void update(Segment[][] in, int points, Form nextPiece, int level) {
		mesh.clear();
		for (int x = 0; x < Shell.XMAX; x++) {
			for (int y = 0; y < Shell.YMAX; y++) {
				mesh.add(in[x][y]);
			}
		}
		this.points = points;
		this.level = level;
		this.nextPiece = nextPiece;
	}
	
	/**
	 * paints the background with the given instance variables
	 */
	public void paintComponent(Graphics g) {
		Graphics2D brush = (Graphics2D) g;
		brush.setPaint(Color.LIGHT_GRAY);
		brush.fillRect(0, 0, 260, 640);
		brush.setPaint(Color.black);
		brush.drawLine(0, 100, 250, 100);
		brush.drawLine(75, 0, 75, 100);
		brush.drawLine(125, 0, 125, 100);
	
		for (int i = 1; i <= 21; i++) {
			if (i < 11) {
				brush.drawLine(i*25, 100, i*25, 600);
			}
			brush.drawLine(0, (i + 3) * 25, 250, (i + 3) * 25);
		}
		brush.drawString("Points:", 20, 25);
		brush.drawString(points + "", 30, 40);
		brush.drawString("Level:", 85, 25);
		brush.drawString(level + "", 100, 40);
		brush.drawString("Next Piece:", 145, 25);
		String name = nextPiece.getName();
		Color color = nextPiece.getA().getShade();
		Rectangle newRect = new Rectangle(25, 25);
		Rectangle newRect1 = new Rectangle(25, 25);
		Rectangle newRect2 = new Rectangle(25, 25);
		Rectangle newRect3 = new Rectangle(25, 25);
		switch(name) {
		case "Square" :
			newRect.setLocation(162, 37);
			newRect1.setLocation(187, 37);
			newRect2.setLocation(162, 62);
			newRect3.setLocation(187, 62);
			break;
		case "Line" :
			newRect.setLocation(137, 50);
			newRect1.setLocation(162, 50);
			newRect2.setLocation(187, 50);
			newRect3.setLocation(212, 50);
			break;
		case "TBlock" :
			newRect.setLocation(150, 62);
			newRect1.setLocation(175, 62);
			newRect2.setLocation(200, 62);
			newRect3.setLocation(175, 37);
			break;
		case "LBlock" :
			newRect.setLocation(150, 62);
			newRect1.setLocation(175, 62);
			newRect2.setLocation(200, 62);
			newRect3.setLocation(200, 37);
			break;
		case "JBlock" :
			newRect.setLocation(150, 62);
			newRect1.setLocation(175, 62);
			newRect2.setLocation(200, 62);
			newRect3.setLocation(150, 37);
			break;
		case "RSquigly" :
			newRect.setLocation(150, 62);
			newRect1.setLocation(175, 62);
			newRect2.setLocation(175, 37);
			newRect3.setLocation(200, 37);
			break;
		case "LSquigly" :
			newRect.setLocation(150, 37);
			newRect1.setLocation(175, 37);
			newRect2.setLocation(175, 62);
			newRect3.setLocation(200, 62);
			break;
		}
		brush.setColor(color);
		brush.fill(newRect);
		brush.fill(newRect1);
		brush.fill(newRect2);
		brush.fill(newRect3);
		brush.setColor(Color.black);
		brush.draw(newRect);
		brush.draw(newRect1);
		brush.draw(newRect2);
		brush.draw(newRect3);
		
		for (int i = 0; i < mesh.size(); i++) {
			Segment hold = mesh.get(i);
			if (hold != null && hold.getY() >= 2) {
				int tempX = hold.getX() * 25;
				int tempY = hold.getY() * 25 + 50;
				Color shade = hold.getShade();
				brush.setColor(shade);
				Rectangle rectan = new Rectangle(tempX, tempY, 25, 25);
				brush.fill(rectan);
				brush.setColor(Color.black);
				brush.draw(rectan);
			}
		}
	}
}
