package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.persistence.TalkMapper;
import com.thoughtworks.twu.persistence.PresentationMapper;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TalkServiceTest {

    private TalkService talkService;
    private TalkMapper mockTalkMapper;
    private PresentationMapper mockPresentationMapper;

    @Before
    public void init(){
        mockTalkMapper = mock(TalkMapper.class);
        mockPresentationMapper = mock(PresentationMapper.class);
        talkService = new TalkService(mockTalkMapper, mockPresentationMapper);
    }

    @Test
    public void shouldInsertPresentationOnCreationOfNewTalk() throws Exception {
        Presentation presentation = new Presentation("test title", "test description", "test presenter");

        talkService.createTalkWithNewPresentation(presentation, "venue", "date", "time");

        verify(mockPresentationMapper).insertPresentation(presentation);
        verify(mockPresentationMapper).getPresentationByTitle("test title");
        verify(mockTalkMapper).insert(new Talk(any(Presentation.class), "venue", "date", "time"));
    }

    @Test
    public void  shouldReturnTalk(){
        int talkId = 0;
        Talk talkExpected = new Talk(new Presentation("test title", "test description", "test owner"),"venue", "date", "time");
        when(mockTalkMapper.getTalk(talkId)).thenReturn(talkExpected);

        Talk talk = talkService.getTalk(talkId);

        assertThat(talk, is(talkExpected));
    }


}



