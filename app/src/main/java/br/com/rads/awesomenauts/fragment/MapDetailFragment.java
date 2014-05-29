package br.com.rads.awesomenauts.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.rads.awesomenauts.activity.R;
import br.com.rads.awesomenauts.model.Map;
import br.com.rads.awesomenauts.model.MapFeature;
import butterknife.ButterKnife;
import butterknife.InjectView;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.view.CardView;

/**
 * Created by rafael_2 on 27/05/2014.
 */
public class MapDetailFragment extends Fragment {

    @InjectView(R.id.map_detail_thumb)
    ImageView thumbImageView;

    @InjectView(R.id.map_content_layout)
    LinearLayout contentLayout;

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
        setAboutMap();
        setFeatures();

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

    private void setAboutMap() {

        Card card = new Card(getActivity(), R.layout.card_map_about);

        CardView cardView = new CardView(getActivity());
        cardView.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        cardView.setCard(card);

        TextView description = (TextView) cardView.findViewById(R.id.map_about_text);
        description.setText(map.getDescription());

        contentLayout.addView(cardView);

    }

    private void setFeatures() {

        for (MapFeature feature : map.getMapFeatures()){

            CardView cardView = createCardView();
            configureCardView(cardView, feature);
            contentLayout.addView(cardView);

        }

    }

    private CardView createCardView() {

        Card card = new Card(getActivity(),R.layout.card_map_feature);

        CardView cardView = new CardView(getActivity());
        cardView.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        cardView.setCard(card);

        return cardView;
    }

    private void configureCardView(CardView cardView, MapFeature feature) {

        TextView title = (TextView) cardView.findViewById(R.id.feature_title_text);
        TextView description = (TextView) cardView.findViewById(R.id.feature_description);
        ImageView image = (ImageView) cardView.findViewById(R.id.feature_image);

        title.setText(feature.getName());
        description.setText(feature.getDescription());
        setFeatureImage(feature.getImage(),image);

    }

    private void setFeatureImage(String drawableName, ImageView imageView) {
        int imageResource = 0;

        try {
            imageResource = getResources().getIdentifier(drawableName, "drawable", getActivity().getPackageName());
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Not found image drawable " + drawableName );
        }

        if (imageResource == 0)
            imageResource = R.drawable.placeholder_image;

        imageView.setImageResource(imageResource);
    }

}
