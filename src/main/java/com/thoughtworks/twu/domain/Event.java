package com.thoughtworks.twu.domain;

import com.thoughtworks.twu.persistence.PresentationMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Event {


   Presentation presentation;
    String venue;
    String date_;
    String time_;

    @Autowired
    private PresentationMapper presentationMapper;


    public Event() {
    }

    public Event(Presentation presentation, String venue, String date_, String time_) {
        this.presentation=presentation;
        this.venue=venue;
        this.date_ = date_;
        this.time_ = time_;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (date_ != null ? !date_.equals(event.date_) : event.date_ != null) return false;
        if (presentation != null ? !presentation.equals(event.presentation) : event.presentation != null) return false;
        if (presentationMapper != null ? !presentationMapper.equals(event.presentationMapper) : event.presentationMapper != null)
            return false;
        if (time_ != null ? !time_.equals(event.time_) : event.time_ != null) return false;
        if (venue != null ? !venue.equals(event.venue) : event.venue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = presentation != null ? presentation.hashCode() : 0;
        result = 31 * result + (venue != null ? venue.hashCode() : 0);
        result = 31 * result + (date_ != null ? date_.hashCode() : 0);
        result = 31 * result + (time_ != null ? time_.hashCode() : 0);
        result = 31 * result + (presentationMapper != null ? presentationMapper.hashCode() : 0);
        return result;
    }

}
