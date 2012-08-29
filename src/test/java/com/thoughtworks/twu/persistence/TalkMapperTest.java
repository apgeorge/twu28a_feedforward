package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.*;
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


    @Test
    public void shouldGetTalkWhenTalkIdIsGiven(){
        presentationMapper.insertPresentation(presentation);
        presentation= presentationMapper.getPresentation(presentation.getTitle(), presentation.getOwner());
        Talk secondTalk=new Talk(presentation,"venue", "date", "time");
        talkMapper.insert(secondTalk);

        Talk talkQueried = talkMapper.getTalk(talkMapper.getLastId());
        assertThat(talkQueried, is(secondTalk));
    }

}
