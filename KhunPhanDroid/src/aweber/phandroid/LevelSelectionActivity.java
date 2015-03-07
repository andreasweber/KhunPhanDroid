package aweber.phandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LevelSelectionActivity extends Activity {

	public static final int EXIT_RETURN_CODE = 2000;

	private static final String LEVEL_0 = "Level 0";
	private static final String LEVEL_1 = "Level 1";
	private static final String LEVEL_2 = "Level 2";
	private static final String LEVEL_3 = "Level 3";
	private static final String LEVEL_4 = "Level 4";
	private static final String LEVEL_5 = "Level 5";
	private static final String LEVEL_6 = "Level 6";

	private static final int SUB_ACTIVITY_REQUEST_CODE_L0 = 20;
	private static final int SUB_ACTIVITY_REQUEST_CODE_L1 = 21;
	private static final int SUB_ACTIVITY_REQUEST_CODE_L2 = 22;
	private static final int SUB_ACTIVITY_REQUEST_CODE_L3 = 23;
	private static final int SUB_ACTIVITY_REQUEST_CODE_L4 = 24;
	private static final int SUB_ACTIVITY_REQUEST_CODE_L5 = 25;
	private static final int SUB_ACTIVITY_REQUEST_CODE_L6 = 26;

	private List<Map<String, String>> _levelSelectionData;

	private Map<String, Intent> _intents;
	
	/** keys for data. */
	private String[] _fromMapKeys;
	
	/** Ids of textfields where data values are written. */
	private int[] _toLayoutId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_selection);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		_intents = new HashMap<String, Intent>(7);
		_intents.put(LEVEL_0, new Intent(this, Level0Activity.class));
		_intents.put(LEVEL_1, new Intent(this, Level1Activity.class));
		_intents.put(LEVEL_2, new Intent(this, Level2Activity.class));
		_intents.put(LEVEL_3, new Intent(this, Level3Activity.class));
		_intents.put(LEVEL_4, new Intent(this, Level4Activity.class));
		_intents.put(LEVEL_5, new Intent(this, Level5Activity.class));
		_intents.put(LEVEL_6, new Intent(this, Level6Activity.class));

		_fromMapKeys = new String[] { "level", "details", "best" };
		_toLayoutId = new int[] { R.id.level_selection_row_text, R.id.level_selection_row_text_details,
				R.id.level_selection_row_best };
		
		createLevelList();
	}

	@Override
	/** overwrite to update level list, cause 'best solution' may have changed. */
	protected void onResume() {
		super.onResume();
		createLevelList();
	}

	private void createLevelList() {
		final Properties props = PropertiesHandler.loadProperties(this);
		final String best0 = props.getProperty(Level0Activity.PROP_BEST) != null ? props
				.getProperty(Level0Activity.PROP_BEST) : "---";
		final String best1 = props.getProperty(Level1Activity.PROP_BEST) != null ? props
				.getProperty(Level1Activity.PROP_BEST) : "---";
		final String best2 = props.getProperty(Level2Activity.PROP_BEST) != null ? props
				.getProperty(Level2Activity.PROP_BEST) : "---";
		final String best3 = props.getProperty(Level3Activity.PROP_BEST) != null ? props
				.getProperty(Level3Activity.PROP_BEST) : "---";
		final String best4 = props.getProperty(Level4Activity.PROP_BEST) != null ? props
				.getProperty(Level4Activity.PROP_BEST) : "---";
		final String best5 = props.getProperty(Level5Activity.PROP_BEST) != null ? props
				.getProperty(Level5Activity.PROP_BEST) : "---";
		final String best6 = props.getProperty(Level6Activity.PROP_BEST) != null ? props
				.getProperty(Level6Activity.PROP_BEST) : "---";

		_levelSelectionData = new ArrayList<Map<String, String>>();
		add(LEVEL_0, "level0_text", "(" + best0 + ")");
		add(LEVEL_1, "level1_text", "(" + best1 + ")");
		add(LEVEL_2, "level2_text", "(" + best2 + ")");
		add(LEVEL_3, "level3_text", "(" + best3 + ")");
		add(LEVEL_4, "level4_text", "(" + best4 + ")");
		add(LEVEL_5, "level5_text", "(" + best5 + ")");
		add(LEVEL_6, "level6_text", "(" + best6 + ")");

		final ListAdapter listAdapter = new SimpleAdapter(this, _levelSelectionData, R.layout.level_selection_row,
				_fromMapKeys, _toLayoutId);
		final ListView listview = (ListView) findViewById(R.id.listview_level_selection);
		listview.setAdapter(listAdapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				final Map<String, String> item = ((Map<String, String>) parent.getItemAtPosition(position));
				if (item != null && item.keySet().contains("level")) {
					final String level = item.get("level");
					final Intent intent = _intents.get(level);
					if (LEVEL_0.equals(level)) {
						startActivityForResult(intent, SUB_ACTIVITY_REQUEST_CODE_L0);
					} else if (LEVEL_1.equals(level)) {
						startActivityForResult(intent, SUB_ACTIVITY_REQUEST_CODE_L1);
					} else if (LEVEL_2.equals(level)) {
						startActivityForResult(intent, SUB_ACTIVITY_REQUEST_CODE_L2);
					} else if (LEVEL_3.equals(level)) {
						startActivityForResult(intent, SUB_ACTIVITY_REQUEST_CODE_L3);
					} else if (LEVEL_4.equals(level)) {
						startActivityForResult(intent, SUB_ACTIVITY_REQUEST_CODE_L4);
					} else if (LEVEL_5.equals(level)) {
						startActivityForResult(intent, SUB_ACTIVITY_REQUEST_CODE_L5);
					} else if (LEVEL_6.equals(level)) {
						startActivityForResult(intent, SUB_ACTIVITY_REQUEST_CODE_L6);
					}
				}
			}
		});
	}

	private void add(String levelText, String levelDetailsId, String bestText) {
		final int detailsId = getResources().getIdentifier(levelDetailsId, "string", getPackageName());
		final String detailsText = getResources().getString(detailsId);
		final Map<String, String> entry = new HashMap<String, String>();
		entry.put("level", levelText);
		entry.put("details", detailsText);
		entry.put("best", bestText);
		_levelSelectionData.add(entry);
	}

	/** Called when child activity finishes. */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if ((requestCode == SUB_ACTIVITY_REQUEST_CODE_L0 && resultCode == Level0Activity.EXIT_RETURN_CODE)
				|| (requestCode == SUB_ACTIVITY_REQUEST_CODE_L1 && resultCode == Level1Activity.EXIT_RETURN_CODE)
				|| (requestCode == SUB_ACTIVITY_REQUEST_CODE_L2 && resultCode == Level2Activity.EXIT_RETURN_CODE)
				|| (requestCode == SUB_ACTIVITY_REQUEST_CODE_L3 && resultCode == Level3Activity.EXIT_RETURN_CODE)
				|| (requestCode == SUB_ACTIVITY_REQUEST_CODE_L4 && resultCode == Level4Activity.EXIT_RETURN_CODE)
				|| (requestCode == SUB_ACTIVITY_REQUEST_CODE_L5 && resultCode == Level5Activity.EXIT_RETURN_CODE)
				|| (requestCode == SUB_ACTIVITY_REQUEST_CODE_L6 && resultCode == Level6Activity.EXIT_RETURN_CODE)) {
			setResult(EXIT_RETURN_CODE, null);
			finish(); // 'Exit' has been chosen in child activity's option menu -> also close parent(=this) activity
		}
	}
}
