/* PuzzleBoard class
 * 
 * The board upon which PuzzlePieces are placed
 * 
 */
public class PuzzleBoard {

    private PuzzlePiece board[][];
    
    /* PuzzleBoard constructor
     * 
     * Default constructor setting the board as a 3 by 3
     */
    public PuzzleBoard() {
        board = new PuzzlePiece[3][3];
    }
    
    /* PuzzleBoard constructor
     * 
     * Constructor setting the board at a given width and height
     */
    public PuzzleBoard(int width, int height) {
        board = new PuzzlePiece[width][height];
    }
    
    // Returns the width of the board
    public int getWidth() {
        return board.length;
    }
    
    //Returns the height of the board
    public int getHeight() {
        return board[0].length;
    }
    
    /* Returns nothing
     * 
     * Sets all coordinates of the board to null
     */
    public void clear() {
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++)
                board[i][j] = null;
    }
    
    /* Returns whether the piece was placed or not
     * 
     *  Places a PuzzlePiece at the specified coordinates
     */
    public boolean placePiece (int x, int y, PuzzlePiece piece) {
        if(isValid(x, y)) {
            board[x][y] = piece;
            return true;
        }
        return false;
    }
    
    /* Returns the removed PuzzlePiece
     * 
     * Sets the specified coordinate as null, "removing" the piece
     */
    public PuzzlePiece removePiece(int x, int y) {
        PuzzlePiece piece = board[x][y];
        board[x][y] = null;
        return piece;
    }
    
    // Returns the PuzzlePiece at the specified coordinate
    public PuzzlePiece getPiece(int x, int y) {
        if (isValid(x, y)) return board[x][y];
        return null;
    }
    
    // Returns whether a given coordinate is valid on the constructed board or not
    public boolean isValid (int x, int y) {
        return x < board.length && x >= 0 && y < board[0].length && y >= 0;
    }
    
    // Returns whether there is a PuzzlePiece at the specified coordinate
    public boolean isOccupied (int x, int y) {
        if(board[x][y] != null)
            return true;
        return false;
    }

    public static void main(String[] args) {
        PuzzleBoard board = new PuzzleBoard(3, 4);
        System.out.println(board.getHeight());
    }

}
