package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.service.FeedbackService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FeedbackControllerTest {
    private FeedbackController feedbackController;
    private FeedbackService feedbackService;

    @Before
    public void setup()
    {
        feedbackService = mock(FeedbackService.class);
        feedbackController = new FeedbackController(feedbackService);
    }
    @Test
    public void shouldLoadAddFeedbackWhenClickedATalk()
    {
        assertThat(feedbackController.enterFeedback(0,"").getViewName(),is("add_feedback"));
    }

    @Test
    public void shouldEnterAFeedback() {
        // Given

        int talkId = 9;
        // When
        ModelAndView result = feedbackController.enterFeedback(talkId,"Feedback comment");
        // Then
        String resultMessage = (String) result.getModel().get("result-message");
        assertThat(resultMessage, is("Thank you for the feedback"));
        verify(feedbackService).enterFeedback(talkId, "Feedback comment", "feedback giver name", "caroline@example.com");
    }

}
