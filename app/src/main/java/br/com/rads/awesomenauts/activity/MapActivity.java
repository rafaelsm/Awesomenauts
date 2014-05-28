package br.com.rads.awesomenauts.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.com.rads.awesomenauts.fragment.MapDetailFragment;
import br.com.rads.awesomenauts.model.Map;
import br.com.rads.awesomenauts.util.DataManager;
import butterknife.ButterKnife;


public class MapActivity extends ActionBarActivity {

    private Map selectedMap;
    private MapDetailFragment mapDetailFragment = new MapDetailFragment();
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.inject(this);

        selectedMap = DataManager.getInstance().getMaps().get(getIntent().getIntExtra(Map.TAG,0));

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(selectedMap.getName());

        loadFragments();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.containerMapDetail,mapDetailFragment);
        transaction.commit();
    }

    private void loadFragments() {
        this.mapDetailFragment = new MapDetailFragment(selectedMap);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
