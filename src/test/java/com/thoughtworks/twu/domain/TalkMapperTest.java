package com.thoughtworks.twu.domain;

import com.thoughtworks.twu.persistence.PresentationMapper;
import com.thoughtworks.twu.persistence.TalkMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class TalkMapperTest extends IntegrationTest {

    @Autowired
    private TalkMapper talkMapper;
    @Autowired
    private PresentationMapper presentationMapper;

    private Presentation presentation;
    private Talk talk;

    @Before
    public void init() {
        presentation = new Presentation("XConf", "Ruby Conference", "Aman King");
        talk = new Talk(presentation, "Pune Office", "23/08/2012", "12:01 pm");
    }

    @Test
    public void shouldVerifyCorrectInsertionOfTalk() throws Exception {
        Talk secondTalk =new Talk(presentation,"sjafh","kxcvn","sdfjde");
        assertThat(talkMapper.insert(talk),is(1));
        assertThat(talkMapper.insert(secondTalk),not(0));

    }
/*
    //Use mapper. Not service..
    @Test
    public void shouldGetTalkWhenTalkIdIsGiven(){
        TalkService talkService = new TalkService(talkMapper, presentationMapper);
        talkService.createTalkWithNewPresentation(presentation,talk.venue, talk.date, talk.time);

        //talkMapper.insert(talk);

        Talk talkQueried = talkMapper.getTalk(talk.getId());

        assertThat(talkQueried, is(talk));
    }*/

}
