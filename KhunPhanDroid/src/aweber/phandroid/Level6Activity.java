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
import aweber.phandroid.game.Piece3lo;
import aweber.phandroid.game.Piece3lu;
import aweber.phandroid.game.Piece3ro;
import aweber.phandroid.game.Piece3ru;

//  ======
//0 |1441|
//1 |1441|
//2 |3377|
//3 |3  7|
//4 |1211|
//5 |6215|
//6 |6655|
//7 |qqqq|
//  ======
public class Level6Activity extends GameActivity {

	public static final int EXIT_RETURN_CODE = 4716;

	public static final String PROP_BEST = "best6"; // property where to store best solution of Level 6

	private static final String PROP_MOVES = "moves6"; // property where to store current moves of Level 6

	private Piece _piece11_1, _piece11_2, _piece11_3, _piece11_4, _piece11_5, _piece11_6, _piece11_7, _piece11_8,//
			_piece12_1, //
			_piece21_1, _piece21_2, // 
			_piece3lu, _piece3lo, _piece3ru, _piece3ro, // 
			_piece22;

	protected boolean containsPieceId(final int id) {
		if (id == R.id.L6Piece11_1 || id == R.id.L6Piece11_2 || id == R.id.L6Piece11_3 || id == R.id.L6Piece11_4
				|| id == R.id.L6Piece11_5 || id == R.id.L6Piece11_6 || id == R.id.L6Piece11_7 || id == R.id.L6Piece11_8
				|| id == R.id.L6Piece12_1 || id == R.id.L6Piece21_1 || id == R.id.L6Piece21_2 || id == R.id.L6Piece3lo
				|| id == R.id.L6Piece3lu || id == R.id.L6Piece3ru || id == R.id.L6Piece3ro || id == R.id.L6Piece22) {
			return true;
		}
		return false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((TextView)findViewById(R.id.txt_moves6)).setTypeface(Assets.ttf);
		((TextView)findViewById(R.id.txt_best_solution6)).setTypeface(Assets.ttf);
	}

	@Override
	protected void onPause() {
		// save current positions
		_props.setProperty("x_L6piece11_1", String.valueOf(_piece11_1.xLeft));
		_props.setProperty("y_L6piece11_1", String.valueOf(_piece11_1.yTop));
		_props.setProperty("x_L6piece11_2", String.valueOf(_piece11_2.xLeft));
		_props.setProperty("y_L6piece11_2", String.valueOf(_piece11_2.yTop));
		_props.setProperty("x_L6piece11_3", String.valueOf(_piece11_3.xLeft));
		_props.setProperty("y_L6piece11_3", String.valueOf(_piece11_3.yTop));
		_props.setProperty("x_L6piece11_4", String.valueOf(_piece11_4.xLeft));
		_props.setProperty("y_L6piece11_4", String.valueOf(_piece11_4.yTop));
		_props.setProperty("x_L6piece11_5", String.valueOf(_piece11_5.xLeft));
		_props.setProperty("y_L6piece11_5", String.valueOf(_piece11_5.yTop));
		_props.setProperty("x_L6piece11_6", String.valueOf(_piece11_6.xLeft));
		_props.setProperty("y_L6piece11_6", String.valueOf(_piece11_6.yTop));
		_props.setProperty("x_L6piece11_7", String.valueOf(_piece11_7.xLeft));
		_props.setProperty("y_L6piece11_7", String.valueOf(_piece11_7.yTop));
		_props.setProperty("x_L6piece11_8", String.valueOf(_piece11_8.xLeft));
		_props.setProperty("y_L6piece11_8", String.valueOf(_piece11_8.yTop));
		
		_props.setProperty("x_L6piece12_1", String.valueOf(_piece12_1.xLeft));
		_props.setProperty("y_L6piece12_1", String.valueOf(_piece12_1.yTop));

		_props.setProperty("x_L6piece21_1", String.valueOf(_piece21_1.xLeft));
		_props.setProperty("y_L6piece21_1", String.valueOf(_piece21_1.yTop));
		_props.setProperty("x_L6piece21_2", String.valueOf(_piece21_2.xLeft));
		_props.setProperty("y_L6piece21_2", String.valueOf(_piece21_2.yTop));
		
		_props.setProperty("x_L6piece3lo", String.valueOf(_piece3lo.xLeft));
		_props.setProperty("y_L6piece3lo", String.valueOf(_piece3lo.yTop));
		_props.setProperty("x_L6piece3lu", String.valueOf(_piece3lu.xLeft));
		_props.setProperty("y_L6piece3lu", String.valueOf(_piece3lu.yTop));
		_props.setProperty("x_L6piece3ro", String.valueOf(_piece3ro.xLeft));
		_props.setProperty("y_L6piece3ro", String.valueOf(_piece3ro.yTop));
		_props.setProperty("x_L6piece3ru", String.valueOf(_piece3ru.xLeft));
		_props.setProperty("y_L6piece3ru", String.valueOf(_piece3ru.yTop));

		_props.setProperty("x_L6piece22", String.valueOf(_piece22.xLeft));
		_props.setProperty("y_L6piece22", String.valueOf(_piece22.yTop));

		_props.setProperty("x_L6free1", String.valueOf(_board._free1.x));
		_props.setProperty("y_L6free1", String.valueOf(_board._free1.y));
		_props.setProperty("x_L6free2", String.valueOf(_board._free2.x));
		_props.setProperty("y_L6free2", String.valueOf(_board._free2.y));

		_props.setProperty(PROP_MOVES, String.valueOf(_noOfMoves));
		super.onPause();
	}

