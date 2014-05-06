package aweber.phandroid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import aweber.phandroid.game.Board;
import aweber.phandroid.game.BoardPos;
import aweber.phandroid.game.Piece;
import aweber.phandroid.game.Piece11;
import aweber.phandroid.game.Piece12;
import aweber.phandroid.game.Piece21;
import aweber.phandroid.game.Piece22;

public class GameActivity extends Activity {

	public static final int EXIT_RETURN_CODE = 4711;

	private static final int BOARD_FIELD_SIZE_DP = 60;

	private static final String PROPERTY_FILE_NAME = "phandroid.props";
	private static final String PROP_BEST = "best"; // property where to store best solution

	private String _version; // the software version
	private FrameLayout _boardLayout;
	private int boardLeft, boardTop; // board frame relative to parent frame
	private Board _board;
	private Piece _piece11_1, _piece11_2, _piece11_3, _piece11_4, _piece12_1, _piece12_2, _piece12_3, _piece12_4,
			_piece21, _piece22;

	/** helper vars for move */
	private BoardPos _oldPos; // object pos before move
	private float _xRawOld, _yRawOld; // cursor pos on display before move
	private boolean _isMoving; // are we currently in a move?
	private boolean _canMoveX; // can we move horizontally in current move
	private boolean _canMoveY; // can we move vertically in current move	
	private int _noOfMoves;

	private MediaPlayer _playerMove, _playerSuccess; // to play sounds
	private boolean _isSoundEnabled = false; // default -> mute
	private Properties _props;

