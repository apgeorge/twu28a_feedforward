package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Feedback;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        Feedback feedback = new Feedback(talkId, "feedbackComment", "attendee", "attendeeMail", new DateTime(DateTimeZone.UTC));
        //When
        feedbackMapper.insertFeedback(feedback);
        Feedback result=feedbackMapper.getLastEnteredFeedback();
        //Then
        assertThat(result, is(feedback));
    }

    @Test
    public void shouldRetrieveFeedbackListByTalkId() throws Exception {
        //Given
        Feedback feedback1 = new Feedback(1, "feedbackComment1", "attendee1", "attendeeMail 1,",new DateTime(2008,DateTimeZone.UTC));
        Feedback feedback2 = new Feedback(1, "feedbackComment2", "attendee2", "attendeeMail 2",new DateTime(2009,DateTimeZone.UTC));
        Feedback feedback = new Feedback(2, "feedbackComment1", "attendee1", "attendeeMail 1",new DateTime(2010, DateTimeZone.UTC));
        Feedback feedback3 = new Feedback(1, "feedbackComment3", "attendee3", "attendeeMail 3",new DateTime(2011,DateTimeZone.UTC));
        Feedback feedback4 = new Feedback(1, "feedbackComment4", "attendee4", "attendeeMail 4",new DateTime(2012,DateTimeZone.UTC));
        feedbackMapper.insertFeedback(feedback1);
        feedbackMapper.insertFeedback(feedback2);
        feedbackMapper.insertFeedback(feedback3);
        feedbackMapper.insertFeedback(feedback4);
        feedbackMapper.insertFeedback(feedback);
        ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
        feedbackList.add(feedback4);
        feedbackList.add(feedback3);
        feedbackList.add(feedback2);
        feedbackList.add(feedback1);
        //When
        List<Feedback> result = feedbackMapper.getFeedbackByTalkId(1);
        //Then
        Assert.assertEquals(feedbackList, result);

    }
}
