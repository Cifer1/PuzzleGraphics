import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

/*PuzzlePanel is the class where the drawing of the jigsqw puzzle occurs. Private data is a JigsawPuzzle,
 coordinates for dimensions, a graphical puzzle Piece, two booleans to determine if the puzzle has been solved by the user
or by the computer, and a buffered image of kim jong-un*/

public class PuzzlePanel extends JPanel {
	private JigsawPuzzle puzzle;
	private  int botLeftX;
	private  int botLeftY;
	private  int sideLen;
	private GraphicalPuzzlePiece tracking;
	private boolean solveFlag = false;
	private boolean cheatSolveFlag = false;
	private BufferedImage kjun;

	/**
	 * Create the panel.
	 */
	
	// This constructor takes in no parameters and creates a puzzle with the 9 images.
	public PuzzlePanel() {
		kjun = null;
		try {
			kjun = ImageIO.read(new File("img/proud.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
			// this method deals with the cases for when the  mouse is clicked 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(solveFlag) return;
				// TODO Auto-generated method stub
				int x = arg0.getX();
				int y = arg0.getY();
				// if the control button is also hit, then the graphical puzzle piece will be rotated
				if(arg0.isControlDown()&&!(x>botLeftX&&x<botLeftX+sideLen&&y>botLeftY&&y<botLeftY+sideLen)){
					GraphicalPuzzlePiece rotated = getPieceAtPoint(x,y);
					if(rotated!=null) rotated.rotateClockwise();
					repaint();
					return;
				}
				
				if(tracking==null){

					tracking = getPieceAtPoint(x,y);
				}
			
				else{
					if(x>botLeftX&&x<botLeftX+sideLen&&y>botLeftY&&y<botLeftY+sideLen){
						int[] coords = getBoardPosAtPoint(x,y);

						if(!puzzle.placePiece(coords[0], coords[1], tracking)) {

							tracking.goHome();
							repaint();
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
			//this method deals when the mouse is dragged
			public void mouseDragged(MouseEvent arg0) {
				if(solveFlag) return;

				// TODO Auto-generated method stub
				if(tracking==null)tracking = getPieceAtPoint(arg0.getX(),arg0.getY());
				/* if the piece is being dragged, the piece will be moved to where its being dragged and the coordinates will
				 be updated */
				if(tracking!=null){
					tracking.setCurrX(arg0.getX()-tracking.getImage().getWidth()/2);
					tracking.setCurrY(arg0.getY()-tracking.getImage().getHeight()/2);
					repaint();
				}
			}

			@Override
			// this method deals with if the mouse is being moved
			public void mouseMoved(MouseEvent arg0) {
				// if the puzzle has been solved, then return
				if(solveFlag) return;

				// if the mouse is being moved, the coordinates are updated
				if(tracking!=null){

					tracking.setCurrX(arg0.getX()-tracking.getImage().getWidth()/2);
					tracking.setCurrY(arg0.getY()-tracking.getImage().getHeight()/2);
					repaint();
				}
				
			}
			
		});
	}
	// Method will deal with when the puzzle has been solved, both by the computer, and by the user
	public void paintComponent(Graphics g){
		// if the puzzle is solved manually, a congrats message is created, and the puzzle grid is cleared
		solveFlag = puzzle.isSolved();
		g.clearRect(0, 0, getWidth(), getHeight());
		sideLen = 70*3;
		
		botLeftX = getWidth()/2 - sideLen/2;
		botLeftY = getHeight()/2 - sideLen/2; 
		
		
		int horizLineInterval = sideLen/3;
		int vertLineInterval  = sideLen/3;

		if(solveFlag &&!cheatSolveFlag){
			g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
			g.drawString("CONGRATS!", getWidth()/2-300, getWidth()/2-200);
		}
		// if the user allows the computer to solve it, the image of kim jong un pops up
		else if(cheatSolveFlag){
			
			AffineTransform at = new AffineTransform();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double xScaleFactor = (double)kjun.getWidth()/getWidth();
			double yScaleFactor = (double)kjun.getHeight()/getHeight();
			at.scale(xScaleFactor, yScaleFactor);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			g.drawImage(op.filter(kjun, null), getWidth()/2 - op.filter(kjun, null).getWidth()/2, getHeight()/4 - op.filter(kjun, null).getHeight()/2, null);
		}
		// drawing the rectangle with the gridlines
		g.drawRect(botLeftX, botLeftY, sideLen, sideLen);
		for(int i = 1; i < 3; i++){
			g.drawLine(botLeftX+horizLineInterval*i, botLeftY, botLeftX+horizLineInterval*i, botLeftY+sideLen);
			g.drawLine(botLeftX, botLeftY+vertLineInterval*i, botLeftX+sideLen, botLeftY+vertLineInterval*i);

		}
		//gets the array list of free pieces that have not been inserted into the puzzle yet
		ArrayList<PuzzlePiece> freePieces = puzzle.getFreePieces();
		for(int i = 0; i < freePieces.size();i++){
			if(freePieces.get(i) instanceof GraphicalPuzzlePiece){
				GraphicalPuzzlePiece curr = (GraphicalPuzzlePiece) freePieces.get(i);
				// drawing the graphical puzzle piece image
				g.drawImage(curr.getImage(), curr.getCurrX(), curr.getCurrY(), null);


			}
		}
		// updating the board every time a new piece is placed. 
		for(int i = 0; i < puzzle.getWidth(); i++){
			for(int j = 0; j < puzzle.getHeight() ; j++ ){
				if(puzzle.getPiece(i,j)!=null) {

					GraphicalPuzzlePiece curr = (GraphicalPuzzlePiece) puzzle.getPiece(i, j);
					curr.setCurrX(i * (sideLen/3) + botLeftX - curr.getImage().getWidth()/5);
					curr.setCurrY(j * (sideLen/3) + botLeftY - curr.getImage().getWidth()/5);
					g.drawImage(curr.getImage(), curr.getCurrX(), curr.getCurrY(), null);
				}
			}
		}
	}
	// returns the free pieces that are not in the board. There are no parameters
	public ArrayList<PuzzlePiece> getFreePieces(){
		return puzzle.getFreePieces();
	}
	// returns a graphical puzzle piece at a specific place in the grid. The parameters are the x and y coordinates in the grid
	public GraphicalPuzzlePiece getPieceAtPoint(int x, int y){
		// the point asked is a valid point, the piece is returned
		if((x>(botLeftX)&&x<botLeftX+sideLen) && (y>botLeftY&&y<botLeftY+sideLen)){
			int[] coords = getBoardPosAtPoint(x,y);
			return (GraphicalPuzzlePiece) puzzle.removePiece(coords[0], coords[1]);
		}
		// if not, then nothing is returned
		else{
			ArrayList<PuzzlePiece> freePieces = puzzle.getFreePieces();
			for(int i = 0; i < freePieces.size(); i++){
				GraphicalPuzzlePiece curr =((GraphicalPuzzlePiece)freePieces.get(i));

				if(x>curr.getCurrX() && x < curr.getCurrX() + curr.getImage().getWidth() && y > curr.getCurrY() && y < curr.getCurrY() + curr.getImage().getHeight()) return curr;
			}
			return null;

		}
	}
	// method takes in an x and y coordinate and returns an array of the board position at a specific point. 
	public int[] getBoardPosAtPoint(int x, int y){
		int[] coords = new int[2];
		// checking to see if its a valid point. If not, then return null.
		if(x>botLeftX && x<botLeftX+sideLen&& y>botLeftY&&y<botLeftY+sideLen){
			coords[0] = (x-botLeftX)/(sideLen/3);
			coords[1] = (y-botLeftY)/(sideLen/3);
			return coords;
		}
		return null;
	}
	// method will reset the board and place the pieces back into the getFreePieces arraylist. No parameters
	public void reset(){
		tracking = null;
		cheatSolveFlag = false;
		solveFlag = false;
		ArrayList<PuzzlePiece> freePieces = puzzle.getFreePieces();
		for(int i = 0; i < freePieces.size(); i++){
			((GraphicalPuzzlePiece) freePieces.get(i)).goHome();

		}
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if (puzzle.getPiece(i, j) != null){
					GraphicalPuzzlePiece curr = ((GraphicalPuzzlePiece)puzzle.removePiece(i, j));
					curr.goHome();
					while(curr.rotations!=0){
						curr.rotateClockwise();
					}
				}
			}
		}
	}
	// solves the puzzle. No parameters
	public void solve(){
		// if the puzzle is already solved, return. 
		if(solveFlag) return;
		//otherwise, reset the board and then call solve. 
		reset();
		cheatSolveFlag = true;
		solveFlag = true;
		puzzle.solvePuzzle();
		repaint();
	}

}