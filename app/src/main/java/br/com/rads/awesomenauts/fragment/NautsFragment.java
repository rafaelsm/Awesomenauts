package br.com.rads.awesomenauts.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.adapter.GridImageAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by rafael_2 on 17/02/14.
 */
public class NautsFragment extends Fragment {

    @InjectView(R.id.gridView)
    public GridView grid;

    private Integer[] icons = new Integer[8];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0 ; i < 8 ; i++){
            icons[i] = R.drawable.placeholder;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_nauts, container, false);
        ButterKnife.inject(this,rootView);

        grid.setAdapter( new GridImageAdapter(this.getActivity(),icons));

        return rootView;
    }
}
