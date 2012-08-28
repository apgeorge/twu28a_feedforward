package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.service.TalkService;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TalkControllerTest {


    private TalkController talkController;

    @Test
    public void shouldReturnTalkDetailsViewForAnId() {
        int talkId = 42;
        Talk talk = new Talk();
        TalkService talkService = mock(TalkService.class);
        talkController = new TalkController(talkService);
        when(talkService.getTalk(talkId)).thenReturn(talk);

        ModelAndView result = talkController.getTalk(talkId);

        assertThat(result.getViewName(), is("talk_details"));
        assertThat((Talk) result.getModel().get("talk"), is(talk));

    }

}
