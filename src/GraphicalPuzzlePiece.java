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

public class GraphicalPuzzlePiece extends PuzzlePiece {

	private BufferedImage pieceImg = null;
	
	private int currX = 0;
	private int currY = 0;
	public final int homeX;
	public final int homeY;
	public int rotations = 0; // negative implies counterclockwise, positive implies clockwise

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

	@Override
	public void rotateClockwise(){
		super.rotateClockwise();
		rotations++;
		rotations += 4;
		rotations %= 4;
	}


	@Override
	public void rotateCounterclockwise(){
		super.rotateCounterclockwise();
		rotations--;
		rotations += 4;
		rotations %= 4;

	}

	public BufferedImage getImage(){
		AffineTransform at = new AffineTransform();
		at.rotate(rotations*Math.PI/2, pieceImg.getWidth()/2, pieceImg.getHeight()/2);
		
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(pieceImg, null);
	}
	public void goHome(){
		currX = homeX;
		currY = homeY;
	}

	public int getCurrX() {
		return currX;
	}

	public void setCurrX(int currX) {
		this.currX = currX;
	}

	public int getCurrY() {
		return currY;
	}

	public void setCurrY(int currY) {
		this.currY = currY;
	}
}