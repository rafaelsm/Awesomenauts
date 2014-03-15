package br.com.rads.awesomenauts.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.model.Attribute;
import butterknife.ButterKnife;
import butterknife.InjectView;
import it.gmariotti.cardslib.library.internal.CardExpand;

/**
 * Created by rafael_2 on 03/03/14.
 */
public class SkillExpandCard extends CardExpand {

    private Context context;
    private List<Attribute> attributes;

    @InjectView(R.id.linear_expanded_root)
    LinearLayout linear;

    public SkillExpandCard(Context context, List<Attribute> attributes) {
        super(context, R.layout.card_skill_expanded);
        this.attributes = attributes;
        this.context = context;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        ButterKnife.inject(this, view);

        for (int i = 0; i < attributes.size(); i++){

            Attribute a = attributes.get(i);

            LinearLayout wrapper = new LinearLayout(context);
            wrapper.setLayoutParams( new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            wrapper.setOrientation(LinearLayout.HORIZONTAL);
            wrapper.setWeightSum(2f);
            wrapper.setPadding(5,5,5,5);

            TextView attributeName = new TextView(context);
            attributeName.setText(a.getName());
            attributeName.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            attributeName.setTextColor(Color.WHITE);

            TextView attributeValues = new TextView(context);
            attributeValues.setText(a.getValue());
            attributeValues.setGravity(Gravity.RIGHT);
            attributeValues.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            attributeValues.setTextColor(Color.WHITE);

            wrapper.addView(attributeName);
            wrapper.addView(attributeValues);

            linear.addView(wrapper);

        }

    }
}
