package com.thoughtworks.twu.controller;

import org.springframework.web.servlet.ModelAndView;

public class TalkController {
    public ModelAndView getTalk(int talkId) {
        ModelAndView modelAndView = new ModelAndView("talk_details");

        return modelAndView;
    }
}
