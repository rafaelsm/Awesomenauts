package br.com.rads.awesomenauts.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael_2 on 25/05/2014.
 */
public class Map {

    private String name;
    private String backgroundDescription;
    private String thumbnail;
    private String image;
    private String dropPodPathLink;
    private List<MapFeature> mapFeatures;
    private List<String> tipsAndTricks;
    private List<String> trivia;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackgroundDescription() {
        return backgroundDescription;
    }

    public void setBackgroundDescription(String backgroundDescription) {
        this.backgroundDescription = backgroundDescription;
    }

    public String getThumbnail() {

        StringBuilder drawableName = new StringBuilder( "mapthumb_" );
        drawableName.append( this.name.toLowerCase().replace(" ", "_").replace("-", "_").replace("ø","o").replace("&","and") ) ;
        return drawableName.toString();

    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public static List<Map> getDummyList() {

        List<Map> dummyMaps = new ArrayList<Map>();

        for (int i = 0; i < 4; i++){
            Map m = new Map();
            m.setName("map_" + i);

            dummyMaps.add(m);
        }

        return dummyMaps;
    }
}
