package aweber.phandroid;

import android.os.Bundle;
import android.widget.TextView;

import aweber.phandroid.game.Board;
import aweber.phandroid.game.BoardPos;
import aweber.phandroid.game.Piece;
import aweber.phandroid.game.Piece11;
import aweber.phandroid.game.Piece12;
import aweber.phandroid.game.Piece21;
import aweber.phandroid.game.Piece22;

// 		======
//	0	|2442|
//	1	|2442|
//	2	|1221|
//	3	|1221|
//	4	|qqqq|
//	5	|2qq2|
//	6	|2qq2|
//	7	| qq |
//		======
public class Level2Activity extends GameActivity {

	public static final int EXIT_RETURN_CODE = 4712;

	public static final String PROP_BEST = "best2"; // property where to store best solution of Level 2

	private static final String PROP_MOVES = "moves2"; // property where to store current moves of Level 2

	private Piece _piece11_1, _piece11_2, _piece11_3, _piece11_4, //
			_piece12_1, _piece12_2, _piece12_3, _piece12_4, _piece12_5, _piece12_6, //
			_piece21_1, _piece21_2, _piece21_3, _piece21_4, _piece21_5, // 
			_piece22;

	protected boolean containsPieceId(final int id) {
		if (id == R.id.L2Piece11_1 || id == R.id.L2Piece11_2 || id == R.id.L2Piece11_3 || id == R.id.L2Piece11_4
				|| id == R.id.L2Piece12_1 || id == R.id.L2Piece12_2 || id == R.id.L2Piece12_3 || id == R.id.L2Piece12_4
				|| id == R.id.L2Piece12_5 || id == R.id.L2Piece12_6 || id == R.id.L2Piece21_1 || id == R.id.L2Piece21_2
				|| id == R.id.L2Piece21_3 || id == R.id.L2Piece21_4 || id == R.id.L2Piece21_5 || id == R.id.L2Piece22) {
			return true;
		}
		return false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((TextView)findViewById(R.id.txt_moves2)).setTypeface(Assets.ttf);
		((TextView)findViewById(R.id.txt_best_solution2)).setTypeface(Assets.ttf);
	}

	@Override
	protected void onPause() {
		// save current positions
		_props.setProperty("x_L2piece11_1", String.valueOf(_piece11_1.xLeft));
		_props.setProperty("y_L2piece11_1", String.valueOf(_piece11_1.yTop));
		_props.setProperty("x_L2piece11_2", String.valueOf(_piece11_2.xLeft));
		_props.setProperty("y_L2piece11_2", String.valueOf(_piece11_2.yTop));
		_props.setProperty("x_L2piece11_3", String.valueOf(_piece11_3.xLeft));
		_props.setProperty("y_L2piece11_3", String.valueOf(_piece11_3.yTop));
		_props.setProperty("x_L2piece11_4", String.valueOf(_piece11_4.xLeft));
		_props.setProperty("y_L2piece11_4", String.valueOf(_piece11_4.yTop));

		_props.setProperty("x_L2piece12_1", String.valueOf(_piece12_1.xLeft));
		_props.setProperty("y_L2piece12_1", String.valueOf(_piece12_1.yTop));
		_props.setProperty("x_L2piece12_2", String.valueOf(_piece12_2.xLeft));
		_props.setProperty("y_L2piece12_2", String.valueOf(_piece12_2.yTop));
		_props.setProperty("x_L2piece12_3", String.valueOf(_piece12_3.xLeft));
		_props.setProperty("y_L2piece12_3", String.valueOf(_piece12_3.yTop));
		_props.setProperty("x_L2piece12_4", String.valueOf(_piece12_4.xLeft));
		_props.setProperty("y_L2piece12_4", String.valueOf(_piece12_4.yTop));
		_props.setProperty("x_L2piece12_5", String.valueOf(_piece12_5.xLeft));
		_props.setProperty("y_L2piece12_5", String.valueOf(_piece12_5.yTop));
		_props.setProperty("x_L2piece12_6", String.valueOf(_piece12_6.xLeft));
		_props.setProperty("y_L2piece12_6", String.valueOf(_piece12_6.yTop));

		_props.setProperty("x_L2piece21_1", String.valueOf(_piece21_1.xLeft));
		_props.setProperty("y_L2piece21_1", String.valueOf(_piece21_1.yTop));
		_props.setProperty("x_L2piece21_2", String.valueOf(_piece21_2.xLeft));
		_props.setProperty("y_L2piece21_2", String.valueOf(_piece21_2.yTop));
		_props.setProperty("x_L2piece21_3", String.valueOf(_piece21_3.xLeft));
		_props.setProperty("y_L2piece21_3", String.valueOf(_piece21_3.yTop));
		_props.setProperty("x_L2piece21_4", String.valueOf(_piece21_4.xLeft));
		_props.setProperty("y_L2piece21_4", String.valueOf(_piece21_4.yTop));
		_props.setProperty("x_L2piece21_5", String.valueOf(_piece21_5.xLeft));
		_props.setProperty("y_L2piece21_5", String.valueOf(_piece21_5.yTop));

		_props.setProperty("x_L2piece22", String.valueOf(_piece22.xLeft));
		_props.setProperty("y_L2piece22", String.valueOf(_piece22.yTop));

		_props.setProperty("x_L2free1", String.valueOf(_board._free1.x));
		_props.setProperty("y_L2free1", String.valueOf(_board._free1.y));
		_props.setProperty("x_L2free2", String.valueOf(_board._free2.x));
		_props.setProperty("y_L2free2", String.valueOf(_board._free2.y));

		_props.setProperty(PROP_MOVES, String.valueOf(_noOfMoves));
		super.onPause();
	}

