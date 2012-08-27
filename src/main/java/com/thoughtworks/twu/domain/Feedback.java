package com.thoughtworks.twu.domain;

public class Feedback {
    private String feedbackComment;
    private int eventId;
    private String attendee;
    private String attendeeMail;

    public Feedback() {
    }

    public Feedback(String feedbackComment, int eventId, String attendee, String attendeeMail) {

        this.feedbackComment = feedbackComment;
        this.eventId = eventId;
        this.attendee = attendee;
        this.attendeeMail = attendeeMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        if (eventId != feedback.eventId) return false;
        if (attendee != null ? !attendee.equals(feedback.attendee) : feedback.attendee != null) return false;
        if (attendeeMail != null ? !attendeeMail.equals(feedback.attendeeMail) : feedback.attendeeMail != null)
            return false;
        if (feedbackComment != null ? !feedbackComment.equals(feedback.feedbackComment) : feedback.feedbackComment != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = feedbackComment != null ? feedbackComment.hashCode() : 0;
        result = 31 * result + eventId;
        result = 31 * result + (attendee != null ? attendee.hashCode() : 0);
        result = 31 * result + (attendeeMail != null ? attendeeMail.hashCode() : 0);
        return result;
    }
}
