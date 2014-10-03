package br.com.rads.awesomenauts.adapter;

import android.app.Activity;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
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

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        final ViewHolder holder;

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

        Awesomenaut a = awesomenauts.get(position);
        holder.text.setText(a.getName());
        holder.position = position;

        new LoadImage(position, holder).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,getImageForNaut(a));


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

        int position;

        public ViewHolder(View view){
            ButterKnife.inject(this, view);
        }

    }

    class LoadImage extends AsyncTask<Integer,Void, Drawable>{

        private ViewHolder holder;
        private int position;

        public LoadImage(int position, ViewHolder holder){
            this.holder = holder;
            this.position = position;
        }

        @Override
        protected Drawable doInBackground(Integer... params) {

            Drawable d = context.getResources().getDrawable(params[0]);
            return d;
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            if (holder.position == position)
                holder.image.setImageDrawable(drawable);
        }
    }

}
