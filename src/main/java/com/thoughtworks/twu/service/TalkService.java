package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.persistence.PresentationMapper;
import com.thoughtworks.twu.persistence.TalkMapper;
import com.thoughtworks.twu.utils.ApplicationClock;
import com.thoughtworks.twu.utils.DateParser;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TalkService {

    private PresentationMapper presentationMapper;
    private ApplicationClock clock;
    private TalkMapper talkMapper;

    @Autowired
    public TalkService(TalkMapper talkMapper, PresentationMapper presentationMapper, ApplicationClock clock) {
        this.talkMapper = talkMapper;
        this.presentationMapper = presentationMapper;
        this.clock = clock;
    }

    public int createTalkWithNewPresentation(Presentation presentation, String venue, String date, String time) {
        presentationMapper.insertPresentation(presentation);
        presentation = presentationMapper.getPresentation(presentation.getTitle(), presentation.getOwner());
        return talkMapper.insert(new Talk(presentation, venue, new DateParser(date,time).convertToDateTime(), clock.now()));
    }

    public Talk getTalk(int talkId) {
        return talkMapper.getTalk(talkId);
    }

    public boolean validate(String title, String venue, String date, String time) {
        return !(title.isEmpty()||venue.isEmpty()||date.isEmpty()||time.isEmpty());
    }

    public List<Talk> getMyTalks(String owner) {
        return talkMapper.getTalksByUsername(owner.toLowerCase());
    }

    public List<Talk> getRecentTalks() {
        return talkMapper.getTalks(clock.now().minusDays(2), clock.now());
    }

    public List<Talk> getUpcomingTalks() {
       List<Talk> upcomingTalksList = talkMapper.getTalks(clock.now(), clock.now().plusMonths(1));
       Collections.reverse(upcomingTalksList);
       return upcomingTalksList;
    }

    public Boolean isUpcomingTalk(Talk talk) {
        return (talk.isUpcoming());

    }

}
