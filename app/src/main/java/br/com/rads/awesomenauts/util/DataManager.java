package br.com.rads.awesomenauts.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rafael_2 on 19/02/14.
 */
public class DataManager {

    private static final String JSON_FILE = "awesomenauts.json";

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
}
