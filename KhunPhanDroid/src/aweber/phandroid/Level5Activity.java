package aweber.phandroid;

import aweber.phandroid.game.Board;
import aweber.phandroid.game.BoardPos;
import aweber.phandroid.game.Piece;
import aweber.phandroid.game.Piece11;
import aweber.phandroid.game.Piece12;
import aweber.phandroid.game.Piece22;
import aweber.phandroid.game.Piece3lu;
import aweber.phandroid.game.Piece3ru;

// 		======
//	0	|1441|
//	1	|1442|
//	2	|1222|
//	3	|2222|
//	4	|2352|
//	5	|3355|
//	6	| 11 |
//		======
public class Level5Activity extends GameActivity {

	public static final int EXIT_RETURN_CODE = 4715;

	private static final String PROP_BEST = "best5"; // property where to store best solution of Level 5

	private static final String PROP_MOVES = "moves5"; // property where to store current moves of Level 5

	private Piece _piece11_1, _piece11_2, _piece11_3, _piece11_4, _piece11_5, _piece11_6, //
			_piece12_1, _piece12_2, _piece12_3, _piece12_4, _piece12_5, //
			_piece3ru, _piece3lu, // 
			_piece22;

	protected boolean containsPieceId(final int id) {
		if (id == R.id.L5Piece11_1 || id == R.id.L5Piece11_2 || id == R.id.L5Piece11_3 || id == R.id.L5Piece11_4
				|| id == R.id.L5Piece11_5 || id == R.id.L5Piece11_6 || id == R.id.L5Piece12_1 || id == R.id.L5Piece12_2
				|| id == R.id.L5Piece12_3 || id == R.id.L5Piece12_4 || id == R.id.L5Piece12_5 || id == R.id.L5Piece3ru
				|| id == R.id.L5Piece3lu || id == R.id.L5Piece22) {
			return true;
		}
		return false;
	}

	@Override
	protected void onPause() {
		// save current positions
		_props.setProperty("x_L5piece11_1", String.valueOf(_piece11_1.xLeft));
		_props.setProperty("y_L5piece11_1", String.valueOf(_piece11_1.yTop));
		_props.setProperty("x_L5piece11_2", String.valueOf(_piece11_2.xLeft));
		_props.setProperty("y_L5piece11_2", String.valueOf(_piece11_2.yTop));
		_props.setProperty("x_L5piece11_3", String.valueOf(_piece11_3.xLeft));
		_props.setProperty("y_L5piece11_3", String.valueOf(_piece11_3.yTop));
		_props.setProperty("x_L5piece11_4", String.valueOf(_piece11_4.xLeft));
		_props.setProperty("y_L5piece11_4", String.valueOf(_piece11_4.yTop));
		_props.setProperty("x_L5piece11_5", String.valueOf(_piece11_5.xLeft));
		_props.setProperty("y_L5piece11_5", String.valueOf(_piece11_5.yTop));
		_props.setProperty("x_L5piece11_6", String.valueOf(_piece11_6.xLeft));
		_props.setProperty("y_L5piece11_6", String.valueOf(_piece11_6.yTop));

		_props.setProperty("x_L5piece12_1", String.valueOf(_piece12_1.xLeft));
		_props.setProperty("y_L5piece12_1", String.valueOf(_piece12_1.yTop));
		_props.setProperty("x_L5piece12_2", String.valueOf(_piece12_2.xLeft));
		_props.setProperty("y_L5piece12_2", String.valueOf(_piece12_2.yTop));
		_props.setProperty("x_L5piece12_3", String.valueOf(_piece12_3.xLeft));
		_props.setProperty("y_L5piece12_3", String.valueOf(_piece12_3.yTop));
		_props.setProperty("x_L5piece12_4", String.valueOf(_piece12_4.xLeft));
		_props.setProperty("y_L5piece12_4", String.valueOf(_piece12_4.yTop));
		_props.setProperty("x_L5piece12_5", String.valueOf(_piece12_5.xLeft));
		_props.setProperty("y_L5piece12_5", String.valueOf(_piece12_5.yTop));
	
		_props.setProperty("x_L5piece3ru", String.valueOf(_piece3ru.xLeft));
		_props.setProperty("y_L5piece3ru", String.valueOf(_piece3ru.yTop));
		_props.setProperty("x_L5piece3lu", String.valueOf(_piece3lu.xLeft));
		_props.setProperty("y_L5piece3lu", String.valueOf(_piece3lu.yTop));
		
		_props.setProperty("x_L5piece22", String.valueOf(_piece22.xLeft));
		_props.setProperty("y_L5piece22", String.valueOf(_piece22.yTop));

		_props.setProperty("x_L5free1", String.valueOf(_board._free1.x));
		_props.setProperty("y_L5free1", String.valueOf(_board._free1.y));
		_props.setProperty("x_L5free2", String.valueOf(_board._free2.x));
		_props.setProperty("y_L5free2", String.valueOf(_board._free2.y));

		_props.setProperty(PROP_MOVES, String.valueOf(_noOfMoves));
		super.onPause();
	}

