package com.codepath.apps.restclienttemplate.models;

import android.content.Entity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


//@Parcel
public class Tweet {

    public Tweet(){}

    public String body;
    public String createdAt;
    public User user;
    public Media media;
    public long id;

    //public String description;

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {

        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user=User.fromJson(jsonObject.getJSONObject("user"));
        tweet.id = jsonObject.getLong("id");

        if(jsonObject.has("extended_entities")) {
            Log.i("found", "extended");
            tweet.media = Media.fromJson(jsonObject.getJSONObject("extended_entities"));

        }
        else
        {
            tweet.media = Media.fromJson(jsonObject.getJSONObject("entities"));
            Log.i("found", "unextended");

        }


        //tweet.image = jsonObject.getString()
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++)
        {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
}
