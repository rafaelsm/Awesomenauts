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
import br.com.rads.awesomenauts.model.Map;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by rafael_2 on 27/05/2014.
 */
public class MapDetailFragment extends Fragment {

    @InjectView(R.id.map_detail_thumb)
    ImageView thumbImageView;

    @InjectView(R.id.map_about_textview)
    TextView aboutTextView;

    private static final String TAG = "MAP_DETAIL_FRAGMENT";
    private Map map;

    public MapDetailFragment(){
        super();
    }

    public MapDetailFragment(Map selectedMap) {
        super();
        this.map = selectedMap;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map_detail,container,false);
        ButterKnife.inject(this,view);

        setThumbImage();
        aboutTextView.setText(map.getDescription());

        return view;
    }

    private void setThumbImage() {
        int imageResource = 0;

        try {
            imageResource = getResources().getIdentifier(map.getThumbnail(), "drawable", getActivity().getPackageName());
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Not found image drawable " + (map.getThumbnail() + " for naut: " + map.getName()));
        }

        if (imageResource == 0)
            imageResource = R.drawable.placeholder_image;

        thumbImageView.setImageResource(imageResource);
    }
}
