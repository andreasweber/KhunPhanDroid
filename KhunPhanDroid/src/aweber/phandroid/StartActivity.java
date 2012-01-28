package aweber.phandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends Activity {

	private static final int SUB_ACTIVITY_REQUEST_CODE = 13;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
	}

	/**
	 * Wird bei Klick auf Start-Button aufgerufen
	 * 
	 * @see res/layout/start.xml
	 */
	public void onClickStartGame(final View v) {
		final Intent i = new Intent(this, GameActivity.class);
		// we can't use startActivity() cause we want to react on child activity's finish (see onActivityResult())
		startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
	}

	/**
	 * Called when child activity finishes.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == SUB_ACTIVITY_REQUEST_CODE && resultCode == GameActivity.EXIT_RETURN_CODE) {
			finish(); // 'Beenden' has been chosen in child activity's option menu -> also close parent(=this) activity
		}
	}

}