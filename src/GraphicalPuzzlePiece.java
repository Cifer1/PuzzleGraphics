import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class GraphicalPuzzlePiece extends PuzzlePiece {

	private BufferedImage pieceImg = null;
	public JComponent pieceComponent = null;
	private int initX = 0;
	private int initY = 0;
	private int screenX = 0;
	private int screenY = 0;
	public GraphicalPuzzlePiece(int north, int east, int south, int west, File src){
		super(north, east, south, west);
		try{
			pieceImg = ImageIO.read(src);
		} catch(IOException e){
			System.out.println(e);
			e.printStackTrace();
		}
		pieceComponent = new JComponent() {
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(pieceImg, 0, 0, null);
				System.out.println("hello, I'm drawing the piece");

			}
		};
		pieceComponent.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				screenX = e.getXOnScreen();
				screenY = e.getYOnScreen();
				
				initX = pieceComponent.getX();
				initY = pieceComponent.getY();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		pieceComponent.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				int deltaX = e.getXOnScreen() - screenX;
				int deltaY = e.getYOnScreen() - screenY;
				pieceComponent.setLocation(initX + deltaX, initY + deltaY);
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	

	@Override
	public void rotateClockwise(){
		super.rotateClockwise();
		AffineTransform at = new AffineTransform();
		at.translate(pieceComponent.getWidth()/2, pieceComponent.getHeight()/2);
		at.rotate(-Math.PI/4);
		at.translate(-pieceImg.getWidth()/2, -pieceImg.getHeight()/2);

		Graphics g = pieceComponent.getGraphics();
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

		Graphics g = pieceComponent.getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(pieceImg, at, null);
	}
	public void repaint(){
		pieceComponent.repaint();
	}
	public BufferedImage getImage(){
		return pieceImg;
	}
}
