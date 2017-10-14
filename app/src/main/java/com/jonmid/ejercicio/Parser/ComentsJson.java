package com.jonmid.ejercicio.Parser;

import com.jonmid.ejercicio.Models.ComentsModel;
import com.jonmid.ejercicio.Models.PhotoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by software on 12/10/2017.
 */

public class ComentsJson {

    public static List<ComentsModel> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<ComentsModel> comentsModelList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);

            ComentsModel comentsModel = new ComentsModel();
            comentsModel.setId(item.getInt("userId"));
            comentsModel.setEmail(item.getString("email"));
            comentsModel.setBody(item.getString("body"));

            comentsModelList.add(comentsModel);
        }
        return comentsModelList;
    }

}
