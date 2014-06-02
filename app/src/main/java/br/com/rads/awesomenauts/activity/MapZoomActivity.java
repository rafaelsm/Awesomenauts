package br.com.rads.awesomenauts.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by rafael_2 on 02/06/2014.
 */
public class MapZoomActivity extends ActionBarActivity {

    public static final String MAP_ZOOM_DRAWABLE = "map_full_screen";

//    @InjectView(R.id.full_image)
    ImageView fullImage;

    private PhotoViewAttacher photoViewAttacher;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_zoom);
//        ButterKnife.inject(this);

        actionBar = getSupportActionBar();
        actionBar.setTitle("full image");

        int drawable = getIntent().getIntExtra(MAP_ZOOM_DRAWABLE,0);

        fullImage = (ImageView) findViewById(R.id.full_image);
        fullImage.setImageResource(drawable);

        photoViewAttacher = new PhotoViewAttacher(fullImage);
//        photoViewAttacher.update();
    }

}
