package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    Context context;
    ImageView DetailProfilePicture;
    TextView DetailAccName;
    TextView DetailAccHandle;
    TextView DetailDescription;
    ImageView DetailPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DetailProfilePicture = findViewById(R.id.DetailProfilePicture);
        DetailAccName = findViewById(R.id.DetailAccName);
        DetailAccHandle = findViewById(R.id.DetailAccHandle);
        DetailDescription = findViewById(R.id.DetailDescription);
        DetailPicture = findViewById(R.id.DetailPicture);

        //Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        //DetailAccName.setText(tweet.user.name);
        String Acc = getIntent().getStringExtra("TweetAccName");
        DetailAccName.setText(Acc);
        DetailDescription.setText(getIntent().getStringExtra("Description"));
        String handle = "@" + getIntent().getStringExtra("Handle");
        DetailAccHandle.setText(handle);


        String ImageUrl = getIntent().getStringExtra("ImageUrl");
        Log.i("fuckk",""+ImageUrl);

        Log.i("working","yep" + ImageUrl);
        String ProfilePic = getIntent().getStringExtra("ProfilePicture");
        Log.i("yep","profilepicurl" + ProfilePic);

        Glide.with(this)
                .load(ProfilePic)
                .into(DetailProfilePicture);

        Glide.with(this)
                .load(ImageUrl)
                .into(DetailPicture);

        getSupportActionBar().setTitle("Tweet");
    }
}