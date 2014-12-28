package aweber.phandroid;

import aweber.phandroid.game.Board;
import aweber.phandroid.game.BoardPos;
import aweber.phandroid.game.Piece;
import aweber.phandroid.game.Piece11;
import aweber.phandroid.game.Piece12;
import aweber.phandroid.game.Piece21;
import aweber.phandroid.game.Piece22;
import aweber.phandroid.game.Piece3lo;
import aweber.phandroid.game.Piece3lu;
import aweber.phandroid.game.Piece3ro;
import aweber.phandroid.game.Piece3ru;

// 0 |244 |
// 1 |244 |
// 2 |1177|
// 3 |qq67|
// 4 |2166|
// 5 |2133|
// 6 |2135|
// 7 |2155|
public class Level4Activity extends GameActivity {

	public static final int EXIT_RETURN_CODE = 4714;

	private static final String PROP_BEST = "best4"; // property where to store best solution of Level 4

	private static final String PROP_MOVES = "moves4"; // property where to store current moves of Level 4

	private Piece _piece11_1, _piece11_2, _piece11_3, _piece11_4, _piece11_5, _piece11_6, //
			_piece12_1, _piece12_2, _piece12_3, //
			_piece21_1, // 
			_piece3lu, _piece3lo, _piece3ru, _piece3ro, // 
			_piece22;

	protected boolean containsPieceId(final int id) {
		if (id == R.id.L4Piece11_1 || id == R.id.L4Piece11_2 || id == R.id.L4Piece11_3 || id == R.id.L4Piece11_4
				|| id == R.id.L4Piece11_5 || id == R.id.L4Piece11_6 || id == R.id.L4Piece12_1 || id == R.id.L4Piece12_2
				|| id == R.id.L4Piece12_3 || id == R.id.L4Piece21_1 || id == R.id.L4Piece3lo || id == R.id.L4Piece3lu
				|| id == R.id.L4Piece3ru || id == R.id.L4Piece3ro || id == R.id.L4Piece22) {
			return true;
		}
		return false;
	}

	@Override
	protected void onPause() {
		// save current positions
		_props.setProperty("x_L4piece11_1", String.valueOf(_piece11_1.xLeft));
		_props.setProperty("y_L4piece11_1", String.valueOf(_piece11_1.yTop));
		_props.setProperty("x_L4piece11_2", String.valueOf(_piece11_2.xLeft));
		_props.setProperty("y_L4piece11_2", String.valueOf(_piece11_2.yTop));
		_props.setProperty("x_L4piece11_3", String.valueOf(_piece11_3.xLeft));
		_props.setProperty("y_L4piece11_3", String.valueOf(_piece11_3.yTop));
		_props.setProperty("x_L4piece11_4", String.valueOf(_piece11_4.xLeft));
		_props.setProperty("y_L4piece11_4", String.valueOf(_piece11_4.yTop));
		_props.setProperty("x_L4piece11_5", String.valueOf(_piece11_5.xLeft));
		_props.setProperty("y_L4piece11_5", String.valueOf(_piece11_5.yTop));
		_props.setProperty("x_L4piece11_6", String.valueOf(_piece11_6.xLeft));
		_props.setProperty("y_L4piece11_6", String.valueOf(_piece11_6.yTop));

		_props.setProperty("x_L4piece12_1", String.valueOf(_piece12_1.xLeft));
		_props.setProperty("y_L4piece12_1", String.valueOf(_piece12_1.yTop));
		_props.setProperty("x_L4piece12_2", String.valueOf(_piece12_2.xLeft));
		_props.setProperty("y_L4piece12_2", String.valueOf(_piece12_2.yTop));
		_props.setProperty("x_L4piece12_3", String.valueOf(_piece12_3.xLeft));
		_props.setProperty("y_L4piece12_3", String.valueOf(_piece12_3.yTop));
		
		_props.setProperty("x_L4piece21_1", String.valueOf(_piece21_1.xLeft));
		_props.setProperty("y_L4piece21_1", String.valueOf(_piece21_1.yTop));
		
		_props.setProperty("x_L4piece3lo", String.valueOf(_piece3lo.xLeft));
		_props.setProperty("y_L4piece3lo", String.valueOf(_piece3lo.yTop));
		_props.setProperty("x_L4piece3lu", String.valueOf(_piece3lu.xLeft));
		_props.setProperty("y_L4piece3lu", String.valueOf(_piece3lu.yTop));
		_props.setProperty("x_L4piece3ro", String.valueOf(_piece3ro.xLeft));
		_props.setProperty("y_L4piece3ro", String.valueOf(_piece3ro.yTop));
		_props.setProperty("x_L4piece3ru", String.valueOf(_piece3ru.xLeft));
		_props.setProperty("y_L4piece3ru", String.valueOf(_piece3ru.yTop));

		_props.setProperty("x_L4piece22", String.valueOf(_piece22.xLeft));
		_props.setProperty("y_L4piece22", String.valueOf(_piece22.yTop));

		_props.setProperty("x_L4free1", String.valueOf(_board._free1.x));
		_props.setProperty("y_L4free1", String.valueOf(_board._free1.y));
		_props.setProperty("x_L4free2", String.valueOf(_board._free2.x));
		_props.setProperty("y_L4free2", String.valueOf(_board._free2.y));

		_props.setProperty(PROP_MOVES, String.valueOf(_noOfMoves));
		super.onPause();
	}

