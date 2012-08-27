package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Event;
import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.persistence.EventMapper;
import com.thoughtworks.twu.persistence.PresentationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

        private PresentationMapper presentationMapper;
        private EventMapper eventMapper;

        @Autowired
        public EventService(EventMapper eventMapper, PresentationMapper presentationMapper) {
            this.eventMapper = eventMapper;
            this.presentationMapper=presentationMapper;
        }

    public void createEventWithNewPresentation(Presentation presentation, String venue, String date,String time) {
         presentationMapper.insertPresentation(presentation);
         presentation=presentationMapper.getPresentationByTitle(presentation.getTitle());
        eventMapper.insertEvent(new Event(presentation,venue,date,time));
    }
}
