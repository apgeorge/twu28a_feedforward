package com.thoughtworks.twu.controller;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EventControllerTest {


    private EventController eventController;

    @Test
    public void shouldReturnEventDetailsViewForAnId() {
        int eventId = 42;
        eventController = new EventController();

        ModelAndView result = eventController.getEvent(eventId);

        assertThat(result.getViewName(), is("event_details"));
    }

}
