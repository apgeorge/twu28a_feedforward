package com.thoughtworks.twu.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Talk {


    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    Presentation presentation;
    int talkId;
    String venue;
    String date;
    String time;

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getTalkId() {
        return talkId;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public String getVenue() {

        return venue;
    }

    public Talk() {
    }

    public Talk(Presentation presentation, String venue, String date, String time) {
        this.presentation=presentation;
        this.venue=venue;
        this.date = date;
        this.time = time;

    }

    public int getId() {
        return talkId;
    }

    /*@Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }*/

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Talk talk = (Talk) o;

        if (date != null ? !date.equals(talk.date) : talk.date != null) return false;
        if (presentation != null ? !presentation.equals(talk.presentation) : talk.presentation != null) return false;
        if (time != null ? !time.equals(talk.time) : talk.time != null) return false;
        if (venue != null ? !venue.equals(talk.venue) : talk.venue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = presentation != null ? presentation.hashCode() : 0;
        result = 31 * result + (venue != null ? venue.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

}
