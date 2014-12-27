package aweber.phandroid.game;

public class Piece22 extends Piece {

	public Piece22(int xLeft, int yTop) {
		super(xLeft, yTop);
	}

	@Override
	public boolean canMove(BoardPos free1, BoardPos free2) {
		// move vertically - check if both neighbors on y are free
		if ((free1.equalPos(xLeft, yTop - 1) && free2.equalPos(xLeft + 1, yTop - 1))
				|| (free1.equalPos(xLeft + 1, yTop - 1) && free2.equalPos(xLeft, yTop - 1))) {
			return true;
		}
		if ((free1.equalPos(xLeft, yTop + 2) && free2.equalPos(xLeft + 1, yTop + 2))
				|| (free1.equalPos(xLeft + 1, yTop + 2) && free2.equalPos(xLeft, yTop + 2))) {
			return true;
		}
		// move horizontally - check if both neighbors on x are free
		if ((free1.equalPos(xLeft - 1, yTop) && free2.equalPos(xLeft - 1, yTop + 1))
				|| (free1.equalPos(xLeft - 1, yTop + 1) && free2.equalPos(xLeft - 1, yTop))) {
			return true;
		}
		if ((free1.equalPos(xLeft + 2, yTop) && free2.equalPos(xLeft + 2, yTop + 1))
				|| (free1.equalPos(xLeft + 2, yTop + 1) && free2.equalPos(xLeft + 2, yTop))) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canMoveTo(BoardPos free1, BoardPos free2, int toLeftX, int toTopY) {
		if (yTop == toTopY) {
			if (xLeft - toLeftX == 1) {
				// move 1 field left
				return (free1.equalPos(xLeft - 1, yTop) && free2.equalPos(xLeft - 1, yTop + 1))
						|| (free1.equalPos(xLeft - 1, yTop + 1) && free2.equalPos(xLeft - 1, yTop));
			} else if (xLeft - toLeftX == -1) {
				// move 1 field right
				return (free1.equalPos(xLeft + 2, yTop) && free2.equalPos(xLeft + 2, yTop + 1))
						|| (free1.equalPos(xLeft + 2, yTop + 1) && free2.equalPos(xLeft + 2, yTop));
			}
		} else if (xLeft == toLeftX) {
			if (yTop - toTopY == 1) {
				// move 1 field up
				return (free1.equalPos(xLeft, yTop - 1) && free2.equalPos(xLeft + 1, yTop - 1))
						|| (free1.equalPos(xLeft + 1, yTop - 1) && free2.equalPos(xLeft, yTop - 1));
			} else if (yTop - toTopY == -1) {
				// move 1 field down
				return (free1.equalPos(xLeft, yTop + 2) && free2.equalPos(xLeft + 1, yTop + 2))
						|| (free1.equalPos(xLeft + 1, yTop + 2) && free2.equalPos(xLeft, yTop + 2));
			}
		}
		return false;
	}

	@Override
	public void move(BoardPos free1, BoardPos free2, int toLeftX, int toTopY) {
		if (xLeft - toLeftX == 1) {
			// move 1 field left
			free1.x += 2;
			free2.x += 2;
		} else if (xLeft - toLeftX == -1) {
			// move 1 field right
			free1.x -= 2;
			free2.x -= 2;
		} else if (yTop - toTopY == 1) {
			// move 1 field up
			free1.y += 2;
			free2.y += 2;
		} else if (yTop - toTopY == -1) {
			// move 1 field down
			free1.y -= 2;
			free2.y -= 2;
		} else {
			throw new IllegalArgumentException("illegal move");
		}
		xLeft = toLeftX;
		yTop = toTopY;
	}

}
