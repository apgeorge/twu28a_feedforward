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
    public void shouldInsertEvent() throws Exception {
        String venue="Pune Office";
        String when="23-08-2012 10:05 am";
        String title="XConf";
        String description="Ruby Conference";

        String owner="Aman King";
        Presentation actualPresentation = new Presentation(title, description, owner);
        Event event=new Event(actualPresentation,venue,when);
        Event secondEvent=new Event(actualPresentation,"sjafh","kxcvn");
        assertThat(eventMapper.insertEvent(event),is(1));
        assertThat(eventMapper.insertEvent(secondEvent),not(2));

    }




    /* @Test
public void shouldCheckCreationOfEvent() throws Exception {
String venue="Pune Office";
String when="23-08-2012 10:05 am";
String title="XConf";
String description="Ruby Conference";

String owner="Aman King";


Event xConfPune=new Event(title,description,venue,when);

presentationMapper.insertPresentation(new Presentation(title,description,owner));
int presentationId=presentationMapper.getPresentationByTitle(title).getId();
eventMapper.insertEvent(presentationId,venue,when);

Event expectedEvent=eventMapper.getEventByName("XConf Pune");
assertThat(expectedEvent,is(xConfPune));
}        */
}
