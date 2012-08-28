package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.persistence.TalkMapper;
import com.thoughtworks.twu.persistence.PresentationMapper;
import org.junit.Test;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TalkServiceTest {

    @Test
    public void shouldInsertPresentationOnCreationOfNewTalk() throws Exception {
        TalkMapper mockTalkMapper =mock(TalkMapper.class);
        PresentationMapper mockPresentationMapper=mock(PresentationMapper.class);


        TalkService talkService =new TalkService(mockTalkMapper,mockPresentationMapper);
        Presentation presentation = new Presentation("blah", "blah-blah", "Manali");
        talkService.createTalkWithNewPresentation(presentation, "venue", "date", "time");

        verify(mockPresentationMapper).insertPresentation(presentation);
        verify(mockPresentationMapper).getPresentationByTitle("blah");
        verify(mockTalkMapper).insert(new Talk(any(Presentation.class), "venue", "date", "time"));

}
}
