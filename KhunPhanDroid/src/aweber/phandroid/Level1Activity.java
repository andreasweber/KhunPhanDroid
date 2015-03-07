package aweber.phandroid;

import aweber.phandroid.game.Board;
import aweber.phandroid.game.BoardPos;
import aweber.phandroid.game.Piece;
import aweber.phandroid.game.Piece11;
import aweber.phandroid.game.Piece12;
import aweber.phandroid.game.Piece21;
import aweber.phandroid.game.Piece22;

public class Level1Activity extends GameActivity {

	public static final int EXIT_RETURN_CODE = 4711;

	public static final String PROP_BEST = "best"; // property where to store best solution of Level 1

	private static final String PROP_MOVES = "moves"; // property where to store current moves of Level 1

	private Piece _piece11_1, _piece11_2, _piece11_3, _piece11_4, _piece12_1, _piece12_2, _piece12_3, _piece12_4,
			_piece21, _piece22;

	protected boolean containsPieceId(final int id) {
		if (id == R.id.Piece11_1 || id == R.id.Piece11_2 || id == R.id.Piece11_3 || id == R.id.Piece11_4
				|| id == R.id.Piece12_1 || id == R.id.Piece12_2 || id == R.id.Piece12_3 || id == R.id.Piece12_4
				|| id == R.id.Piece21 || id == R.id.Piece22) {
			return true;
		}
		return false;
	}

	@Override
	protected void onPause() {
		// save current positions
		_props.setProperty("x_piece11_1", String.valueOf(_piece11_1.xLeft));
		_props.setProperty("y_piece11_1", String.valueOf(_piece11_1.yTop));
		_props.setProperty("x_piece11_2", String.valueOf(_piece11_2.xLeft));
		_props.setProperty("y_piece11_2", String.valueOf(_piece11_2.yTop));
		_props.setProperty("x_piece11_3", String.valueOf(_piece11_3.xLeft));
		_props.setProperty("y_piece11_3", String.valueOf(_piece11_3.yTop));
		_props.setProperty("x_piece11_4", String.valueOf(_piece11_4.xLeft));
		_props.setProperty("y_piece11_4", String.valueOf(_piece11_4.yTop));
		_props.setProperty("x_piece12_1", String.valueOf(_piece12_1.xLeft));
		_props.setProperty("y_piece12_1", String.valueOf(_piece12_1.yTop));
		_props.setProperty("x_piece12_2", String.valueOf(_piece12_2.xLeft));
		_props.setProperty("y_piece12_2", String.valueOf(_piece12_2.yTop));
		_props.setProperty("x_piece12_3", String.valueOf(_piece12_3.xLeft));
		_props.setProperty("y_piece12_3", String.valueOf(_piece12_3.yTop));
		_props.setProperty("x_piece12_4", String.valueOf(_piece12_4.xLeft));
		_props.setProperty("y_piece12_4", String.valueOf(_piece12_4.yTop));
		_props.setProperty("x_piece21", String.valueOf(_piece21.xLeft));
		_props.setProperty("y_piece21", String.valueOf(_piece21.yTop));
		_props.setProperty("x_piece22", String.valueOf(_piece22.xLeft));
		_props.setProperty("y_piece22", String.valueOf(_piece22.yTop));

		_props.setProperty("x_free1", String.valueOf(_board._free1.x));
		_props.setProperty("y_free1", String.valueOf(_board._free1.y));
		_props.setProperty("x_free2", String.valueOf(_board._free2.x));
		_props.setProperty("y_free2", String.valueOf(_board._free2.y));

		_props.setProperty(PROP_MOVES, String.valueOf(_noOfMoves));
		super.onPause();
	}

