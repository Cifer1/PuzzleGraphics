/* Class to represent the rules of a Jigsaw Puzzle.

   Programmed by Jacob Kreuze on April 7th, 2017. */

import java.util.ArrayList;

public class JigsawPuzzle {
    private PuzzleBoard board;
    private ArrayList<PuzzlePiece> pool;

    /* Constructs a JigsawPuzzle from the given width, height, and pieces.

       Parameters
       ==========
       * [int] width  - The desired width of the puzzle board.
       * [int] height - The desired height of the puzzle board.
       * [ArrayList<PuzzlePiece>] pieces - Pieces that are not yet on the board. */
    public JigsawPuzzle(int width, int height, ArrayList<PuzzlePiece> pieces) {
        this.board = new PuzzleBoard(width, height);
        this.pool  = pieces;
    }

    /* Returns whether or not the give piece fits at the given coordinates.

       Parameters
       ==========
       * [int] x - The x coordinate to check.
       * [int] y - The y coordinate to check.
       * [PuzzlePiece] piece - The piece to check.  */
    public boolean doesFit(int x, int y, PuzzlePiece piece) {
        PuzzlePiece north, east, south, west;
        north = board.getPiece(x, y - 1);
        east  = board.getPiece(x + 1, y);
        south = board.getPiece(x, y + 1);
        west  = board.getPiece(x - 1, y);
        if (north != null && north.getSouth() + piece.getNorth() != 0) {
            return false;
        } if (east != null && east.getWest() + piece.getEast() != 0) {
            return false;
        } if (south != null && south.getNorth() + piece.getSouth() != 0) {
            return false;
        } if (west != null && west.getEast() + piece.getWest() != 0) {
            return false;
        }
        return true;
    }

    /* Returns the pool of pieces that have not yet been put pn the board.

       Parameters
       ==========
       None.

       Returns an ArrayList of PuzzlePieces. */
    public ArrayList<PuzzlePiece> getFreePieces() {
        ArrayList<PuzzlePiece> clone = new ArrayList<PuzzlePiece>();
        for (PuzzlePiece p: this.pool) {
            clone.add(p);
        }
        return clone;
    }

    /* Attempts to place the given piece at the given coordinates.

       Parameters
       ==========
       * [int] x - The x coordinate to place the piece at.
       * [int] y - The y coordinate to place the piece at.
       * [PuzzlePiece] piece - The piece to place.

       Returns true if the piece was successfully placed. */
    public boolean placePiece(int x, int y, PuzzlePiece piece) {
        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).equals(piece)) {
                pool.remove(i);
                break;
            }
        }
        return board.placePiece(x, y, piece);
    }

    /* Pops a piece off of the puzzle board and returns it.

       Parameters
       ==========
       * [int] x - The x coordinate of the piece to remove.
       * [int] y - The y coordinate of the piece to remove.

       Returns a PuzzlePiece. */
    public PuzzlePiece removePiece(int x, int y) {
        PuzzlePiece piece = board.removePiece(x, y);
        if (piece != null)
            pool.add(piece);
        return piece;
    }

    /* Returns the piece at the given position.

       Parameters
       ==========
       * [int] x - The x coordinate of the piece to get.
       * [int] y - The y coordinate of the piece to get.

       Returns a PuzzlePiece. */
    public PuzzlePiece getPiece(int x, int y) {
        return board.getPiece(x, y);
    }

    /* Removes all pieces from the board and places them into the pool
       of unused pieces.

       Parameters
       ==========
       None.

       Returns nothing. */
    public void clear() {
        for (int y = 0; y < this.board.getHeight(); y++) {
            for (int x = 0; x < this.board.getWidth(); x++) {
                removePiece(x, y);
            }
        }
    }

    /* Returns the current width of the board.

       Parameters
       ==========
       None.

       Returns an integer. */
    public int getWidth() {
        return this.board.getWidth();
    }

    /* Returns the current height of the board.

       Parameters
       ==========
       None.

       Returns an integer. */
    public int getHeight() {
        return this.board.getHeight();
    }

    /* Checks whether or not the puzzle is solved.

       Parameters
       ==========
       None.

       Returns a boolean. */
    public boolean isSolved() {
        PuzzlePiece piece;
        for (int y = 0; y < this.board.getHeight(); y++) {
            for (int x = 0; x < this.board.getWidth(); x++) {
                piece = this.board.getPiece(x, y);
                if (piece == null || !doesFit(x, y, piece)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* Solves the puzzle.

       Parameters
       ==========
       * [int] x - The X position to start solving at.
       * [int] y - The Y position to start solving at.

       Returns a boolean representing whether or not the puzzle could be
       solved going from the given x and y coordinates. */
    private boolean solvePuzzle(int x, int y) {
        if (isSolved()) return true;
        // Stop - it's solved but there are not enough pieces for the board..
        if (pool.size() == 0) return true;
        for (int i = 0; i < pool.size(); i++) {
            PuzzlePiece piece = pool.get(i);
            for (int orientation = 0; orientation < 4; orientation++) {
                if (doesFit(x, y, piece)) {
                    placePiece(x, y, piece);
                    int nextX = (x + 1) % this.board.getWidth();
                    int nextY = (y + (x + 1) / this.board.getWidth()) % this.board.getHeight();
                    if (solvePuzzle(nextX, nextY)) {
                        return true;
                    }
                    // Remove any duds that lead us astray. 
                    removePiece(x, y);
                }
                piece.rotateClockwise();
            }
        }
        return false;
    }

    /* Solves the puzzle.

       Parameters
       ==========
       None.

       Returns nothing. */
    public void solvePuzzle() {
        clear();
        solvePuzzle(0, 0);
    }
}
