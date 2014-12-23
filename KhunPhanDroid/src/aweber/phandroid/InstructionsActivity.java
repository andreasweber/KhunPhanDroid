package aweber.phandroid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class InstructionsActivity extends Activity {
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instructions);

		// get the listview
		ExpandableListView expListView = (ExpandableListView) findViewById(R.id.listview_instructions);

		// preparing list data
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();
		
		add("Instructions_Goal", "Instructions_Goal_Text");
		add("Instructions_Rules", "Instructions_Rules_Text");
		add("Instructions_Menu", "Instructions_Menu_Text");
		add("Instructions_Levels", "Instructions_Levels_Text");
		add("Instructions_Save", "Instructions_Save_Text");
		add("Instructions_Legend", "Instructions_Legend_Text");

		ExpandableListAdapter listAdapter = new InstructionsExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
	}

	private void add(String instructionHeaderId, String instructionTextId) {
		int idTerm = getResources().getIdentifier(instructionHeaderId, "string", getPackageName());
		String term = getResources().getString(idTerm);

		int idText = getResources().getIdentifier(instructionTextId, "string", getPackageName());
		String text = getResources().getString(idText);

		listDataHeader.add(term);
		listDataChild.put(term, Arrays.asList(text));
	}

}
