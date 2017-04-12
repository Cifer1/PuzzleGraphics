import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class GraphicalPuzzlePiece extends PuzzlePiece {

	private BufferedImage pieceImg = null;
	private JComponent pieceComponent = null;
	public GraphicalPuzzlePiece(int north, int east, int south, int west, File src){
		super(north, east, south, west);
		try{
			pieceImg = ImageIO.read(src);
		} catch(IOException e){
		}
		pieceComponent = new JComponent() {
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(pieceImg, getWidth()/2, getHeight()/2, null);

			}
		};
	}

	@Override
	public void rotateClockwise(){
		super.rotateClockwise();
		AffineTransform at = new AffineTransform();
		at.translate(pieceComponent.getWidth()/2, pieceComponent.getHeight()/2);
		at.rotate(-Math.PI/4);
		at.translate(-pieceImg.getWidth()/2, -pieceImg.getHeight()/2);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(pieceImg, at, null);
	}
	@Override
	public void rotateCounterClockwise(){
		super.rotateCounterClockwise();
		AffineTransform at = new AffineTransform();
		at.translate(pieceComponent.getWidth()/2, pieceComponent.getHeight()/2);
		at.rotate(Math.PI/4);
		at.translate(-pieceImg.getWidth()/2, -pieceImg.getHeight()/2);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(pieceImg, at, null);
	}
}
