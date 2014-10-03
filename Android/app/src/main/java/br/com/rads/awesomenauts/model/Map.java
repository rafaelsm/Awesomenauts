package br.com.rads.awesomenauts.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael_2 on 25/05/2014.
 */
public class Map {

    public static final String TAG = "map";

    private String name;
    private String description;
    private String dropPodPathLink;
    private List<MapFeature> mapFeatures;
    private List<String> tipsAndTricks;
    private List<String> trivia;

    public static List<Map> parseJSON(String json) {

        List<Map> allMaps = new ArrayList<Map>();

        try {

            JSONArray jsonArray = new JSONArray(json);

            for ( int i = 0; i < jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Map map = new Map();
                map.setName(jsonObject.getString("name"));
                map.setDescription(jsonObject.getString("description"));
                map.setDropPodPathLink("dropPodPath");
                map.setMapFeatures(parseMapFeature(jsonObject.getJSONArray("mapFeatures")));
                map.setTipsAndTricks(parseStringArray(jsonObject.getJSONArray("tipsAndTricks")));
                map.setTrivia(parseStringArray(jsonObject.getJSONArray("trivia")));

                allMaps.add(map);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allMaps;
    }

    private static List<MapFeature> parseMapFeature(JSONArray mapFeatures) throws JSONException {
        return MapFeature.parseJSONArray(mapFeatures);
    }

    private static List<String> parseStringArray(JSONArray stringArray) throws JSONException {

        List<String> strings = new ArrayList<String>();

        for (int i = 0; i < stringArray.length(); i++){
            strings.add(stringArray.getString(i));
        }

        return strings;
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

    public String getThumbnail() {

        StringBuilder drawableName = new StringBuilder( "map_thumb_" );
        drawableName.append( this.name.toLowerCase().replace(" ", "_").replace("-", "_").replace("ø","o").replace("&","and") ) ;
        return drawableName.toString();

    }

    public String getImage() {

        StringBuilder drawableName = new StringBuilder( "mp_fu_" );
        drawableName.append( this.name.toLowerCase().replace(" ", "_").replace("-", "_").replace("ø","o").replace("&","and") ) ;
        return drawableName.toString();

    }

    public String getDropPodPathLink() {
        return dropPodPathLink;
    }

    public void setDropPodPathLink(String dropPodPathLink) {
        this.dropPodPathLink = dropPodPathLink;
    }

    public List<MapFeature> getMapFeatures() {
        return mapFeatures;
    }

    public void setMapFeatures(List<MapFeature> mapFeatures) {
        this.mapFeatures = mapFeatures;
    }

    public List<String> getTipsAndTricks() {
        return tipsAndTricks;
    }

    public void setTipsAndTricks(List<String> tipsAndTricks) {
        this.tipsAndTricks = tipsAndTricks;
    }

    public List<String> getTrivia() {
        return trivia;
    }

    public void setTrivia(List<String> trivia) {
        this.trivia = trivia;
    }

}
