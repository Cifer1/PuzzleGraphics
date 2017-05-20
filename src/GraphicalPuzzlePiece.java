import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/* class to represent a graphical puzzle piece and extends the puzzlePiece class. 
Private data include a buffered image, a current x and y, a home x and y, and  counter for the number or rotations*/

public class GraphicalPuzzlePiece extends PuzzlePiece {

	private BufferedImage pieceImg = null;
	
	private int currX = 0;
	private int currY = 0;
	public final int homeX;
	public final int homeY;
	public int rotations = 0; // negative implies counterclockwise, positive implies clockwise

	/* This constructor takes in 4 ints to represent the suit at the north, south, east, and west directions of the piece,
	 the file for the image, and the x and y coordinates. It then calls the PuzzlePiece constructor for the directions, and
	 sets the x and y coordinates.*/
	public GraphicalPuzzlePiece(int north, int east, int south, int west, File src, int homeX, int homeY){
		super(north, east, south, west);
		try{
			pieceImg = ImageIO.read(src);
		} catch(IOException e){

			e.printStackTrace();
		}
		this.homeX = homeX;
		currX = homeX;
		this.homeY = homeY;
		currY = homeY;

	}
	/* This method overrides the rotateClockwise method in the PuzzlePiece constructor. It increments the rotation count, and then
	 converts it to a number mod4. */
	@Override
	public void rotateClockwise(){
		super.rotateClockwise();
		rotations++;
		rotations += 4;
		rotations %= 4;
	}

	/* This method overrides the rotateCounterClockwise method in the PuzzlePiece constructor. It decrements the rotation count, 
	 and then converts it to a number mod 4. */
	@Override
	public void rotateCounterclockwise(){
		super.rotateCounterclockwise();
		rotations--;
		rotations += 4;
		rotations %= 4;

	}
	// This method returns a buffered Image. 
	public BufferedImage getImage(){
		AffineTransform at = new AffineTransform();
		at.rotate(rotations*Math.PI/2, pieceImg.getWidth()/2, pieceImg.getHeight()/2);
		
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(pieceImg, null);
	}
	// sets the x and y coordinates to the original coordinates. 
	public void goHome(){
		currX = homeX;
		currY = homeY;
	}
	// gets the current x coordinate
	public int getCurrX() {
		return currX;
	}
	// sets the current x coordinate
	public void setCurrX(int currX) {
		this.currX = currX;
	}
	// gets the current y coordinate
	public int getCurrY() {
		return currY;
	}
	// sets the current y coordinate
	public void setCurrY(int currY) {
		this.currY = currY;
	}
}