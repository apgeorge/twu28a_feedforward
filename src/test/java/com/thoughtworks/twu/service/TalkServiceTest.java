package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.persistence.PresentationMapper;
import com.thoughtworks.twu.persistence.TalkMapper;
import com.thoughtworks.twu.utils.DateParser;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TalkServiceTest {
    private TalkService talkService;
    private TalkMapper mockTalkMapper;
    private PresentationMapper mockPresentationMapper;
    String date;
    String time;

    @Before
    public void init() {
        mockTalkMapper = mock(TalkMapper.class);
        mockPresentationMapper = mock(PresentationMapper.class);
        talkService = new TalkService(mockTalkMapper, mockPresentationMapper);
        date = "12/04/1999";
        time = "11:00 AM";
    }

    @Test
    public void shouldInsertPresentationOnCreationOfNewTalk() throws Exception {
        Presentation presentation = new Presentation("test title", "test description", "test presenter");
        talkService.createTalkWithNewPresentation(presentation, "venue", date, time);
        verify(mockPresentationMapper).insertPresentation(presentation);
        verify(mockPresentationMapper).getPresentation("test title", "test presenter");
        verify(mockTalkMapper).insert(new Talk(any(Presentation.class), "venue", new DateParser(date, time).convertToDateTime()));
    }

    @Test
    public void shouldReturnTalk() {
        int talkId = 0;
        Talk talkExpected = new Talk(new Presentation("test title", "test description", "test owner"), "venue", new DateParser(date, time).convertToDateTime());
        when(mockTalkMapper.getTalk(talkId)).thenReturn(talkExpected);
        Talk talk = talkService.getTalk(talkId);
        verify(mockTalkMapper).getTalk(talkId);
        assertThat(talk, is(talkExpected));
    }

    @Test
    public void shouldGetAListOfUsersTalks() throws Exception {
        List<Talk> expectedTalkList = new ArrayList<Talk>();
        String owner = "test owner";
        expectedTalkList.add(new Talk(new Presentation("test title", "test description", owner), "venue", new DateParser(date, time).convertToDateTime()));
        expectedTalkList.add(new Talk(new Presentation("title", "description", owner), "test venue", new DateParser("01/08/2012", "10:00 AM").convertToDateTime()));
        when(mockTalkMapper.getTalksByUsername(owner)).thenReturn(expectedTalkList);

        assertThat(talkService.getMyTalks(owner), is(expectedTalkList));
        verify(mockTalkMapper).getTalksByUsername(owner);

    }

    @Test
    public void shouldGetAListOfTalksInThePastTwoDays() {
        //Given
        List<Talk> expectedTalkList = new ArrayList<Talk>();
        when(mockTalkMapper.getListOfRecentTalks(any(DateTime.class), any(DateTime.class))).thenReturn(expectedTalkList);
        //When
        List<Talk> actualTalksList = talkService.getRecentTalks();

        //Then
        verify(mockTalkMapper).getListOfRecentTalks(any(DateTime.class), any(DateTime.class));
        assertThat(actualTalksList, is(expectedTalkList));
    }

    @Test
    public void shouldMakeTalkWithLowercaseOwnerName() {
        Presentation upperCasePresentation = new Presentation("test title", "test description", "TEST_PRESENTER");
        talkService.createTalkWithNewPresentation(upperCasePresentation, "venue", date, time);
        Talk originalTalk = new Talk(upperCasePresentation, "venue", new DateParser(date, time).convertToDateTime());
        ArrayList<Talk> originalTalkList = new ArrayList<Talk>();
        originalTalkList.add(originalTalk);
        when(mockTalkMapper.getTalksByUsername("test_presenter")).thenReturn(originalTalkList);
        List<Talk> expected = talkService.getMyTalks("TEST_PRESENTER");
        assertThat(expected.contains(originalTalk), is(true));
    }
}



