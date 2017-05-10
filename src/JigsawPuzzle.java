import java.util.ArrayList;

public class JigsawPuzzle {

	private PuzzleBoard board;
	private ArrayList<PuzzlePiece> pieceStorage = new ArrayList<PuzzlePiece>();
	
	public JigsawPuzzle(int width, int height, ArrayList<PuzzlePiece> piece){
		for(int i = 0; i < piece.size(); i++){
			pieceStorage.add(piece.get(i));
		}
		board = new PuzzleBoard(width, height);
	}
	
	public boolean doesFit(int x, int y, PuzzlePiece piece){
		if(board.isOccupied(x+1, y)){
			if(piece.getEast()+board.getPiece(x+1, y).getWest()!=0){
				System.out.println("this piece's right part doesn't fit with the right one's left part");

				return false;
			}
		}
		if(board.isOccupied(x, y-1)){
			if(piece.getNorth()+board.getPiece(x, y-1).getSouth()!=0){
				System.out.println("this piece's top doesn't fit with the upper one's bottom");
				return false;
			}
		}
		if(board.isOccupied(x,y+1)){
			if(piece.getSouth()+board.getPiece(x, y+1).getNorth()!=0){
				System.out.println("this piece's bottom doesn't fit with the lower one's top");

				return false;
			}
		}
		if(board.isOccupied(x-1, y)){
			if(piece.getWest()+board.getPiece(x-1, y).getEast()!=0){
				System.out.println("this piece's left part doesn't fit with the left one's right part");

				return false;
			}
		}
		System.out.println("fits");
		return true;
	}
	
	//returns an array of pieces that have not yet been placed on the board
	public ArrayList<PuzzlePiece> getFreePieces(){
		return pieceStorage; 
	}
	
	//solves the jigsaw puzzle if user gives up
	public void solvePuzzle(){
		System.out.println("solvePuzzle");
	}
	
	//places a free piece puzzle piece onto the board
	public boolean placePiece(int x, int y, PuzzlePiece piece){ 
		if(board.isValid(x, y)&&doesFit(x,y,piece)){
			board.placePiece(x, y, piece);
			pieceStorage.remove(piece);
			return true;
		}
		return false;
	}
	
	//removes a puzzle piece from the board, the piece is now a free piece
	public PuzzlePiece removePiece(int x, int y){
		if(board.isOccupied(x, y)){
			pieceStorage.add(board.getPiece(x, y));
			return board.removePiece(x, y);
			
		}
		return null;
	
	}
	
	//checks to see if the jigsaw puzzle is solved
	public boolean isSolved(){ 
		//return false;
		for(int i = 0; i<board.getHeight();i++){
			for(int j = 0; j<board.getWidth();j++){
				if(!doesFit(i,j,board.getPiece(i, j))) return false;
			}
		}
		return true;
	}
	
	public PuzzlePiece getPiece(int x, int y){
		if(board.isOccupied(x, y)){
			return board.getPiece(x, y);
		}
		return null;
	}
	
	//removes every piece from the board
	public void clear(){ 
		board.clear();
		for(int i = 0; i<board.getHeight();i++){
			for(int j=0;j<board.getWidth();j++){
				pieceStorage.add(board.getPiece(i, j));
				board.removePiece(i, j);
			}
		}
	}
	
	public int getWidth(){
		return board.getWidth();
	}
	
	public int getHeight(){
		return board.getHeight();
	}
	
}
