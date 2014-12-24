package aweber.phandroid;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LevelSelectionActivity extends Activity {
	
	public static final int EXIT_RETURN_CODE = 2000;
	
	private static final int SUB_ACTIVITY_REQUEST_CODE_L1 = 21;

	private static final int SUB_ACTIVITY_REQUEST_CODE_L2 = 22;
	
	private static final int SUB_ACTIVITY_REQUEST_CODE_L3 = 23;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_selection);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		final ListView listview = (ListView) findViewById(R.id.listview_level_selection);
		
		String[] values = new String[] { getText(R.string.level1_text).toString(), //
				getText(R.string.level2_text).toString(), //
				getText(R.string.level3_text).toString() //
		};
		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.level_selection_row, list);
		listview.setAdapter(adapter);

		final Intent level1Intent = new Intent(this, Level1Activity.class);
		final Intent level2Intent = new Intent(this, Level2Activity.class);
		final Intent level3Intent = new Intent(this, Level3Activity.class);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				final String item = ((String) parent.getItemAtPosition(position));
				if (item != null && item.startsWith("Level 1")) {
					startActivityForResult(level1Intent, SUB_ACTIVITY_REQUEST_CODE_L1);
				} else if (item != null && item.startsWith("Level 2")) {
					startActivityForResult(level2Intent, SUB_ACTIVITY_REQUEST_CODE_L2);
				} else if (item != null && item.startsWith("Level 3")) {
					startActivityForResult(level3Intent, SUB_ACTIVITY_REQUEST_CODE_L3);
				}
			}

		});
	}
	
	/** Called when child activity finishes. */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if ((requestCode == SUB_ACTIVITY_REQUEST_CODE_L1 && resultCode == Level1Activity.EXIT_RETURN_CODE)
				|| (requestCode == SUB_ACTIVITY_REQUEST_CODE_L2 && resultCode == Level2Activity.EXIT_RETURN_CODE)
				|| (requestCode == SUB_ACTIVITY_REQUEST_CODE_L3 && resultCode == Level3Activity.EXIT_RETURN_CODE)) {
			setResult(EXIT_RETURN_CODE, null);
			finish(); // 'Exit' has been chosen in child activity's option menu -> also close parent(=this) activity
		}
	}

}
