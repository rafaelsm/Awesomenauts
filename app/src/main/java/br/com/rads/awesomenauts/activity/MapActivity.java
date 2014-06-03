package br.com.rads.awesomenauts.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import br.com.rads.awesomenauts.fragment.MapDetailFragment;
import br.com.rads.awesomenauts.model.Map;
import br.com.rads.awesomenauts.util.DataManager;
import butterknife.ButterKnife;


public class MapActivity extends ActionBarActivity {

    private Map selectedMap;
    private MapDetailFragment mapDetailFragment = new MapDetailFragment();
    private ActionBar actionBar;
    private static int selectedMapIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.inject(this);

        if(savedInstanceState == null) {
            if(getIntent().hasExtra(Map.TAG)) {
                selectedMapIndex = getIntent().getIntExtra(Map.TAG, 0);
            }
        }else{
            selectedMapIndex = savedInstanceState.getInt(Map.TAG, 0);
        }

        selectedMap = DataManager.getInstance().getMaps().get(selectedMapIndex);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(selectedMap.getName());

        loadFragments();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.containerMapDetail,mapDetailFragment);
        transaction.commit();

    }

    private void loadFragments() {
        this.mapDetailFragment = new MapDetailFragment(selectedMap);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Map.TAG, selectedMapIndex);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
