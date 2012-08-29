package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.persistence.TalkMapper;
import com.thoughtworks.twu.persistence.PresentationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TalkService {

    private PresentationMapper presentationMapper;
    private TalkMapper talkMapper;

    @Autowired
    public TalkService(TalkMapper talkMapper, PresentationMapper presentationMapper) {
        this.talkMapper = talkMapper;
        this.presentationMapper = presentationMapper;
    }

    public int createTalkWithNewPresentation(Presentation presentation, String venue, String date, String time) {
        presentationMapper.insertPresentation(presentation);
        presentation = presentationMapper.getPresentation(presentation.getTitle(), presentation.getOwner());
        return talkMapper.insert(new Talk(presentation, venue, date, time));
    }

    public Talk getTalk(int talkId) {
        return talkMapper.getTalk(talkId);
    }

    public boolean validate(String title, String description, String venue, String date, String time) {
        return !(title.isEmpty()||description.isEmpty()||venue.isEmpty()||date.isEmpty()||time.isEmpty());
    }
}
