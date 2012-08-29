package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.service.FeedbackService;
import com.thoughtworks.twu.utils.TestClock;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FeedbackControllerTest {
    private FeedbackController feedbackController;

    @Test
    public void shouldEnterAFeedback() {
        // Given
        FeedbackService feedbackService = mock(FeedbackService.class);
        TestClock testClock = new TestClock();
        feedbackController = new FeedbackController(feedbackService, testClock);
        int talkId = 9;
        Feedback feedback = new Feedback(talkId, "Feedback comment", "feedback giver name", "caroline@example.com", testClock.now());
        // When
        ModelAndView result = feedbackController.enterFeedback(talkId,"Feedback comment");
        // Then
        String resultMessage = (String) result.getModel().get("result-message");
        assertThat(resultMessage, is("Thank you for the feedback"));
        verify(feedbackService).enterFeedback(feedback);
    }



}
