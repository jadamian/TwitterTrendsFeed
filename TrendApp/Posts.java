package com.example.trendapp;

import java.util.ArrayList;

public class Posts {

    private String url;
    private int weight;
    private ArrayList<String> keywords;

    public Posts(String url) {
        this.url = url;
        keywords = new ArrayList<String>();
    }
    public Posts(String url, int weight) {
        this.url = url;
        keywords = new ArrayList<String>();
        this.weight = weight;
    }

    public String getUrl() {
        return url;
    }
    public int getWeight() {
        return weight;
    }
    //public void setWeight(int add) {
    //weight += add;
    //}
    //enhanced weight change
    public void setWeight(ArrayList<Interest> userInterests) {
        weight = 0;
        for(Interest topic: userInterests) {
            if(keywords.contains(topic.getTopic())) {
                weight += topic.getTopicEng();
            }
        }
    }

    public void addKeyWords(ArrayList<String> keys) {
        for(String str: keys) {
            keywords.add(str);
        }
    }

    public void addKeyWords(String keys) {
        if(!keywords.contains(keys)) {
            keywords.add(keys);
        }
    }

    public ArrayList<String> getKeywords(){
        return keywords;
    }
    public int compareTo(Posts other) {
        if(this.weight <= other.weight)
            return 1;
        else
            return -1;
    }

}
