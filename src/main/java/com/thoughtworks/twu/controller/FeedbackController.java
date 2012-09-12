package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.service.ExportService;
import com.thoughtworks.twu.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class FeedbackController {

    private FeedbackService feedbackService;
    private ExportService exportService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, ExportService exportService) {
        this.feedbackService = feedbackService;
        this.exportService = exportService;
    }

    @RequestMapping(value = "/add_feedback.htm*", method = RequestMethod.POST)
    public ModelAndView enterFeedback(HttpServletRequest request, @RequestParam(value = "talkId", defaultValue = "") int talkId,
                                      @RequestParam(value = "feedbackComment", defaultValue = "") String feedbackComment) {
        String username = request.getUserPrincipal().getName().toLowerCase();
        feedbackService.enterFeedback(talkId, feedbackComment, username, username+"@thoughtworks.com");
        return getListOfPastFeedback(talkId);
    }

    @RequestMapping(value = "/add_feedback.htm*", method = RequestMethod.GET)
    public ModelAndView getListOfPastFeedback(@RequestParam(value = "talk_id", defaultValue = "0") int talkId) {
        ArrayList<Feedback> feedbackArrayList = feedbackService.retrieveFeedbackByTalkId(talkId);
        ModelAndView modelAndView = new ModelAndView("add_feedback");
        modelAndView.addObject("retrieved_feedback_list", feedbackArrayList);
        modelAndView.addObject("talk_id",talkId);
        return modelAndView;
    }

    @RequestMapping(value = "/export_feedback.htm*", method=RequestMethod.POST)
    public ModelAndView exportTalkWithFeedback(@RequestParam(value = "talk_id", defaultValue = "0")int talkId) {
        exportService.exportTalkWithFeedback(talkId);
        ModelAndView modelAndView=new ModelAndView("message");
        modelAndView.addObject("status","true");
        return modelAndView;
    }
}

