package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;

import java.util.ArrayList;

public class ExportService {
    private FeedbackService feedbackService;
    private TalkService talkService;
    private MailService mailService;

    public ExportService(FeedbackService feedbackService, TalkService talkService, MailService mailService) {
        this.feedbackService = feedbackService;
        this.talkService = talkService;
        this.mailService = mailService;
    }

    public void exportTalkWithFeedback(int talkId) {
        Talk talk = talkService.getTalk(talkId);
        Presentation presentation = talk.getPresentation();

        String subject = String.format("Feedback Export : %s by %s on %s at %s",
                presentation.getTitle(),
                presentation.getOwner(),
                talk.getDateTime().toString("dd/MM/yyyy"),
                talk.getVenue());

        ArrayList<Feedback> feedbacks = feedbackService.retrieveFeedbackByTalkId(talkId);
        StringBuffer text = new StringBuffer();
        for (Feedback feedback : feedbacks) {
            text.append(String.format("\"%s\", \"%s\", \"%s\"\n", feedback.getFeedbackComment(), feedback.getAttendee(), feedback.getTimeAtCreation().toString("dd/MM/yyyy")));
        }

        mailService.send(presentation.getOwner() + "@thoughtworks.com", subject, text.toString());
    }
}
