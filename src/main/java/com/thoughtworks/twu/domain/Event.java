package com.thoughtworks.twu.domain;

import com.thoughtworks.twu.persistence.PresentationMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Event {


   Presentation presentation;
    String venue;
    String when;

    @Autowired
    private PresentationMapper presentationMapper;


    public Event() {
    }

    public Event(Presentation presentation, String venue, String when) {
        this.presentation=presentation;
        this.venue=venue;
        this.when=when;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (presentation != null ? !presentation.equals(event.presentation) : event.presentation != null) return false;
        if (presentationMapper != null ? !presentationMapper.equals(event.presentationMapper) : event.presentationMapper != null)
            return false;
        if (venue != null ? !venue.equals(event.venue) : event.venue != null) return false;
        if (when != null ? !when.equals(event.when) : event.when != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = presentation != null ? presentation.hashCode() : 0;
        result = 31 * result + (venue != null ? venue.hashCode() : 0);
        result = 31 * result + (when != null ? when.hashCode() : 0);
        result = 31 * result + (presentationMapper != null ? presentationMapper.hashCode() : 0);
        return result;
    }
}
