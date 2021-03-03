package com.codepath.apps.restclienttemplate.models;

import android.media.Image;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Media {
    public String VideoUrl;
    public String ImageUrl1;
    public String ImageUrl2;
    public String ImageUrl3;
    public String ImageUrl4;
    public String MediaID;
    public int NumberOfImages = 1;

    public static Media fromJson(JSONObject jsonObject ) throws JSONException {
        Media media = new Media();

        String TempImageUrl1 = "";
        String TempImageUrl2 = "";
        String TempImageUrl3 = "";
        String TempImageUrl4 = "";
        int number;
        JSONArray mediaarray;
        JSONObject temp;
        if (jsonObject.has("media")) {
            mediaarray = jsonObject.getJSONArray("media");
            TempImageUrl1 = mediaarray.getJSONObject(0).getString("media_url_https");
            media.MediaID = mediaarray.getJSONObject(0).getString("id_str");
            media.ImageUrl1 = TempImageUrl1;
        }
        return media;
    }
}

        /*
            if (which==0) {
                Log.i("Reached",""+0);
                mediaarray = jsonObject.getJSONArray("media");
                number = mediaarray.length();
                for (int i = 0; i < mediaarray.length(); i++) {
                    switch (i) {
                        case 0:
                            TempImageUrl1 = mediaarray.getJSONObject(i).getString("media_url_https");
                            break;
                        case 1:
                            TempImageUrl2 = mediaarray.getJSONObject(i).getString("media_url_https");
                            break;
                        case 2:
                            TempImageUrl3 = mediaarray.getJSONObject(i).getString("media_url_https");
                            break;
                        case 3:
                            TempImageUrl4 = mediaarray.getJSONObject(i).getString("media_url_https");
                            break;
                    }
                }

                Log.i("Tests", TempImageUrl1);
                Log.i("Tests", TempImageUrl2);
                Log.i("Tests", TempImageUrl3);
                Log.i("Tests", TempImageUrl4);
                media.MediaID = mediaarray.getJSONObject(0).getString("id_str");
                media.ImageUrl1 = TempImageUrl1;
                media.ImageUrl2 = TempImageUrl2;
                media.ImageUrl3 = TempImageUrl3;
                media.ImageUrl4 = TempImageUrl4;
                media.NumberOfImages = number;
                return media;

                //Log.i("yeet","length: "+mediaarray.length());
                //Log.i("yeet",mediaarray.toString());
                Log.i("yeet",mediaarray.getJSONObject(1).getString("media_url_https"));
                ImageHold[0] = mediaarray.getJSONObject(1).getString("media_url_https");
                Log.i("yeet",ImageHold[0]);

                for(int i=0;i<mediaarray.length();i++) {
                    ImageHold[i] = mediaarray.getJSONObject(i).getString("media_url_https");
                }
                //Images.add(mediaarray.getJSONObject(0).getString("media_url_https"));
                //Log.i("arraylist","test "+Images.get(0));
                media.Images=ImageHold;
                for(int i=0;i<ImageHold.length;i++) {
                    Log.i("yeet",ImageHold[i]);
                }
                media.ImageUrl=ImageHold[0];


            }
            if (which==1) {
                Log.i("Reached",""+1);;
                mediaarray = jsonObject.getJSONArray("media");
                temp = mediaarray.getJSONObject(0);
                media.ImageUrl1 = temp.getString("media_url_https");
                media.MediaID = temp.getString("id_str");
                media.NumberOfImages = 1;
                return media;
            }
        }



    }
}
*/