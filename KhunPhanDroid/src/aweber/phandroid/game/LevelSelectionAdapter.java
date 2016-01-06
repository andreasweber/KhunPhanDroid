package aweber.phandroid.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aweber.phandroid.Assets;
import aweber.phandroid.R;

public class LevelSelectionAdapter extends SimpleAdapter {

    private Context _context;
    private List<Map<String, String>> _data;

    public LevelSelectionAdapter(Context context, List<Map<String, String>> data,
                                 int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        _context = context;
        _data = data;
    }

    public View getView(int position, View view, ViewGroup parent){

        View v = view;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.level_selection_row, null);
        }
        TextView viewHeader = (TextView) v.findViewById(R.id.level_selection_row_text);
        viewHeader.setText(_data.get(position).get("level"));
        viewHeader.setTypeface(Assets.ttf);

        TextView viewBest = (TextView) v.findViewById(R.id.level_selection_row_best);
        viewBest.setText(_data.get(position).get("best"));
        viewBest.setTypeface(Assets.ttf);

        TextView viewDetails = (TextView) v.findViewById(R.id.level_selection_row_text_details);
        viewDetails.setText(_data.get(position).get("details"));
        viewDetails.setTypeface(Assets.ttf);

        return v;
    }
}
