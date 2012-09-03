package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.persistence.PresentationMapper;
import com.thoughtworks.twu.persistence.TalkMapper;
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
        verify(mockPresentationMapper).getPresentation("test title", "test presenter");
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

    @Test
    public void shouldGetAListOfUsersTalks() throws Exception {


        List<Talk> expectedTalkList=new ArrayList<Talk>();
        String owner = "test owner";
        expectedTalkList.add(new Talk(new Presentation("test title", "test description", owner),"venue", "date", "time"));
        expectedTalkList.add(new Talk(new Presentation("title", "description", owner),"test venue", "test date", "test time"));
        when(mockTalkMapper.getTalksByUsername(owner)).thenReturn(expectedTalkList);

        assertThat(talkService.getListOfMyTalks(owner),is(expectedTalkList));


    }
}



