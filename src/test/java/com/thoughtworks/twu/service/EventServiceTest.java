package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Event;
import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.persistence.EventMapper;
import com.thoughtworks.twu.persistence.PresentationMapper;
import org.junit.Test;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EventServiceTest {

    @Test
    public void shouldInsertPresentationOnCreationOfNewEvent() throws Exception {
        EventMapper mockEventMapper=mock(EventMapper.class);
        PresentationMapper mockPresentationMapper=mock(PresentationMapper.class);


        EventService eventService=new EventService(mockEventMapper,mockPresentationMapper);
        Presentation presentation = new Presentation("blah", "blah-blah", "Manali");
        eventService.createEventWithNewPresentation(presentation,"venue","date","time");

        verify(mockPresentationMapper).insertPresentation(presentation);
        verify(mockPresentationMapper).getPresentationByTitle("blah");
        verify(mockEventMapper).insertEvent(new Event(presentation,"venue","date","time"));

}
}