	@Override
	protected void initBoard() {
		super.initBoard();

		_board = new Board(16, 6);

		// load current positions, use default if not stored in properties

		_piece3lo = new Piece3lo(Integer.valueOf(_props.getProperty("x_L6piece3lo", "0")), Integer.valueOf(_props
				.getProperty("y_L6piece3lo", "2"))); // default pos (0,2)
		addPiece(R.id.L6Piece3lo, _piece3lo);
		_piece3lu = new Piece3lu(Integer.valueOf(_props.getProperty("x_L6piece3lu", "0")), Integer.valueOf(_props
				.getProperty("y_L6piece3lu", "5"))); // default pos (0,5)
		addPiece(R.id.L6Piece3lu, _piece3lu);
		_piece3ro = new Piece3ro(Integer.valueOf(_props.getProperty("x_L6piece3ro", "2")), Integer.valueOf(_props
				.getProperty("y_L6piece3ro", "2"))); // default pos (2,2)
		addPiece(R.id.L6Piece3ro, _piece3ro);
		_piece3ru = new Piece3ru(Integer.valueOf(_props.getProperty("x_L6piece3ru", "2")), Integer.valueOf(_props
				.getProperty("y_L6piece3ru", "5"))); // default pos (2,5)
		addPiece(R.id.L6Piece3ru, _piece3ru);

		_piece11_1 = new Piece11(Integer.valueOf(_props.getProperty("x_L6piece11_1", "0")), Integer.valueOf(_props
				.getProperty("y_L6piece11_1", "0"))); // default pos (0,0)
		addPiece(R.id.L6Piece11_1, _piece11_1);
		_piece11_2 = new Piece11(Integer.valueOf(_props.getProperty("x_L6piece11_2", "0")), Integer.valueOf(_props
				.getProperty("y_L6piece11_2", "1"))); // default pos (0,1)
		addPiece(R.id.L6Piece11_2, _piece11_2);
		_piece11_3 = new Piece11(Integer.valueOf(_props.getProperty("x_L6piece11_3", "3")), Integer.valueOf(_props
				.getProperty("y_L6piece11_3", "0"))); // default pos (3,0)
		addPiece(R.id.L6Piece11_3, _piece11_3);
		_piece11_4 = new Piece11(Integer.valueOf(_props.getProperty("x_L6piece11_4", "3")), Integer.valueOf(_props
				.getProperty("y_L6piece11_4", "1"))); // default pos (3,1)
		addPiece(R.id.L6Piece11_4, _piece11_4);
		_piece11_5 = new Piece11(Integer.valueOf(_props.getProperty("x_L6piece11_5", "0")), Integer.valueOf(_props
				.getProperty("y_L6piece11_5", "4"))); // default pos (0,4)
		addPiece(R.id.L6Piece11_5, _piece11_5);
		_piece11_6 = new Piece11(Integer.valueOf(_props.getProperty("x_L6piece11_6", "2")), Integer.valueOf(_props
				.getProperty("y_L6piece11_6", "4"))); // default pos (2,4)
		addPiece(R.id.L6Piece11_6, _piece11_6);
		_piece11_7 = new Piece11(Integer.valueOf(_props.getProperty("x_L6piece11_7", "3")), Integer.valueOf(_props
				.getProperty("y_L6piece11_7", "4"))); // default pos (3,4)
		addPiece(R.id.L6Piece11_7, _piece11_7);
		_piece11_8 = new Piece11(Integer.valueOf(_props.getProperty("x_L6piece11_8", "2")), Integer.valueOf(_props
				.getProperty("y_L6piece11_8", "5"))); // default pos (2,5)
		addPiece(R.id.L6Piece11_8, _piece11_8);
		
		_piece12_1 = new Piece12(Integer.valueOf(_props.getProperty("x_L6piece12_1", "1")), Integer.valueOf(_props
				.getProperty("y_L6piece12_1", "4"))); // default pos (1,4)
		addPiece(R.id.L6Piece12_1, _piece12_1);
		
		_piece21_1 = new Piece21(Integer.valueOf(_props.getProperty("x_L6piece21_1", "0")), Integer.valueOf(_props
				.getProperty("y_L6piece21_1", "7"))); // default pos (0,7)
		addPiece(R.id.L6Piece21_1, _piece21_1);
		_piece21_2 = new Piece21(Integer.valueOf(_props.getProperty("x_L6piece21_2", "2")), Integer.valueOf(_props
				.getProperty("y_L6piece21_2", "7"))); // default pos (2,7)
		addPiece(R.id.L6Piece21_2, _piece21_2);

		_piece22 = new Piece22(Integer.valueOf(_props.getProperty("x_L6piece22", "1")), Integer.valueOf(_props
				.getProperty("y_L6piece22", "0"))); // default pos (1,0)
		addPiece(R.id.L6Piece22, _piece22);

		final BoardPos free1 = new BoardPos(Integer.valueOf(_props.getProperty("x_L6free1", "1")),
				Integer.valueOf(_props.getProperty("y_L6free1", "3"))); // default pos (1,3)
		final BoardPos free2 = new BoardPos(Integer.valueOf(_props.getProperty("x_L6free2", "2")),
				Integer.valueOf(_props.getProperty("y_L6free2", "3"))); // default pos (2,3)
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
		_props.remove("x_L6piece11_1");
		_props.remove("y_L6piece11_1");
		_props.remove("x_L6piece11_2");
		_props.remove("y_L6piece11_2");
		_props.remove("x_L6piece11_3");
		_props.remove("y_L6piece11_3");
		_props.remove("x_L6piece11_4");
		_props.remove("y_L6piece11_4");
		_props.remove("x_L6piece11_5");
		_props.remove("y_L6piece11_5");
		_props.remove("x_L6piece11_6");
		_props.remove("y_L6piece11_6");
		_props.remove("x_L6piece11_7");
		_props.remove("y_L6piece11_7");
		_props.remove("x_L6piece11_8");
		_props.remove("y_L6piece11_8");

		_props.remove("x_L6piece12_1");
		_props.remove("y_L6piece12_1");
		
		_props.remove("x_L6piece21_1");
		_props.remove("y_L6piece21_1");
		_props.remove("x_L6piece21_2");
		_props.remove("y_L6piece21_2");
		
		_props.remove("x_L6piece3lu");
		_props.remove("y_L6piece3lu");
		_props.remove("x_L6piece3lo");
		_props.remove("y_L6piece3lo");
		_props.remove("x_L6piece3ru");
		_props.remove("y_L6piece3ru");
		_props.remove("x_L6piece3ro");
		_props.remove("y_L6piece3ro");

		_props.remove("x_L6piece22");
		_props.remove("y_L6piece22");

		_props.remove("x_L6free1");
		_props.remove("y_L6free1");
		_props.remove("x_L6free2");
		_props.remove("y_L6free2");

		_props.remove(PROP_MOVES);
		super.reset();
	}

	@Override
	protected void exit() {
		super.exit(EXIT_RETURN_CODE);
	}

	@Override
	protected int getContentView() {
		return R.layout.level6;
	}

	@Override
	protected int getInnerBoardLayout() {
		return R.id.InnerBoardLayout6;
	}

	@Override
	protected int getOuterBoardLayout() {
		return R.id.OuterBoardLayout6;
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
		return R.id.txt_moves6;
	}

	@Override
	protected int getTxtBestSolution() {
		return R.id.txt_best_solution6;
	}

	@Override
	protected String getPropBestSolution() {
		return PROP_BEST;
	}

}
