package br.com.rads.awesomenauts.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.rads.awesomenauts.model.Awesomenaut;
import br.com.rads.awesomenauts.model.Map;

/**
 * Created by rafael_2 on 19/02/14.
 */
public class DataManager {

    public static final String JSON_FILE_AWESOMENAUTS = "awesomenauts.json";
    public static final String JSON_FILE_MAPS = "maps.json";

    private List<Awesomenaut> awesomenauts = new ArrayList<Awesomenaut>();
    private List<Map> maps = new ArrayList<Map>();

    private static DataManager instance = new DataManager();
    private DataManager(){
    };

    public static DataManager getInstance(){
        return instance;
    }

    public static String loadJSONFromAssets(Context context, String fileName){

        String json = null;

        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public List<Awesomenaut> getAwesomenauts() {
        return awesomenauts;
    }

    public void setAwesomenauts(List<Awesomenaut> awesomenauts) {
        this.awesomenauts = awesomenauts;
    }

    public List<Map> getMaps() {
        return maps;
    }

    public void setMaps(List<Map> maps) {
        this.maps = maps;
    }
}
