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

    public void createTalkWithNewPresentation(Presentation presentation, String venue, String date, String time) {
        presentationMapper.insertPresentation(presentation);
        presentation = presentationMapper.getPresentationByTitle(presentation.getTitle());
        talkMapper.insert(new Talk(presentation, venue, date, time));
    }

    public Talk getTalk(int talkId) {
        return talkMapper.getTalk(talkId);
    }
}
