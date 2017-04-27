import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridBagLayout;

public class PuzzlePanel extends JPanel {
	private JigsawPuzzle puzzle;

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
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.CLUB_OUT, PuzzlePiece.HEART_OUT, PuzzlePiece.DIAMOND_IN, PuzzlePiece.CLUB_IN, new File("img/piece_1.png")));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.SPADE_OUT, PuzzlePiece.DIAMOND_OUT, PuzzlePiece.SPADE_IN, PuzzlePiece.HEART_IN, new File("img/piece_2.png")));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.HEART_OUT, PuzzlePiece.SPADE_OUT, PuzzlePiece.SPADE_IN, PuzzlePiece.CLUB_IN, new File("img/piece_3.png")));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.HEART_OUT, PuzzlePiece.DIAMOND_OUT, PuzzlePiece.CLUB_IN, PuzzlePiece.CLUB_IN, new File("img/piece_4.png")));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.SPADE_OUT, PuzzlePiece.SPADE_OUT, PuzzlePiece.HEART_IN, PuzzlePiece.CLUB_IN, new File("img/piece_5.png")));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.HEART_OUT, PuzzlePiece.DIAMOND_OUT, PuzzlePiece.DIAMOND_IN, PuzzlePiece.HEART_IN, new File("img/piece_6.png")));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.SPADE_OUT, PuzzlePiece.DIAMOND_OUT, PuzzlePiece.HEART_IN, PuzzlePiece.DIAMOND_IN, new File("img/piece_7.png")));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.CLUB_OUT, PuzzlePiece.HEART_OUT, PuzzlePiece.SPADE_IN, PuzzlePiece.HEART_IN, new File("img/piece_8.png")));
		pieces.add(new GraphicalPuzzlePiece(PuzzlePiece.DIAMOND_OUT, PuzzlePiece.CLUB_OUT, PuzzlePiece.CLUB_IN, PuzzlePiece.DIAMOND_IN, new File("img/piece_9.png")));
		
		puzzle = new JigsawPuzzle(3,3,pieces);
		ArrayList<PuzzlePiece> freePieces = puzzle.getFreePieces();

		for (int i = 0; i < freePieces.size(); i++) {
			System.out.println("I'm printing freePieces");
			System.out.println(freePieces.get(i));
			if (freePieces.get(i) instanceof GraphicalPuzzlePiece) {
				System.out.println("it's graphical");
				add(((GraphicalPuzzlePiece) freePieces.get(i)).pieceComponent);
				((GraphicalPuzzlePiece) freePieces.get(i)).pieceComponent.setVisible(true);
				//System.out.println(pieceComponent.getLocationOnScreen());
				System.out.println(((GraphicalPuzzlePiece) freePieces.get(i)).pieceComponent);
				((GraphicalPuzzlePiece) freePieces.get(i)).pieceComponent.getGraphics().drawImage(((GraphicalPuzzlePiece)
				freePieces.get(i)).getImage(), 0, 0, null);

			}
		}
		addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
	}
	public void paintComponent(Graphics g){
		int botLeftX = getWidth()/4;
		int botLeftY = getHeight()/4;
		int sideLen = Math.min(getWidth()/2, getHeight()/2);
		g.drawRect(botLeftX, botLeftY, sideLen, sideLen);
		int horizLineInterval = (sideLen)/3;
		int vertLineInterval  = (sideLen)/3;
		
		for(int i = 1; i < 3; i++){
			g.drawLine(botLeftX+horizLineInterval*i, botLeftY, botLeftX+horizLineInterval*i, botLeftY+sideLen);
			g.drawLine(botLeftX, botLeftY+vertLineInterval*i, botLeftX+sideLen, botLeftY+vertLineInterval*i);

		}
		ArrayList<PuzzlePiece> freePieces = puzzle.getFreePieces();
		for(int i = 0; i < freePieces.size();i++){
			System.out.println("I'm printing freePieces");
			System.out.println(freePieces.get(i));
			if(freePieces.get(i) instanceof GraphicalPuzzlePiece){
				System.out.println("it's graphical");
				JComponent pieceComponent = ((GraphicalPuzzlePiece)freePieces.get(i)).pieceComponent;
//				add(pieceComponent);
//				pieceComponent.setVisible(true);
//				System.out.println(pieceComponent.getLocationOnScreen());
				g.drawImage(((GraphicalPuzzlePiece) freePieces.get(i)).getImage(), 0, 0, null);


			}
		}
	}
	public ArrayList<PuzzlePiece> getFreePieces(){
		return puzzle.getFreePieces();
	}

}
