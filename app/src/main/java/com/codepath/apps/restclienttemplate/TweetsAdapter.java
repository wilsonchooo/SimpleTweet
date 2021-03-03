package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{
    public interface OnClickListener
    {
        void onItemClicked(int position);
    }
    Context context;
    List<Tweet> tweets;
    OnClickListener clickListener;


    public TweetsAdapter(Context context,List<Tweet> tweets, OnClickListener clickListener) {
        this.context = context;
        this.tweets= tweets;
        this.clickListener = clickListener;
    }
    // Pass in the context and list of tweets

    // For each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //Get data at position
        Tweet tweet = tweets.get(position);
        // Bind the tweet with viewholder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    //clean elements of recycler
    public void clear(){
        tweets.clear();
        notifyDataSetChanged();
    }
    //add a list of items
    public void addAll(List<Tweet> tweetsList){
        tweets.addAll(tweetsList);
        notifyDataSetChanged();
    }

    // Define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        RelativeLayout container;
        TextView tvCreatedAt;
        TextView tvHandle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage=itemView.findViewById(R.id.ivProfileImage);
            tvBody=itemView.findViewById(R.id.tvBody);
            tvScreenName=itemView.findViewById(R.id.tvScreenName);
            container = itemView.findViewById(R.id.container);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvHandle=itemView.findViewById(R.id.tvHandle);
            //created at textview
        }

        // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
        public String getRelativeTimeAgo(String rawJsonDate) {
            String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
            SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
            sf.setLenient(true);

            String relativeDate = "";
            try {
                long dateMillis = sf.parse(rawJsonDate).getTime();
                relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return relativeDate;
        }

        public void bind(final Tweet tweet){
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            tvCreatedAt.setText(getRelativeTimeAgo(tweet.createdAt)); //parse created at
            tvHandle.setText("@"+tweet.user.name);

            tvBody.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
            Glide.with(context)
                    .load(tweet.user.profileImageUrl)
                    .into(ivProfileImage);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context,tweet.body,Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context,DetailActivity.class);
                    i.putExtra("TweetAccName",tweet.user.name);
                    i.putExtra("Description",tweet.body);
                    i.putExtra("ProfilePicture",tweet.user.profileImageUrl);
                    i.putExtra("Handle",tweet.user.screenName);
                    i.putExtra("CreatedAt",getRelativeTimeAgo(tweet.createdAt));

                    Log.i("help","yikes "+tweet.media.ImageUrl1);
                    i.putExtra("ImageUrl",tweet.media.ImageUrl1);




                    //i.putExtra("image",)
                   // i.putExtra("tweet", Parcels.wrap(tweet));

                    context.startActivity(i);

                    //i.putExtra("Tweet",Parcels.wrap)
                }
            });
        }


    }



}
