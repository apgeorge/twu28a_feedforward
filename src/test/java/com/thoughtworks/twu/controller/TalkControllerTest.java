package com.thoughtworks.twu.controller;

import com.sun.security.auth.UserPrincipal;
import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.service.TalkService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class TalkControllerTest {

    private TalkController talkController;
    TalkService talkService;
    private MockHttpServletRequest request;

    @Before
    public void setUp() {
         talkService = mock(TalkService.class);
        talkController = new TalkController(talkService);
        UserPrincipal userPrincipal = new UserPrincipal("test.twu");
        request = new MockHttpServletRequest();
        request.setUserPrincipal(userPrincipal);
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
        UserPrincipal userPrincipal = new UserPrincipal("test.twu");
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setUserPrincipal(userPrincipal);
        ModelAndView result = talkController.getHomePage(mockHttpServletRequest);
        assertThat(result.getViewName(),is("home"));
        assertThat((String)result.getModel().get("username"),is("test.twu"));

    }

    @Test
     public void shouldLoadTalksPage() throws Exception {
        assertThat(talkController.getTalksPage().getViewName(),is("talks"));
    }


    @Test
    public void shouldLoadTalkTabPage() throws Exception {
        assertThat(talkController.getTalkTabPage().getViewName(),is("talk_tab"));
    }


    @Test
    public void shouldLoadNewTalkPage() throws Exception {
        assertThat(talkController.getNewTalkPage().getViewName(),is("new_talk"));
    }


    @Test
    public void shouldAddTalkCreationSuccessfulMessageUponCreationOfTalkOnMyTalksPage() throws Exception {
        String message="New Talk Created";

        Presentation presentation = new Presentation("title", "description", "test.twu");
        when(talkService.createTalkWithNewPresentation(presentation,"venue","date","time")).thenReturn(1);
        when(talkService.validate("title", "venue","date","time")).thenReturn(true);

        ModelAndView modelAndView= talkController.newTalksFormSubmit(request, "title", "description", "venue", "date", "time");

        assertThat(modelAndView.getModel().get("status").toString(),equalTo("true"));

    }

    @Test
    public void shouldReturnFalseStatusOnInvalidTalkSubmission() throws Exception {
        ModelAndView modelAndView= talkController.newTalksFormSubmit(request, "", "", "", "", "");
        assertThat(modelAndView.getModel().get("status").toString(),equalTo("false"));
    }

    @Test
    public void shouldCreateNewTalkWithEnteredDetails() throws Exception {
        String title = "title";
        String description = "description";
        when(talkService.validate(title, "venue", "date", "time")).thenReturn(true);
        talkController.newTalksFormSubmit(request,title, description, "venue", "date", "time");
        verify(talkService).validate(title, "venue","date","time");
        verify(talkService).createTalkWithNewPresentation(new Presentation(title,description,"test.twu"),"venue","date","time");
    }

    @Test
    public void shouldReturnListOfMyTalksToPage() throws Exception {
        List<Talk> myTalksList=new ArrayList<Talk>();
        UserPrincipal userPrincipal=new UserPrincipal("test.twu");
        MockHttpServletRequest request=new MockHttpServletRequest();
        request.setUserPrincipal(userPrincipal);
        when(talkService.getListOfMyTalks(userPrincipal.getName())).thenReturn(myTalksList);

        ModelAndView modelAndView=talkController.getMyTalksPage(request);

        verify(talkService).getListOfMyTalks("test.twu");

        assertThat((List<Talk>) modelAndView.getModel().get("myTalksList"),is(myTalksList));

    }
}
