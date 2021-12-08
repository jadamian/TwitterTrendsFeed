package com.example.trendapp;

import java.util.ArrayList;

public class UserData {

    private ArrayList<Interest> interests;
    //private ArrayList<String> interestWords;

    public UserData() {
        interests = new ArrayList<Interest>();
    }

    public UserData(int i){
        interests = new ArrayList<Interest>();
        Interest inter = new Interest("cat", 5);
        Interest inter1 = new Interest("neko", 10);
        Interest inter2 = new Interest("doge", 0);
        Interest inter3 = new Interest("fish", 1);
        interests.add(inter);
        interests.add(inter1);
        interests.add(inter2);
        interests.add(inter3);
        sort();
    }

    public ArrayList<Interest> getInterests(){
        return interests;
    }

    public boolean checkInterests(String keyword) {
        for(Interest topics: interests) {
            if(topics.getTopic().equals(keyword)) {
                System.out.println("already added interest");
                return true;
            }
        }
        return false;
    }

    //add interests but for more words
    public ArrayList<String> addkeywords(ArrayList<String> keywords){
        ArrayList<String> keys = new ArrayList<String>();
        boolean contains = false;
        for(String key: keywords) {
            for(Interest inter: interests) {
                if(inter.getTopic().equals(key))
                    contains = true;
            }
            if(!contains)
                addInterest(key);
        }

        return keys;
    }

    public void addInterest(String keyword) {
        interests.add(new Interest(keyword));
        //interestWords.add(keyword);
    }

    public void interaction(String topic, int val) {
        for(Interest topics: interests) {
            if(topics.getTopic().equals(topic)) {
                topics.setEng(val);
            }
        }
    }

    //updates user data for future feed
    public void addinteraction(ArrayList<String> topics, int val) {
        boolean contains = false;
        for(String key: topics) {
            for(Interest interest: interests) {
                if(interest.getTopic().equals(key)) {
                    contains = true;
                    interest.setEng(val);
                }

            }
            if(!contains)
                interests.add(new Interest(key,val));
            //interestWords.add(key);
        }
        //interests.sort(null);
        sort();
    }

    public void sort(){
        ArrayList<Interest> sorted = new ArrayList<Interest>();
        Interest[] index = new Interest[interests.size()];

        for(int i = 0; i < interests.size(); i++){
            index[i] = interests.get(i);
        }
        int n = index.length;

        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < n - i - 1; j++){
                if(index[j].getTopicEng() > index[j+1].getTopicEng()){
                    Interest temp = index[j];
                    index[j] = index[j+1];
                    index[j] = temp;
                }
            }
        }

        for(int i = 0; i< index.length; i++){
            sorted.add(index[i]);
        }

        interests = sorted;
    }

    public static void main(String args[]){
        UserData ud = new UserData(0);
        for(Interest inter: ud.interests){
            System.out.println(inter.getTopic());
        }
    }

}
