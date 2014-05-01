package aweber.phandroid.game;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Board {

	// ---------------
	// 0/0 1/0 2/0 3/0
	// 0/1 1/1 2/1 3/1
	// 0/2 1/2 2/2 3/2
	// 0/3 1/3 2/3 3/3
	// 0/4 1/4 2/4 3/4
	// ----_______----

	public static final int X_MAX = 3;
	public static final int Y_MAX = 4;

	public BoardPos _free1, _free2;

	private Map<Integer, Piece> _pieceMap;

	public Board() {
		_pieceMap = new HashMap<Integer, Piece>(10);
	}

	public void setFreePos(BoardPos free1, BoardPos free2) {
		_free1 = free1;
		_free2 = free2;
	}

	public void addPiece(int id, Piece p) {
		_pieceMap.put(id, p);
	}

	public Collection<Piece> getPieces() {
		return _pieceMap.values();
	}

	/** @return 'true' if Piece can move at all. */
	public boolean canMove(int pieceId) {
		Piece p = _pieceMap.get(pieceId);
		return p.canMove(_free1, _free2);
	}
	
	/** @return 'true' if Piece can move horizontally. */
	public boolean canMoveX(int pieceId, int x, int y) {
		Piece p = _pieceMap.get(pieceId);
		return p.canMoveTo(_free1, _free2, x + 1, y) || p.canMoveTo(_free1, _free2, x - 1, y);
	}
	
	/** @return 'true' if Piece can move vertically. */
	public boolean canMoveY(int pieceId, int x, int y) {
		Piece p = _pieceMap.get(pieceId);
		return p.canMoveTo(_free1, _free2, x, y + 1) || p.canMoveTo(_free1, _free2, x, y - 1);
	}

	/**
	 * @param pieceId
	 * @param toX
	 *            Feld X ganz links wo Piece (nach dem Zug) beginnt
	 * @param toY
	 *            Feld Y ganz oben wo Piece (nach dem Zug) beginnt
	 */
	public boolean canMoveTo(int pieceId, int toX, int toY) {
		Piece p = _pieceMap.get(pieceId);
		return p.canMoveTo(_free1, _free2, toX, toY);
	}

	/**
	 * @param pieceId
	 * @param toX
	 *            Feld X ganz links wo Piece (nach dem Zug) beginnt
	 * @param toY
	 *            Feld Y ganz oben wo Piece (nach dem Zug) beginnt
	 */
	public void move(int pieceId, int toX, int toY) {
		Piece p = _pieceMap.get(pieceId);
		p.move(_free1, _free2, toX, toY);
	}

	public int getXPos(int pieceId) {
		Piece p = _pieceMap.get(pieceId);
		return p.xLeft;
	}

	public int getYPos(int pieceId) {
		Piece p = _pieceMap.get(pieceId);
		return p.yTop;
	}

	public String getFreePosAsString() {
		return "[" + _free1.x + "," + _free1.y + "] / [" + _free2.x + "," + _free2.y + "]";
	}

	public boolean isSolution() {
		for (Piece p : _pieceMap.values()) {
			if (p instanceof Piece22) {
				if (p.xLeft == 1 && p.yTop == 3) {
					return true;
				}
				break;
			}
		}
		return false;
	}
}
