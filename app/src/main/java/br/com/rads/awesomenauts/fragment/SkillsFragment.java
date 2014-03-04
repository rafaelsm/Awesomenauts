package br.com.rads.awesomenauts.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.model.Awesomenaut;
import br.com.rads.awesomenauts.model.Skill;
import butterknife.ButterKnife;
import butterknife.InjectView;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.view.CardView;

/**
 * Created by rafael_2 on 01/03/14.
 */
public class SkillsFragment extends Fragment {

    private static final String TAG = "SKILLS_FRAGMENT";
    private Awesomenaut awesomenaut;

    @InjectView(R.id.card_skill_1)
    CardView skill1;

    @InjectView(R.id.card_skill_2)
    CardView skill2;

    @InjectView(R.id.card_skill_3)
    CardView skill3;

    @InjectView(R.id.card_skill_4)
    CardView skill4;

    public SkillsFragment() {
    }

    public SkillsFragment(Awesomenaut awesomenaut) {
        this.awesomenaut = awesomenaut;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_naut_skills, container, false);
        ButterKnife.inject(this, view);

        loadSkillsCard();

        return view;
    }

    private void loadSkillsCard() {

        skill1.setCard(new Card(getActivity(), R.layout.card_skill));
        skill2.setCard(new Card(getActivity(), R.layout.card_skill));
        skill3.setCard(new Card(getActivity(), R.layout.card_skill));
        skill4.setCard(new Card(getActivity(), R.layout.card_skill));

        configCardView(skill1, awesomenaut.getSkills().get(0));
        configCardView(skill2, awesomenaut.getSkills().get(1));
        configCardView(skill3, awesomenaut.getSkills().get(2));
        configCardView(skill4, awesomenaut.getSkills().get(3));

    }

    private void configCardView(CardView cardView, Skill skill) {

        ImageView image = (ImageView) cardView.findViewById(R.id.skill_image);
        TextView solar = (TextView) cardView.findViewById(R.id.skill_solar);
        TextView name = (TextView) cardView.findViewById(R.id.skill_name_textview);
        TextView description = (TextView) cardView.findViewById(R.id.skill_desc_textview);

        int imageResource = R.drawable.placeholder_skill;

        try {
            imageResource = getResources().getIdentifier(skill.getImage(), "drawable", getActivity().getPackageName());
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Not found image drawable " + skill.getImage() + " for skill: " + skill.getName());
        }

        image.setImageResource(imageResource);
        name.setText(skill.getName());
        description.setText(skill.getDescription());

        if(skill.getSolar() > 0){
            solar.setText(String.valueOf(skill.getSolar()));
        }else{
            solar.setVisibility(View.INVISIBLE);
        }

    }
}
