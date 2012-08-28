package com.thoughtworks.twu.controller;

import org.springframework.web.servlet.ModelAndView;

public class EventController {
    public ModelAndView getEvent(int eventId) {
        ModelAndView modelAndView = new ModelAndView("event_details");

        return modelAndView;
    }
}