	@Override
	protected void initBoard() {
		super.initBoard();

		_board = new Board(10, 3);

		// load current positions, use default if not stored in properties
		_piece11_1 = new Piece11(Integer.valueOf(_props.getProperty("x_piece11_1", "1")), Integer.valueOf(_props
				.getProperty("y_piece11_1", "3"))); // default pos (1,3)
		addPiece(R.id.Piece11_1, _piece11_1);
		_piece11_2 = new Piece11(Integer.valueOf(_props.getProperty("x_piece11_2", "1")), Integer.valueOf(_props
				.getProperty("y_piece11_2", "4"))); // default pos (1,4)
		addPiece(R.id.Piece11_2, _piece11_2);
		_piece11_3 = new Piece11(Integer.valueOf(_props.getProperty("x_piece11_3", "2")), Integer.valueOf(_props
				.getProperty("y_piece11_3", "3"))); // default pos (2,3)
		addPiece(R.id.Piece11_3, _piece11_3);
		_piece11_4 = new Piece11(Integer.valueOf(_props.getProperty("x_piece11_4", "2")), Integer.valueOf(_props
				.getProperty("y_piece11_4", "4"))); // default pos (2,4)
		addPiece(R.id.Piece11_4, _piece11_4);

		_piece12_1 = new Piece12(Integer.valueOf(_props.getProperty("x_piece12_1", "0")), Integer.valueOf(_props
				.getProperty("y_piece12_1", "0"))); // default pos (0,0)
		addPiece(R.id.Piece12_1, _piece12_1);
		_piece12_2 = new Piece12(Integer.valueOf(_props.getProperty("x_piece12_2", "0")), Integer.valueOf(_props
				.getProperty("y_piece12_2", "3"))); // default pos (0,3)
		addPiece(R.id.Piece12_2, _piece12_2);
		_piece12_3 = new Piece12(Integer.valueOf(_props.getProperty("x_piece12_3", "3")), Integer.valueOf(_props
				.getProperty("y_piece12_3", "0"))); // default pos (3,0)
		addPiece(R.id.Piece12_3, _piece12_3);
		_piece12_4 = new Piece12(Integer.valueOf(_props.getProperty("x_piece12_4", "3")), Integer.valueOf(_props
				.getProperty("y_piece12_4", "3"))); // default pos (3,3)
		addPiece(R.id.Piece12_4, _piece12_4);

		_piece21 = new Piece21(Integer.valueOf(_props.getProperty("x_piece21", "1")), Integer.valueOf(_props
				.getProperty("y_piece21", "2"))); // default pos (1,2)
		addPiece(R.id.Piece21, _piece21);
		_piece22 = new Piece22(Integer.valueOf(_props.getProperty("x_piece22", "1")), Integer.valueOf(_props
				.getProperty("y_piece22", "0"))); // default pos (1,0)
		addPiece(R.id.Piece22, _piece22);

		final BoardPos free1 = new BoardPos(Integer.valueOf(_props.getProperty("x_free1", "0")), Integer.valueOf(_props
				.getProperty("y_free1", "2"))); // default pos (0,2)
		final BoardPos free2 = new BoardPos(Integer.valueOf(_props.getProperty("x_free2", "3")), Integer.valueOf(_props
				.getProperty("y_free2", "2"))); // default pos (3,2)
		_board.setFreePos(free1, free2);
		// showFreePositions(); // for debugging

		if (!_props.containsKey(PROP_BEST)) {
			// property entry doesn't exist yet, create it
			_props.setProperty(PROP_BEST, "---");
			PropertiesHandler.saveProperties(this, _props);
		}
		showBestSolution();

		_noOfMoves = 0;
		if (_props.containsKey(PROP_MOVES)) {
			_noOfMoves = Integer.valueOf(_props.getProperty(PROP_MOVES));
		}
		showMoves();
	}

	@Override
	protected void reset() {
		_props.remove("x_piece11_1");
		_props.remove("y_piece11_1");
		_props.remove("x_piece11_2");
		_props.remove("y_piece11_2");
		_props.remove("x_piece11_3");
		_props.remove("y_piece11_3");
		_props.remove("x_piece11_4");
		_props.remove("y_piece11_4");
		_props.remove("x_piece12_1");
		_props.remove("y_piece12_1");
		_props.remove("x_piece12_2");
		_props.remove("y_piece12_2");
		_props.remove("x_piece12_3");
		_props.remove("y_piece12_3");
		_props.remove("x_piece12_4");
		_props.remove("y_piece12_4");
		_props.remove("x_piece21");
		_props.remove("y_piece21");
		_props.remove("x_piece22");
		_props.remove("y_piece22");

		_props.remove("x_free1");
		_props.remove("y_free1");
		_props.remove("x_free2");
		_props.remove("y_free2");

		_props.remove(PROP_MOVES);
		super.reset();
	}

	@Override
	protected void exit() {
		super.exit(EXIT_RETURN_CODE);
	}

	@Override
	protected int getContentView() {
		return R.layout.level1;
	}

	@Override
	protected int getInnerBoardLayout() {
		return R.id.InnerBoardLayout;
	}

	@Override
	protected int getOuterBoardLayout() {
		return R.id.OuterBoardLayout;
	}

	@Override
	protected int getBoardHeight() {
		return (int) getResources().getDimension(R.dimen.outer_height_l1);
	}

	@Override
	protected int getBoardFieldSize() {
		return (int) getResources().getDimension(R.dimen.piece1_l1);
	}

	@Override
	protected int getTxtMoves() {
		return R.id.txt_moves;
	}

	@Override
	protected int getTxtBestSolution() {
		return R.id.txt_best_solution;
	}

	@Override
	protected String getPropBestSolution() {
		return PROP_BEST;
	}
}