	@Override
	protected void initBoard() {
		super.initBoard();

		_board = new Board(16, 6); 

		// load current positions, use default if not stored in properties
		_piece11_1 = new Piece11(Integer.valueOf(_props.getProperty("x_L4piece11_1", "0")), Integer.valueOf(_props
				.getProperty("y_L4piece11_1", "2"))); // default pos (0,2)
		addPiece(R.id.L4Piece11_1, _piece11_1);
		_piece11_2 = new Piece11(Integer.valueOf(_props.getProperty("x_L4piece11_2", "1")), Integer.valueOf(_props
				.getProperty("y_L4piece11_2", "2"))); // default pos (1,2)
		addPiece(R.id.L4Piece11_2, _piece11_2);
		_piece11_3 = new Piece11(Integer.valueOf(_props.getProperty("x_L4piece11_3", "1")), Integer.valueOf(_props
				.getProperty("y_L4piece11_3", "4"))); // default pos (1,4)
		addPiece(R.id.L4Piece11_3, _piece11_3);
		_piece11_4 = new Piece11(Integer.valueOf(_props.getProperty("x_L4piece11_4", "1")), Integer.valueOf(_props
				.getProperty("y_L4piece11_4", "5"))); // default pos (1,5)
		addPiece(R.id.L4Piece11_4, _piece11_4);
		_piece11_5 = new Piece11(Integer.valueOf(_props.getProperty("x_L4piece11_5", "1")), Integer.valueOf(_props
				.getProperty("y_L4piece11_5", "6"))); // default pos (1,6)
		addPiece(R.id.L4Piece11_5, _piece11_5);
		_piece11_6 = new Piece11(Integer.valueOf(_props.getProperty("x_L4piece11_6", "1")), Integer.valueOf(_props
				.getProperty("y_L4piece11_6", "7"))); // default pos (1,7)
		addPiece(R.id.L4Piece11_6, _piece11_6);

		_piece12_1 = new Piece12(Integer.valueOf(_props.getProperty("x_L4piece12_1", "0")), Integer.valueOf(_props
				.getProperty("y_L4piece12_1", "0"))); // default pos (0,0)
		addPiece(R.id.L4Piece12_1, _piece12_1);
		_piece12_2 = new Piece12(Integer.valueOf(_props.getProperty("x_L4piece12_2", "0")), Integer.valueOf(_props
				.getProperty("y_L4piece12_2", "4"))); // default pos (0,4)
		addPiece(R.id.L4Piece12_2, _piece12_2);
		_piece12_3 = new Piece12(Integer.valueOf(_props.getProperty("x_L4piece12_3", "0")), Integer.valueOf(_props
				.getProperty("y_L4piece12_3", "6"))); // default pos (0,6)
		addPiece(R.id.L4Piece12_3, _piece12_3);
	
		_piece21_1 = new Piece21(Integer.valueOf(_props.getProperty("x_L4piece21_1", "0")), Integer.valueOf(_props
				.getProperty("y_L4piece21_1", "3"))); // default pos (0,3)
		addPiece(R.id.L4Piece21_1, _piece21_1);
	
		_piece3lo = new Piece3lo(Integer.valueOf(_props.getProperty("x_L4piece3lo", "2")), Integer.valueOf(_props
				.getProperty("y_L4piece3lo", "5"))); // default pos (2,5)
		addPiece(R.id.L4Piece3lo, _piece3lo);
		_piece3lu = new Piece3lu(Integer.valueOf(_props.getProperty("x_L4piece3lu", "2")), Integer.valueOf(_props
				.getProperty("y_L4piece3lu", "3"))); // default pos (2,3)
		addPiece(R.id.L4Piece3lu, _piece3lu);
		_piece3ro = new Piece3ro(Integer.valueOf(_props.getProperty("x_L4piece3ro", "2")), Integer.valueOf(_props
				.getProperty("y_L4piece3ro", "2"))); // default pos (2,2)
		addPiece(R.id.L4Piece3ro, _piece3ro);
		_piece3ru = new Piece3ru(Integer.valueOf(_props.getProperty("x_L4piece3ru", "2")), Integer.valueOf(_props
				.getProperty("y_L4piece3ru", "6"))); // default pos (2,6)
		addPiece(R.id.L4Piece3ru, _piece3ru);

		_piece22 = new Piece22(Integer.valueOf(_props.getProperty("x_L4piece22", "1")), Integer.valueOf(_props
				.getProperty("y_L4piece22", "0"))); // default pos (1,0)
		addPiece(R.id.L4Piece22, _piece22);

		final BoardPos free1 = new BoardPos(Integer.valueOf(_props.getProperty("x_L4free1", "3")),
				Integer.valueOf(_props.getProperty("y_L4free1", "0"))); // default pos (3,0)
		final BoardPos free2 = new BoardPos(Integer.valueOf(_props.getProperty("x_L4free2", "3")),
				Integer.valueOf(_props.getProperty("y_L4free2", "1"))); // default pos (3,1)
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
		_props.remove("x_L4piece11_1");
		_props.remove("y_L4piece11_1");
		_props.remove("x_L4piece11_2");
		_props.remove("y_L4piece11_2");
		_props.remove("x_L4piece11_3");
		_props.remove("y_L4piece11_3");
		_props.remove("x_L4piece11_4");
		_props.remove("y_L4piece11_4");
		_props.remove("x_L4piece11_5");
		_props.remove("y_L4piece11_5");
		_props.remove("x_L4piece11_6");
		_props.remove("y_L4piece11_6");

		_props.remove("x_L4piece12_1");
		_props.remove("y_L4piece12_1");
		_props.remove("x_L4piece12_2");
		_props.remove("y_L4piece12_2");
		_props.remove("x_L4piece12_3");
		_props.remove("y_L4piece12_3");
		
		_props.remove("x_L4piece21_1");
		_props.remove("y_L4piece21_1");
		
		_props.remove("x_L4piece3lu");
		_props.remove("y_L4piece3lu");
		_props.remove("x_L4piece3lo");
		_props.remove("y_L4piece3lo");
		_props.remove("x_L4piece3ru");
		_props.remove("y_L4piece3ru");
		_props.remove("x_L4piece3ro");
		_props.remove("y_L4piece3ro");

		_props.remove("x_L4piece22");
		_props.remove("y_L4piece22");

		_props.remove("x_L4free1");
		_props.remove("y_L4free1");
		_props.remove("x_L4free2");
		_props.remove("y_L4free2");

		_props.remove(PROP_MOVES);
		super.reset();
	}

	@Override
	protected void exit() {
		super.exit(EXIT_RETURN_CODE);
	}

	@Override
	protected int getContentView() {
		return R.layout.level4;
	}

	@Override
	protected int getInnerBoardLayout() {
		return R.id.InnerBoardLayout4;
	}

	@Override
	protected int getOuterBoardLayout() {
		return R.id.OuterBoardLayout4;
	}

	@Override
	protected int getBoardHeight() {
		return (int) getResources().getDimension(R.dimen.outer_height_l2);
	}

	@Override
	protected int getBoardFieldSize() {
		return (int) getResources().getDimension(R.dimen.piece1_l2);
	}

	@Override
	protected int getTxtMoves() {
		return R.id.txt_moves4;
	}

	@Override
	protected int getTxtBestSolution() {
		return R.id.txt_best_solution4;
	}

	@Override
	protected String getPropBestSolution() {
		return PROP_BEST;
	}

}
