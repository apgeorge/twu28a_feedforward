package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.persistence.FeedbackMapper;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FeedbackServiceTest {
    @Test
    public void shouldInsertFeedback() {
        //Given
        FeedbackMapper mockFeedback = mock(FeedbackMapper.class);
        FeedbackService feedbackService = new FeedbackService(mockFeedback);

        //When
        Feedback feedback = new Feedback(9, "Great", "bleh", "test@test.com");
        feedbackService.enterFeedback(feedback);

        //Then
        verify(mockFeedback).insertFeedback(feedback);
    }
}
