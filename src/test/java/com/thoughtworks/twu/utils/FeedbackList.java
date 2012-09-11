package com.thoughtworks.twu.utils;

import com.thoughtworks.twu.domain.Feedback;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.ArrayList;

public class FeedbackList {
    public ArrayList<Feedback> feedbackListInitial(){
        int talkId = 1;
        Feedback feedback = new Feedback(talkId, "comment1", "Vegeta", "vegeta@dragon.ball", new DateTime(2008, DateTimeZone.UTC));
        Feedback feedback2 = new Feedback(talkId, "comment2", "Vegeta", "vegeta@dragon.ball", new DateTime(2009, DateTimeZone.UTC));
        Feedback feedback3 = new Feedback(talkId, "comment3", "Vegeta", "vegeta@dragon.ball", new DateTime(2010, DateTimeZone.UTC));
        Feedback feedback4 = new Feedback(talkId, "comment4", "Vegeta", "vegeta@dragon.ball", new DateTime(2011, DateTimeZone.UTC));
        ArrayList<Feedback> feedbackArrayList = new ArrayList<Feedback>();
        feedbackArrayList.add(feedback);
        feedbackArrayList.add(feedback2);
        feedbackArrayList.add(feedback3);
        feedbackArrayList.add(feedback4);
        return feedbackArrayList;
    }

}
