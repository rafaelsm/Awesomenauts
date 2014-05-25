package br.com.rads.awesomenauts.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.rads.awesomenauts.activity.MainActivity;
import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.adapter.MapListAdapter;
import br.com.rads.awesomenauts.model.Map;
import butterknife.ButterKnife;

/**
 * Created by rafael_2 on 24/02/14.
 */
    public class MapsFragment extends ListFragment {

    private List<Map> maps;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity)activity).onSectionAttached(1);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        ButterKnife.inject(this,view);

        maps = Map.getDummyList();
        setListAdapter( new MapListAdapter(getActivity(), maps));

        return view;
    }

    public List<Map> getMaps() {
        return maps;
    }

    public void setMaps(List<Map> maps) {
        this.maps = maps;
    }
}
