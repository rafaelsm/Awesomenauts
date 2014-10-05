package br.com.rads.awesomenauts.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.model.Map;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by rafael_2 on 25/05/2014.
 */
public class MapListAdapter extends BaseAdapter {

    private Context context;
    private List<Map> maps;

    public MapListAdapter(Context context, List<Map> maps) {
        this.context = context;
        this.maps = maps;
    }

    @Override
    public int getCount() {
        return maps.size();
    }

    @Override
    public Object getItem(int position) {
        return maps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.map_cell, parent, false);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Map map = this.maps.get(position);

        viewHolder.position = position;
        viewHolder.mapNameTextView.setText(map.getName());
        changeTextSide(viewHolder.mapNameTextView, position);
        new LoadImage(position, viewHolder).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,getThumbForMap(map));

        return convertView;
    }

    private void changeTextSide(TextView textView, int position) {

        int sizeInDp = 10;
        float scale = context.getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (sizeInDp*scale + 0.5f);

        if (position % 2 == 0){
            textView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            textView.setPadding(dpAsPixels,0,0,0);
        }else{
            textView.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
            textView.setPadding(0,0,dpAsPixels,0);
        }

    }

    private int getThumbForMap(Map map) {
        int drawable = context.getResources().getIdentifier(map.getThumbnail(), "drawable", context.getPackageName());

        if (drawable == 0)
            drawable = R.drawable.map_thumb_placeholder;

        return drawable;
    }

    static class ViewHolder {

        @InjectView(R.id.map_cell_image)
        ImageView mapThumbImageView;

        @InjectView(R.id.map_cell_text)
        TextView mapNameTextView;

        int position;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    class LoadImage extends AsyncTask<Integer,Void, Drawable> {

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
                holder.mapThumbImageView.setImageDrawable(drawable);
        }
    }
}
