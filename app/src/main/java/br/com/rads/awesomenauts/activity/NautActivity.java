package br.com.rads.awesomenauts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import br.com.rads.awesomenauts.fragment.NautFragment;

/**
 * Created by rafael_2 on 25/02/14.
 */
public class NautActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naut);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null){

            Bundle arguments = new Bundle();
            arguments.putString(NautFragment.SELECTED_NAUT_ID, getIntent().getStringExtra(NautFragment.SELECTED_NAUT_ID));

            NautFragment nautFragment = new NautFragment();
            nautFragment.setArguments(arguments);

            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragManager.beginTransaction();
            transaction.add(R.id.nauts_detail_container, nautFragment);
            transaction.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            NavUtils.navigateUpTo(this, new Intent(this,MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
