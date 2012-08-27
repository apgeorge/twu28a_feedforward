package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Event;
import com.thoughtworks.twu.domain.Feedback;
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
        int eventId = 9;
        String feedbackComment = "Feedback comment";
        String attendee = "owner";
        String attendeeMail = "caroline";
        Feedback feedback = new Feedback(feedbackComment, eventId, attendee, attendeeMail);
        // When
        ModelAndView result = feedbackController.enterFeedback(feedbackComment, attendee, eventId, attendeeMail);
        // Then
        String resultMessage = (String) result.getModel().get("result-message");
        assertThat(resultMessage, is("Thank you for the feedback"));
        verify(feedbackService).enterFeedback(feedback);
    }

}
