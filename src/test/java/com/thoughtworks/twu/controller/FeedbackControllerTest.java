package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.service.FeedbackService;
import org.hamcrest.CoreMatchers;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class FeedbackControllerTest {
    private FeedbackService feedbackService;
    private FeedbackController feedbackController;

    @Before
    public void setUp() throws Exception {
        feedbackService = mock(FeedbackService.class);
        feedbackController = new FeedbackController(feedbackService);

    }

    @Test
    public void shouldEnterAFeedback() {
        // When
        int talkId = 9;
        ModelAndView result = feedbackController.enterFeedback(talkId,"Feedback comment");
        // Then
        String resultMessage = (String) result.getModel().get("result-message");
        assertThat(resultMessage, is("Thank you for the feedback"));
        verify(feedbackService).enterFeedback(talkId, "Feedback comment", "feedback giver name", "caroline@example.com");
    }


    @Test
    public void shouldLoadAddFeedbackPage() throws Exception {
        //Given
        int talkId=1;
        Feedback feedback = new Feedback(talkId, "comment1", "Vegeta", "vegeta@dragon.ball", new DateTime(2008, DateTimeZone.UTC));
        Feedback feedback2 = new Feedback(talkId, "comment2", "Vegeta", "vegeta@dragon.ball", new DateTime(2009,DateTimeZone.UTC));
        Feedback feedback3 = new Feedback(talkId, "comment3", "Vegeta", "vegeta@dragon.ball", new DateTime(2010,DateTimeZone.UTC));
        Feedback feedback4 = new Feedback(talkId, "comment4", "Vegeta", "vegeta@dragon.ball", new DateTime(2011,DateTimeZone.UTC));
        ArrayList<Feedback> feedbackArrayList = new ArrayList<Feedback>();
        feedbackArrayList.add(feedback);
        feedbackArrayList.add(feedback2);
        feedbackArrayList.add(feedback3);
        feedbackArrayList.add(feedback4);

        when(feedbackService.retrieveFeedbackByTalkId(talkId)).thenReturn(feedbackArrayList);

        ModelAndView result = feedbackController.getAddFeedbackPage(talkId);

        assertThat(result.getViewName(), CoreMatchers.is("add_feedback"));
        assertThat((ArrayList<Feedback>) result.getModel().get("retrieved_feedback_list"), CoreMatchers.is(feedbackArrayList));
        verify(feedbackService).retrieveFeedbackByTalkId(talkId);

    }
}
