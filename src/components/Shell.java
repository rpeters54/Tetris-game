package components;

import Tetrominoes.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/*
 * defines the frame in which the tetrominoes will be pasted
 * tetris is 10 by 20, the extra 2 spaces are spawn chunks
 * x increases to the right
 * y increases downward
 */

public class Shell implements Playable {
	//dimensions of tetris mesh 10x22
	public static final int XMAX = 10;
	public static final int YMAX = 22;
	protected Segment[][] mesh = new Segment[XMAX][YMAX];
	private Form currentPiece;
	private Form nextPiece;
	private String username;
	private int points;
	private int level;
	private int linesCleared;
	private ArrayList<Integer> pointHolder;
	private KeyListener keys;
	private ActionListener time;
	private Timer t;
	private JFrame keep;
	private JFrame frame;
	private JFrame closer;
	private Background cover;
	
	/**
	 * Initializes all instance variables including the three necessary Jframes and all other data
	 */
	public Shell() {
		nextPiece = this.selectPiece();
		currentPiece = null;
		points = 0;
		level = 0;
		linesCleared = 0;
		username = "";
		pointHolder = new ArrayList<Integer>();
		keys = new UserListener();
		time = new tickRate();
		t = new Timer(750, time);
		keep = new JFrame();
		cover = new Background(mesh, nextPiece);
		frame = new JFrame();
		closer = new JFrame();
	}
	
	/**
	 * Selects a random tetromino and returns it
	 */
	public Form selectPiece() {
		//random needs fixing
		Random rand = new Random();
		int piece = rand.nextInt(7);
		switch(piece) {
		case 0:
			Form ret = new Line();
			return ret;
		case 1:
			Form ret1 = new TBlock();
			return ret1;
		case 2:
			Form ret2 = new LBlock();
			return ret2;
		case 3:
			Form ret3 = new JBlock();
			return ret3;
		case 4:
			Form ret4 = new Square();
			return ret4;
		case 5:
			Form ret5 = new RSquigly();
			return ret5;
		case 6:
			Form ret6 = new LSquigly();
			return ret6;
		default:
			Form ret7 = new RSquigly();
			return ret7;
		}
		
	}
	
	/**
	 * shifts the nextPiece value into the ready position and sends it to the place piece method
	 * resets the nextPiece
	 */
	public void nextPiece() {
		currentPiece = nextPiece;
		nextPiece = this.selectPiece();
		this.placePiece(currentPiece);
	}
	
	/**
	 * Places the currentPiece on the mesh to be manipulated
	 * Used in the beginning placement and translation and rotation
	 */
	public void placePiece(Form tetra) {
		Segment a = tetra.getA();
		Segment b = tetra.getB();
		Segment c = tetra.getC();
		Segment d = tetra.getD();
		mesh[a.getX()][a.getY()] = a;
		mesh[b.getX()][b.getY()] = b;
		mesh[c.getX()][c.getY()] = c;
		mesh[d.getX()][d.getY()] = d;
	}
	
	/**
	 * Clears the piece off of the mesh
	 * @param tetra: the tetromino that is going to be removed
	 * Used in translation and rotation methods
	 */
	public void clearPiece(Form tetra) {	
		Segment a = tetra.getA();
		Segment b = tetra.getB();
		Segment c = tetra.getC();
		Segment d = tetra.getD();
		mesh[a.getX()][a.getY()] = null;
		mesh[b.getX()][b.getY()] = null;
		mesh[c.getX()][c.getY()] = null;
		mesh[d.getX()][d.getY()] = null;
	}
	
	/**
	 * Following methods are checkers to test if a piece is going to exceed the bounds of the array
	 */
	
	/**
	 * Checks if out of the right bound of the mesh 
	 */
	public boolean checkOutOfRHBounds(Form tetra) {
		boolean test = true;
		Segment a = tetra.getA();
		Segment b = tetra.getB();
		Segment c = tetra.getC();
		Segment d = tetra.getD();
		if (a.getX() < XMAX && b.getX() < XMAX && c.getX() < XMAX && d.getX() < XMAX) {
			test = false;
		}
		return test;
	}
	
