package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
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

import java.util.List;

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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage=itemView.findViewById(R.id.ivProfileImage);
            tvBody=itemView.findViewById(R.id.tvBody);
            tvScreenName=itemView.findViewById(R.id.tvScreenName);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(final Tweet tweet){
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
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
                    Toast.makeText(context,tweet.body,Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context,DetailActivity.class);
                    i.putExtra("TweetAccName",tweet.user.name);
                    i.putExtra("Description",tweet.body);
                    i.putExtra("ProfilePicture",tweet.user.profileImageUrl);
                    i.putExtra("Handle",tweet.user.screenName);

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
