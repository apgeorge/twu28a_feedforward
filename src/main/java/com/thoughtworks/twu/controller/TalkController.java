package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.service.TalkService;
import org.springframework.web.servlet.ModelAndView;

public class TalkController {
    private TalkService talkService;

    public TalkController(TalkService talkService) {

        this.talkService = talkService;
    }

    public ModelAndView getTalk(int talkId) {
        ModelAndView modelAndView = new ModelAndView("talk_details");
        Talk talk = talkService.getTalk(talkId);
        modelAndView.addObject("talk",talk);
        return modelAndView;
    }
}
