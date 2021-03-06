package br.com.rads.awesomenauts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import br.com.rads.awesomenauts.fragment.MapsFragment;
import br.com.rads.awesomenauts.fragment.MultiPaneMenuFragment;
import br.com.rads.awesomenauts.fragment.NautsGridFragment;
import br.com.rads.awesomenauts.fragment.NavigationDrawerFragment;
import br.com.rads.awesomenauts.fragment.RonimoFragment;
import br.com.rads.awesomenauts.model.Awesomenaut;
import br.com.rads.awesomenauts.model.Map;
import br.com.rads.awesomenauts.util.DataManager;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, MultiPaneMenuFragment.MultiPaneMenuListener {

    private static final String TAG = "MAIN_ACTIVITY";
    public static final String SELECTED_DRAWER_ITEM = "select_item";

    /**
     * Fragments
     */
    private NavigationDrawerFragment navigationDrawerFragment;
    private NautsGridFragment nautsGridFragment = new NautsGridFragment();
    private MapsFragment mapsFragment = new MapsFragment();
    private RonimoFragment ronimoFragment = new RonimoFragment();

    /**
     * Variables
     */
    private boolean onTwoPane;
    private CharSequence activityTitle;
    private List<Awesomenaut> awesomenauts;
    private List<Map> maps;
    private MultiPaneMenuFragment multiPaneMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Load all data
         */
        loadAwesomenauts();
        loadMaps();
        loadFragments();

        /**
         * Start navigation drawer
         */
        navigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        /**
         * If in single pane(not tablet), setup navigation drawer
         */
        if (navigationDrawerFragment != null) {
            navigationDrawerFragment.setUp(
                    R.id.navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout));

            onNavigationDrawerItemSelected(getIntent().getIntExtra(MainActivity.SELECTED_DRAWER_ITEM, 0));

        } else {
            multiPaneMenuFragment = (MultiPaneMenuFragment) getSupportFragmentManager().findFragmentById(R.id.nauts_list_fragment);
            multiPaneMenuFragment.setActivatedOnItemClick(true);
            onPaneMenuSelected(getString(R.string.title_section_nauts));
            onTwoPane = true;

        }

        activityTitle = getTitle();

    }

    private void loadAwesomenauts() {
        Log.d(TAG, "Starting AWESOMENAUTS json parser");

        long start = System.currentTimeMillis();
        String json = DataManager.loadJSONFromAssets(this.getApplicationContext(), DataManager.JSON_FILE_AWESOMENAUTS);
        awesomenauts = Awesomenaut.parseJSON(json);
        DataManager.getInstance().setAwesomenauts(awesomenauts);
        long end = System.currentTimeMillis();

        Log.d(TAG, "total time: " + ((end - start) / 1000));
    }

    private void loadMaps() {

        Log.d(TAG, "Starting MAPS json parser");

        long start = System.currentTimeMillis();
        String json = DataManager.loadJSONFromAssets(this.getApplicationContext(), DataManager.JSON_FILE_MAPS);
        maps = Map.parseJSON(json);
        DataManager.getInstance().setMaps(maps);
        long end = System.currentTimeMillis();

        Log.d(TAG, "total time: " + ((end - start) / 1000));

    }

    private void loadFragments() {

        nautsGridFragment = new NautsGridFragment();
        nautsGridFragment.setAwesomenauts(awesomenauts);

        mapsFragment = new MapsFragment();
        mapsFragment.setMaps(maps);

        ronimoFragment = new RonimoFragment();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the activity_main_two_pane content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position) {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.containerMapDetail, nautsGridFragment)
                        .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.containerMapDetail, mapsFragment)
                        .commit();
                break;

            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.containerMapDetail, ronimoFragment)
                        .commit();
                break;
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                activityTitle = getString(R.string.title_section_nauts);
                getIntent().putExtra(MainActivity.SELECTED_DRAWER_ITEM, 0);
                break;
            case 1:
                activityTitle = getString(R.string.title_section_maps);
                getIntent().putExtra(MainActivity.SELECTED_DRAWER_ITEM, 1);
                break;
            case 2:
                activityTitle = getString(R.string.title_section_ronimo);
                getIntent().putExtra(MainActivity.SELECTED_DRAWER_ITEM, 2);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(activityTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if ((navigationDrawerFragment != null && !navigationDrawerFragment.isDrawerOpen()) || isOnTwoPane()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent i = new Intent(this, AboutAppActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPaneMenuSelected(String id) {

        Fragment fragmentToInsert = new Fragment();

        if (id.equalsIgnoreCase(getString(R.string.title_section_ronimo))) {
            fragmentToInsert = ronimoFragment;
        } else if (id.equalsIgnoreCase(getString(R.string.title_section_nauts))) {
            fragmentToInsert = nautsGridFragment;
        } else if (id.equalsIgnoreCase(getString(R.string.title_section_maps))) {
            fragmentToInsert = mapsFragment;
        }

        Bundle arguments = new Bundle();
        arguments.putString(MultiPaneMenuFragment.STATE_ACTIVATED_POSITION, id);

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();
        transaction.replace(R.id.nauts_detail_container, fragmentToInsert);
        transaction.commit();
    }

    public boolean isOnTwoPane() {
        return onTwoPane;
    }

}