	private int _board_field_size_px;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		_boardLayout = (FrameLayout) findViewById(R.id.InnerBoardLayout);
		_playerMove = MediaPlayer.create(GameActivity.this, R.raw.move);
		_playerSuccess = MediaPlayer.create(GameActivity.this, R.raw.success);
		_version = getVersion();
		_board_field_size_px = Math.round(BOARD_FIELD_SIZE_DP * getResources().getDisplayMetrics().density);
		initBoard();
	}

	@Override
	protected void onPause() {
		super.onPause();
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

		_props.setProperty("moves", String.valueOf(_noOfMoves));

		saveProperties();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.game_menu, menu);
		return true; // "return true for the menu to be displayed...".
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// sound menu item
		if (_isSoundEnabled) {
			// sound is enabled - show menu item for disabling sound
			menu.getItem(1).setTitle(R.string.menu_sound_off); // menu item '2' is sound item
		} else {
			// sound is disabled - show menu item for enabling sound
			menu.getItem(1).setTitle(R.string.menu_sound_on); // menu item '2' is sound item
		}
		return true; // "return true for the menu to be displayed...".
	}

	@Override
	// Return false to allow normal menu processing to proceed, true to consume it here.
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.opt_finish:
			exit();
			return true;
		case R.id.opt_restart:
			reset();
			return true;
		case R.id.opt_sound:
			if (_isSoundEnabled) {
				_isSoundEnabled = false; // sound was enabled, now disabled
			} else {
				_isSoundEnabled = true; // sound was disabled, now enabled
			}
			return true;
		case R.id.opt_about:
			showAboutDialog();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void exit() {
		if (_playerMove != null) {
			_playerMove.release();
		}
		if (_playerSuccess != null) {
			_playerSuccess.release();
		}
		setResult(EXIT_RETURN_CODE, null);
		finish();
	}

	private void initBoard() {
		_board = new Board();

		loadProperties(); // load properties where app state is saved

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
			saveProperties();
		}
		showBestSolution();

		_noOfMoves = 0;
		if (_props.containsKey("moves")) {
			_noOfMoves = Integer.valueOf(_props.getProperty("moves"));
		}
		showMoves();
	}

	final OnTouchListener onTouch = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {

			boardTop = _boardLayout.getTop();
			boardLeft = _boardLayout.getLeft();
		
			switch (v.getId()) {// What is being touched
			case R.id.Piece11_1:
			case R.id.Piece11_2:
			case R.id.Piece11_3:
			case R.id.Piece11_4:
			case R.id.Piece12_1:
			case R.id.Piece12_2:
			case R.id.Piece12_3:
			case R.id.Piece12_4:
			case R.id.Piece21:
			case R.id.Piece22: {
				switch (event.getAction()) {
				case MotionEvent.ACTION_MOVE: { // move happend during pressed gesture
					if (_isMoving) {
						
						// for debugging:
						//showEventCoordinates(event);
						//showMoveTendency(event, v.getId());

						final FrameLayout.LayoutParams boardLayoutParams = (LayoutParams) v.getLayoutParams();
						if (_canMoveX) {
							boardLayoutParams.leftMargin = (int) event.getRawX() - boardLeft - (v.getWidth() / 2);
						}
						if (_canMoveY) {
							boardLayoutParams.topMargin = (int) event.getRawY() - boardTop - Math.round(v.getHeight());
						}
						v.setLayoutParams(boardLayoutParams);
					}
					break;
				}
				case MotionEvent.ACTION_UP: { // finished pressed gesture
					if (_isMoving) {
						final FrameLayout.LayoutParams boardLayoutParams = (LayoutParams) v.getLayoutParams();
						
						// for debugging:
						//showEventCoordinates(event);
						//showMoveTendency(event, v.getId());

						// check if we are within board frame
						final BoardPos newPos = calculateNewPosition(event, v.getId());
						if (_board.canMoveTo(v.getId(), newPos.x, newPos.y)) {
							_board.move(v.getId(), newPos.x, newPos.y);
							// object left margin to board frame = board field * board field size
							boardLayoutParams.leftMargin = newPos.x * _board_field_size_px;
							boardLayoutParams.topMargin = newPos.y * _board_field_size_px;
							_noOfMoves++;
							showMoves();
							// showFreePositions(); // for debugging
							if (_board.isSolution()) {
								handleSuccess();
							} else {
								playMoveSound();
							}
						} else {
							// we cannot move here -> place piece back on old position
							boardLayoutParams.leftMargin = _oldPos.x * _board_field_size_px;
							boardLayoutParams.topMargin = _oldPos.y * _board_field_size_px;
						}
						v.setLayoutParams(boardLayoutParams);
					}
					break;
				}
				case MotionEvent.ACTION_DOWN: { // started pressed gesture
					final int pieceId = v.getId();
					if (_board.canMove(pieceId)) {
						_oldPos = new BoardPos(_board.getXPos(pieceId), _board.getYPos(pieceId));
						_xRawOld = event.getRawX();
						_yRawOld = event.getRawY();
						// TODO mark piece (e.g. different color)
						// v.setLayoutParams(boardLayoutParams);
						_isMoving = true;
						if (_board.canMoveX(v.getId(), _oldPos.x, _oldPos.y)) {
							_canMoveX = true;
						} else {
							_canMoveX = false;
						}
						if (_board.canMoveY(v.getId(), _oldPos.x, _oldPos.y)) {
							_canMoveY = true;
						} else {
							_canMoveY = false;
						}
					} else {
						_isMoving = false;
					}
					break;
				}
				}// inner switch
				break;
			}
			}// outer switch
			return true;
		}
	};

	private BoardPos calculateNewPosition(MotionEvent event, int pieceId) {
		int xOld = _board.getXPos(pieceId);
		int yOld = _board.getYPos(pieceId);

		final BoardPos result = new BoardPos(xOld, yOld);

		float tendenceX = (event.getRawX() - _xRawOld) / _board_field_size_px;
		float tendenceY = (event.getRawY() - _yRawOld) / _board_field_size_px;

		if (_canMoveX && (!_canMoveY || Math.abs(tendenceX) > Math.abs(tendenceY))) {
			int xDist = Math.round(tendenceX);
			if (xDist >= 2) {
				if (_board.canMoveTo(pieceId, xOld + 2, yOld)) {
					xDist = 2;
				} else {
					xDist = 1;
				}
			} else if (xDist <= -2) {
				if (_board.canMoveTo(pieceId, xOld - 2, yOld)) {
					xDist = -2;
				} else {
					xDist = -1;
				}
			} else if (xDist == 0) {
				if (tendenceX > 0.3) {
					xDist = 1;
				} else if (tendenceX < -0.3) {
					xDist = -1;
				}
			}
			result.x += xDist;
		} else {
			int yDist = Math.round(tendenceY);
			if (yDist >= 2) {
				if (_board.canMoveTo(pieceId, xOld, yOld + 2)) {
					yDist = 2;
				} else {
					yDist = 1;
				}
			} else if (yDist <= -2) {
				if (_board.canMoveTo(pieceId, xOld, yOld - 2)) {
					yDist = -2;
				} else {
					yDist = -1;
				}
			} else if (yDist == 0) {
				if (tendenceY > 0.3) {
					yDist = 1;
				} else if (tendenceY < -0.3) {
					yDist = -1;
				}
			}
			result.y += yDist;
		}
		return result;
	}

	private void addPiece(int id, Piece p) {
		_board.addPiece(id, p);
		final View v = findViewById(id);
		v.setOnTouchListener(onTouch);
		final FrameLayout.LayoutParams boardLayoutParams = (LayoutParams) v.getLayoutParams();
		boardLayoutParams.leftMargin = p.xLeft * _board_field_size_px;
		boardLayoutParams.topMargin = p.yTop * _board_field_size_px;
		v.setLayoutParams(boardLayoutParams);
	}

	private void playMoveSound() {
		if (_isSoundEnabled) {
			_playerMove.start();
		}
	}

	private void playSuccessSound() {
		if (_isSoundEnabled) {
			_playerSuccess.start();
		}
	}

	/** reset board, board properties, move counter. */
	private void reset() {
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

		_props.remove("moves");

		saveProperties();

		initBoard();
	}

	/** load the properties file where app state is stored. */
	private void loadProperties() {
		_props = new Properties();
		FileInputStream propsIn = null;
		try {
			propsIn = openFileInput(PROPERTY_FILE_NAME);
			_props.load(propsIn);
		} catch (FileNotFoundException e) {
			// property file doesn't exist yet, that's ok
		} catch (IOException e) {
			// error while reading input strean (TODO what to do?)
			throw new RuntimeException(e);
		} finally {
			if (propsIn != null) {
				try {
					propsIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** save the properties file where app state is stored. */
	private void saveProperties() {
		FileOutputStream propsOut = null;
		try {
			// create property file not readable by other apps
			propsOut = openFileOutput(PROPERTY_FILE_NAME, MODE_PRIVATE);
			_props.store(propsOut, null);
		} catch (IOException e) {
			e.printStackTrace(); // TODO can this happen? openFileOutput creates the file if it doesn't exist
		} finally {
			if (propsOut != null) {
				try {
					propsOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void handleSuccess() {
		playSuccessSound();

		// check if we have a new best solution
		int oldBest = Integer.MAX_VALUE;
		try {
			oldBest = Integer.valueOf(_props.getProperty(PROP_BEST));
		} catch (NumberFormatException e) {
			// expected when no entry yet
		}
		if (_noOfMoves < oldBest) {
			_props.setProperty(PROP_BEST, String.valueOf(_noOfMoves));
			saveProperties();
		}

		// show success dialog
		final Dialog dialog = new Dialog(GameActivity.this);
		dialog.setContentView(R.layout.dialog_success);
		final Button button = (Button) dialog.findViewById(R.id.button_new_game);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				reset();
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	private void showMoves() {
		final TextView txtMoves = (TextView) findViewById(R.id.txt_moves);
		txtMoves.setText(getString(R.string.game_moves) + ": " + String.valueOf(_noOfMoves));
	}

	private void showBestSolution() {
		final TextView txtMoves = (TextView) findViewById(R.id.txt_best_solution);
		txtMoves.setText(getString(R.string.game_best) + ": " + _props.getProperty(PROP_BEST));
		txtMoves.setGravity(Gravity.RIGHT);
	}

	private void showAboutDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(getText(R.string.dialog_help) + "\n\n" + getText(R.string.dialog_about) + " " + _version);
		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				return;
			}
		});
		final AlertDialog aboutDialog = builder.create();
		aboutDialog.show();
	}

	/** for debugging... */
	private void showFreePositions() {
		// final TextView txtFreePos = (TextView) findViewById(R.id.txt_freepositions);
		// txtFreePos.setText(board.getFreePosAsString());
	}

	/** for debugging.. */
	private void showEventCoordinates(MotionEvent event) {
		final TextView txtMoves = (TextView) findViewById(R.id.txt_moves);
		txtMoves.setText("x: " + Math.round(event.getRawX() - boardLeft) + ", y:"
				+ Math.round(event.getRawY() - boardTop));
	}

	/** for debugging.. */
	private void showMoveTendency(MotionEvent event, int pieceId) {
		int xOld = _board.getXPos(pieceId);
		int yOld = _board.getYPos(pieceId);
		final BoardPos oldPos = new BoardPos(xOld, yOld);
		final BoardPos newPos = calculateNewPosition(event, pieceId);
		final TextView txtMoves = (TextView) findViewById(R.id.txt_best_solution);
		txtMoves.setText("old: " + oldPos + ", new: " + newPos);
		txtMoves.setGravity(Gravity.RIGHT);
	}

	/** Gets the version from the Manifest. */
	private String getVersion() {
		try {
			final PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			return packageInfo.versionName;
		} catch (PackageManager.NameNotFoundException e) {
		}
		return "<unknown>";
	}

}