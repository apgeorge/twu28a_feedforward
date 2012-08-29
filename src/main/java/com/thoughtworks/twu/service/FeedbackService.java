package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.persistence.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    private FeedbackMapper feedbackMapper;

    @Autowired
    public FeedbackService(FeedbackMapper feedbackMapper) {
        this.feedbackMapper = feedbackMapper;
    }

    public void enterFeedback(Feedback feedback) {
        feedbackMapper.insertFeedback(feedback);

    }

    public List<Feedback> retrieveFeedbackByTalkId(int talkId) {
        return feedbackMapper.getFeedbackByTalkId(talkId);
    }
}
