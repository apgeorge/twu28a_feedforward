package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.persistence.FeedbackMapper;
import com.thoughtworks.twu.utils.ApplicationClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    private FeedbackMapper feedbackMapper;
    private ApplicationClock clock;

    @Autowired
    public FeedbackService(FeedbackMapper feedbackMapper, ApplicationClock clock) {
        this.feedbackMapper = feedbackMapper;
        this.clock = clock;
    }

    public void enterFeedback(int talkId, String feedbackComment, String feedbackGiver, String feedbackGiverEmail) {
        Feedback feedback = new Feedback( talkId, feedbackComment, feedbackGiver, feedbackGiverEmail, clock.now());
        feedbackMapper.insertFeedback(feedback);
    }
}
