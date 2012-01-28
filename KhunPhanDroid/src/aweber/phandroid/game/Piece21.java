package aweber.phandroid.game;

public class Piece21 extends Piece {

	public Piece21(int xLeft, int yTop) {
		super(xLeft, yTop);
	}

	@Override
	public boolean canMove(BoardPos free1, BoardPos free2) {
		// move verically - check if both neighbours on y are free
		boolean yFree = ((yTop - 1 == free1.y) && (yTop - 1 == free2.y))
				|| ((yTop + 1 == free1.y) && (yTop + 1 == free2.y));
		boolean xFree = ((xLeft == free1.x) && (xLeft + 1 == free2.x))
				|| ((xLeft == free2.x) && (xLeft + 1 == free1.x));
		if (xFree && yFree) {
			return true;
		}
		// move horizontally
		if ((yTop == free1.y) && ((xLeft + 2 == free1.x) || (xLeft - 1 == free1.x))) {
			return true;
		}
		if ((yTop == free2.y) && ((xLeft + 2 == free2.x) || (xLeft - 1 == free2.x))) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canMoveTo(BoardPos free1, BoardPos free2, int toLeftX, int toTopY) {
		if (yTop == toTopY) {
			if (xLeft - toLeftX == 2) {
				// move 2 fields to left
				return (free1.equalPos(xLeft - 2, yTop) && free2.equalPos(xLeft - 1, yTop))
						|| (free1.equalPos(xLeft - 1, yTop) && free2.equalPos(xLeft - 2, yTop));
			} else if (xLeft - toLeftX == -2) {
				// move 2 fields to right
				return (free1.equalPos(xLeft + 2, yTop) && free2.equalPos(xLeft + 3, yTop))
						|| (free1.equalPos(xLeft + 3, yTop) && free2.equalPos(xLeft + 2, yTop));
			} else if (xLeft - toLeftX == 1) {
				// move 1 field to left
				return free1.equalPos(xLeft - 1, yTop) || free2.equalPos(xLeft - 1, yTop);
			} else if (xLeft - toLeftX == -1) {
				// move 1 field to right
				return free1.equalPos(xLeft + 2, yTop) || free2.equalPos(xLeft + 2, yTop);
			}
		} else if (xLeft == toLeftX) {
			if (yTop - toTopY == 1) {
				// move 1 field up
				return (free1.equalPos(xLeft, yTop - 1) && free2.equalPos(xLeft + 1, yTop - 1))
						|| (free1.equalPos(xLeft + 1, yTop - 1) && free2.equalPos(xLeft, yTop - 1));
			} else if (yTop - toTopY == -1) {
				// move 1 field down
				return (free1.equalPos(xLeft, yTop + 1) && free2.equalPos(xLeft + 1, yTop + 1))
						|| (free1.equalPos(xLeft + 1, yTop + 1) && free2.equalPos(xLeft, yTop + 1));
			}
		}
		return false;
	}

	@Override
	public void move(BoardPos free1, BoardPos free2, int toLeftX, int toTopY) {
		if (xLeft - toLeftX == 2) {
			// move 2 fields to left
			free1.x += 2;
			free2.x += 2;
		} else if (xLeft - toLeftX == -2) {
			// move 2 fields to right
			free1.x -= 2;
			free2.x -= 2;
		} else if (xLeft - toLeftX == 1) {
			// move 1 field to left
			if (free1.equalPos(xLeft - 1, yTop)) {
				free1.x += 2;
			} else {
				free2.x += 2;
			}
		} else if (xLeft - toLeftX == -1) {
			// move 1 field to right
			if (free1.equalPos(xLeft + 2, yTop)) {
				free1.x -= 2;
			} else {
				free2.x -= 2;
			}
		} else if (yTop - toTopY == 1) {
			// move 1 field up
			free1.y++;
			free2.y++;
		} else if (yTop - toTopY == -1) {
			// move 1 field down
			free1.y--;
			free2.y--;
		} else {
			throw new IllegalArgumentException("illegal move");
		}
		xLeft = toLeftX;
		yTop = toTopY;

	}

}