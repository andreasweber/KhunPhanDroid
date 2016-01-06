package aweber.phandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class StartActivity extends Activity {

	private static final int SUB_ACTIVITY_REQUEST_CODE_I = 13;

	private static final int SUB_ACTIVITY_REQUEST_CODE_LS = 14;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);

        if (Assets.ttf == null) {
            Assets.ttf = Typeface.createFromAsset(getAssets(), "Peax.ttf");
        }
        ((Button) findViewById(R.id.button_instructions)).setTypeface(Assets.ttf);
        ((Button) findViewById(R.id.button_level_selection)).setTypeface(Assets.ttf);
    }

	/**
	 * Called when clicking Instructions-Button
	 * 
	 * @see res/layout/start.xml
	 */
	public void onClickStartInstructions(final View v) {
		final Intent i = new Intent(this, InstructionsActivity.class);
		// we can't use startActivity() cause we want to react on child activity's finish (see onActivityResult())
		startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE_I);
	}

	/**
	 * Called when clicking StartGane-Button
	 * 
	 * @see res/layout/start.xml
	 */
	public void onClickStartLevelSelection(final View v) {
		final Intent i = new Intent(this, LevelSelectionActivity.class);
		// we can't use startActivity() cause we want to react on child activity's finish (see onActivityResult())
		startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE_LS);
	}

	/** Called when child activity finishes. */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == SUB_ACTIVITY_REQUEST_CODE_LS && resultCode == LevelSelectionActivity.EXIT_RETURN_CODE) {
			finish(); // 'Exit' has been chosen in child activity's option menu -> also close parent(=this) activity
		}
	}
}