package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FeedbackController {

    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @RequestMapping(value = "feedback", method = RequestMethod.POST)
    public ModelAndView enterFeedback(int talkId, String feedbackComment) {
        feedbackService.enterFeedback(talkId, feedbackComment, "feedback giver name", "caroline@example.com");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("result-message", "Thank you for the feedback");
        return modelAndView;
    }
}
