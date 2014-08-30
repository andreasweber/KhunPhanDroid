package aweber.phandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends Activity {

	private static final int SUB_ACTIVITY_REQUEST_CODE_L1 = 13;

	private static final int SUB_ACTIVITY_REQUEST_CODE_L2 = 14;
	
	private static final int SUB_ACTIVITY_REQUEST_CODE_L3 = 15;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
	}

	/**
	 * Called when clicking Level1-Button
	 * 
	 * @see res/layout/start.xml
	 */
	public void onClickStartLevel1(final View v) {
		final Intent i = new Intent(this, Level1Activity.class);
		// we can't use startActivity() cause we want to react on child activity's finish (see onActivityResult())
		startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE_L1);
	}

	/**
	 * Called when clicking Level2-Button
	 * 
	 * @see res/layout/start.xml
	 */
	public void onClickStartLevel2(final View v) {
		final Intent i = new Intent(this, Level2Activity.class);
		// we can't use startActivity() cause we want to react on child activity's finish (see onActivityResult())
		startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE_L2);
	}
	
	/**
	 * Called when clicking Level3-Button
	 * 
	 * @see res/layout/start.xml
	 */
	public void onClickStartLevel3(final View v) {
		final Intent i = new Intent(this, Level3Activity.class);
		// we can't use startActivity() cause we want to react on child activity's finish (see onActivityResult())
		startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE_L3);
	}

	/** Called when child activity finishes. */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if ((requestCode == SUB_ACTIVITY_REQUEST_CODE_L1 && resultCode == Level1Activity.EXIT_RETURN_CODE)
				|| (requestCode == SUB_ACTIVITY_REQUEST_CODE_L2 && resultCode == Level2Activity.EXIT_RETURN_CODE)
				|| (requestCode == SUB_ACTIVITY_REQUEST_CODE_L3 && resultCode == Level3Activity.EXIT_RETURN_CODE)) {
			finish(); // 'Beenden' has been chosen in child activity's option menu -> also close parent(=this) activity
		}
	}

}