	/**
	 * Checks if out of the left bound of the mesh 
	 */
	public boolean checkOutOfLHBounds(Form tetra) {
		boolean test = true;
		Segment a = tetra.getA();
		Segment b = tetra.getB();
		Segment c = tetra.getC();
		Segment d = tetra.getD();
		if (a.getX() >= 0 && b.getX() >= 0 && c.getX() >= 0 && d.getX() >= 0) {
			test = false;
		}
		return test;
	}
	
	/**
	 * Checks if out of the bottom bound of the mesh 
	 */
	public boolean checkOutOfVBounds(Form tetra) {
		boolean test = true;
		Segment a = tetra.getA();
		Segment b = tetra.getB();
		Segment c = tetra.getC();
		Segment d = tetra.getD();
		if (a.getY() < YMAX && b.getY() < YMAX && c.getY() < YMAX && d.getY() < YMAX) {
			test = false;
		}
		return test;
	}
	
	/**
	 * Checks if out of the top bound of the mesh 
	 */
	public boolean checkTopBounds(Form tetra) {
		boolean test = true;
		Segment a = tetra.getA();
		Segment b = tetra.getB();
		Segment c = tetra.getC();
		Segment d = tetra.getD();
		if (a.getY() >= 0 && b.getY() >= 0 && c.getY() >= 0 && d.getY() >= 0) {
			test = false;
		}
		return test;
	}
	
