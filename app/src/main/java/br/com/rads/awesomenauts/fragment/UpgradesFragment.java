package br.com.rads.awesomenauts.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.adapter.SpinnerUpgradesAdapter;
import br.com.rads.awesomenauts.model.Awesomenaut;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by rafael_2 on 01/03/14.
 */
public class UpgradesFragment extends Fragment {

    private Awesomenaut awesomenaut;

    @InjectView(R.id.upgrades_spinner)
    Spinner spinner;

    public UpgradesFragment() {
    }

    public UpgradesFragment(Awesomenaut awesomenaut){
        this.awesomenaut = awesomenaut;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_naut_upgrades, container, false);
        ButterKnife.inject(this,view);

        loadSpinnerItems();

        return view;
    }

    private void loadSpinnerItems() {

        SpinnerUpgradesAdapter adapter = new SpinnerUpgradesAdapter(getActivity(), R.layout.spinner_upgrade_row, awesomenaut.getSkills());
        spinner.setAdapter(adapter);
    }
}
