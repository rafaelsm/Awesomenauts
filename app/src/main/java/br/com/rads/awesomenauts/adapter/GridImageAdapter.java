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

import java.util.ArrayList;
import java.util.List;

import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.model.Awesomenaut;
import butterknife.ButterKnife;
import butterknife.InjectView;

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

        ViewHolder holder;

        if (convertView == null){

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.grid_cell, parent, false);

            //TODO: verificar porque nao consigo ajustar as imagens da forma correta
            convertView.setLayoutParams( new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.image.setImageResource(getImageForNaut(awesomenauts.get(position)));

        holder.text.setText(awesomenauts.get(position).getName());

        return convertView;
    }

    private int getImageForNaut(Awesomenaut awesomenaut) {
        int drawable = context.getResources().getIdentifier(awesomenaut.getDrawableName(), "drawable", context.getPackageName());

        if(drawable == 0)
            drawable = R.drawable.placeholder;

        return drawable;
    }

    static class ViewHolder{

        @InjectView(R.id.cell_image)
        ImageView image;

        @InjectView(R.id.cell_text)
        TextView text;

        public ViewHolder(View view){
            ButterKnife.inject(this, view);
        }

    }
}
