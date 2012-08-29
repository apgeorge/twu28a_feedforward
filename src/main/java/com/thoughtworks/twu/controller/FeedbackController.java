package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.utils.ApplicationClock;
import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FeedbackController {

    private FeedbackService feedbackService;
    private ApplicationClock clock;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, ApplicationClock clock) {
        this.feedbackService = feedbackService;
        this.clock = clock;
    }

    @RequestMapping(value = "feedback", method = RequestMethod.POST)
    public ModelAndView enterFeedback(int talk, String feedbackComment) {
        Feedback feedback = new Feedback(talk, feedbackComment, "feedback giver name", "caroline@example.com", clock.now());
        feedbackService.enterFeedback(feedback);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("result-message", "Thank you for the feedback");
        return modelAndView;
    }
}
