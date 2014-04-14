package br.com.rads.awesomenauts.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import br.com.rads.awesomenauts.fragment.InformationFragment;
import br.com.rads.awesomenauts.fragment.SkillsFragment;
import br.com.rads.awesomenauts.fragment.UpgradesFragment;
import br.com.rads.awesomenauts.model.Awesomenaut;
import br.com.rads.awesomenauts.util.DataManager;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by rafael_2 on 25/02/14.
 */
public class NautActivity extends ActionBarActivity implements ActionBar.TabListener{

    private static final String TAG = "NAUT_ACTIVITY";
    private static final int TABS_QUANTITY = 3;

    private ActionBar actionBar;
    private TabsPageAdapter tabsPageAdapter;
    public ViewPager viewPager;

    private InformationFragment informationFragment;
    private SkillsFragment skillsFragment;
    private UpgradesFragment upgradesFragment;

    private Awesomenaut awesomenaut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naut);
        ButterKnife.inject(this);

        /**
         * Get Naut
         */
        awesomenaut = DataManager.getInstance().getAwesomenauts().get(getIntent().getIntExtra(Awesomenaut.TAG, 0));

        /**
         * Config navigation mode to use viewpager
         */
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setStackedBackgroundDrawable( new ColorDrawable(Color.parseColor("#e4e4e4")));

        loadFragments();

        /**
         * Start tabs adapter for return the fragment in each tab
         */
        tabsPageAdapter = new TabsPageAdapter(getSupportFragmentManager());

        /**
         * Start viewpager for swipe
         */
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(tabsPageAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        loadTabs();

        actionBar.setTitle(awesomenaut.getName());

//        if (savedInstanceState == null) {
//
//            Bundle arguments = new Bundle();
//            arguments.putString(InformationFragment.SELECTED_NAUT_ID, getIntent().getStringExtra(InformationFragment.SELECTED_NAUT_ID));
//
//            InformationFragment informationFragment = new InformationFragment();
//            informationFragment.setArguments(arguments);
//
//            FragmentManager fragManager = getSupportFragmentManager();
//            FragmentTransaction transaction = fragManager.beginTransaction();
//            transaction.add(R.id.nauts_detail_container, informationFragment);
//            transaction.commit();
//        }

    }

    private void loadFragments() {
        informationFragment = new InformationFragment(awesomenaut);
        skillsFragment = new SkillsFragment(awesomenaut);
        upgradesFragment = new UpgradesFragment(awesomenaut);
    }

    private void loadTabs() {
        Tab t1 = actionBar.newTab();
        t1.setText(tabsPageAdapter.getPageTitle(0));
        t1.setTabListener(this);

        Tab t2 = actionBar.newTab();
        t2.setText(tabsPageAdapter.getPageTitle(1));
        t2.setTabListener(this);

        Tab t3 = actionBar.newTab();
        t3.setText(tabsPageAdapter.getPageTitle(2));
        t3.setTabListener(this);

        actionBar.addTab(t1);
        actionBar.addTab(t2);
        actionBar.addTab(t3);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     *
     * TabListener Methods
     *
     */
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     *
     * Inner class for adapter
     *
     */
    private class TabsPageAdapter extends FragmentPagerAdapter {

        public TabsPageAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = new Fragment();

            switch (position) {
                case 0:
                    fragment = informationFragment;
                    break;
                case 1:
                    fragment = skillsFragment;
                    break;
                case 2:
                    fragment = upgradesFragment;
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return TABS_QUANTITY;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            String title = "";

            switch (position) {
                case 0:
                    title = getString(R.string.information);
                    break;
                case 1:
                    title = getString(R.string.skills);
                    break;
                case 2:
                    title = getString(R.string.upgrades);
                    break;
            }

            return title.toUpperCase();
        }
    }
}
