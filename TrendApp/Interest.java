package com.example.trendapp;

public class Interest implements Comparable<Interest>{

    private String topic;
    private int eng; //engagement
    public Interest() {
        topic = "";
        eng = 0;
    }

    public Interest(String topic) {
        this.topic = topic;
        eng = 0;
    }

    public Interest(String topic, int val) {
        this.topic = topic;
        eng = val;
    }

    public String getTopic() {
        return topic;
    }
    public int getTopicEng() {
        return eng;
    }
    public void setEng(int val) {
        eng += val;
    }

    @Override
    public int compareTo(Interest o) {
        if(this.eng > o.eng)
            return 1;
        return 0;
    }

}
