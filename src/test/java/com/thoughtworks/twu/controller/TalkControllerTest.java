package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Talk;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TalkControllerTest {


    private TalkController talkController;

    @Test
    public void shouldReturnTalkDetailsViewForAnId() {
        int talkId = 42;
        Talk talk = new Talk();
        talkController = new TalkController();

        ModelAndView result = talkController.getTalk(talkId);

        assertThat(result.getViewName(), is("talk_details"));
        //assertThat((Talk)result.getModel().get("talk"), is(talk));
    }

}
