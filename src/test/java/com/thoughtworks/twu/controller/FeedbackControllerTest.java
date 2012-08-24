package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.service.FeedbackService;
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
        feedbackController = new FeedbackController(feedbackService);

        // When
        ModelAndView result = feedbackController.enterFeedback("Feedback comment", "Type");

        // Then
        String resultMessage = (String) result.getModel().get("result-message");
        assertThat(resultMessage, is("Thank you for the feedback"));
        verify(feedbackService).enterFeedback("Feedback comment", "Type");
    }


}
