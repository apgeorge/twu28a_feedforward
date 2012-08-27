package com.thoughtworks.twu.service;

import com.thoughtworks.twu.persistence.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    private FeedbackMapper feedbackMapper;

    @Autowired
    public FeedbackService(FeedbackMapper feedbackMapper) {
        this.feedbackMapper = feedbackMapper;
    }

    public void enterFeedback(String feedbackComment) {
        feedbackMapper.insertFeedback(feedbackComment);

    }
}
