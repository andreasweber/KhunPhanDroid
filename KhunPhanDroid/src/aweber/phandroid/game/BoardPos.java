package aweber.phandroid.game;

public class BoardPos {
	public int x;
	public int y;

	public BoardPos(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public boolean equalPos(int x, int y) {
		return (this.x == x) && (this.y == y);
	}

	@Override
	public String toString() {
		return "x:" + x + ", y:" + y;
	}
}
