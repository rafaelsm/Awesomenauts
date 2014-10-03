package br.com.rads.awesomenauts.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.model.LevelUpgrade;

/**
 * Created by rafael_2 on 09/03/14.
 */
public class UpgradeListAdapter extends ArrayAdapter<LevelUpgrade> {

    private Context context;
    private List<LevelUpgrade> levelUpgrades;

    public UpgradeListAdapter(Context context, List<LevelUpgrade> levelUpgrades) {
        super(context, 0, levelUpgrades);

        this.context = context;
        this.levelUpgrades = levelUpgrades;
    }

    @Override
    public int getCount() {

        int totalUpgrades = 0;
        String lastUpdateName = "";

        for (LevelUpgrade lu : levelUpgrades) {

            if (!lu.getAttrName().equalsIgnoreCase(lastUpdateName)) {
                totalUpgrades++;
            }

            lastUpdateName = lu.getAttrName();
        }

        return totalUpgrades;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.upgrade_list_item, parent, false);
        }

        LinearLayout linear = (LinearLayout) view.findViewById(R.id.levels_linear_layout);
        populateLevels(linear, levelUpgrades.get(position));

        return view;
    }

    private void populateLevels(LinearLayout linear, LevelUpgrade levelUpgrade) {

        LinearLayout wrapper = new LinearLayout(context);
        wrapper.setLayoutParams( new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        wrapper.setOrientation(LinearLayout.HORIZONTAL);
        wrapper.setWeightSum(2f);

        TextView upgradeName = new TextView(context);
        upgradeName.setText(levelUpgrade.getAttrName());
        upgradeName.setLayoutParams( new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1f));

        TextView upgradeValues = new TextView(context);
        upgradeValues.setText(levelUpgrade.getAttrValueAsString());
        upgradeValues.setGravity(Gravity.RIGHT);
        upgradeValues.setLayoutParams( new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1f));

        wrapper.addView(upgradeName);
        wrapper.addView(upgradeValues);

        linear.addView(wrapper);

    }
}
