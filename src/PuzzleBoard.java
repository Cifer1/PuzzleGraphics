import java.util.ArrayList;

public class PuzzleBoard {
	//private ArrayList<PuzzlePiece> list=new ArrayList<PuzzlePiece>();
	private int width;
	private int height;
	private PuzzlePiece[][] board = new PuzzlePiece[height][width]; 
	
	public PuzzleBoard(int width, int height){
		this.height=height;
		this.width=width;
	}
	
	public PuzzleBoard(){
		width=3;
		height=3;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void clear(){
		for(int i=0;i<board.length;i++){
			for(int j = 0; j<board[i].length;j++){
	//			list.add(removePiece(i,j));
			}
		}
	}
	
	public boolean placePiece(int x, int y, PuzzlePiece piece){
	
		if(!isOccupied(x,y)){
			board[x][y]=piece;
	//		list.remove(piece);
			return true;
		}
		else{
			return false;
		}
	}
	
	//returns null if empty
	public PuzzlePiece removePiece(int x, int y){ 
		System.out.println("test removePiece");
		if(isOccupied(x,y)){
			PuzzlePiece p = board[x][y];
			board[x][y]=null;
//			list.add(p);
			return p;
		}
		else{
			return null;
		}
	}
	
	//returns null if empty
	public PuzzlePiece getPiece(int x, int y){ 
		return board[x][y];
	}
	
	public boolean isValid(int x, int y){
		System.out.println("test isValid");
		return false;
	}
	
	public boolean isOccupied(int x, int y){
		if(board[x][y]==null) return false;
		return true;
	}


}
