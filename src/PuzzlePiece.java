
public class PuzzlePiece {
	
	public static final int HEART_IN = 1;
	public static final int HEART_OUT = -1;
	public static final int DIAMOND_IN = 2;
	public static final int DIAMOND_OUT = -2;
	public static final int SPADE_IN = 3;
	public static final int SPADE_OUT = -3;
	public static final int CLUB_IN = 4;
	public static final int CLUB_OUT = -4;
	
	private int north;
	private int south;
	private int east;
	private int west;
	
	public PuzzlePiece(int north, int east, int south, int west){
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}
	
	public void rotateClockwise(){

	
		
		int temp = north;
		north = west;
		west = south;
		south = east;
		east = temp;
		
	}
	
	public void rotateCounterClockwise(){
		int temp = north;
		north = east;
		east = south;
		south = west;
		west = temp;
		
	}
	
	public int getNorth(){
		return north;
	}
	
	public int getEast(){
		return east;
	}
	
	public int getSouth(){
		return south;
	}
	
	public int getWest(){
		return west;
	}
	public static void main(String[] args){
		PuzzlePiece p = new PuzzlePiece(-1,-4,3,2);
		System.out.println(p.getEast());
		p.rotateCounterClockwise();
		System.out.println(p.getEast());
	}
}