	@Override
	protected void initBoard() {
		super.initBoard();

		_board = new Board(14, 5);

		// load current positions, use default if not stored in properties
		_piece11_1 = new Piece11(Integer.valueOf(_props.getProperty("x_L5piece11_1", "0")), Integer.valueOf(_props
				.getProperty("y_L5piece11_1", "0"))); // default pos (0,0)
		addPiece(R.id.L5Piece11_1, _piece11_1);
		_piece11_2 = new Piece11(Integer.valueOf(_props.getProperty("x_L5piece11_2", "0")), Integer.valueOf(_props
				.getProperty("y_L5piece11_2", "1"))); // default pos (0,1)
		addPiece(R.id.L5Piece11_2, _piece11_2);
		_piece11_3 = new Piece11(Integer.valueOf(_props.getProperty("x_L5piece11_3", "0")), Integer.valueOf(_props
				.getProperty("y_L5piece11_3", "2"))); // default pos (0,2)
		addPiece(R.id.L5Piece11_3, _piece11_3);
		_piece11_4 = new Piece11(Integer.valueOf(_props.getProperty("x_L5piece11_4", "3")), Integer.valueOf(_props
				.getProperty("y_L5piece11_4", "0"))); // default pos (3,0)
		addPiece(R.id.L5Piece11_4, _piece11_4);
		_piece11_5 = new Piece11(Integer.valueOf(_props.getProperty("x_L5piece11_5", "1")), Integer.valueOf(_props
				.getProperty("y_L5piece11_5", "6"))); // default pos (1,6)
		addPiece(R.id.L5Piece11_5, _piece11_5);
		_piece11_6 = new Piece11(Integer.valueOf(_props.getProperty("x_L5piece11_6", "2")), Integer.valueOf(_props
				.getProperty("y_L5piece11_6", "6"))); // default pos (2,6)
		addPiece(R.id.L5Piece11_6, _piece11_6);

		_piece12_1 = new Piece12(Integer.valueOf(_props.getProperty("x_L5piece12_1", "0")), Integer.valueOf(_props
				.getProperty("y_L5piece12_1", "3"))); // default pos (0,3)
		addPiece(R.id.L5Piece12_1, _piece12_1);
		_piece12_2 = new Piece12(Integer.valueOf(_props.getProperty("x_L5piece12_2", "1")), Integer.valueOf(_props
				.getProperty("y_L5piece12_2", "2"))); // default pos (1,2)
		addPiece(R.id.L5Piece12_2, _piece12_2);
		_piece12_3 = new Piece12(Integer.valueOf(_props.getProperty("x_L5piece12_3", "2")), Integer.valueOf(_props
				.getProperty("y_L5piece12_3", "2"))); // default pos (2,2)
		addPiece(R.id.L5Piece12_3, _piece12_3);
		_piece12_4 = new Piece12(Integer.valueOf(_props.getProperty("x_L5piece12_4", "3")), Integer.valueOf(_props
				.getProperty("y_L5piece12_4", "1"))); // default pos (3,1)
		addPiece(R.id.L5Piece12_4, _piece12_4);
		_piece12_5 = new Piece12(Integer.valueOf(_props.getProperty("x_L5piece12_5", "3")), Integer.valueOf(_props
				.getProperty("y_L5piece12_5", "3"))); // default pos (3,3)
		addPiece(R.id.L5Piece12_5, _piece12_5);
	
		_piece3ru = new Piece3ru(Integer.valueOf(_props.getProperty("x_L5piece3ru", "0")), Integer.valueOf(_props
				.getProperty("y_L5piece3ru", "4"))); 
		addPiece(R.id.L5Piece3ru, _piece3ru);
		_piece3lu = new Piece3lu(Integer.valueOf(_props.getProperty("x_L5piece3lu", "2")), Integer.valueOf(_props
				.getProperty("y_L5piece3lu", "4"))); 
		addPiece(R.id.L5Piece3lu, _piece3lu);
	
		_piece22 = new Piece22(Integer.valueOf(_props.getProperty("x_L5piece22", "1")), Integer.valueOf(_props
				.getProperty("y_L5piece22", "0"))); // default pos (1,0)
		addPiece(R.id.L5Piece22, _piece22);

		final BoardPos free1 = new BoardPos(Integer.valueOf(_props.getProperty("x_L5free1", "0")),
				Integer.valueOf(_props.getProperty("y_L5free1", "6"))); // default pos (0,6)
		final BoardPos free2 = new BoardPos(Integer.valueOf(_props.getProperty("x_L5free2", "3")),
				Integer.valueOf(_props.getProperty("y_L5free2", "6"))); // default pos (3,6)
		_board.setFreePos(free1, free2);
		// showFreePositions(); // for debugging

		if (!_props.containsKey(PROP_BEST)) {
			// property entry doesn't exist yet, create it
			_props.setProperty(PROP_BEST, "---");
			saveProperties();
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
		_props.remove("x_L5piece11_1");
		_props.remove("y_L5piece11_1");
		_props.remove("x_L5piece11_2");
		_props.remove("y_L5piece11_2");
		_props.remove("x_L5piece11_3");
		_props.remove("y_L5piece11_3");
		_props.remove("x_L5piece11_4");
		_props.remove("y_L5piece11_4");
		_props.remove("x_L5piece11_5");
		_props.remove("y_L5piece11_5");
		_props.remove("x_L5piece11_6");
		_props.remove("y_L5piece11_6");
		
		_props.remove("x_L5piece12_1");
		_props.remove("y_L5piece12_1");
		_props.remove("x_L5piece12_2");
		_props.remove("y_L5piece12_2");
		_props.remove("x_L5piece12_3");
		_props.remove("y_L5piece12_3");
		_props.remove("x_L5piece12_4");
		_props.remove("y_L5piece12_4");
		_props.remove("x_L5piece12_5");
		_props.remove("y_L5piece12_5");

		_props.remove("x_L5piece3ru");
		_props.remove("y_L5piece3ru");
		_props.remove("x_L5piece3lu");
		_props.remove("y_L5piece3lu");
	
		_props.remove("x_L5piece22");
		_props.remove("y_L5piece22");

		_props.remove("x_L5free1");
		_props.remove("y_L5free1");
		_props.remove("x_L5free2");
		_props.remove("y_L5free2");

		_props.remove(PROP_MOVES);
		super.reset();
	}

	@Override
	protected void exit() {
		super.exit(EXIT_RETURN_CODE);
	}

	@Override
	protected int getContentView() {
		return R.layout.level5;
	}

	@Override
	protected int getInnerBoardLayout() {
		return R.id.InnerBoardLayout5;
	}

	@Override
	protected int getOuterBoardLayout() {
		return R.id.OuterBoardLayout5;
	}

	@Override
	protected int getBoardHeight() {
		return (int) getResources().getDimension(R.dimen.outer_height_l3);
	}

	@Override
	protected int getBoardFieldSize() {
		return (int) getResources().getDimension(R.dimen.piece1_l3);
	}

	@Override
	protected int getTxtMoves() {
		return R.id.txt_moves5;
	}

	@Override
	protected int getTxtBestSolution() {
		return R.id.txt_best_solution5;
	}

	@Override
	protected String getPropBestSolution() {
		return PROP_BEST;
	}

}
