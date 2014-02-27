package br.com.rads.awesomenauts.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class NautFragment extends Fragment {

    public static final String SELECTED_NAUT_ID = "naut_id";

    @InjectView(R.id.naut_name_textview)
    TextView nautName;

    @InjectView(R.id.naut_backstory_textview)
    TextView nautBackstory;

    private Awesomenaut awesomenaut;

    /**
     * Construtor vazio, necessário para instanciar o fragment em caso de rotação
     */
    public NautFragment(){
    }

    public NautFragment(Awesomenaut awesomenaut) {

        this.awesomenaut = awesomenaut;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Pegar o NAUT que vem com a key specificada, vou pegar o swiggins só pra exibir algo
        if(awesomenaut == null)
            awesomenaut = DataManager.getInstance().getAwesomenauts().get(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_naut_detail,container,false);
        ButterKnife.inject(this, view);

        nautName.setText( awesomenaut.getName() );
        nautBackstory.setText( awesomenaut.getBackstory() );

        return view;
    }
}
