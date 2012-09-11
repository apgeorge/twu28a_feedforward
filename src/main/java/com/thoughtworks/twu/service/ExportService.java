package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;

import java.util.ArrayList;

public class ExportService {
    private FeedbackService feedbackService;
    private TalkService talkService;

    public ExportService(FeedbackService feedbackService, TalkService talkService) {
        this.feedbackService = feedbackService;
        this.talkService = talkService;
    }

    public String exportTalkWithFeedback(int talkId) {
        Talk talk = talkService.getTalk(talkId);
        Presentation presentation = talk.getPresentation();

        String export = String.format("%s by %s on %s at %s\n",
                presentation.getTitle(),
                presentation.getOwner(),
                talk.getDateTime().toString("dd/MM/yyyy"),
                talk.getVenue());

        ArrayList<Feedback> feedbacks = feedbackService.retrieveFeedbackByTalkId(talkId);
        for(Feedback feedback:feedbacks){
            export=export.concat(String.format("\"%s\", \"%s\", \"%s\"\n",feedback.getFeedbackComment(),feedback.getAttendee(),feedback.getTimeAtCreation().toString("dd/MM/yyyy")));
        }

        return export;
    }
}
