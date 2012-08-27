package com.thoughtworks.twu.domain;

import com.thoughtworks.twu.persistence.EventMapper;
import com.thoughtworks.twu.persistence.PresentationMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class EventMapperTest extends IntegrationTest {

    @Autowired
    private EventMapper eventMapper;

    @Test
    public void shouldVerifyCorrectInsertionOfEvent() throws Exception {
        String venue="Pune Office";
        String when="23/08/2012";
        String title="XConf";
        String description="Ruby Conference";
        String time="12:01 pm";
        String owner="Aman King";
        Presentation actualPresentation = new Presentation(title, description, owner);
        Event event=new Event(actualPresentation,venue,when,time);
        Event secondEvent=new Event(actualPresentation,"sjafh","kxcvn","sdfjde");
        assertThat(eventMapper.insertEvent(event),is(1));
        assertThat(eventMapper.insertEvent(secondEvent),not(0));

    }


}