package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class Overlay extends JComponent{
	private static final long serialVersionUID = 1L;
	public void paintComponent(Graphics g) {
		Graphics2D brush = (Graphics2D) g;
		brush.setPaint(Color.black);
		brush.drawLine(0, 100, 500, 100);
		brush.drawLine(150, 0, 150, 100);
		brush.drawLine(300, 0, 300, 100);
		for (int i = 1; i <= 20; i++) {
			if (i < 11) {
				brush.drawLine(i*50, 100, i*50, 1100);
			}
			brush.drawLine(0, (i + 2) * 50, 500, (i + 2) * 50);
		}
	}
}
