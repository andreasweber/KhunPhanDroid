package aweber.phandroid.game;

public class Piece11 extends Piece {

	public Piece11(int xLeft, int yTop) {
		super(xLeft, yTop);
	}

	@Override
	public boolean canMove(BoardPos free1, BoardPos free2) {
		// check if neighbour on x is free
		if (free1.equalPos(xLeft - 1, yTop) || free1.equalPos(xLeft + 1, yTop) || free2.equalPos(xLeft - 1, yTop)
				|| free2.equalPos(xLeft + 1, yTop)) {
			return true;
		}
		// check if neighbour on y is free
		if (free1.equalPos(xLeft, yTop - 1) || free1.equalPos(xLeft, yTop + 1) || free2.equalPos(xLeft, yTop - 1)
				|| free2.equalPos(xLeft, yTop + 1)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canMoveTo(BoardPos free1, BoardPos free2, int toLeftX, int toTopY) {
		if (yTop == toTopY) {
			if (xLeft - toLeftX == 2) {
				// move 2 fields left
				return (free1.equalPos(xLeft - 1, yTop) && free2.equalPos(xLeft - 2, yTop))
						|| (free1.equalPos(xLeft - 2, yTop) && free2.equalPos(xLeft - 1, yTop));
			} else if (xLeft - toLeftX == -2) {
				// move 2 fields right
				return (free1.equalPos(xLeft + 1, yTop) && free2.equalPos(xLeft + 2, yTop))
						|| (free1.equalPos(xLeft + 2, yTop) && free2.equalPos(xLeft + 1, yTop));
			} else if (xLeft - toLeftX == 1) {
				// move 1 field left
				return free1.equalPos(xLeft - 1, yTop) || free2.equalPos(xLeft - 1, yTop);
			} else if (xLeft - toLeftX == -1) {
				// move 1 field right
				return free1.equalPos(xLeft + 1, yTop) || free2.equalPos(xLeft + 1, yTop);
			}
		} else if (xLeft == toLeftX) {
			if (yTop - toTopY == 2) {
				// move 2 fields up
				return (free1.equalPos(xLeft, yTop - 1) && free2.equalPos(xLeft, yTop - 2))
						|| (free1.equalPos(xLeft, yTop - 2) && free2.equalPos(xLeft, yTop - 1));
			} else if (yTop - toTopY == -2) {
				// move 2 fields down
				return (free1.equalPos(xLeft, yTop + 1) && free2.equalPos(xLeft, yTop + 2))
						|| (free1.equalPos(xLeft, yTop + 2) && free2.equalPos(xLeft, yTop + 1));
			} else if (yTop - toTopY == 1) {
				// move 1 field up
				return free1.equalPos(xLeft, yTop - 1) || free2.equalPos(xLeft, yTop - 1);
			} else if (yTop - toTopY == -1) {
				// move 1 field down
				return free1.equalPos(xLeft, yTop + 1) || free2.equalPos(xLeft, yTop + 1);
			}
		}
		return false;
	}

	@Override
	public void move(BoardPos free1, BoardPos free2, int toLeftX, int toTopY) {
		if (xLeft - toLeftX == 2) {
			// move 2 fields left
			free1.x++;
			free2.x++;
		} else if (xLeft - toLeftX == -2) {
			// move 2 fields right
			free1.x--;
			free2.x--;
		} else if (xLeft - toLeftX == 1) {
			// move 1 field left
			if (free1.equalPos(xLeft - 1, yTop)) {
				free1.x++;
			} else {
				free2.x++;
			}
		} else if (xLeft - toLeftX == -1) {
			// move 1 field right
			if (free1.equalPos(xLeft + 1, yTop)) {
				free1.x--;
			} else {
				free2.x--;
			}
		} else if (yTop - toTopY == 2) {
			// move 2 fields up
			free1.y++;
			free2.y++;
		} else if (yTop - toTopY == -2) {
			// move 2 fields down
			free1.y--;
			free2.y--;
		} else if (yTop - toTopY == 1) {
			// move 1 field up
			if (free1.equalPos(xLeft, yTop - 1)) {
				free1.y++;
			} else {
				free2.y++;
			}
		} else if (yTop - toTopY == -1) {
			// move 1 field down
			if (free1.equalPos(xLeft, yTop + 1)) {
				free1.y--;
			} else {
				free2.y--;
			}
		} else {
			throw new IllegalArgumentException("illegal move");
		}
		xLeft = toLeftX;
		yTop = toTopY;
	}
}
