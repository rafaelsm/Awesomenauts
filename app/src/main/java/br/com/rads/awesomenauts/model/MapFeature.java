package br.com.rads.awesomenauts.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael_2 on 25/05/2014.
 */
public class MapFeature {

    private String name;
    private String description;

    public static List<MapFeature> parseJSONArray(JSONArray jsonArray) throws JSONException {

        List<MapFeature> mapFeatures = new ArrayList<MapFeature>();

        for (int i = 0; i < jsonArray.length(); i++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            MapFeature mapFeature = new MapFeature();
            mapFeature.setName(jsonObject.getString("name"));
            mapFeature.setDescription(jsonObject.getString("description"));

            mapFeatures.add(mapFeature);

        }

        return mapFeatures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {

        StringBuilder imageName = new StringBuilder( "map_feature_" );
        imageName.append(this.name.toLowerCase().replace(" ", "_").replace("-", "_").replace("ø", "o").replace("&", "and")) ;

        return imageName.toString();

    }

}