	@Override
	protected void initBoard() {
		super.initBoard();

		_board = new Board(16, 6); 

		// load current positions, use default if not stored in properties
		_piece11_1 = new Piece11(Integer.valueOf(_props.getProperty("x_L2piece11_1", "0")), Integer.valueOf(_props
				.getProperty("y_L2piece11_1", "2"))); // default pos (0,2)
		addPiece(R.id.L2Piece11_1, _piece11_1);
		_piece11_2 = new Piece11(Integer.valueOf(_props.getProperty("x_L2piece11_2", "0")), Integer.valueOf(_props
				.getProperty("y_L2piece11_2", "3"))); // default pos (0,3)
		addPiece(R.id.L2Piece11_2, _piece11_2);
		_piece11_3 = new Piece11(Integer.valueOf(_props.getProperty("x_L2piece11_3", "3")), Integer.valueOf(_props
				.getProperty("y_L2piece11_3", "2"))); // default pos (3,2)
		addPiece(R.id.L2Piece11_3, _piece11_3);
		_piece11_4 = new Piece11(Integer.valueOf(_props.getProperty("x_L2piece11_4", "3")), Integer.valueOf(_props
				.getProperty("y_L2piece11_4", "3"))); // default pos (3,3)
		addPiece(R.id.L2Piece11_4, _piece11_4);

		_piece12_1 = new Piece12(Integer.valueOf(_props.getProperty("x_L2piece12_1", "0")), Integer.valueOf(_props
				.getProperty("y_L2piece12_1", "0"))); // default pos (0,0)
		addPiece(R.id.L2Piece12_1, _piece12_1);
		_piece12_2 = new Piece12(Integer.valueOf(_props.getProperty("x_L2piece12_2", "1")), Integer.valueOf(_props
				.getProperty("y_L2piece12_2", "2"))); // default pos (1,2)
		addPiece(R.id.L2Piece12_2, _piece12_2);
		_piece12_3 = new Piece12(Integer.valueOf(_props.getProperty("x_L2piece12_3", "3")), Integer.valueOf(_props
				.getProperty("y_L2piece12_3", "0"))); // default pos (3,0)
		addPiece(R.id.L2Piece12_3, _piece12_3);
		_piece12_4 = new Piece12(Integer.valueOf(_props.getProperty("x_L2piece12_4", "2")), Integer.valueOf(_props
				.getProperty("y_L2piece12_4", "2"))); // default pos (2,2)
		addPiece(R.id.L2Piece12_4, _piece12_4);
		_piece12_5 = new Piece12(Integer.valueOf(_props.getProperty("x_L2piece12_5", "0")), Integer.valueOf(_props
				.getProperty("y_L2piece12_5", "5"))); // default pos (0,5)
		addPiece(R.id.L2Piece12_5, _piece12_5);
		_piece12_6 = new Piece12(Integer.valueOf(_props.getProperty("x_L2piece12_6", "3")), Integer.valueOf(_props
				.getProperty("y_L2piece12_6", "5"))); // default pos (3,5)
		addPiece(R.id.L2Piece12_6, _piece12_6);

		_piece21_1 = new Piece21(Integer.valueOf(_props.getProperty("x_L2piece21_1", "0")), Integer.valueOf(_props
				.getProperty("y_L2piece21_1", "4"))); // default pos (0,4)
		addPiece(R.id.L2Piece21_1, _piece21_1);
		_piece21_2 = new Piece21(Integer.valueOf(_props.getProperty("x_L2piece21_2", "2")), Integer.valueOf(_props
				.getProperty("y_L2piece21_2", "4"))); // default pos (2,4)
		addPiece(R.id.L2Piece21_2, _piece21_2);
		_piece21_3 = new Piece21(Integer.valueOf(_props.getProperty("x_L2piece21_3", "1")), Integer.valueOf(_props
				.getProperty("y_L2piece21_3", "5"))); // default pos (1,5)
		addPiece(R.id.L2Piece21_3, _piece21_3);
		_piece21_4 = new Piece21(Integer.valueOf(_props.getProperty("x_L2piece21_4", "1")), Integer.valueOf(_props
				.getProperty("y_L2piece21_4", "6"))); // default pos (1,6)
		addPiece(R.id.L2Piece21_4, _piece21_4);
		_piece21_5 = new Piece21(Integer.valueOf(_props.getProperty("x_L2piece21_5", "1")), Integer.valueOf(_props
				.getProperty("y_L2piece21_5", "7"))); // default pos (1,7)
		addPiece(R.id.L2Piece21_5, _piece21_5);
		
		_piece22 = new Piece22(Integer.valueOf(_props.getProperty("x_L2piece22", "1")), Integer.valueOf(_props
				.getProperty("y_L2piece22", "0"))); // default pos (1,0)
		addPiece(R.id.L2Piece22, _piece22);

		final BoardPos free1 = new BoardPos(Integer.valueOf(_props.getProperty("x_L2free1", "0")),
				Integer.valueOf(_props.getProperty("y_L2free1", "7"))); // default pos (0,7)
		final BoardPos free2 = new BoardPos(Integer.valueOf(_props.getProperty("x_L2free2", "3")),
				Integer.valueOf(_props.getProperty("y_L2free2", "7"))); // default pos (3,7)
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
		_props.remove("x_L2piece11_1");
		_props.remove("y_L2piece11_1");
		_props.remove("x_L2piece11_2");
		_props.remove("y_L2piece11_2");
		_props.remove("x_L2piece11_3");
		_props.remove("y_L2piece11_3");
		_props.remove("x_L2piece11_4");
		_props.remove("y_L2piece11_4");
		
		_props.remove("x_L2piece12_1");
		_props.remove("y_L2piece12_1");
		_props.remove("x_L2piece12_2");
		_props.remove("y_L2piece12_2");
		_props.remove("x_L2piece12_3");
		_props.remove("y_L2piece12_3");
		_props.remove("x_L2piece12_4");
		_props.remove("y_L2piece12_4");
		_props.remove("x_L2piece12_5");
		_props.remove("y_L2piece12_5");
		_props.remove("x_L2piece12_6");
		_props.remove("y_L2piece12_6");
		
		_props.remove("x_L2piece21_1");
		_props.remove("y_L2piece21_1");
		_props.remove("x_L2piece21_2");
		_props.remove("y_L2piece21_2");
		_props.remove("x_L2piece21_3");
		_props.remove("y_L2piece21_3");
		_props.remove("x_L2piece21_4");
		_props.remove("y_L2piece21_4");
		_props.remove("x_L2piece21_5");
		_props.remove("y_L2piece21_5");
		
		_props.remove("x_L2piece22");
		_props.remove("y_L2piece22");

		_props.remove("x_L2free1");
		_props.remove("y_L2free1");
		_props.remove("x_L2free2");
		_props.remove("y_L2free2");

		_props.remove(PROP_MOVES);
		super.reset();
	}

	@Override
	protected void exit() {
		super.exit(EXIT_RETURN_CODE);
	}

	@Override
	protected int getContentView() {
		return R.layout.level2;
	}

	@Override
	protected int getInnerBoardLayout() {
		return R.id.InnerBoardLayout2;
	}

	@Override
	protected int getOuterBoardLayout() {
		return R.id.OuterBoardLayout2;
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
		return R.id.txt_moves2;
	}

	@Override
	protected int getTxtBestSolution() {
		return R.id.txt_best_solution2;
	}

	@Override
	protected String getPropBestSolution() {
		return PROP_BEST;
	}

}
