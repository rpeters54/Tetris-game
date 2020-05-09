package components;

import javax.swing.JFrame;

/*
 * defines the frame in which the tetrominoes will be pasted
 * tetris is 10 by 20, the extra 2 spaces are spawn chunks
 */
public class Shell {
	public final int XMAX = 10;
	public final int YMAX = 22;
	
	public static void main(String[] args) {
		JFrame keep = new JFrame();
		Overlay game = new Overlay();
		keep.setSize(515, 1100);
		keep.setTitle("Tetris");
		keep.toFront();
		keep.add(game);
		keep.setVisible(true);
		keep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
