package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.persistence.PresentationMapper;
import com.thoughtworks.twu.persistence.TalkMapper;
import com.thoughtworks.twu.utils.ApplicationClock;
import com.thoughtworks.twu.utils.DateParser;
import com.thoughtworks.twu.utils.TestClock;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TalkServiceTest {
    private TalkService talkService;
    private TalkMapper mockTalkMapper;
    private PresentationMapper mockPresentationMapper;
    private TestClock testClock;
    private static final String DATE = "12/04/1999";
    private static final String TIME = "11:00 AM";
    private Presentation presentation;

    @Before
    public void init() {
        mockTalkMapper = mock(TalkMapper.class);
        mockPresentationMapper = mock(PresentationMapper.class);
        testClock = new TestClock();
        talkService = new TalkService(mockTalkMapper, mockPresentationMapper, testClock);
        presentation = new Presentation("test title", "test description", "test presenter");
    }

    @Test
    public void shouldInsertPresentationOnCreationOfNewTalk() throws Exception {
        talkService.createTalkWithNewPresentation(presentation, "venue", DATE, TIME);
        verify(mockPresentationMapper).insertPresentation(presentation);
        verify(mockPresentationMapper).getPresentation("test title", "test presenter");
        verify(mockTalkMapper).insert(new Talk(any(Presentation.class), "venue", new DateParser(DATE, TIME).convertToDateTime(), testClock.now()));
    }

    @Test
    public void shouldReturnTalk() {
        int talkId = 0;
        Talk talkExpected = new Talk(new Presentation("test title", "test description", "test owner"), "venue", new DateParser(DATE, TIME).convertToDateTime(), testClock.now());
        when(mockTalkMapper.getTalk(talkId)).thenReturn(talkExpected);
        Talk talk = talkService.getTalk(talkId);
        verify(mockTalkMapper).getTalk(talkId);
        assertThat(talk, is(talkExpected));
    }

    @Test
    public void shouldGetAListOfUsersTalks() throws Exception {
        List<Talk> expectedTalkList = new ArrayList<Talk>();
        String owner = "test owner";
        expectedTalkList.add(new Talk(new Presentation("test title", "test description", owner), "venue", new DateParser(DATE, TIME).convertToDateTime(), testClock.now()));
        expectedTalkList.add(new Talk(new Presentation("title", "description", owner), "test venue", new DateParser("01/08/2012", "10:00 AM").convertToDateTime(), testClock.now()));
        when(mockTalkMapper.getTalksByUsername(owner)).thenReturn(expectedTalkList);

        assertThat(talkService.getMyTalks(owner), is(expectedTalkList));
        verify(mockTalkMapper).getTalksByUsername(owner);

    }

    @Test
    public void shouldGetAListOfTalksInThePastTwoDays() {
        List<Talk> expectedTalkList = new ArrayList<Talk>();
        when(mockTalkMapper.getTalks(any(DateTime.class), any(DateTime.class))).thenReturn(expectedTalkList);

        List<Talk> actualTalksList = talkService.getRecentTalks();

        verify(mockTalkMapper).getTalks(testClock.now().minusDays(2), testClock.now());
        assertThat(actualTalksList, is(expectedTalkList));
    }

    @Test
    public void shouldGetAListOfUpcomingTalksForTheNextMonth() throws Exception {
        List<Talk> expectedList = new ArrayList<Talk>();
        when(mockTalkMapper.getTalks(any(DateTime.class), any(DateTime.class))).thenReturn(expectedList);

        List<Talk> actualTalkList = talkService.getUpcomingTalks();

        assertThat(expectedList, is(actualTalkList));
        verify(mockTalkMapper).getTalks(testClock.now(), testClock.now().plusMonths(1));
    }

    @Test
    public void shouldReturnTrueForUpcomingTalk() throws Exception {
        Talk talk = new Talk(presentation, "test venue", new ApplicationClock().now().plusDays(24), testClock.now());
        int talkId = 1;
        when(mockTalkMapper.getTalk(talkId)).thenReturn(talk);
        assertTrue(talkService.isUpcomingTalk(talk));

    }

    @Test
    public void shouldMakeTalkWithLowercaseOwnerName() {
        Presentation upperCasePresentation = new Presentation("test title", "test description", "TEST_PRESENTER");
        talkService.createTalkWithNewPresentation(upperCasePresentation, "venue", DATE, TIME);
        Talk originalTalk = new Talk(upperCasePresentation, "venue", new DateParser(DATE, TIME).convertToDateTime(), testClock.now());
        ArrayList<Talk> originalTalkList = new ArrayList<Talk>();
        originalTalkList.add(originalTalk);
        when(mockTalkMapper.getTalksByUsername("test_presenter")).thenReturn(originalTalkList);
        List<Talk> expected = talkService.getMyTalks("TEST_PRESENTER");
        assertThat(expected.contains(originalTalk), is(true));
    }

}



