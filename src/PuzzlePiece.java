
public class PuzzlePiece {
	
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
		System.out.println("clockwise test");
	}
	
	public void rotateCounterClockwise(){
		System.out.println("counterClockwise test");
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
}
