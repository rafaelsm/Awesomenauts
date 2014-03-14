package br.com.rads.awesomenauts.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.adapter.UpgradeListAdapter;
import br.com.rads.awesomenauts.model.Upgrade;
import butterknife.ButterKnife;
import butterknife.InjectView;
import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by rafael_2 on 09/03/14.
 */
public class UpgradeCard extends Card {

    private Upgrade upgrade;

    @InjectView(R.id.upgrade_image_view)
    ImageView upgradeImage;

    @InjectView(R.id.upgrade_name_text_view)
    TextView upgradeName;

    @InjectView(R.id.upgrade_desc_text_view)
    TextView upgradeDescription;

    @InjectView(R.id.upgrade_flavor_text_view)
    TextView upgradeFlavor;

    @InjectView(R.id.upgrade_solar_text_view)
    TextView upgradeSolar;

    @InjectView(R.id.upgrade_inner_list)
    UpgradeListLayout upgradeList;

    public UpgradeCard(Context context, Upgrade upgrade) {
        this(context, R.layout.card_upgrade_inner_content);
        this.upgrade = upgrade;
    }

    public UpgradeCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        ButterKnife.inject(this, view);

        int imageResource = R.drawable.placeholder_skill;

        try {
            imageResource = getContext().getResources().getIdentifier(upgrade.getImage(), "drawable", getContext().getPackageName());
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Not found image drawable " + upgrade.getImage() + " for skill: " + upgrade.getName());
        }

        upgradeImage.setImageResource(imageResource);

        upgradeName.setText(upgrade.getName());
        upgradeSolar.setText(String.valueOf(upgrade.getSolar()));
        upgradeDescription.setText(upgrade.getDescription());
        upgradeFlavor.setText(upgrade.getFlavor());

        UpgradeListAdapter adapter = new UpgradeListAdapter(super.getContext(), upgrade.getLevelUpgrades());
        upgradeList.setAdapter(adapter);

    }

}
