import java.util.ArrayList;

public class JigsawPuzzle {

	private PuzzleBoard board;
	private ArrayList<PuzzlePiece> pieceStorage = new ArrayList<PuzzlePiece>();
	
	public JigsawPuzzle(int width, int height, ArrayList<PuzzlePiece> piece){
		
	}
	
	public boolean doesFit(int x, int y, PuzzlePiece piece){
		return false;
	}
	
	//returns an array of pieces that have not yet been placed on the board
	public ArrayList<PuzzlePiece> getFreePieces(){
		return pieceStorage; 
	}
	
	//solves the jigsaw puzzle if user gives up
	public void solvePuzzle(){
		System.out.println("test solvePuzzle");
	}
	
	//places a free piece puzzle piece onto the board
	public boolean placePiece(int x, int y, PuzzlePiece piece){ 
		return false;
	}
	
	//removes a puzzle piece from the board, the piece is now a free piece
	public PuzzlePiece removePiece(int x, int y){
		return null; 
	
	}
	
	//checks to see if the jigsaw puzzle is solved
	public boolean isSolved(){ 
		return false;
	}
	
	public PuzzlePiece getPiece(int x, int y){
		return null;
	}
	
	//removes every piece from the board
	public void clear(){ 
		System.out.println("test Jigsaw clear");
	}
	
	public int getWidth(){
		return 0;
	}
	
	public int getHeight(){
		return 0;
	}
}
