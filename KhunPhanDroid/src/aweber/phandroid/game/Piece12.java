package aweber.phandroid.game;

public class Piece12 extends Piece {

	public Piece12(int xLeft, int yTop) {
		super(xLeft, yTop);
	}

	@Override
	public boolean canMove(BoardPos free1, BoardPos free2) {
		// can move horizontally - check if both neighbours on x are free
		boolean xFree = ((xLeft - 1 == free1.x) && (xLeft - 1 == free2.x))
				|| ((xLeft + 1 == free1.x) && (xLeft + 1 == free2.x));
		boolean yFree = ((yTop == free1.y) && (yTop + 1 == free2.y)) || ((yTop == free2.y) && (yTop + 1 == free1.y));
		if (xFree && yFree) {
			return true;
		}
		// can move vertically
		if ((xLeft == free1.x) && ((yTop + 2 == free1.y) || (yTop - 1 == free1.y))) {
			return true;
		}
		if ((xLeft == free2.x) && ((yTop + 2 == free2.y) || (yTop - 1 == free2.y))) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canMoveTo(BoardPos free1, BoardPos free2, int toLeftX, int toTopY) {
		if (xLeft == toLeftX) {
			if (yTop - toTopY == 2) {
				// move 2 fields up
				return (free1.equalPos(xLeft, yTop - 1) && free2.equalPos(xLeft, yTop - 2))
						|| (free1.equalPos(xLeft, yTop - 2) && free2.equalPos(xLeft, yTop - 1));
			} else if (yTop - toTopY == -2) {
				// move 2 fields down
				return (free1.equalPos(xLeft, yTop + 2) && free2.equalPos(xLeft, yTop + 3))
						|| (free1.equalPos(xLeft, yTop + 3) && free2.equalPos(xLeft, yTop + 2));
			} else if (yTop - toTopY == 1) {
				// move 1 field up
				return free1.equalPos(xLeft, yTop - 1) || free2.equalPos(xLeft, yTop - 1);
			} else if (yTop - toTopY == -1) {
				// move 1 field down
				return free1.equalPos(xLeft, yTop + 2) || free2.equalPos(xLeft, yTop + 2);
			}
		} else if (yTop == toTopY) {
			if (xLeft - toLeftX == 1) {
				// move 1 field left
				return (free1.equalPos(xLeft - 1, yTop) && free2.equalPos(xLeft - 1, yTop + 1))
						|| (free1.equalPos(xLeft - 1, yTop + 1) && free2.equalPos(xLeft - 1, yTop));
			} else if (xLeft - toLeftX == -1) {
				// move 1 field right
				return (free1.equalPos(xLeft + 1, yTop) && free2.equalPos(xLeft + 1, yTop + 1))
						|| (free1.equalPos(xLeft + 1, yTop + 1) && free2.equalPos(xLeft + 1, yTop));
			}
		}
		return false;
	}

	@Override
	public void move(BoardPos free1, BoardPos free2, int toLeftX, int toTopY) {
		if (xLeft - toLeftX == 1) {
			// move 1 field left
			free1.x++;
			free2.x++;
		} else if (xLeft - toLeftX == -1) {
			// move 1 field right
			free1.x--;
			free2.x--;
		} else if (yTop - toTopY == 2) {
			// move 2 fields up
			free1.y += 2;
			free2.y += 2;
		} else if (yTop - toTopY == -2) {
			// move 2 fields down
			free1.y -= 2;
			free2.y -= 2;
		} else if (yTop - toTopY == 1) {
			// move 1 field up
			if (free1.equalPos(xLeft, yTop - 1)) {
				free1.y += 2;
			} else {
				free2.y += 2;
			}
		} else if (yTop - toTopY == -1) {
			// move 1 field down
			if (free1.equalPos(xLeft, yTop + 2)) {
				free1.y -= 2;
			} else {
				free2.y -= 2;
			}
		} else {
			throw new IllegalArgumentException("illegal move");
		}
		xLeft = toLeftX;
		yTop = toTopY;
	}

}
