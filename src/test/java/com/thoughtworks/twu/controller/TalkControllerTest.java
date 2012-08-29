package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.service.TalkService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

public class TalkControllerTest {

    private TalkController talkController;
    TalkService talkService;

    @Before
    public void setUp() {
         talkService = mock(TalkService.class);
        talkController = new TalkController(talkService);
    }

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

    @Test
    public void shouldLoadHomePageWhenAtHome() throws Exception {
        assertThat(talkController.getHomePage().getViewName(),is("home"));
    }

    @Test
     public void shouldLoadTalksPage() throws Exception {
        assertThat(talkController.getTalksPage().getViewName(),is("talks"));
    }

    @Test
     public void shouldMyTalksPage() throws Exception {
        assertThat(talkController.getMyTalksPage().getViewName(),is("my_talks"));
    }

    @Test
    public void shouldLoadTalkTabPage() throws Exception {
        assertThat(talkController.getTalkTabPage().getViewName(),is("talk_tab"));
    }

    @Test
    public void shouldAddTalkCreationSuccessfulMessageUponCreationOfTalkOnMyTalksPage() throws Exception {
        String message="New Talk Created";

        Presentation presentation = new Presentation("title", "description", "owner");
        when(talkService.createTalkWithNewPresentation(presentation,"venue","date","time")).thenReturn(1);
        when(talkService.validate("title","description","venue","date","time")).thenReturn(true);

        ModelAndView modelAndView= talkController.newTalksPage("title", "description", "venue", "date", "time");

        assertThat(modelAndView.getModel().get("message").toString(),equalTo(message));
        assertViewName(modelAndView,"talk_tab");

    }

    @Test
    public void shouldLoadNewTalkPage() throws Exception {
        ModelAndView modelAndView= talkController.newTalksPage("","","","","");
        assertViewName(modelAndView,"new_talk");
    }

    @Test
    public void shouldCreateNewTalkWithEnteredDetails() throws Exception {
        String title = "title";
        String description = "description";
        when(talkService.validate(title, description, "venue", "date", "time")).thenReturn(true);
        talkController.newTalksPage(title, description, "venue", "date", "time");
        verify(talkService).validate(title,description,"venue","date","time");
        verify(talkService).createTalkWithNewPresentation(new Presentation(title,description,"owner"),"venue","date","time");
    }
}
