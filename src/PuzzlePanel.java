import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

public class PuzzlePanel extends JPanel {
	private JigsawPuzzle puzzle;
	private  int botLeftX;
	private  int botLeftY;
	private  int sideLen;
	private GraphicalPuzzlePiece tracking;

	/**
	 * Create the panel.
	 */
	public PuzzlePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gridBagLayout);
		ArrayList<PuzzlePiece> pieces = new ArrayList<PuzzlePiece>();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int horizOffset = (int) (screenSize.getWidth()/25);
		int vertOffset = (int) (screenSize.getWidth()/25);
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.CLUB_OUT, PuzzlePiece.HEART_OUT, PuzzlePiece.DIAMOND_IN, PuzzlePiece.CLUB_IN, new File("img/piece_1.png"), horizOffset, vertOffset));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.SPADE_OUT, PuzzlePiece.DIAMOND_OUT, PuzzlePiece.SPADE_IN, PuzzlePiece.HEART_IN, new File("img/piece_2.png"), horizOffset, vertOffset*3));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.HEART_OUT, PuzzlePiece.SPADE_OUT, PuzzlePiece.SPADE_IN, PuzzlePiece.CLUB_IN, new File("img/piece_3.png"), horizOffset, vertOffset*5));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.HEART_OUT, PuzzlePiece.DIAMOND_OUT, PuzzlePiece.CLUB_IN, PuzzlePiece.CLUB_IN, new File("img/piece_4.png"), horizOffset*3, vertOffset));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.SPADE_OUT, PuzzlePiece.SPADE_OUT, PuzzlePiece.HEART_IN, PuzzlePiece.CLUB_IN, new File("img/piece_5.png"), horizOffset*5, vertOffset));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.HEART_OUT, PuzzlePiece.DIAMOND_OUT, PuzzlePiece.DIAMOND_IN, PuzzlePiece.HEART_IN, new File("img/piece_6.png"), horizOffset*7, vertOffset));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.SPADE_OUT, PuzzlePiece.DIAMOND_OUT, PuzzlePiece.HEART_IN, PuzzlePiece.DIAMOND_IN, new File("img/piece_7.png"), horizOffset, vertOffset*7));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.CLUB_OUT, PuzzlePiece.HEART_OUT, PuzzlePiece.SPADE_IN, PuzzlePiece.HEART_IN, new File("img/piece_8.png"), horizOffset, vertOffset*9));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.DIAMOND_OUT, PuzzlePiece.CLUB_OUT, PuzzlePiece.CLUB_IN, PuzzlePiece.DIAMOND_IN, new File("img/piece_9.png"), horizOffset*9, vertOffset));
		
		puzzle = new JigsawPuzzle(3,3,pieces);
		addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int x = arg0.getX();
				int y = arg0.getY();
				if(arg0.isControlDown()&&!(x>botLeftX&&x<botLeftX+sideLen&&y>botLeftY&&y<botLeftY+sideLen)){
					GraphicalPuzzlePiece rotated = getPieceAtPoint(x,y);
					if(rotated!=null) rotated.rotateClockwise();
					repaint();
					return;
				}
				if(tracking==null){
				
					System.out.println("click x: " + x + "click y: " + y );
					tracking = getPieceAtPoint(x,y);
				}
				else{
					if(x>botLeftX&&x<botLeftX+sideLen&&y>botLeftY&&y<botLeftY+sideLen){
						int[] coords = getBoardPosAtPoint(x,y);
						System.out.println("x: " + coords[0]  + " y: " + coords[1]);
						if(!puzzle.placePiece(coords[0], coords[1], tracking)) {
							System.out.println(puzzle.getPiece(coords[0], coords[1]));
							tracking.goHome();
						}
						else{
							tracking.setCurrX(coords[0] * (sideLen/3) + botLeftX - tracking.getImage().getWidth()/5);
							tracking.setCurrY(coords[1] * (sideLen/3) + botLeftY - tracking.getImage().getWidth()/5);
						}
						repaint();
					}
					tracking=null;
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(tracking==null)tracking = getPieceAtPoint(arg0.getX(),arg0.getY());

				if(tracking!=null){
					tracking.setCurrX(arg0.getX()-tracking.getImage().getWidth()/2);
					tracking.setCurrY(arg0.getY()-tracking.getImage().getHeight()/2);
					repaint();
				}
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(tracking!=null){
					System.out.println("Tracking:  " +tracking);
					tracking.setCurrX(arg0.getX()-tracking.getImage().getWidth()/2);
					tracking.setCurrY(arg0.getY()-tracking.getImage().getHeight()/2);
					repaint();
				}
				
			}
			
		});
	}
	public void paintComponent(Graphics g){
		g.clearRect(0, 0, getWidth(), getHeight());
		sideLen = 70*3;

		botLeftX = getWidth()/2 - sideLen/2;
		botLeftY = getHeight()/2 - sideLen/2; 
		
		g.drawRect(botLeftX, botLeftY, sideLen, sideLen);
		int horizLineInterval = sideLen/3;
		int vertLineInterval  = sideLen/3;
		
		for(int i = 1; i < 3; i++){
			g.drawLine(botLeftX+horizLineInterval*i, botLeftY, botLeftX+horizLineInterval*i, botLeftY+sideLen);
			g.drawLine(botLeftX, botLeftY+vertLineInterval*i, botLeftX+sideLen, botLeftY+vertLineInterval*i);

		}
		ArrayList<PuzzlePiece> freePieces = puzzle.getFreePieces();
		for(int i = 0; i < freePieces.size();i++){
//			System.out.println("I'm printing freePieces");
//			System.out.println(freePieces.get(i));
			if(freePieces.get(i) instanceof GraphicalPuzzlePiece){
//				System.out.println("it's graphical");
				GraphicalPuzzlePiece curr = (GraphicalPuzzlePiece) freePieces.get(i);

				g.drawImage(curr.getImage(), curr.getCurrX(), curr.getCurrY(), null);


			}
		}
		for(int i = 0; i < puzzle.getWidth(); i++){
			for(int j = 0; j < puzzle.getHeight() ; j++ ){
				if(puzzle.getPiece(i,j)!=null) {
					System.out.println("painting the board pieces");
					GraphicalPuzzlePiece curr = (GraphicalPuzzlePiece) puzzle.getPiece(i, j);
					curr.setCurrX(i * (sideLen/3) + botLeftX - curr.getImage().getWidth()/5);
					curr.setCurrY(j * (sideLen/3) + botLeftY - curr.getImage().getWidth()/5);
					g.drawImage(curr.getImage(), curr.getCurrX(), curr.getCurrY(), null);
				}
			}
		}
	}
	public ArrayList<PuzzlePiece> getFreePieces(){
		return puzzle.getFreePieces();
	}
	public GraphicalPuzzlePiece getPieceAtPoint(int x, int y){
		if((x>(botLeftX)&&x<botLeftX+sideLen) && (y>botLeftY&&y<botLeftY+sideLen)){
			int[] coords = getBoardPosAtPoint(x,y);
			return (GraphicalPuzzlePiece) puzzle.removePiece(coords[0], coords[1]);
		}
		else{
			ArrayList<PuzzlePiece> freePieces = puzzle.getFreePieces();
			for(int i = 0; i < freePieces.size(); i++){
				GraphicalPuzzlePiece curr =((GraphicalPuzzlePiece)freePieces.get(i));

				if(x>curr.getCurrX() && x < curr.getCurrX() + curr.getImage().getWidth() && y > curr.getCurrY() && y < curr.getCurrY() + curr.getImage().getHeight()) return curr;
			}
			return null;

		}
	}
	public int[] getBoardPosAtPoint(int x, int y){
		int[] coords = new int[2];
		if(x>botLeftX && x<botLeftX+sideLen&& y>botLeftY&&y<botLeftY+sideLen){
			coords[0] = (x-botLeftX)/(sideLen/3);
			coords[1] = (y-botLeftY)/(sideLen/3);
			return coords;
		}
		return null;
	}
	public void reset(){
		ArrayList<PuzzlePiece> freePieces = puzzle.getFreePieces();
		for(int i = 0; i < freePieces.size(); i++){
			((GraphicalPuzzlePiece) freePieces.get(i)).goHome();
		}
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if (puzzle.getPiece(i, j) != null)((GraphicalPuzzlePiece)puzzle.removePiece(i, j)).goHome();
			}
		}
	}
	public void solve(){
		puzzle.solvePuzzle();
		repaint();
	}

}