	/**
	 * Checks if out of any bounds of the mesh 
	 */
	public boolean checkOutOfBounds(Form tetra) {
		boolean vert = checkOutOfVBounds(tetra);
		boolean right = checkOutOfRHBounds(tetra);
		boolean left = checkOutOfLHBounds(tetra);
		boolean top = checkTopBounds(tetra);
		if (!(vert || right || left || top)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Checks if any pieces are stuck in the spawn region
	 * If a piece is endGame is called to take the player to the final screen
	 */
	public void checkEndCondition() {
		boolean gameover = false;
		for (int y = 0; y < 2; y++) {
			for (int x = 0; x < XMAX; x++) {
				if (mesh[x][y] != null) {
					gameover = true;
				}
			}
		}
		if (gameover) {
			endGame();
		}
	}
	
	/**
	 * Checks if the space a piece is moving to is valid and has not been taken
	 * @return true if the space is accessible 
	 */
	public boolean validSpace(Form tetra) throws ArrayIndexOutOfBoundsException{
		int aX = tetra.getA().getX();
		int bX = tetra.getB().getX();
		int cX = tetra.getC().getX();
		int dX = tetra.getD().getX();
		int aY = tetra.getA().getY();
		int bY = tetra.getB().getY();
		int cY = tetra.getC().getY();
		int dY = tetra.getD().getY();
		if (mesh[aX][aY] == null && mesh[bX][bY] == null && mesh[cX][cY] == null && mesh[dX][dY] == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Next methods have to do with the movement of the currentPiece object over the mesh array
	 */
	
	/**
	 * Moves the currentPiece down by one increment
	 */
	public void moveDown() {
		this.clearPiece(currentPiece);
		currentPiece.moveDown();
		if (!checkOutOfVBounds(currentPiece) &&  validSpace(currentPiece)) {
			this.placePiece(currentPiece);
		} else {
			currentPiece.moveUp();
			currentPiece.setStationary();
			this.placePiece(currentPiece);
			checkEndCondition();
			addPoints();
			nextPiece();
		}
	}
	
	/**
	 * Moves the currentPiece left by one increment
	 */
	public void moveLeft() {
		this.clearPiece(currentPiece);
		currentPiece.moveLeft();
		if (!checkOutOfLHBounds(currentPiece) && validSpace(currentPiece)) {
			this.placePiece(currentPiece);
		} else {
			currentPiece.moveRight();
			this.placePiece(currentPiece);
		}
	}
	
	/**
	 * Moves the currentPiece right by one increment
	 */
	public void moveRight() {
		this.clearPiece(currentPiece);
		currentPiece.moveRight();
		if (!checkOutOfRHBounds(currentPiece) && validSpace(currentPiece)) {
			this.placePiece(currentPiece);
		} else {
			currentPiece.moveLeft();
			this.placePiece(currentPiece);
		}
	}
	
	/**
	 * Rotates the currentPiece based on the individual method defined by the piece
	 */
	public void rotate() {
		clearPiece(currentPiece);
		currentPiece.rotate();
		if (!checkOutOfBounds(currentPiece) && validSpace(currentPiece)) {
			placePiece(currentPiece);
		} else {
			currentPiece.undo();
			placePiece(currentPiece);
		}
	}
	
	/**
	 * Moves the currentPiece down until it hits the bottom or another piece
	 */
	public void drop() {
		int a = 0;
		int b = 0;
		do {
			b = currentPiece.getA().getY();
			moveDown(); 
			a = currentPiece.getA().getY();
			points++;
		} while(a > b);
	}
	

	
	/**
	 * checks if any pieces are moving on the board
	 * helper method for line removal, point increase, and new Form spawn
	 * @return boolean: if true, a piece is moving
	 */
	public boolean checkMovement() {
		boolean test = false;
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 22; y++) {
				Segment check = mesh[x][y];
				if (check != null) {
					if (!check.isStationary()) {
						test = true;
						break;
					}
				}
			}
			if (test) { break;}
		}
		return test;
	}
	
	/**
	 * if movement has stopped: the method will remove full lines, shift lines above down,
	 * generate new null lines, and return a int value of the lines removed
	 */
	public int lineRemove() {
		int result = 0;
		ArrayList<Integer> rows = new ArrayList<Integer>();
		if (!checkMovement()) {
			for (int y = 0; y < YMAX; y++) {
				int count = 0;
				for (int x = 0; x < XMAX; x++) {
					if (mesh[x][y] != null) {
						count++;
					}
				}
				if(count == 10) {
					result++;
					rows.add(y);
				}
			}
			while (rows.size() > 0) {
				int hold = rows.get(0);
				for (int i = hold; i > 0; i--) {
					for (int x = 0; x < XMAX; x++) {
						mesh[x][i] = mesh[x][i - 1];
						if (mesh[x][i - 1] instanceof Segment) {
							mesh[x][i - 1].translate(0, 1);
						}
					}
				}
				rows.remove(0);
				updateFrame();
			}
			for (int i = 0; i < XMAX; i++) {
				mesh[i][0] = null;
			}
			return result;
		} else {
			return result;
		}
	}
	
	/**
	 * method that utilizes lineRemove and checkMovement to determine whether it can run
	 * changes the points value based on the amount of lines removed
	 */
	public void addPoints() {
		int lines = lineRemove();
		linesCleared += lines;
		int points = 0;
		switch(lines) {
		case 4: 
			points = 1200 * (level + 1);
			break;
		case 3:
			points = 300 * (level + 1);
			break;
		case 2:
			points = 100 * (level + 1);
			break;
		case 1:
			points = 40 * (level + 1);
			break;
		default:
			points = 0;
		}
		this.points += points;
		levelChange();
	}
	
	/**
	 * changes the level based on the amount of lines that have been removed
	 */
	public void levelChange() {
		if (linesCleared < 10) {
			//nothing
		} else if (linesCleared < 20) {
			level = 1;
			t.setDelay(700);
		} else if (linesCleared < 30) {
			level = 2;
			t.setDelay(650);
		} else if (linesCleared < 40) {
			level = 3;
			t.setDelay(550);
		} else if (linesCleared < 50) {
			level = 4;
			t.setDelay(450);
		} else if (linesCleared < 60) {
			level = 5;
			t.setDelay(380);
		} else if (linesCleared < 70) {
			level = 6;
			t.setDelay(300);
		} else if (linesCleared < 80) {
			level = 7;
			t.setDelay(210);
		} else if (linesCleared < 90) {
			level = 8;
			t.setDelay(150);
		} else {
			level = 9;
			t.setDelay(100);
		}
	}
	
	/**
	 * Listener used to take in player input and translate it into board movement
	 */
	public class UserListener implements KeyListener {

		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == (KeyEvent.VK_DOWN)) {
				moveDown();
				points++;
				updateFrame();
				//printBoard();
			} else if (arg0.getKeyCode() == (KeyEvent.VK_UP)) {
				rotate();
				updateFrame();
				//printBoard();
			} else if (arg0.getKeyCode() == (KeyEvent.VK_LEFT)) {
				moveLeft();
				updateFrame();
				//printBoard();
			} else if (arg0.getKeyCode() == (KeyEvent.VK_RIGHT)) {
				moveRight();
				updateFrame();
				//printBoard();
			} else if (arg0.getKeyCode() == (KeyEvent.VK_SPACE)) {
				drop();
				updateFrame();
				//printBoard();
			} else if (arg0.getKeyCode() == (KeyEvent.VK_ESCAPE)) {
				//keep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//keep.dispatchEvent(new WindowEvent(keep, WindowEvent.WINDOW_CLOSING));
				endGame();
			}
		}
		public void keyReleased(KeyEvent arg0) {}
		public void keyTyped(KeyEvent arg0) {}	
	}
	
