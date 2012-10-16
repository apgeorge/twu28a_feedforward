package com.thoughtworks.twu.controller;

import com.sun.security.auth.UserPrincipal;
import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.service.TalkService;
import com.thoughtworks.twu.utils.DateParser;
import org.joda.time.DateTime;
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
    int talkId = 42;

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
        Talk talk = new Talk(new Presentation(null, null, "test.twu"), null, null, null);
        when(talkService.getTalk(talkId)).thenReturn(talk);

        ModelAndView result = talkController.getTalkDetails(request, talkId);

        assertThat(result.getViewName(), is("talk_details"));
        assertThat((Talk) result.getModel().get("talk"), is(talk));
    }

    @Test
    public void shouldLoadTalkTabPage() throws Exception {
        ModelAndView modelAndView = talkController.getTalkTabPage();

        assertThat(modelAndView.getViewName(), is("talk_tab"));
    }


    @Test
    public void shouldLoadNewTalkPage() throws Exception {
        ModelAndView modelAndView = talkController.getNewTalkPage();

        assertThat(modelAndView.getViewName(), is("new_talk"));
    }


    @Test
    public void shouldAddTalkCreationSuccessfulMessageUponCreationOfTalkOnMyTalksPage() throws Exception {
        Presentation presentation = new Presentation("title", "description", "test.twu");
        when(talkService.createTalkWithNewPresentation(presentation, "venue", "date", "time")).thenReturn(1);
        when(talkService.validate("title", "venue", "date", "time")).thenReturn(true);

        ModelAndView modelAndView = talkController.newTalksFormSubmit(request, "title", "description", "venue", "date", "time");

        assertThat(modelAndView.getModel().get("status").toString(), equalTo("true"));

    }

    @Test
    public void shouldReturnFalseStatusOnInvalidTalkSubmission() throws Exception {
        ModelAndView modelAndView = talkController.newTalksFormSubmit(request, "", "", "", "", "");
        assertThat(modelAndView.getModel().get("status").toString(), equalTo("false"));
    }

    @Test
    public void shouldCreateNewTalkWithEnteredDetails() throws Exception {
        String title = "title";
        String description = "description";
        when(talkService.validate(title, "venue", "date", "time")).thenReturn(true);
        talkController.newTalksFormSubmit(request, title, description, "venue", "date", "time");
        verify(talkService).validate(title, "venue", "date", "time");
        verify(talkService).createTalkWithNewPresentation(new Presentation(title, description, "test.twu"), "venue", "date", "time");
    }

    @Test
    public void shouldReturnListOfMyTalksToPage() throws Exception {
        List<Talk> myTalksList = new ArrayList<Talk>();
        UserPrincipal userPrincipal = new UserPrincipal("test.twu");
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setUserPrincipal(userPrincipal);
        when(talkService.getMyTalks(userPrincipal.getName())).thenReturn(myTalksList);

        ModelAndView modelAndView = talkController.getMyTalksPage(request);

        verify(talkService).getMyTalks("test.twu");

        assertThat((List<Talk>) modelAndView.getModel().get("myTalksList"), is(myTalksList));

    }

    @Test
    public void shouldReturnAListOfTalksHappenedInPastTwoDays() {
        List<Talk> recentTalksList = new ArrayList<Talk>();
        when(talkService.getRecentTalks()).thenReturn(recentTalksList);

        ModelAndView modelAndView = talkController.getRecentTalksPage();

        assertThat(modelAndView.getViewName(), is("talks"));
        verify(talkService).getRecentTalks();
        assertThat((List<Talk>) modelAndView.getModel().get("talksList"), is(recentTalksList));
    }

    @Test
    public void shouldReturnAListOfUpcomingTalks() throws Exception {
        List<Talk> upcomingTalksList = new ArrayList<Talk>();
        when(talkService.getUpcomingTalks()).thenReturn(upcomingTalksList);

        ModelAndView modelAndView = talkController.getUpcomingTalks();

        assertThat(modelAndView.getViewName(), is("talks"));
        verify(talkService).getUpcomingTalks();
        assertThat((List<Talk>) modelAndView.getModel().get("talksList"), is(upcomingTalksList));
    }

    @Test
    public void shouldReturnTalkDetailsPageWithoutFeedback() throws Exception {

        Talk talk = new Talk(new Presentation(null, null, "test.twu"), null, null, null);
        when(talkService.isUpcomingTalk(talk)).thenReturn(true);
        when(talkService.getTalk(talkId)).thenReturn(talk);
        ModelAndView modelAndView = talkController.getTalkDetails(request, talkId);
        assertThat(modelAndView.getViewName(), is("talk_details"));
        verify(talkService).isUpcomingTalk(talk);
        assertThat((String) modelAndView.getModel().get("isUpcoming"), is("isAnUpcomingTalk"));


    }

    @Test
    public void shouldReturnTalkDetailsPageWithFeedbackForRecentTalk() throws Exception {

        Talk talk = new Talk(new Presentation(null, null, "test.twu"), null, null, null);
        when(talkService.isUpcomingTalk(talk)).thenReturn(false);
        int talkId = 1;
        when(talkService.getTalk(talkId)).thenReturn(talk);
        ModelAndView modelAndView = talkController.getTalkDetails(request, talkId);
        assertThat(modelAndView.getViewName(), is("talk_details"));
        verify(talkService).isUpcomingTalk(talk);
        assertThat((String) modelAndView.getModel().get("isUpcoming"), is("isNotAnUpcomingTalk"));


    }


    @Test
    public void shouldShowEditTalkDetailsIconForOwner() throws Exception {

        Talk talk = new Talk(new Presentation(null, null, "test.twu"), null, null, null);
        when(talkService.getTalk(0)).thenReturn(talk);
        when(talkService.isUpcomingTalk(talk)).thenReturn(true);
        ModelAndView modelAndView = talkController.getTalkDetails(request, 0);
        assertThat((String) modelAndView.getModel().get("isEditable"), is("true"));
    }

   @Test
    public void shouldEditDescriptionOfTalk(){
       Talk talk = new Talk(new Presentation(null, "desc", "test.twu"), null, null, null);
       when(talkService.getTalk(talkId)).thenReturn(talk);
       when(talkService.editTalkDescription(talkId, "Edited desc")).thenReturn(1);
       ModelAndView modelAndView = talkController.editTalkDetails(request, "description", talkId, "Edited desc");
       assertThat((String)modelAndView.getModel().get("status"), is("Edited desc"));
   }


    @Test
    public void shouldEditVenueOfTalk(){
        Talk talk = new Talk(new Presentation(null, "desc", "test.twu"), "venue", null, null);
        when(talkService.getTalk(talkId)).thenReturn(talk);
        when(talkService.editTalkVenue(talkId, "Edited venue")).thenReturn(1);
        ModelAndView modelAndView = talkController.editTalkDetails(request, "venue", talkId, "Edited venue");
        assertThat((String)modelAndView.getModel().get("status"), is("Edited venue"));
    }

    @Test
    public void shouldEditDateOfTalk(){
        Talk talk = new Talk(new Presentation(null, "desc", "test.twu"), "venue", DateTime.now(), null);
        DateTime now = DateTime.now();

        when(talkService.getTalk(talkId)).thenReturn(talk);
        when(talkService.editTalkDateTime(talkId, now)).thenReturn(1);
        ModelAndView modelAndView = talkController.editTalkDetails(request, "date", talkId, "10/04/2010");


        assertThat((String)modelAndView.getModel().get("status"), is("10/04/2010"));
    }


    @Test
    public void shouldEditTimeOfTalk(){
        Talk talk = new Talk(new Presentation(null, "desc", "test.twu"), "venue", DateTime.now(), null);
        DateTime now = new DateParser("11/11/2001","10:10 AM").convertToDateTime();


        when(talkService.getTalk(talkId)).thenReturn(talk);
        when(talkService.editTalkDateTime(talkId, now)).thenReturn(1);
        ModelAndView modelAndView = talkController.editTalkDetails(request, "time", talkId, "10:10 AM");


        assertThat((String)modelAndView.getModel().get("status"), is("10:10 AM"));
    }

    @Test
    public void shouldDeleteTalk() throws Exception {
        when(talkService.deleteTalk(talkId)).thenReturn(1);
        ModelAndView modelAndView = talkController.deleteTalk(talkId);

        assertThat((String)modelAndView.getModel().get("status"),is("talkWithId"+talkId+"Deleted"));

    }
}
