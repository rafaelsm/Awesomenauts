package br.com.rads.awesomenauts.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.rads.awesomenauts.activity.R;

/**
 * Created by rafael_2 on 20/02/14.
 */
public class NautsListFragment extends ListFragment {

    private static final String TAG = "NAUTS_LIST_FRAGMENT";
    public static final String STATE_ACTIVATED_POSITION = "activated_position";

    private int activatedPosition = ListView.INVALID_POSITION;
    private String[] listOptionArray;

    /**
     * Delegate
     */
    public interface NautsListListener {
        public void onNautsListSelected(String id);
    }

    private NautsListListener listener = dummyListener;

    private static NautsListListener dummyListener = new NautsListListener() {
        @Override
        public void onNautsListSelected(String id) {
            Log.d(TAG, "Selected: " + id);
        }
    };

    public NautsListFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof NautsListListener)) {
            throw new IllegalStateException(activity + " must implement NautsListListener");
        }

        listener = (NautsListListener) activity;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listOptionArray = new String[]{
                getString(R.string.title_section1),
                getString(R.string.title_section2),
                getString(R.string.title_section3),
        };

        setListAdapter(new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1, listOptionArray));

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setBackgroundColor(Color.parseColor("#222222"));
        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        listener = dummyListener;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (activatedPosition != ListView.INVALID_POSITION) {
            outState.putInt(STATE_ACTIVATED_POSITION, activatedPosition);
        }
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        listener.onNautsListSelected(listOptionArray[position]);
    }

    public void setActivatedOnItemClick(boolean active) {
        getListView().setChoiceMode(active ?
                ListView.CHOICE_MODE_SINGLE :
                ListView.CHOICE_MODE_NONE
        );
    }

    private void setActivatedPosition(int position) {

        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(activatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        activatedPosition = position;
    }
}
