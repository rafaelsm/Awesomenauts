package br.com.rads.awesomenauts.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.rads.awesomenauts.model.Awesomenaut;

/**
 * Created by rafael_2 on 19/02/14.
 */
public class DataManager {

    private static final String JSON_FILE = "awesomenauts.json";

    private List<Awesomenaut> awesomenauts = new ArrayList<Awesomenaut>();

    private static DataManager instance = new DataManager();
    private DataManager(){
    };

    public static DataManager getInstance(){
        return instance;
    }

    public static String loadJSONFromAssets(Context context){

        String json = null;

        try {
            InputStream is = context.getAssets().open(JSON_FILE);
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

}
