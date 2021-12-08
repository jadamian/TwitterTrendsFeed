package com.example.trendapp;

import android.os.StrictMode;

import java.util.ArrayList;

import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MLFeed {

    private Twitter tweet;
    private ArrayList<Posts> postList;
    private UserData user;
    private int current;
    private int searchAmount;

    public MLFeed(UserData user) {
        postList = new ArrayList<Posts>();
        this.user = user;
        searchAmount = 3;
        configBuilder();
        addPosts(user.getInterests());

        for(Posts posts: postList) {
            posts.setWeight(user.getInterests());
        }
        sort();
        current = 0;
    }

    public MLFeed(UserData user, int search) {
        //enableStrictMode();
        postList = new ArrayList<Posts>();
        this.user = user;
        searchAmount = search;
        configBuilder();
        addPosts(user.getInterests());

        for(Posts posts: postList) {
            posts.setWeight(user.getInterests());
        }
        sort();
        current = 0;
    }

    public void enableStrictMode()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    public MLFeed(String search) {
        postList = new ArrayList<Posts>();
        configBuilder();
        searchAmount = 10;
        ArrayList<Status> feed = new ArrayList<Status>();
        HashtagEntity[] hashs;

        feed = searchResults(search);
        Posts post;
        for(Status stats: feed) {
            post = new Posts("https://twitter.com/" + stats.getUser().getScreenName()+ "/status/" + stats.getId());
            hashs = stats.getHashtagEntities();
            for(HashtagEntity hash: hashs) {
                post.addKeyWords(hash.getText());
            }
            postList.add(post);
        }
    }

    public ArrayList<Status> searchResults(String str){
        try {
            ArrayList<Status> stats = searchTweets(str);
            return stats;
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            //System.out.println("could not search "+ inter.getTopic());
            e.printStackTrace();
        }
        return null;
    }

    public void addPosts(ArrayList<Interest> interests) {
        ArrayList<Status> feed = new ArrayList<Status>();
        ArrayList<Status> f = new ArrayList<Status>();
        HashtagEntity[] hashs;
        for(Interest inter: interests) {
            try {
                feed = searchTweets(inter.getTopic());
                //for(Status s: f){
                //  feed.add(s);
                //}
            } catch (TwitterException e) {
                // TODO Auto-generated catch block
                System.out.println("could not search "+ inter.getTopic());
                e.printStackTrace();
            }
            System.out.println("adding postclass for " + inter.getTopic());
            Posts post;
            for(Status stats: feed) {
                post = new Posts("https://twitter.com/" + stats.getUser().getScreenName()+ "/status/" + stats.getId());
                hashs = stats.getHashtagEntities();
                for(HashtagEntity hash: hashs) {
                    post.addKeyWords(hash.getText());
                }
                postList.add(post);
            }

        }

    }

    public ArrayList<Status> searchTweets(String str) throws TwitterException {
        ArrayList<Status> tweets = new ArrayList<Status>();
        Query query = new Query(str);
        query.count(searchAmount);
        System.out.println("str" + query.getCount());
        QueryResult qr = tweet.search(query);
        for(Status stats: qr.getTweets()) {
            tweets.add(stats);
        }
        return tweets;
    }

    public void sort() {
        //postList.sort(null);
        ArrayList<Posts> sorted = new ArrayList<Posts>();
        Posts[] index = new Posts[postList.size()];

        for(int i = 0; i < postList.size(); i++){
            index[i] = postList.get(i);
        }
        int n = index.length;

        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < n - i - 1; j++){
                if(index[j].getWeight() > index[j+1].getWeight()){
                    Posts temp = index[j];
                    index[j] = index[j+1];
                    index[j] = temp;
                }
            }
        }

        for(int i = 0; i< index.length; i++){
            sorted.add(index[i]);
        }

        postList = sorted;
    }

    public ArrayList<Posts> getPosts(int i){
        ArrayList<Posts> posts = new ArrayList<Posts>();
        if(i > postList.size() - current) {
            for(Posts p: postList) {
                posts.add(p);
            }
            current = postList.size();
        }
        else {
            for(int j =0; j < i; j++) {
                posts.add(postList.get(j));
            }
            current += i;
        }
        return posts;
    }

    public ArrayList<Posts> getPosts(){
        return postList;
    }

    //get authentication
    public void configBuilder() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("zC3Smz2kLLmw4Ng69v5QiXxph")
                .setOAuthConsumerSecret("mHaR1iHWSuAAW4Yqtum27P324rCRLYRaYXhvsRg4c2qNkqUciR")
                .setOAuthAccessToken("1117812777517608960-NjUHzxRxMSeyY3nwwPdzmCCi1tymBV")
                .setOAuthAccessTokenSecret("UTyekKxor1LWi5cIHQgKqdBBIACjZ7c1xVLEvTQ6jtdMb");
        TwitterFactory tf = new TwitterFactory(cb.build());
        tweet = tf.getInstance();
    }

    public static void main(String[] args) {
        UserData data = new UserData();
        data.addInterest("cat");
        data.addInterest("tesla");
        data.addInterest("food");

        MLFeed ml = new MLFeed(data);

        for(Posts post: ml.getPosts()) {
            System.out.println(post.getUrl());
        }

    }

}
