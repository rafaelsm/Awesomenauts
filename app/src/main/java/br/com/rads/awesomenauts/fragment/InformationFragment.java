package br.com.rads.awesomenauts.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.model.Awesomenaut;
import br.com.rads.awesomenauts.util.DataManager;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by rafael_2 on 25/02/14.
 */
public class InformationFragment extends Fragment {

    public static final String SELECTED_NAUT_ID = "naut_id";

    @InjectView(R.id.naut_backstory_textview)
    TextView nautBackstoryTextView;

    @InjectView(R.id.health_textview)
    TextView healthTextView;

    @InjectView(R.id.move_speed_textview)
    TextView moveSpeedTextView;

    @InjectView(R.id.attack_type_textview)
    TextView attackTypeTextView;

    @InjectView(R.id.role_textview)
    TextView roleTextView;

    @InjectView(R.id.mobility_textview)
    TextView mobilityTextView;

    private Awesomenaut awesomenaut;

    /**
     * Empty constructor for rotation
     */
    public InformationFragment(){
    }

    public InformationFragment(Awesomenaut awesomenaut) {
        this.awesomenaut = awesomenaut;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(awesomenaut == null){
            awesomenaut = DataManager.getInstance().getAwesomenauts().get(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_naut_detail,container,false);
        ButterKnife.inject(this, view);

        nautBackstoryTextView.setText( awesomenaut.getBackstory() );
        healthTextView.setText(Html.fromHtml(getString(R.string.health) + awesomenaut.getStats().getHealthAsString()));
        moveSpeedTextView.setText(Html.fromHtml(getString(R.string.move_speed) + String.valueOf(awesomenaut.getStats().getMovementSpeed())));
        attackTypeTextView.setText(Html.fromHtml(getString(R.string.attack_type) + String.valueOf(awesomenaut.getStats().getAttackType())));
        roleTextView.setText(Html.fromHtml(getString(R.string.role) + awesomenaut.getStats().getRoles().toString()));
        mobilityTextView.setText(Html.fromHtml(getString(R.string.mobility) + awesomenaut.getStats().getMobility().toString()));

        return view;
    }
}
