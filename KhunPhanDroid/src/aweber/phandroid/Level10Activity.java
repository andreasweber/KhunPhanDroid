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
import aweber.phandroid.game.Piece3lu;

//   ======
// 0 |2442|
// 1 |2442|
// 2 |qqqq|
// 3 |2112|
// 4 |2qq2|
// 5 |1  3|
// 6 |6qq3|
// 7 |6613|
//   ======
public class Level10Activity extends GameActivity {

    public static final int EXIT_RETURN_CODE = 4720;

    public static final String PROP_BEST = "best10"; // property where to store best solution of Level 10

    private static final String PROP_MOVES = "moves10"; // property where to store current moves of Level 10

    private Piece _piece11_1, _piece11_2, _piece11_3, _piece11_4, //
            _piece12_1, _piece12_2, _piece12_3, _piece12_4, //
            _piece21_1, _piece21_2, _piece21_3, _piece21_4, //
            _piece13_1, _piece3lu, //
            _piece22;

    protected boolean containsPieceId(final int id) {
        if (id == R.id.L10Piece11_1 || id == R.id.L10Piece11_2 || id == R.id.L10Piece11_3 || id == R.id.L10Piece11_4
                || id == R.id.L10Piece12_1 || id == R.id.L10Piece12_2 || id == R.id.L10Piece12_3 || id == R.id.L10Piece12_4
                || id == R.id.L10Piece21_1 || id == R.id.L10Piece21_2 || id == R.id.L10Piece21_3 || id == R.id.L10Piece21_4
                || id == R.id.L10Piece13_1 || id == R.id.L10Piece3lu || id == R.id.L10Piece22) {
            return true;
        }
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TextView)findViewById(R.id.txt_moves10)).setTypeface(Assets.ttf);
        ((TextView)findViewById(R.id.txt_best_solution10)).setTypeface(Assets.ttf);
    }

    @Override
    protected void onPause() {
        // save current positions
        _props.setProperty("x_L10piece11_1", String.valueOf(_piece11_1.xLeft));
        _props.setProperty("y_L10piece11_1", String.valueOf(_piece11_1.yTop));
        _props.setProperty("x_L10piece11_2", String.valueOf(_piece11_2.xLeft));
        _props.setProperty("y_L10piece11_2", String.valueOf(_piece11_2.yTop));
        _props.setProperty("x_L10piece11_3", String.valueOf(_piece11_3.xLeft));
        _props.setProperty("y_L10piece11_3", String.valueOf(_piece11_3.yTop));
        _props.setProperty("x_L10piece11_4", String.valueOf(_piece11_4.xLeft));
        _props.setProperty("y_L10piece11_4", String.valueOf(_piece11_4.yTop));

        _props.setProperty("x_L10piece12_1", String.valueOf(_piece12_1.xLeft));
        _props.setProperty("y_L10piece12_1", String.valueOf(_piece12_1.yTop));
        _props.setProperty("x_L10piece12_2", String.valueOf(_piece12_2.xLeft));
        _props.setProperty("y_L10piece12_2", String.valueOf(_piece12_2.yTop));
        _props.setProperty("x_L10piece12_3", String.valueOf(_piece12_3.xLeft));
        _props.setProperty("y_L10piece12_3", String.valueOf(_piece12_3.yTop));
        _props.setProperty("x_L10piece12_4", String.valueOf(_piece12_4.xLeft));
        _props.setProperty("y_L10piece12_4", String.valueOf(_piece12_4.yTop));

        _props.setProperty("x_L10piece21_1", String.valueOf(_piece21_1.xLeft));
        _props.setProperty("y_L10piece21_1", String.valueOf(_piece21_1.yTop));
        _props.setProperty("x_L10piece21_2", String.valueOf(_piece21_2.xLeft));
        _props.setProperty("y_L10piece21_2", String.valueOf(_piece21_2.yTop));
        _props.setProperty("x_L10piece21_3", String.valueOf(_piece21_3.xLeft));
        _props.setProperty("y_L10piece21_3", String.valueOf(_piece21_3.yTop));
        _props.setProperty("x_L10piece21_4", String.valueOf(_piece21_4.xLeft));
        _props.setProperty("y_L10piece21_4", String.valueOf(_piece21_4.yTop));

        _props.setProperty("x_L10piece13_1", String.valueOf(_piece13_1.xLeft));
        _props.setProperty("y_L10piece13_1", String.valueOf(_piece13_1.yTop));
        _props.setProperty("x_L10piece3lu", String.valueOf(_piece3lu.xLeft));
        _props.setProperty("y_L10piece3lu", String.valueOf(_piece3lu.yTop));

        _props.setProperty("x_L10piece22", String.valueOf(_piece22.xLeft));
        _props.setProperty("y_L10piece22", String.valueOf(_piece22.yTop));

        _props.setProperty("x_L10free1", String.valueOf(_board._free1.x));
        _props.setProperty("y_L10free1", String.valueOf(_board._free1.y));
        _props.setProperty("x_L10free2", String.valueOf(_board._free2.x));
        _props.setProperty("y_L10free2", String.valueOf(_board._free2.y));

        _props.setProperty(PROP_MOVES, String.valueOf(_noOfMoves));
        super.onPause();
    }

    @Override
    protected void initBoard() {
        super.initBoard();

        _board = new Board(19, 6);

        // load current positions, use default if not stored in properties

        _piece3lu = new Piece3lu(Integer.valueOf(_props.getProperty("x_L10piece3lu", "0")), Integer.valueOf(_props
                .getProperty("y_L10piece3lu", "6"))); // default pos (0,6)
        addPiece(R.id.L10Piece3lu, _piece3lu);

        _piece11_1 = new Piece11(Integer.valueOf(_props.getProperty("x_L10piece11_1", "1")), Integer.valueOf(_props
                .getProperty("y_L10piece11_1", "3"))); // default pos (1,3)
        addPiece(R.id.L10Piece11_1, _piece11_1);
        _piece11_2 = new Piece11(Integer.valueOf(_props.getProperty("x_L10piece11_2", "2")), Integer.valueOf(_props
                .getProperty("y_L10piece11_2", "3"))); // default pos (2,3)
        addPiece(R.id.L10Piece11_2, _piece11_2);
        _piece11_3 = new Piece11(Integer.valueOf(_props.getProperty("x_L10piece11_3", "0")), Integer.valueOf(_props
                .getProperty("y_L10piece11_3", "5"))); // default pos (0,5)
        addPiece(R.id.L10Piece11_3, _piece11_3);
        _piece11_4 = new Piece11(Integer.valueOf(_props.getProperty("x_L10piece11_4", "2")), Integer.valueOf(_props
                .getProperty("y_L10piece11_4", "7"))); // default pos (2,7)
        addPiece(R.id.L10Piece11_4, _piece11_4);

        _piece12_1 = new Piece12(Integer.valueOf(_props.getProperty("x_L10piece12_1", "0")), Integer.valueOf(_props
                .getProperty("y_L10piece12_1", "0"))); // default pos (0,0)
        addPiece(R.id.L10Piece12_1, _piece12_1);
        _piece12_2 = new Piece12(Integer.valueOf(_props.getProperty("x_L10piece12_2", "3")), Integer.valueOf(_props
                .getProperty("y_L10piece12_2", "0"))); // default pos (3,0)
        addPiece(R.id.L10Piece12_2, _piece12_2);
        _piece12_3 = new Piece12(Integer.valueOf(_props.getProperty("x_L10piece12_3", "0")), Integer.valueOf(_props
                .getProperty("y_L10piece12_3", "3"))); // default pos (0,3)
        addPiece(R.id.L10Piece12_3, _piece12_3);
        _piece12_4 = new Piece12(Integer.valueOf(_props.getProperty("x_L10piece12_4", "3")), Integer.valueOf(_props
                .getProperty("y_L10piece12_4", "3"))); // default pos (3,3)
        addPiece(R.id.L10Piece12_4, _piece12_4);

        _piece21_1 = new Piece21(Integer.valueOf(_props.getProperty("x_L10piece21_1", "0")), Integer.valueOf(_props
                .getProperty("y_L10piece21_1", "2"))); // default pos (0,2)
        addPiece(R.id.L10Piece21_1, _piece21_1);
        _piece21_2 = new Piece21(Integer.valueOf(_props.getProperty("x_L10piece21_2", "2")), Integer.valueOf(_props
                .getProperty("y_L10piece21_2", "2"))); // default pos (2,2)
        addPiece(R.id.L10Piece21_2, _piece21_2);
        _piece21_3 = new Piece21(Integer.valueOf(_props.getProperty("x_L10piece21_3", "1")), Integer.valueOf(_props
                .getProperty("y_L10piece21_3", "4"))); // default pos (1,4)
        addPiece(R.id.L10Piece21_3, _piece21_3);
        _piece21_4 = new Piece21(Integer.valueOf(_props.getProperty("x_L10piece21_4", "1")), Integer.valueOf(_props
                .getProperty("y_L10piece21_4", "6"))); // default pos (1,6)
        addPiece(R.id.L10Piece21_4, _piece21_4);

        _piece13_1 = new Piece13(Integer.valueOf(_props.getProperty("x_L10piece13_1", "3")), Integer.valueOf(_props
                .getProperty("y_L10piece13_1", "5"))); // default pos (3,5)
        addPiece(R.id.L10Piece13_1, _piece13_1);

        _piece22 = new Piece22(Integer.valueOf(_props.getProperty("x_L10piece22", "1")), Integer.valueOf(_props
                .getProperty("y_L10piece22", "0"))); // default pos (1,0)
        addPiece(R.id.L10Piece22, _piece22);

        final BoardPos free1 = new BoardPos(Integer.valueOf(_props.getProperty("x_L10free1", "1")),
                Integer.valueOf(_props.getProperty("y_L10free1", "5"))); // default pos (1,5)
        final BoardPos free2 = new BoardPos(Integer.valueOf(_props.getProperty("x_L10free2", "2")),
                Integer.valueOf(_props.getProperty("y_L10free2", "5"))); // default pos (2,5)
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
        _props.remove("x_L10piece11_1");
        _props.remove("y_L10piece11_1");
        _props.remove("x_L10piece11_2");
        _props.remove("y_L10piece11_2");
        _props.remove("x_L10piece11_3");
        _props.remove("y_L10piece11_3");
        _props.remove("x_L10piece11_4");
        _props.remove("y_L10piece11_4");

        _props.remove("x_L10piece12_1");
        _props.remove("y_L10piece12_1");
        _props.remove("x_L10piece12_2");
        _props.remove("y_L10piece12_2");
        _props.remove("x_L10piece12_3");
        _props.remove("y_L10piece12_3");
        _props.remove("x_L10piece12_4");
        _props.remove("y_L10piece12_4");

        _props.remove("x_L10piece21_1");
        _props.remove("y_L10piece21_1");
        _props.remove("x_L10piece21_2");
        _props.remove("y_L10piece21_2");
        _props.remove("x_L10piece21_3");
        _props.remove("y_L10piece21_3");
        _props.remove("x_L10piece21_4");
        _props.remove("y_L10piece21_4");

        _props.remove("x_L10piece13_1");
        _props.remove("y_L10piece13_1");
        _props.remove("x_L10piece3lu");
        _props.remove("y_L10piece3lu");

        _props.remove("x_L10piece22");
        _props.remove("y_L10piece22");

        _props.remove("x_L10free1");
        _props.remove("y_L10free1");
        _props.remove("x_L10free2");
        _props.remove("y_L10free2");

        _props.remove(PROP_MOVES);
        super.reset();
    }

    @Override
    protected void exit() {
        super.exit(EXIT_RETURN_CODE);
    }

    @Override
    protected int getContentView() {
        return R.layout.level10;
    }

    @Override
    protected int getInnerBoardLayout() {
        return R.id.InnerBoardLayout10;
    }

    @Override
    protected int getOuterBoardLayout() {
        return R.id.OuterBoardLayout10;
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
        return R.id.txt_moves10;
    }

    @Override
    protected int getTxtBestSolution() {
        return R.id.txt_best_solution10;
    }

    @Override
    protected String getPropBestSolution() {
        return PROP_BEST;
    }

}
