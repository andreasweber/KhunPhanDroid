package aweber.phandroid;

import android.os.Bundle;
import android.widget.TextView;

import aweber.phandroid.game.Board;
import aweber.phandroid.game.BoardPos;
import aweber.phandroid.game.Piece;
import aweber.phandroid.game.Piece11;
import aweber.phandroid.game.Piece12;
import aweber.phandroid.game.Piece13;
import aweber.phandroid.game.Piece21;
import aweber.phandroid.game.Piece22;
import aweber.phandroid.game.Piece3lo;
import aweber.phandroid.game.Piece3lu;
import aweber.phandroid.game.Piece3ro;
import aweber.phandroid.game.Piece3ru;

// ======
//0 |1441
//1 |1441
//2 |1221
//3 |2222
//4 |2  2
//5 |5116
//6 |5116
//7 |5116
// ======
public class Level7Activity extends GameActivity {

	public static final int EXIT_RETURN_CODE = 4717;

	public static final String PROP_BEST = "best7"; // property where to store best solution of Level 7

	private static final String PROP_MOVES = "moves7"; // property where to store current moves of Level 7

	private Piece _piece11_1, _piece11_2, _piece11_3, _piece11_4, _piece11_5, _piece11_6, _piece11_7, _piece11_8,//
			_piece11_9, _piece11_10, _piece11_11, _piece11_12, //
			_piece12_1, _piece12_2, _piece12_3, _piece12_4, //
			_piece13_1, _piece13_2, //
			_piece22;

	protected boolean containsPieceId(final int id) {
		if (id == R.id.L7Piece11_1 || id == R.id.L7Piece11_2 || id == R.id.L7Piece11_3 || id == R.id.L7Piece11_4
				|| id == R.id.L7Piece11_5 || id == R.id.L7Piece11_6 || id == R.id.L7Piece11_7 || id == R.id.L7Piece11_8
                || id == R.id.L7Piece11_9 || id == R.id.L7Piece11_10 || id == R.id.L7Piece11_11 || id == R.id.L7Piece11_12
				|| id == R.id.L7Piece12_1 || id == R.id.L7Piece12_2 || id == R.id.L7Piece12_3 || id == R.id.L7Piece12_4
				|| id == R.id.L7Piece13_1 || id == R.id.L7Piece13_2 || id == R.id.L7Piece22) {
			return true;
		}
		return false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((TextView)findViewById(R.id.txt_moves7)).setTypeface(Assets.ttf);
		((TextView)findViewById(R.id.txt_best_solution7)).setTypeface(Assets.ttf);
	}

	@Override
	protected void onPause() {
		// save current positions
		_props.setProperty("x_L7piece11_1", String.valueOf(_piece11_1.xLeft));
		_props.setProperty("y_L7piece11_1", String.valueOf(_piece11_1.yTop));
		_props.setProperty("x_L7piece11_2", String.valueOf(_piece11_2.xLeft));
		_props.setProperty("y_L7piece11_2", String.valueOf(_piece11_2.yTop));
		_props.setProperty("x_L7piece11_3", String.valueOf(_piece11_3.xLeft));
		_props.setProperty("y_L7piece11_3", String.valueOf(_piece11_3.yTop));
		_props.setProperty("x_L7piece11_4", String.valueOf(_piece11_4.xLeft));
		_props.setProperty("y_L7piece11_4", String.valueOf(_piece11_4.yTop));
		_props.setProperty("x_L7piece11_5", String.valueOf(_piece11_5.xLeft));
		_props.setProperty("y_L7piece11_5", String.valueOf(_piece11_5.yTop));
		_props.setProperty("x_L7piece11_6", String.valueOf(_piece11_6.xLeft));
		_props.setProperty("y_L7piece11_6", String.valueOf(_piece11_6.yTop));
		_props.setProperty("x_L7piece11_7", String.valueOf(_piece11_7.xLeft));
		_props.setProperty("y_L7piece11_7", String.valueOf(_piece11_7.yTop));
		_props.setProperty("x_L7piece11_8", String.valueOf(_piece11_8.xLeft));
		_props.setProperty("y_L7piece11_8", String.valueOf(_piece11_8.yTop));
        _props.setProperty("x_L7piece11_9", String.valueOf(_piece11_9.xLeft));
        _props.setProperty("y_L7piece11_9", String.valueOf(_piece11_9.yTop));
        _props.setProperty("x_L7piece11_10", String.valueOf(_piece11_10.xLeft));
        _props.setProperty("y_L7piece11_10", String.valueOf(_piece11_10.yTop));
        _props.setProperty("x_L7piece11_11", String.valueOf(_piece11_11.xLeft));
        _props.setProperty("y_L7piece11_11", String.valueOf(_piece11_11.yTop));
        _props.setProperty("x_L7piece11_12", String.valueOf(_piece11_12.xLeft));
        _props.setProperty("y_L7piece11_12", String.valueOf(_piece11_12.yTop));
		
		_props.setProperty("x_L7piece12_1", String.valueOf(_piece12_1.xLeft));
		_props.setProperty("y_L7piece12_1", String.valueOf(_piece12_1.yTop));
        _props.setProperty("x_L7piece12_2", String.valueOf(_piece12_2.xLeft));
        _props.setProperty("y_L7piece12_2", String.valueOf(_piece12_2.yTop));
        _props.setProperty("x_L7piece12_3", String.valueOf(_piece12_3.xLeft));
        _props.setProperty("y_L7piece12_3", String.valueOf(_piece12_3.yTop));
        _props.setProperty("x_L7piece12_4", String.valueOf(_piece12_4.xLeft));
        _props.setProperty("y_L7piece12_4", String.valueOf(_piece12_4.yTop));

        _props.setProperty("x_L7piece13_1", String.valueOf(_piece13_1.xLeft));
        _props.setProperty("y_L7piece13_1", String.valueOf(_piece13_1.yTop));
        _props.setProperty("x_L7piece13_2", String.valueOf(_piece13_2.xLeft));
        _props.setProperty("y_L7piece13_2", String.valueOf(_piece13_2.yTop));

        _props.setProperty("x_L7piece22", String.valueOf(_piece22.xLeft));
		_props.setProperty("y_L7piece22", String.valueOf(_piece22.yTop));

		_props.setProperty("x_L7free1", String.valueOf(_board._free1.x));
		_props.setProperty("y_L7free1", String.valueOf(_board._free1.y));
		_props.setProperty("x_L7free2", String.valueOf(_board._free2.x));
		_props.setProperty("y_L7free2", String.valueOf(_board._free2.y));

		_props.setProperty(PROP_MOVES, String.valueOf(_noOfMoves));
		super.onPause();
	}

