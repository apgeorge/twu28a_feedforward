package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Feedback;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FeedbackMapperTest extends IntegrationTest {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Test
    public void shouldInsertFeedbackIntoDatabase() {
        //Given
        int talkId=9;
        Feedback feedback = new Feedback(talkId, "feedbackComment", "attendee", "attendeeMail");
        //When
        feedbackMapper.insertFeedback(feedback);
        Feedback result=feedbackMapper.getLastEnteredFeedback();
        //Then
        assertThat(result, is(feedback));
    }

    @Test
    public void shouldRetrieveFeedbackListByTalkId() throws Exception {
        //Given
        Feedback feedback1 = new Feedback(1, "feedbackComment1", "attendee1", "attendeeMail 1");
        Feedback feedback3 = new Feedback(1, "feedbackComment3", "attendee3", "attendeeMail 3");
        Feedback feedback = new Feedback(2, "feedbackComment1", "attendee1", "attendeeMail 1");
        Feedback feedback2 = new Feedback(1, "feedbackComment2", "attendee2", "attendeeMail 2");
        feedbackMapper.insertFeedback(feedback1);
        feedbackMapper.insertFeedback(feedback2);
        feedbackMapper.insertFeedback(feedback3);
        feedbackMapper.insertFeedback(feedback);
        ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
        feedbackList.add(feedback3);
        feedbackList.add(feedback2);
        feedbackList.add(feedback1);
        //When
        List<Feedback> result = feedbackMapper.getFeedbackByTalkId(1);
        //Then
        Assert.assertEquals(feedbackList, result);

    }
}
