package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.persistence.FeedbackMapper;
import com.thoughtworks.twu.utils.TestClock;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FeedbackServiceTest {
    @Test
    public void shouldInsertFeedback() {
        //Given
        FeedbackMapper mockFeedback = mock(FeedbackMapper.class);
        TestClock testClock = new TestClock();
        FeedbackService feedbackService = new FeedbackService(mockFeedback, testClock);
        Feedback feedback = new Feedback(9, "Feedback comment", "feedback giver name", "caroline@example.com", testClock.now());

        //When
        feedbackService.enterFeedback(9, "Feedback comment", "feedback giver name", "caroline@example.com");

        //Then
        verify(mockFeedback).insertFeedback(feedback);
    }
}
