package br.com.rads.awesomenauts.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.model.Awesomenaut;

/**
 * Created by rafael_2 on 17/02/14.
 */
public class GridImageAdapter extends BaseAdapter {

    private Context context;
    private List<Awesomenaut> awesomenauts;

    public GridImageAdapter(Context context, List<Awesomenaut> awesomenauts){
        this.context = context;
        this.awesomenauts = awesomenauts;
    }

    @Override
    public int getCount() {
        return awesomenauts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridViewCell;

        if (convertView == null){

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            gridViewCell = inflater.inflate(R.layout.grid_cell, parent, false);

            //TODO: verificar porque nao consigo ajustar as imagens da forma correta
            gridViewCell.setLayoutParams( new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 540));

        } else {
            gridViewCell = convertView;
        }

        ImageView imageView = (ImageView) gridViewCell.findViewById(R.id.cell_image);
        imageView.setImageResource(getImageForNaut(awesomenauts.get(position)));

        TextView textView = (TextView) gridViewCell.findViewById(R.id.cell_text);
        textView.setText(awesomenauts.get(position).getName());

        return gridViewCell;
    }

    private int getImageForNaut(Awesomenaut awesomenaut) {
        int drawable = context.getResources().getIdentifier(awesomenaut.getDrawableName(), "drawable", context.getPackageName());
        return drawable;
    }
}
