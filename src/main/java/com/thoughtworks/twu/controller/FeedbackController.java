package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FeedbackController {

    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
       this.feedbackService=feedbackService;
    }

    public ModelAndView enterFeedback(String feedbackComment, String type) {
        feedbackService.enterFeedback(feedbackComment,type);
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.addObject("result-message","Thank you for the feedback");
        return modelAndView;
    }
}
