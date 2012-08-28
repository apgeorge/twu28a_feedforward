package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

public class EventControllerTest {

    private EventController eventController ;
    EventService eventService;

    @Before
    public void setUp() {
         eventService= mock(EventService.class);
        eventController = new EventController(eventService);
    }


    @Test
    public void shouldLoadHomePageWhenAtHome() throws Exception {
        assertThat(eventController.getHomePage().getViewName(),is("home"));
    }

    @Test
     public void shouldLoadTalksPage() throws Exception {
        assertThat(eventController.getTalksPage().getViewName(),is("talks"));
    }

    @Test
     public void shouldMyTalksPage() throws Exception {
        assertThat(eventController.getMyTalksPage().getViewName(),is("my_talks"));
    }

    @Test
    public void shouldLoadTalkTabPage() throws Exception {
        assertThat(eventController.getTalkTabPage().getViewName(),is("talk_tab"));
    }

    @Test
    public void shouldAddTalkCreationSuccessfulMessageUponCreationOfTalkOnMyTalksPage() throws Exception {
        String message="New Talk Created";

        Presentation presentation = new Presentation("title", "description", "owner");
        when(eventService.createEventWithNewPresentation(presentation,"venue","date","time")).thenReturn(1);
        when(eventService.validate("title","description","venue","date","time")).thenReturn(true);

        ModelAndView modelAndView=eventController.newTalksPage("title", "description", "venue", "date", "time");

        assertThat(modelAndView.getModel().get("message").toString(),equalTo(message));
        assertViewName(modelAndView,"talk_tab");

    }

    @Test
    public void shouldLoadNewTalkPage() throws Exception {
        ModelAndView modelAndView=eventController.newTalksPage("","","","","");
        assertViewName(modelAndView,"new_talk");
    }

    @Test
    public void shouldCreateNewEventWithEnteredDetails() throws Exception {
        String title = "title";
        String description = "description";
        when(eventService.validate(title, description, "venue", "date", "time")).thenReturn(true);
        eventController.newTalksPage(title, description, "venue", "date", "time");
        verify(eventService).validate(title,description,"venue","date","time");
        verify(eventService).createEventWithNewPresentation(new Presentation(title,description,"owner"),"venue","date","time");
    }
}
