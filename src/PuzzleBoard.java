
public class PuzzleBoard {

	private int width;
	private int height;
	
	public PuzzleBoard(int width, int height){
		
	}
	
	public PuzzleBoard(){
		
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void clear(){
		System.out.println("cleared");
	}
	
	public boolean placePiece(int x, int y, PuzzlePiece piece){
		System.out.println("test placePiece");
		return false;
	}
	
	//returns null if empty
	public PuzzlePiece removePiece(int x, int y){ 
		System.out.println("test removePiece");
		return null;
	}
	
	//returns null if empty
	public PuzzlePiece getPiece(int x, int y){ 
		System.out.println("test getPiece");
		return null;
	}
	
	public boolean isValid(int x, int y){
		System.out.println("test isValid");
		return false;
	}
	
	public boolean isOccupied(int x, int y){
		System.out.println("test isOccupied");
		return false;
	}
	
	public boolean equals(PuzzlePiece piece){
		return false;
	}
}
