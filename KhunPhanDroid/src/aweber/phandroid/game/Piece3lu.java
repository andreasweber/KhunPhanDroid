package aweber.phandroid.game;

// X
// XX
public class Piece3lu extends Piece {

	// P
	// XX
	public Piece3lu(int xLeft, int yTop) {
		super(xLeft, yTop);
	}

	// FP
	// FXX
	protected boolean canMoveLeft(BoardPos free1, BoardPos free2) {
		if ((free1.equalPos(xLeft - 1, yTop) && free2.equalPos(xLeft - 1, yTop + 1))
				|| (free2.equalPos(xLeft - 1, yTop) && free1.equalPos(xLeft - 1, yTop + 1))) {
			return true;
		}
		return false;
	}

	// PF
	// XXF
	protected boolean canMoveRight(BoardPos free1, BoardPos free2) {
		if ((free1.equalPos(xLeft + 1, yTop) && free2.equalPos(xLeft + 2, yTop + 1))
				|| (free2.equalPos(xLeft + 1, yTop) && free1.equalPos(xLeft + 2, yTop + 1))) {
			return true;
		}
		return false;
	}

	// F
	// PF
	// XX
	protected boolean canMoveUp(BoardPos free1, BoardPos free2) {
		if ((free1.equalPos(xLeft, yTop - 1) && free2.equalPos(xLeft + 1, yTop))
				|| (free2.equalPos(xLeft, yTop - 1) && free1.equalPos(xLeft + 1, yTop))) {
			return true;
		}
		return false;
	}

	// P
	// XX
	// FF
	protected boolean canMoveDown(BoardPos free1, BoardPos free2) {
		if ((free1.equalPos(xLeft, yTop + 2) && free2.equalPos(xLeft + 1, yTop + 2))
				|| (free2.equalPos(xLeft, yTop + 2) && free1.equalPos(xLeft + 1, yTop + 2))) {
			return true;
		}
		return false;
	}

	@Override
	/** there are always two combinations of free1/free2 that will work. */
	public boolean canMove(BoardPos free1, BoardPos free2) {
		// can move left?
		if (canMoveLeft(free1, free2)) {
			return true;
		}
		// can move right?
		if (canMoveRight(free1, free2)) {
			return true;
		}
		// can move up?
		if (canMoveUp(free1, free2)) {
			return true;
		}
		// can move down?
		if (canMoveDown(free1, free2)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canMoveTo(BoardPos free1, BoardPos free2, int toLeftX, int toTopY) {
		if (yTop == toTopY) {
			if (xLeft - toLeftX == 1) {
				// can move left?
				return canMoveLeft(free1, free2);
			} else if (xLeft - toLeftX == -1) {
				// can move right?
				return canMoveRight(free1, free2);
			}
		} else if (xLeft == toLeftX) {
			if (yTop - toTopY == 1) {
				// can move up?
				return canMoveUp(free1, free2);
			} else if (yTop - toTopY == -1) {
				// can move down?
				return canMoveDown(free1, free2);
			}
		}
		return false;
	}

	@Override
	// P
	// XX
	public void move(BoardPos free1, BoardPos free2, int toLeftX, int toTopY) {
		if (xLeft - toLeftX == 1) {
			// move 1 field left
			if (free1.equalPos(xLeft - 1, yTop)) {
				free1.x++;
				free2.x += 2;
			} else {
				free2.x++;
				free1.x += 2;
			}
		} else if (xLeft - toLeftX == -1) {
			// move 1 field right
			if (free1.equalPos(xLeft + 1, yTop)) {
				free1.x--;
				free2.x -= 2;
			} else {
				free2.x--;
				free1.x -= 2;
			}
		} else if (yTop - toTopY == 1) {
			// move 1 field up
			if (free1.equalPos(xLeft, yTop - 1)) {
				free1.y += 2;
				free2.y++;
			} else {
				free2.y += 2;
				free1.y++;
			}
		} else if (yTop - toTopY == -1) {
			// move 1 field down
			if (free1.equalPos(xLeft, yTop + 2)) {
				free1.y -= 2;
				free2.y--;
			} else {
				free2.y -= 2;
				free1.y--;
			}
		} else {
			throw new IllegalArgumentException("illegal move");
		}
		xLeft = toLeftX;
		yTop = toTopY;
	}

}
