package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.persistence.FeedbackMapper;
import com.thoughtworks.twu.utils.TestClock;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FeedbackServiceTest {

    private FeedbackMapper mockFeedbackMapper;
    private TestClock testClock;
    private FeedbackService feedbackService;

    @Before
    public void setUp() throws Exception {
        mockFeedbackMapper = mock(FeedbackMapper.class);
        testClock = new TestClock();
        feedbackService = new FeedbackService(mockFeedbackMapper, testClock);
    }

    @Test
    public void shouldInsertFeedback() {
        //Given
        Feedback feedback = new Feedback(1,"Goku's feedback", "Goku", "goku@dragon.ball", testClock.now());

        //When
        feedbackService.enterFeedback(1, "Goku's feedback", "Goku", "goku@dragon.ball");

        //Then
        verify(mockFeedbackMapper).insertFeedback(feedback);
    }

    @Test
    public void shouldRetrieveFeedbackListByTalkId() {
        //Given
        int talk_id = 1;
        Feedback feedback1 = new Feedback(talk_id, "feedbackComment1", "attendee1", "attendeeMail 1",new DateTime(DateTimeZone.UTC));
        Feedback feedback2 = new Feedback(talk_id, "feedbackComment2", "attendee2", "attendeeMail 2",new DateTime(DateTimeZone.UTC));
        Feedback feedback3 = new Feedback(talk_id, "feedbackComment3", "attendee3", "attendeeMail 3",new DateTime(DateTimeZone.UTC));
        Feedback feedback = new Feedback(2, "feedbackComment1", "attendee1", "attendeeMail 1",new DateTime(DateTimeZone.UTC));
        feedbackService.enterFeedback(1,feedback1.toString(), "Goku", "goku@dragon.ball");
        feedbackService.enterFeedback(1,feedback2.toString(), "Goku", "goku@dragon.ball");
        feedbackService.enterFeedback(1,feedback3.toString(), "Goku", "goku@dragon.ball");
        feedbackService.enterFeedback(1,feedback.toString(), "Goku", "goku@dragon.ball");
        ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
        feedbackList.add(feedback3);
        feedbackList.add(feedback2);
        feedbackList.add(feedback1);
        //When
        List<Feedback> result = feedbackService.retrieveFeedbackByTalkId(talk_id);
        //Then
        verify(mockFeedbackMapper).getFeedbackByTalkId(talk_id);

    }

    @Test
    public void shouldHaveLowercaseAttendeeAndAttendeeMail() throws Exception {
        //Given
        int talk_id = 1;
        Feedback feedbackUppercase = new Feedback(talk_id, "feedbackComment", "ATTENDEE", "ATTENDEE@mail.com",new DateTime(DateTimeZone.UTC));
        ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
        feedbackList.add(feedbackUppercase);
        //When
        when(mockFeedbackMapper.getFeedbackByTalkId(talk_id)).thenReturn(feedbackList);
        feedbackService.enterFeedback(talk_id, feedbackUppercase.toString(), "ATTENDEE", "ATTENDEE@mail.com");
        //Then
        assertThat(feedbackService.retrieveFeedbackByTalkId(talk_id).contains(feedbackUppercase),is(true));

    }
}
