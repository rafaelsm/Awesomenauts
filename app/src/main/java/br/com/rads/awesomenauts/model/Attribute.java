package br.com.rads.awesomenauts.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael_2 on 18/02/14.
 */
public class Attribute {

    private String name;
    private float value;
    private Format format;

    public static List<Attribute> parseJSONArray(JSONArray attributesArray) throws JSONException {

        List<Attribute> attributes = new ArrayList<Attribute>();

        for (int i = 0; i < attributesArray.length(); i++){

            JSONObject attributeJSON =  attributesArray.getJSONObject(i);

            Attribute attribute = new Attribute();
            attribute.name = attributeJSON.getString("name");
            attribute.value = (float) attributeJSON.getDouble("value");
            attribute.format = Format.valueOf(attributeJSON.getString("format").toUpperCase());

            attributes.add(attribute);

        }

        return attributes;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public Format getFormat() {
        return format;
    }
}
