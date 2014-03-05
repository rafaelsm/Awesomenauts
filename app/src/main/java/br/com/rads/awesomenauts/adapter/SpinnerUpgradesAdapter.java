package br.com.rads.awesomenauts.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.model.Skill;
import br.com.rads.awesomenauts.util.Utils;

/**
 * Created by rafael_2 on 04/03/14.
 */
public class SpinnerUpgradesAdapter extends ArrayAdapter<Skill> {

    private static final String TAG = "SPINNER_UPGRADES_ADAPTER";

    private Context context;
    private List<Skill> skills = new ArrayList<Skill>();

    public SpinnerUpgradesAdapter(Context context, int resource, List<Skill> objects) {
        super(context, resource, objects);

        this.context = context;
        this.skills = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent, true);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent, false);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent, boolean addPadding) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.spinner_upgrade_row, parent, false);
        TextView rowTextView = (TextView) row.findViewById(R.id.row_upgrade_textview);

        if (addPadding) {
            int padding = Utils.getPadding(context.getResources().getDisplayMetrics().density, 4);
            rowTextView.setPadding(padding, padding, padding, padding);
        }

        Skill skill = skills.get(position);

        rowTextView.setText(skill.getName());

        int imageResource = 0;

        try {
            imageResource = context.getResources().getIdentifier(skill.getImage(), "drawable", context.getPackageName());
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Not found image drawable " + skill.getImage() + " for skill: " + skill.getName());
        }

        if (imageResource == 0)
            imageResource = R.drawable.placeholder_skill;

        Drawable resizedDrawable = Utils.resizeDrawable(context, imageResource, 0.7f);

        rowTextView.setCompoundDrawablesWithIntrinsicBounds(resizedDrawable, null, null, null);

        return row;
    }

}
