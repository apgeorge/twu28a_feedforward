package com.thoughtworks.twu.controller;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TalkControllerTest {


    private TalkController talkController;

    @Test
    public void shouldReturnEventDetailsViewForAnId() {
        int eventId = 42;
        talkController = new TalkController();

        ModelAndView result = talkController.getEvent(eventId);

        assertThat(result.getViewName(), is("talk_details"));
    }

}
