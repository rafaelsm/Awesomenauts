package br.com.rads.awesomenauts.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.adapter.SpinnerUpgradesAdapter;
import br.com.rads.awesomenauts.model.Awesomenaut;
import br.com.rads.awesomenauts.model.LevelUpgrade;
import br.com.rads.awesomenauts.model.Skill;
import br.com.rads.awesomenauts.model.Upgrade;
import br.com.rads.awesomenauts.view.UpgradeCard;
import butterknife.ButterKnife;
import butterknife.InjectView;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by rafael_2 on 01/03/14.
 */
public class UpgradesFragment extends Fragment {

    public static final String TAG = "UPGRADE_FRAGMENT";

    private Awesomenaut awesomenaut;

    @InjectView(R.id.upgrades_spinner)
    Spinner spinner;

    @InjectView(R.id.myList)
    CardListView cardListView;

    public UpgradesFragment() {
    }

    public UpgradesFragment(Awesomenaut awesomenaut) {
        this.awesomenaut = awesomenaut;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_naut_upgrades, container, false);
        ButterKnife.inject(this, view);

        loadSpinnerItems();
        setSpinnerListener();

        return view;
    }

    private void loadSpinnerItems() {

        SpinnerUpgradesAdapter adapter = new SpinnerUpgradesAdapter(getActivity(), R.layout.spinner_upgrade_row, awesomenaut.getSkills());
        spinner.setAdapter(adapter);

    }

    private void setSpinnerListener() {
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        loadCards(awesomenaut.getSkills().get(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }


    private void loadCards(Skill skill) {
        List<Card> upgradeCards = new ArrayList<Card>();

        for (Upgrade u : skill.getUpgrades()) {
            UpgradeCard card = new UpgradeCard(this.getActivity(), u);
            upgradeCards.add(card);
        }

        CardArrayAdapter arrayAdapter = new CardArrayAdapter(this.getActivity(), upgradeCards);
        cardListView.setAdapter(arrayAdapter);

        if (cardListView != null) {
            cardListView.setAdapter(arrayAdapter);
        }

    }

}
