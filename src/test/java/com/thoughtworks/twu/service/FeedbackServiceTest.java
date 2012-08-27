package com.thoughtworks.twu.service;

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
        String feedbackComment = "Sample Feedback";
        feedbackService.enterFeedback(feedbackComment);

        //Then
        verify(mockFeedback).insertFeedback(feedbackComment);
    }
}