	/**
	 * Listener that uses a consistent timer to increment the piece downward at
	 * a rate determined by the level
	 */
	public class tickRate implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			moveDown();
			updateFrame();
			//printBoard();
		}
	}

	/**
	 * Initializes the first JFrame that takes in the username and begins the game
	 */
	public void preface() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		String user = JOptionPane.showInputDialog(frame, "Welcome to Tetris!\nUse left, right, and down to move the piece.\nUp rotates the piece and"
				+ " space is used for an instant drop.\n Please enter your username to begin", "Tetris", JOptionPane.INFORMATION_MESSAGE);
		username = user;
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		start();
	}
	
	/**
	 * Called after preface, this is the intial start to the game
	 * Initializes the main JFrame and begins the timer and keylistener
	 */
	public void start() {
		points = 0;
		keep.setSize(266, 640);
		keep.setTitle("Tetris");
		keep.toFront();
		keep.add(cover);
		keep.setVisible(true);
		keep.addKeyListener(keys);
		keep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (int x = 0; x < XMAX; x++) {
			for (int y = 0; y < YMAX; y++) {
				mesh[x][y] = null;
			}
		}
		nextPiece();
		t.start();
		//printBoard();
	}
	
	/**
	 * resets the board for another game and begins again
	 */
	public void restart() {
		points = 0;
		level = 0;
		linesCleared = 0;
		t.setDelay(750);
		keep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (int x = 0; x < XMAX; x++) {
			for (int y = 0; y < YMAX; y++) {
				mesh[x][y] = null;
			}
		}
		t.start();
	}
	
	/**
	 * method called by checkEndCondition that sends the game to the final JFrame
	 * checks if you want to restart or end the game
	 */
	public void endGame() {
		t.stop();
		pointHolder.add(points);
		keep.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		keep.dispatchEvent(new WindowEvent(keep, WindowEvent.WINDOW_CLOSING));
		closer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int i = JOptionPane.showConfirmDialog(closer, "Well done! You scored " + points + " points.\nDo you want to play again?", "Gameover", JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION) {
			restart();
			closer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			closer.dispatchEvent(new WindowEvent(closer, WindowEvent.WINDOW_CLOSING));
		} else {
			closer.dispatchEvent(new WindowEvent(closer, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	
	/**
	 * Repaints the JFrame to show the score, level, nextPiece, and board movement
	 */
	public void updateFrame() {
		cover.update(mesh, points, nextPiece, level);
		keep.repaint();
	}
	
	/**
	 * Debugging tool to test the game without the use of a JFrame
	 */
	public void printBoard() {
		for (int y = 0; y < YMAX; y++) {
			for (int x = 0; x < XMAX; x++) {
				System.out.print(mesh[x][y] + " ");
			}
			System.out.println();
		}
		System.out.println("==================================== " + points);
	}
	
	/**
	 * Part of the Playable interface
	 * @return the best score out of the games played
	 */
	public String getScore() {
		int max = pointHolder.get(0);
		for (int i = 0; i < pointHolder.size(); i++) {
			if (pointHolder.get(i) > max) {
				max = pointHolder.get(i);
			}
		}
		return max + "";
	}
	
	/**
	 * Accesses the username from the current player
	 * @return the current username
	 */
	public String returnUsername() {
		return username;
	}
	
	/**
	 * Checks whether the game is running or not
	 * @return true if game is running
	 */
	public boolean playGame() {
		if (currentPiece != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Implemented in the Playable Interface, begins the game
	 */
	public void openGame() {
		preface();
	}
	
	public static void main(String[] args) {
		Shell game = new Shell();
		game.preface();
	}
}