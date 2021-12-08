package com.example.trendapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.ToggleButton;

public class HomeActivity extends AppCompatActivity {

    //buttons
    private Button next;
    private Button prev;
    private Button like;
    private Button disLike;
    private Button share;
    private Button feedSwitch;
    private SearchView searchBar;
    private String queryTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpButtons();
    }
    public void setUpButtons(){
        //next and prev
        next = (Button) findViewById(R.id.homeNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        prev = (Button) findViewById(R.id.homePrev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //interaction buttons
        like = (Button) findViewById(R.id.homeLike);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        disLike = (Button) findViewById(R.id.homeDis);
        disLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        share = (Button) findViewById(R.id.homeShare);
        disLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //to personal
        feedSwitch = (Button) findViewById(R.id.personalFeed);
        feedSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
            }
        });

        searchBar = (SearchView) findViewById(R.id.searchbar);
        searchBar.isSubmitButtonEnabled();

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("------------------------------------------" + queryTxt);
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                queryTxt = newText;
                return false;
            }
        });
/*
        searchBar.setOnSearchClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //String str = textBar.getEditableText().toString();
                System.out.println("------------------------------------------" + str);
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
            }
        });
        */
    }
}
