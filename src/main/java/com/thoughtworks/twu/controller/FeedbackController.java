package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class FeedbackController {

    private FeedbackService feedbackService;
    private String username;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
        this.username="anonymous";
    }

    @RequestMapping(value = "/add_feedback.htm*", method = RequestMethod.POST)
    public ModelAndView enterFeedback(@RequestParam(value = "talkId", defaultValue = "") int talkId,
                                      @RequestParam(value = "feedbackComment", defaultValue = "") String feedbackComment) {
        feedbackService.enterFeedback(talkId, feedbackComment, username, username+"@thoughtworks.com");
        return getListOfPastFeedback(talkId,username);

    }

    @RequestMapping(value = "/add_feedback.htm*", method = RequestMethod.GET)
    public ModelAndView getListOfPastFeedback(@RequestParam(value = "talk_id", defaultValue = "0") int talkId,
                                              @RequestParam(value = "username", defaultValue = "") String username) {
        ArrayList<Feedback> feedbackArrayList = feedbackService.retrieveFeedbackByTalkId(talkId);
        ModelAndView modelAndView = new ModelAndView("add_feedback");
        modelAndView.addObject("retrieved_feedback_list", feedbackArrayList);
        this.username=username;
        return modelAndView;
    }

}

