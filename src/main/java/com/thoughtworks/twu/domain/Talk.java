package com.thoughtworks.twu.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
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

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
