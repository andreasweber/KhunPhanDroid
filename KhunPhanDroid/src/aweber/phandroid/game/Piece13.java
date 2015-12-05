package aweber.phandroid.game;

// (X) pos
//  X
//  X
public class Piece13 extends Piece {

	public Piece13(int xLeft, int yTop) {
		super(xLeft, yTop);
	}

	@Override
	public boolean canMove(BoardPos free1, BoardPos free2) {
		// can't move horizontally
		// scan move vertically
		if ((xLeft == free1.x) && ((yTop + 3 == free1.y) || (yTop - 1 == free1.y))) {
			return true;
		}
		if ((xLeft == free2.x) && ((yTop + 3 == free2.y) || (yTop - 1 == free2.y))) {
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
				return (free1.equalPos(xLeft, yTop + 3) && free2.equalPos(xLeft, yTop + 4))
						|| (free1.equalPos(xLeft, yTop + 4) && free2.equalPos(xLeft, yTop + 3));
			} else if (yTop - toTopY == 1) {
				// move 1 field up
				return free1.equalPos(xLeft, yTop - 1) || free2.equalPos(xLeft, yTop - 1);
			} else if (yTop - toTopY == -1) {
				// move 1 field down
				return free1.equalPos(xLeft, yTop + 3) || free2.equalPos(xLeft, yTop + 3);
			}
		}
		return false;
	}

	@Override
	public void move(BoardPos free1, BoardPos free2, int toLeftX, int toTopY) {
		if (yTop - toTopY == 2) {
			// move 2 fields up
			free1.y += 3;
			free2.y += 3;
		} else if (yTop - toTopY == -2) {
			// move 2 fields down
			free1.y -= 3;
			free2.y -= 3;
		} else if (yTop - toTopY == 1) {
			// move 1 field up
			if (free1.equalPos(xLeft, yTop - 1)) {
				free1.y += 3;
			} else {
				free2.y += 3;
			}
		} else if (yTop - toTopY == -1) {
			// move 1 field down
			if (free1.equalPos(xLeft, yTop + 3)) {
				free1.y -= 3;
			} else {
				free2.y -= 3;
			}
		} else {
			throw new IllegalArgumentException("illegal move");
		}
		xLeft = toLeftX;
		yTop = toTopY;
	}

}
