/* Class to represent a puzzle piece.

   Programmed by Jacob Kreuze on April 7th, 2017. */

public class PuzzlePiece {
    /* Constants representing the piece sides. */
    public static final int HEART_IN    =  1;
    public static final int HEART_OUT   = -1;
    public static final int SPADE_IN    =  2;
    public static final int SPADE_OUT   = -2;
    public static final int CLUB_IN     =  3;
    public static final int CLUB_OUT    = -3;
    public static final int DIAMOND_IN  =  4;
    public static final int DIAMOND_OUT = -4;

    private int north, east, south, west;

    /* Constructs a PuzzlePiece containing the given side values.

       Parameters
       ==========
       * [int] north - The north side.
       * [int] east  - The east side.
       * [int] south - The south side.
       * [int] west  - The west side. */
    public PuzzlePiece(int north, int east, int south, int west) {
        this.north = north;
        this.east  = east;
        this.south = south;
        this.west  = west;
    }

    /* Returns the edge value on the piece's north side.

       Parameters
       ==========
       None.

       Returns an integer. */
    public int getNorth() {
        return this.north;
    }

    /* Returns the edge value on the piece's east side.

       Parameters
       ==========
       None.

       Returns an integer. */
    public int getEast() {
        return this.east;
    }

    /* Returns the edge value on the piece's south side.

       Parameters
       ==========
       None.

       Returns an integer. */
    public int getSouth() {
        return this.south;
    }

    /* Returns the edge value on the piece's west side.

       Parameters
       ==========
       None.

       Returns an integer. */
    public int getWest() {
        return this.west;
    }


    /* Rotates the piece 90 degrees clockwise.

       Parameters
       ==========
       None.

       Returns nothing. */
    public void rotateClockwise() {
        int temp   = this.north;
        this.north = this.west;
        this.west  = this.south;
        this.south = this.east;
        this.east  = temp;
    }

    /* Rotates the piece 90 degrees counterclockwise.

       Parameters
       ==========
       None.

       Returns nothing. */
    public void rotateCounterclockwise() {
        int temp   = this.north;
        this.north = this.east;
        this.east  = this.south;
        this.south = this.west;
        this.west  = temp;
    }

    /* Returns whether or not the piece is equivalent to aother piece.

       Parameters
       ==========
       * [PuzzlePiece] piece - The piece to compare to.

       Returns a boolean. */
    public boolean equals(PuzzlePiece piece) {
        if (this == piece) return true;
        if (this.getNorth() != piece.getNorth() ||
            this.getEast()  != piece.getEast()  ||
            this.getSouth() != piece.getSouth() ||
            this.getWest()  != piece.getWest())
            return false;
        return true;
    }
}
