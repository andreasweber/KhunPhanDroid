package aweber.phandroid.game;

public abstract class Piece {
	public int xLeft;
	public int yTop;

	public Piece(int xLeft, int yTop) {
		super();
		this.xLeft = xLeft;
		this.yTop = yTop;
	}
	
	/** Can piece move at all? */
	public abstract boolean canMove(BoardPos free1, BoardPos free2);

	/** Can piece move at given position? */
	public abstract boolean canMoveTo(BoardPos free1, BoardPos free2, int toLeftX, int toTopY);

	/** execute move. */
	public abstract void move(BoardPos free1, BoardPos free2, int toLeftX, int toTopY);
}
