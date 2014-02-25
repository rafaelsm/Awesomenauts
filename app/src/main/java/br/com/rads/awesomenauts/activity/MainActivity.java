package br.com.rads.awesomenauts.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.rads.awesomenauts.fragment.MapsFragment;
import br.com.rads.awesomenauts.fragment.MultiPaneMenuFragment;
import br.com.rads.awesomenauts.fragment.NautsFragment;
import br.com.rads.awesomenauts.fragment.NewsFragment;
import br.com.rads.awesomenauts.model.Awesomenaut;
import br.com.rads.awesomenauts.util.DataManager;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, MultiPaneMenuFragment.MultiPaneMenuListener {

    private static final String TAG = "MAIN_ACTIVITY";

    /**
     * Fragments
     */
    private NavigationDrawerFragment navigationDrawerFragment;
    private NautsFragment nautsFragment = new NautsFragment();
    private NewsFragment newsFragment = new NewsFragment();
    private MapsFragment mapsFragment = new MapsFragment();

    /**
     * Variables
     */
    private CharSequence activityTitle;
    private List<Awesomenaut> awesomenauts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Load all data
         */
        loadAwesomenauts();
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
        }else{
            ((MultiPaneMenuFragment)getSupportFragmentManager().findFragmentById(R.id.nauts_list_fragment)).setActivatedOnItemClick(true);
        }

        activityTitle = getTitle();
    }


    private void loadAwesomenauts() {
        Log.d(TAG, "Starting json parser");

        long start = System.currentTimeMillis();
        String json = DataManager.loadJSONFromAssets(this.getApplicationContext());
        awesomenauts = Awesomenaut.parseJSON(json);
        long end = System.currentTimeMillis();

        Log.d(TAG,"total time: " + ((end - start) / 1000));
    }

    private void loadFragments() {
        nautsFragment = new NautsFragment(awesomenauts);
        newsFragment = new NewsFragment();
        mapsFragment = new MapsFragment();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the activity_main_two_pane content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position){
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, newsFragment)
                        .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, nautsFragment)
                        .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mapsFragment)
                        .commit();
                break;
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                activityTitle = getString(R.string.title_section_news);
                break;
            case 2:
                activityTitle = getString(R.string.title_section_nauts);
                break;
            case 3:
                activityTitle = getString(R.string.title_section_maps);
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
        if (navigationDrawerFragment != null && !navigationDrawerFragment.isDrawerOpen()) {
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPaneMenuSelected(String id) {

        Fragment fragmentToInsert = new Fragment();

        if( id.equalsIgnoreCase(getString(R.string.title_section_news))){
            fragmentToInsert = newsFragment;
        }
        else if (id.equalsIgnoreCase(getString(R.string.title_section_nauts))){
            fragmentToInsert = nautsFragment;
        }
        else if (id.equalsIgnoreCase(getString(R.string.title_section_maps))){
            fragmentToInsert = mapsFragment;
        }

        Bundle arguments = new Bundle();
        arguments.putString(MultiPaneMenuFragment.STATE_ACTIVATED_POSITION,id);

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();
        transaction.replace(R.id.nauts_detail_container, fragmentToInsert);
        transaction.commit();
    }

}
