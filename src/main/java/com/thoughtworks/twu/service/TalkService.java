package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.persistence.PresentationMapper;
import com.thoughtworks.twu.persistence.TalkMapper;
import com.thoughtworks.twu.utils.ApplicationClock;
import com.thoughtworks.twu.utils.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return talkMapper.insert(new Talk(presentation, venue, new DateParser(date,time).convertToDateTime()));
    }

    public Talk getTalk(int talkId) {
        return talkMapper.getTalk(talkId);
    }

    public boolean validate(String title, String venue, String date, String time) {
        return !(title.isEmpty()||venue.isEmpty()||date.isEmpty()||time.isEmpty());
    }

    public List<Talk> getListOfMyTalks(String owner) {
        return talkMapper.getTalksByUsername(owner);
    }

    public List<Talk> getListOfRecentTalks() {
        return talkMapper.getListOfRecentTalks(new ApplicationClock().now().minusDays(1), new ApplicationClock().now());
    }
}
