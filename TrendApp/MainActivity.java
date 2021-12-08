package com.example.trendapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //testing values
    private ArrayList<PostData> postList;
    public int currentPost = 0;
    private boolean liked;
    private boolean shared;
    private int changeweight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Write a message to the database
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");

        //myRef.setValue("Hello, World!");
        PostGather posts = new PostGather();
        postList = new ArrayList<>(posts.getPosts());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //display link
        post = (WebView) findViewById(R.id.webView);
        post.loadUrl(postList.get(currentPost).getUrl());
        setButtons();
    }




    //buttons
    private Button next;
    private Button prev;
    private Button like;
    private Button disLike;
    private Button share;
    private Button feedSwitch;
    private WebView post;


    private void setButtons(){
        //next and prev functions
        next = (Button) findViewById(R.id.nextButton);
        prev = (Button) findViewById(R.id.prevButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPost++;
                if(currentPost >= postList.size()){
                    currentPost--;
                }
                displayLink();
                System.out.println("next");
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPost--;
                if(currentPost < 0){
                    currentPost = 0;
                }
                displayLink();
                System.out.println("prev");
            }
        });
        //Like and dislike funcions
        like = (Button) findViewById(R.id.like);
        disLike = (Button) findViewById(R.id.dislike);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //postList.get(currentPost).setPostWeight();

                System.out.println("like: " + postList.get(currentPost).getPostWeight());
            }
        });
        disLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //postList.get(currentPost).setPostWeight(0);

                System.out.println("dislike: "+ postList.get(currentPost).getPostWeight());
            }
        });
        //sharing buttons
        share = (Button) findViewById(R.id.shareButton);
        share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                postList.get(currentPost).shared();
                System.out.println("share: "+ postList.get(currentPost).getUrl());
            }
        });

        //switch feeds button
        feedSwitch = (Button) findViewById(R.id.homeFeed);
        feedSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }

    public void displayLink(){
        post.setWebViewClient(new WebViewClient());
        post.loadUrl(postList.get(currentPost).getUrl());
    }
}


