package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TalkController {
    private TalkService talkService;

    @Autowired
    public TalkController(TalkService talkService) {
         this.talkService = talkService;
    }


    @RequestMapping(value = "/talk_details.htm*", method = RequestMethod.GET)
    public ModelAndView getTalk(@RequestParam(value = "talk_id", defaultValue = "-1") int talkId) {
        Talk talk = talkService.getTalk(talkId);
        ModelAndView modelAndView = new ModelAndView("talk_details");
        modelAndView.addObject("talk",talk);
        return modelAndView;
    }

    @RequestMapping(value = "/talks.htm*", method = RequestMethod.GET)
    public ModelAndView getTalksPage()
    {
        return new ModelAndView("talks");
    }

    @RequestMapping(value = "/home.htm*", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        return new ModelAndView("home");
    }


    @RequestMapping(value = "/new_talk_submit.htm*", method = RequestMethod.GET)
    public ModelAndView newTalksFormSubmit(@RequestParam(value = "title", defaultValue = "") String title,
                                           @RequestParam(value = "description", defaultValue = "") String description,
                                           @RequestParam(value = "venue", defaultValue = "") String venue,
                                           @RequestParam(value = "date", defaultValue = "") String date,
                                           @RequestParam(value = "time", defaultValue = "") String time) {
        ModelAndView modelAndView = new ModelAndView("message");
        if(!talkService.validate(title,description,venue,date,time)){
            return addFailuireMessageToModelAndView(modelAndView);
        }
        Presentation presentation = new Presentation(title,description,"owner");
        int resultOfInsertion = talkService.createTalkWithNewPresentation(presentation, venue, date, time);

        if(resultOfInsertion == 0 )
            return  addFailuireMessageToModelAndView(modelAndView);

        modelAndView.addObject("status","true") ;
        return modelAndView;

    }

    private ModelAndView addFailuireMessageToModelAndView(ModelAndView modelAndView) {
        modelAndView.addObject("status", "false");
        return modelAndView;
    }

    @RequestMapping(value = "/my_talks.htm*", method = RequestMethod.GET)
    public ModelAndView getMyTalksPage() {
        return new ModelAndView("my_talks");
    }

    @RequestMapping(value = "/new_talk.htm*", method = RequestMethod.GET)
    public ModelAndView getNewTalkPage() {
        return new ModelAndView("new_talk");
    }

    @RequestMapping(value = "/talk_tab.htm*", method = RequestMethod.GET)
    public ModelAndView getTalkTabPage() {
        return new ModelAndView("talk_tab");
    }


}