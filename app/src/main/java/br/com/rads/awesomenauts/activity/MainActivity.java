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
import android.widget.Toast;

import br.com.rads.awesomenauts.fragment.NautsFragment;
import br.com.rads.awesomenauts.fragment.NautsListFragment;
import br.com.rads.awesomenauts.model.Awesomenaut;
import br.com.rads.awesomenauts.util.DataManager;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, NautsListFragment.NautsListListener {

    private static final String TAG = "MAIN_ACTIVITY";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;


    private NautsFragment nautsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        if (mNavigationDrawerFragment != null) {
            mNavigationDrawerFragment.setUp(
                    R.id.navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout));
        }

        mTitle = getTitle();

        nautsFragment = new NautsFragment();

        Log.d(TAG, "Starting json parser");
        long start = System.currentTimeMillis();
        String json = DataManager.loadJSONFromAssets(this.getApplicationContext());
        Awesomenaut.parseJSON(json);
        long end = System.currentTimeMillis();

        Log.d(TAG,"total time: " + ((end - start) / 1000));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the activity_main_two_pane content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (position == 1) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, nautsFragment)
                    .commit();
            return;
        }

        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mNavigationDrawerFragment != null && !mNavigationDrawerFragment.isDrawerOpen()) {
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
    public void onNautsListSelected(String id) {
        if (id.equalsIgnoreCase(getString(R.string.title_section1))){
            Bundle arguments = new Bundle();
            arguments.putString(NautsListFragment.STATE_ACTIVATED_POSITION,id);

            NautsFragment nautsFragment1 = new NautsFragment();

            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragManager.beginTransaction();
            transaction.replace(R.id.nauts_detail_container, nautsFragment1);
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
