package com.thoughtworks.twu.controller;

import com.sun.security.auth.UserPrincipal;
import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.service.ExportService;
import com.thoughtworks.twu.service.FeedbackService;
import com.thoughtworks.twu.service.TalkService;
import org.hamcrest.CoreMatchers;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertTrue;

public class FeedbackControllerTest {
    private FeedbackController feedbackController;
    private FeedbackService mockFeedbackService;
    private ExportService mockExportService;
    private TalkService mockTalkService;
    private MockHttpServletRequest mockHttpServletRequest;

    @Before
    public void setUp() throws Exception {
        mockFeedbackService = mock(FeedbackService.class);
        mockExportService=mock(ExportService.class);
        mockTalkService=mock(TalkService.class);
        feedbackController = new FeedbackController(mockFeedbackService, mockExportService,mockTalkService);
        UserPrincipal userPrincipal = new UserPrincipal("test.twu");
        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setUserPrincipal(userPrincipal);
    }

    @Test
    public void shouldLoadAddFeedbackWhenClickedATalk() {
        assertThat(feedbackController.enterFeedback(mockHttpServletRequest, 0, "").getViewName(), is("add_feedback"));
    }

    @Test
    public void shouldEnterAFeedback() {
        int talkId = 9;
        ArrayList<Feedback> feedbackArrayList = new ArrayList<Feedback>();
        when(mockFeedbackService.retrieveFeedbackByTalkId(talkId)).thenReturn(feedbackArrayList);

        ModelAndView result = feedbackController.enterFeedback(mockHttpServletRequest, talkId, "Feedback comment");

        verify(mockFeedbackService).enterFeedback(talkId, "Feedback comment", "test.twu", "test.twu@thoughtworks.com");
        verify(mockFeedbackService).retrieveFeedbackByTalkId(talkId);
        assertThat((ArrayList<Feedback>) result.getModel().get("retrieved_feedback_list"), CoreMatchers.is(feedbackArrayList));
    }

    @Test
    public void shouldShowListOfPreviousFeedbackByTalkIdOrderedByMostRecent() throws Exception {
        //Given
        int talkId = 1;
        Feedback feedback = new Feedback(talkId, "comment1", "Vegeta", "vegeta@dragon.ball", new DateTime(2008, DateTimeZone.UTC));
        Feedback feedback2 = new Feedback(talkId, "comment2", "Vegeta", "vegeta@dragon.ball", new DateTime(2009, DateTimeZone.UTC));
        Feedback feedback3 = new Feedback(talkId, "comment3", "Vegeta", "vegeta@dragon.ball", new DateTime(2010, DateTimeZone.UTC));
        Feedback feedback4 = new Feedback(talkId, "comment4", "Vegeta", "vegeta@dragon.ball", new DateTime(2011, DateTimeZone.UTC));
        ArrayList<Feedback> feedbackArrayList = new ArrayList<Feedback>();
        feedbackArrayList.add(feedback);
        feedbackArrayList.add(feedback2);
        feedbackArrayList.add(feedback3);
        feedbackArrayList.add(feedback4);
        when(mockFeedbackService.retrieveFeedbackByTalkId(talkId)).thenReturn(feedbackArrayList);
        //When
        ModelAndView result = feedbackController.getListOfPastFeedback(mockHttpServletRequest,talkId);
        //Then
        assertThat(result.getViewName(), CoreMatchers.is("add_feedback"));
        assertThat((ArrayList<Feedback>) result.getModel().get("retrieved_feedback_list"), CoreMatchers.is(feedbackArrayList));
        verify(mockFeedbackService).retrieveFeedbackByTalkId(talkId);

    }

    @Test
    public void shouldExportFeedback() {
        int talkId=1;
        String username= mockHttpServletRequest.getUserPrincipal().getName();
        when(mockExportService.exportTalkWithFeedback(talkId,username)).thenReturn(true);
        boolean expected=mockExportService.exportTalkWithFeedback(talkId,username);

        ModelAndView result = feedbackController.exportTalkWithFeedback(mockHttpServletRequest,talkId);

        assertThat(result.getViewName(), is("message"));
        String status = (String) result.getModel().get("status");
        Assert.assertTrue(expected);
        assertThat(status,is("isExported"));

    }



}

