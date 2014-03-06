package br.com.rads.awesomenauts.model;


import com.google.common.base.Splitter;
import com.google.common.base.Strings;

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
    private String value;

    public static List<Attribute> parseJSONArray(JSONArray attributesArray) throws JSONException {

        List<Attribute> attributes = new ArrayList<Attribute>();

        for (int i = 0; i < attributesArray.length(); i++){

            JSONObject attributeJSON =  attributesArray.getJSONObject(i);

            Attribute attribute = new Attribute();
            attribute.name = attributeJSON.getString("name");
            attribute.value = attributeJSON.getString("value");

            attributes.add(attribute);

        }

        return attributes;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder(name);
        sb.append("\t\t\t\t");
        sb.append(String.valueOf(value));

        return sb.toString();
    }
}
