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

import br.com.rads.awesomenauts.fragment.MultiPaneMenuFragment;
import br.com.rads.awesomenauts.fragment.NautsFragment;
import br.com.rads.awesomenauts.model.Awesomenaut;
import br.com.rads.awesomenauts.util.DataManager;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, MultiPaneMenuFragment.MultiPaneMenuListener {

    private static final String TAG = "MAIN_ACTIVITY";

    /**
     * Fragments
     */
    private NavigationDrawerFragment navigationDrawerFragment;
    private NautsFragment nautsFragment;

    /**
     * Variables
     */
    private CharSequence activityTitle;
    private List<Awesomenaut> awesomenauts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        if (navigationDrawerFragment != null) {
            navigationDrawerFragment.setUp(
                    R.id.navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout));
        }

        activityTitle = getTitle();

        loadAwesomenauts();
        loadFragments();

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
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the activity_main_two_pane content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position){
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, nautsFragment)
                        .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        .commit();
                break;
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                activityTitle = getString(R.string.title_section1);
                break;
            case 2:
                activityTitle = getString(R.string.title_section2);
                break;
            case 3:
                activityTitle = getString(R.string.title_section3);
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
        if (id.equalsIgnoreCase(getString(R.string.title_section2))){
            Bundle arguments = new Bundle();
            arguments.putString(MultiPaneMenuFragment.STATE_ACTIVATED_POSITION,id);

            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragManager.beginTransaction();
            transaction.replace(R.id.nauts_detail_container, nautsFragment);
            transaction.commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
