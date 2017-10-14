package com.jonmid.ejercicio.Parser;

import com.jonmid.ejercicio.Models.PhotoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by software on 12/10/2017.
 */

public class PhotoJson {

    public static List<PhotoModel> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<PhotoModel> photoModelList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);

            PhotoModel photoModel = new PhotoModel();
            photoModel.setId(item.getInt("userId"));
            photoModel.setTitle(item.getString("title"));

            photoModel.setBody(item.getString("body"));

            photoModelList.add(photoModel);
        }
        return photoModelList;
    }

}
