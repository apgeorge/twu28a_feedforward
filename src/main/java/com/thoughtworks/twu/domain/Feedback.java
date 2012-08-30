package com.thoughtworks.twu.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;


public class Feedback {
    private int talkId;
    private String feedbackComment;
    private String attendee;
    private String attendeeMail;
    private DateTime timeAtCreation;


    public Feedback() {
    }

    public Feedback(int talkId, String feedbackComment, String attendee, String attendeeMail, DateTime dateTime) {
        this.feedbackComment = feedbackComment;
        this.talkId = talkId;
        this.attendee = attendee;
        this.attendeeMail = attendeeMail;
        this.timeAtCreation = dateTime;
    }

    public String getAttendee() {
        return attendee;
    }

    public String getAttendeeMail() {
        return attendeeMail;
    }

    public DateTime getTimeAtCreation() {
        return timeAtCreation;
    }

    public String getFeedbackComment() {
        return feedbackComment;
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
