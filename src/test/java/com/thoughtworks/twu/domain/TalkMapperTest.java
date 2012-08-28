package com.thoughtworks.twu.domain;

import com.thoughtworks.twu.persistence.TalkMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class TalkMapperTest extends IntegrationTest {

    @Autowired
    private TalkMapper talkMapper;

    @Test
    public void shouldVerifyCorrectInsertionOfTalk() throws Exception {
        String venue="Pune Office";
        String when="23/08/2012";
        String title="XConf";
        String description="Ruby Conference";
        String time="12:01 pm";
        String owner="Aman King";
        Presentation actualPresentation = new Presentation(title, description, owner);
        Talk talk =new Talk(actualPresentation,venue,when,time);
        Talk secondTalk =new Talk(actualPresentation,"sjafh","kxcvn","sdfjde");
        assertThat(talkMapper.insert(talk),is(1));
        assertThat(talkMapper.insert(secondTalk),not(0));

    }


}
