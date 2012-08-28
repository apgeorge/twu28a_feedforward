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

    public int createEventWithNewPresentation(Presentation presentation, String venue, String date, String time) {
         presentationMapper.insertPresentation(presentation);
         presentationMapper.getPresentationByTitle(presentation.getTitle());
         return eventMapper.insertEvent(new Event(presentation,venue,date,time));
    }

    public boolean validate(String title, String description, String venue, String date, String time) {
        return !(title.isEmpty()||description.isEmpty()||venue.isEmpty()||date.isEmpty()||time.isEmpty());
}
}
