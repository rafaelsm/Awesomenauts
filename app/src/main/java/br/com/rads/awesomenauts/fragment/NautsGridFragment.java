package br.com.rads.awesomenauts.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

import br.com.rads.awesomenauts.activity.MainActivity;
import br.com.rads.awesomenauts.activity.NautActivity;
import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.adapter.GridImageAdapter;
import br.com.rads.awesomenauts.model.Awesomenaut;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by rafael_2 on 17/02/14.
 */
public class NautsGridFragment extends Fragment {

    private List<Awesomenaut> awesomenauts = null;

    @InjectView(R.id.gridView)
    public GridView grid;

    public NautsGridFragment(List<Awesomenaut> awesomenauts){
        this.awesomenauts = awesomenauts;
    }

    public NautsGridFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity)activity).onSectionAttached(2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_nauts_grid, container, false);
        ButterKnife.inject(this,rootView);

        grid.setAdapter( new GridImageAdapter(this.getActivity(),awesomenauts));
        grid.setOnItemClickListener( new GridClickListener());

        return rootView;
    }

    private class GridClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            MainActivity mainActivity = (MainActivity) getActivity();

            if (mainActivity.isOnTwoPane()){

                Bundle bundle = new Bundle();
                bundle.putString( InformationFragment.SELECTED_NAUT_ID, awesomenauts.get(position).getName());

                InformationFragment informationFragment = new InformationFragment(awesomenauts.get(position));
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nauts_detail_container, informationFragment);
                fragmentTransaction.commit();

            } else{
                Intent i = new Intent(getActivity(), NautActivity.class);
                startActivity(i);
            }
        }
    }

}
