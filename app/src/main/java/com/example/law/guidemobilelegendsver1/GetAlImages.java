package com.example.law.guidemobilelegendsver1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Belal on 9/19/2015.
 */
public class GetAlImages {

    public static String[] nama;
    public static String[] id_hero;

    public static Bitmap[] bitmaps;

    public static final String JSON_ARRAY="result";
    public static String[] imageURLs;
    public static final String IMAGE_URL = "hero_image";
    private String json;
    private JSONArray urls;

    public GetAlImages(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getImage(JSONObject jo){
        URL url;
        Bitmap image = null;
        try {
            url = new URL(jo.getString(IMAGE_URL));
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }  catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void getAllImages() throws JSONException {
        bitmaps = new Bitmap[urls.length()];
        nama = new String[urls.length()];
      id_hero = new String[urls.length()];


        for(int i=0;i<urls.length();i++){
            id_hero[i] = urls.getJSONObject(i).getString("id_hero");
            nama[i] = urls.getJSONObject(i).getString("hero_name");

           // imageURLs[i] = urls.getJSONObject(i).getString(IMAGE_URL);
            JSONObject jsonObject = urls.getJSONObject(i);
            bitmaps[i]= getImage(jsonObject);
        }
    }
}
