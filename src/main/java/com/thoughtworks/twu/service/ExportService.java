package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ExportService {
    private FeedbackService feedbackService;
    private TalkService talkService;
    private MailService mailService;

    @Autowired
    public ExportService(FeedbackService feedbackService, TalkService talkService, MailService mailService) {
        this.feedbackService = feedbackService;
        this.talkService = talkService;
        this.mailService = mailService;
    }

    public boolean exportTalkWithFeedback(int talkId, String username) {
        Talk talk = talkService.getTalk(talkId);
        Presentation presentation = talk.getPresentation();

        if (talkService.isMyTalk(talk,username)){

            String subject = String.format("Feedback Export : %s by %s on %s at %s",
                    presentation.getTitle(),
                    presentation.getOwner(),
                    talk.getDateTime().toString("dd/MM/yyyy"),
                    talk.getVenue());

            String subjectDelimiter=createDelimiter("=",80);
            String textDelimiter=createDelimiter("- ",70);

            ArrayList<Feedback> feedbacks = feedbackService.retrieveFeedbackByTalkId(talkId);
            StringBuffer text = new StringBuffer();
            text.append(subjectDelimiter+"\n"+subject+"\n"+subjectDelimiter+"\n");
            for (Feedback feedback : feedbacks) {
                text.append(String.format("%s  \n\n-By  %s  On  %s\n"+textDelimiter+"\n\n", feedback.getFeedbackComment(), feedback.getAttendee(), feedback.getTimeAtCreation().toString("dd/MM/yyyy")));
            }

            mailService.send(presentation.getOwner() + "@thoughtworks.com", subject, text.toString());
            return true;
        }
        return false;
    }

    private String createDelimiter(String character, int length){
        String delimiter = "";
        for(int i=0;i<length;i++){
            delimiter+=character;
        }
        return delimiter;
    }
}