	@Override
	protected void initBoard() {
		super.initBoard();

		_board = new Board(19, 6);

		// load current positions, use default if not stored in properties

		_piece11_1 = new Piece11(Integer.valueOf(_props.getProperty("x_L7piece11_1", "0")), Integer.valueOf(_props
				.getProperty("y_L7piece11_1", "0"))); // default pos (0,0)
		addPiece(R.id.L7Piece11_1, _piece11_1);
		_piece11_2 = new Piece11(Integer.valueOf(_props.getProperty("x_L7piece11_2", "0")), Integer.valueOf(_props
				.getProperty("y_L7piece11_2", "1"))); // default pos (0,1)
		addPiece(R.id.L7Piece11_2, _piece11_2);
		_piece11_3 = new Piece11(Integer.valueOf(_props.getProperty("x_L7piece11_3", "3")), Integer.valueOf(_props
				.getProperty("y_L7piece11_3", "0"))); // default pos (3,0)
		addPiece(R.id.L7Piece11_3, _piece11_3);
		_piece11_4 = new Piece11(Integer.valueOf(_props.getProperty("x_L7piece11_4", "3")), Integer.valueOf(_props
				.getProperty("y_L7piece11_4", "1"))); // default pos (3,1)
		addPiece(R.id.L7Piece11_4, _piece11_4);
		_piece11_5 = new Piece11(Integer.valueOf(_props.getProperty("x_L7piece11_5", "0")), Integer.valueOf(_props
				.getProperty("y_L7piece11_5", "2"))); // default pos (0,2)
		addPiece(R.id.L7Piece11_5, _piece11_5);
		_piece11_6 = new Piece11(Integer.valueOf(_props.getProperty("x_L7piece11_6", "3")), Integer.valueOf(_props
				.getProperty("y_L7piece11_6", "2"))); // default pos (3,2)
		addPiece(R.id.L7Piece11_6, _piece11_6);
		_piece11_7 = new Piece11(Integer.valueOf(_props.getProperty("x_L7piece11_7", "1")), Integer.valueOf(_props
				.getProperty("y_L7piece11_7", "5"))); // default pos (1,5)
		addPiece(R.id.L7Piece11_7, _piece11_7);
		_piece11_8 = new Piece11(Integer.valueOf(_props.getProperty("x_L7piece11_8", "2")), Integer.valueOf(_props
				.getProperty("y_L7piece11_8", "5"))); // default pos (2,5)
		addPiece(R.id.L7Piece11_8, _piece11_8);
        _piece11_9 = new Piece11(Integer.valueOf(_props.getProperty("x_L7piece11_9", "1")), Integer.valueOf(_props
                .getProperty("y_L7piece11_9", "6"))); // default pos (1,6)
        addPiece(R.id.L7Piece11_9, _piece11_9);
        _piece11_10 = new Piece11(Integer.valueOf(_props.getProperty("x_L7piece11_10", "2")), Integer.valueOf(_props
                .getProperty("y_L7piece11_10", "6"))); // default pos (2,6)
        addPiece(R.id.L7Piece11_10, _piece11_10);
        _piece11_11 = new Piece11(Integer.valueOf(_props.getProperty("x_L7piece11_11", "1")), Integer.valueOf(_props
                .getProperty("y_L7piece11_11", "7"))); // default pos (1,7)
        addPiece(R.id.L7Piece11_11, _piece11_11);
        _piece11_12 = new Piece11(Integer.valueOf(_props.getProperty("x_L7piece11_12", "2")), Integer.valueOf(_props
                .getProperty("y_L7piece11_12", "7"))); // default pos (2,7)
        addPiece(R.id.L7Piece11_12, _piece11_12);

        _piece12_1 = new Piece12(Integer.valueOf(_props.getProperty("x_L7piece12_1", "0")), Integer.valueOf(_props
				.getProperty("y_L7piece12_1", "3"))); // default pos (0,3)
		addPiece(R.id.L7Piece12_1, _piece12_1);
        _piece12_2 = new Piece12(Integer.valueOf(_props.getProperty("x_L7piece12_2", "1")), Integer.valueOf(_props
                .getProperty("y_L7piece12_2", "2"))); // default pos (1,2)
        addPiece(R.id.L7Piece12_2, _piece12_2);
        _piece12_3 = new Piece12(Integer.valueOf(_props.getProperty("x_L7piece12_3", "2")), Integer.valueOf(_props
                .getProperty("y_L7piece12_3", "2"))); // default pos (2,2)
        addPiece(R.id.L7Piece12_3, _piece12_3);
        _piece12_4 = new Piece12(Integer.valueOf(_props.getProperty("x_L7piece12_4", "3")), Integer.valueOf(_props
                .getProperty("y_L7piece12_4", "3"))); // default pos (3,3)
        addPiece(R.id.L7Piece12_4, _piece12_4);

        _piece13_1 = new Piece13(Integer.valueOf(_props.getProperty("x_L7piece13_1", "0")), Integer.valueOf(_props
                .getProperty("y_L7piece13_1", "5"))); // default pos (0,5)
        addPiece(R.id.L7Piece13_1, _piece13_1);
        _piece13_2 = new Piece13(Integer.valueOf(_props.getProperty("x_L7piece13_2", "3")), Integer.valueOf(_props
                .getProperty("y_L7piece13_2", "5"))); // default pos (3,5)
        addPiece(R.id.L7Piece13_2, _piece13_2);

		_piece22 = new Piece22(Integer.valueOf(_props.getProperty("x_L7piece22", "1")), Integer.valueOf(_props
				.getProperty("y_L7piece22", "0"))); // default pos (1,0)
		addPiece(R.id.L7Piece22, _piece22);

		final BoardPos free1 = new BoardPos(Integer.valueOf(_props.getProperty("x_L7free1", "1")),
				Integer.valueOf(_props.getProperty("y_L7free1", "4"))); // default pos (1,4)
		final BoardPos free2 = new BoardPos(Integer.valueOf(_props.getProperty("x_L7free2", "2")),
				Integer.valueOf(_props.getProperty("y_L7free2", "4"))); // default pos (2,4)
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
		_props.remove("x_L7piece11_1");
		_props.remove("y_L7piece11_1");
		_props.remove("x_L7piece11_2");
		_props.remove("y_L7piece11_2");
		_props.remove("x_L7piece11_3");
		_props.remove("y_L7piece11_3");
		_props.remove("x_L7piece11_4");
		_props.remove("y_L7piece11_4");
		_props.remove("x_L7piece11_5");
		_props.remove("y_L7piece11_5");
		_props.remove("x_L7piece11_6");
		_props.remove("y_L7piece11_6");
		_props.remove("x_L7piece11_7");
		_props.remove("y_L7piece11_7");
		_props.remove("x_L7piece11_8");
		_props.remove("y_L7piece11_8");
        _props.remove("x_L7piece11_9");
        _props.remove("y_L7piece11_9");
        _props.remove("x_L7piece11_10");
        _props.remove("y_L7piece11_10");
        _props.remove("x_L7piece11_11");
        _props.remove("y_L7piece11_11");
        _props.remove("x_L7piece11_12");
        _props.remove("y_L7piece11_12");

		_props.remove("x_L7piece12_1");
		_props.remove("y_L7piece12_1");
        _props.remove("x_L7piece12_2");
        _props.remove("y_L7piece12_2");
        _props.remove("x_L7piece12_3");
        _props.remove("y_L7piece12_3");
        _props.remove("x_L7piece12_4");
        _props.remove("y_L7piece12_4");

        _props.remove("x_L7piece13_1");
        _props.remove("y_L7piece13_1");
        _props.remove("x_L7piece13_2");
        _props.remove("y_L7piece13_2");

		_props.remove("x_L7piece22");
		_props.remove("y_L7piece22");

		_props.remove("x_L7free1");
		_props.remove("y_L7free1");
		_props.remove("x_L7free2");
		_props.remove("y_L7free2");

		_props.remove(PROP_MOVES);
		super.reset();
	}

	@Override
	protected void exit() {
		super.exit(EXIT_RETURN_CODE);
	}

	@Override
	protected int getContentView() {
		return R.layout.level7;
	}

	@Override
	protected int getInnerBoardLayout() {
		return R.id.InnerBoardLayout7;
	}

	@Override
	protected int getOuterBoardLayout() {
		return R.id.OuterBoardLayout7;
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
		return R.id.txt_moves7;
	}

	@Override
	protected int getTxtBestSolution() {
		return R.id.txt_best_solution7;
	}

	@Override
	protected String getPropBestSolution() {
		return PROP_BEST;
	}

}
