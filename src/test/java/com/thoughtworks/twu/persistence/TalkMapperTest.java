package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.utils.ApplicationClock;
import com.thoughtworks.twu.utils.DateParser;
import com.thoughtworks.twu.utils.TestClock;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TalkMapperTest extends IntegrationTest {

    @Autowired
    private TalkMapper talkMapper;
    @Autowired
    private PresentationMapper presentationMapper;

    private Presentation presentation;
    private Talk talk;
    private TestClock testClock;

    @Before
    public void init() {
        presentation = new Presentation("XConf", "Ruby Conference", "aman king");
        testClock = new TestClock();
        talk = new Talk(presentation, "Pune Office", new DateParser("23/08/2012", "12:01 pm").convertToDateTime(), testClock.now());

    }

    @Test
    public void shouldVerifyCorrectInsertionOfTalk() throws Exception {
        Talk secondTalk = new Talk(presentation, "sjafh", new DateParser("22/08/2012", "04:56 AM").convertToDateTime(), testClock.now());
        assertThat(talkMapper.insert(talk), is(1));
        assertThat(talkMapper.insert(secondTalk), not(0));

    }


    @Test
    public void shouldGetTalkWhenTalkIdIsGiven() {
        presentationMapper.insertPresentation(presentation);
        presentation = presentationMapper.getPresentation(presentation.getTitle(), presentation.getOwner());
        Talk secondTalk = new Talk(presentation, "venue", new DateParser("22/08/2012", "04:56 AM").convertToDateTime(), testClock.now());
        talkMapper.insert(secondTalk);

        Talk talkQueried = talkMapper.getTalk(talkMapper.getLastId());
        assertThat(talkQueried, is(secondTalk));
    }


    @Test
    public void shouldGetAListOfTalksByUserName() {


        Presentation firstPresentation = new Presentation("XConf", "Ruby Conference", "aman king");
        presentationMapper.insertPresentation(firstPresentation);


        Presentation secondPresentation = new Presentation("Pecha Kucha", "seven wise men", "aman king");
        presentationMapper.insertPresentation(secondPresentation);


        Presentation firstPresentationWithId = presentationMapper.getPresentation(firstPresentation.getTitle(), firstPresentation.getOwner());
        Presentation secondPresentationWithId = presentationMapper.getPresentation(secondPresentation.getTitle(), secondPresentation.getOwner());


        Talk firstTalk = new Talk(firstPresentationWithId, "Pune Office", new DateParser("23/08/2012", "12:01 pm").convertToDateTime(), testClock.now());
        Talk secondTalk = new Talk(firstPresentationWithId, "pune", new DateParser("22/08/2012", "04:56 AM").convertToDateTime(), testClock.now());
        Talk thirdTalk = new Talk(secondPresentationWithId, "chennai", new DateParser("15/05/1990", "11:00 PM").convertToDateTime(), testClock.now());

        talkMapper.insert(firstTalk);
        talkMapper.insert(secondTalk);
        talkMapper.insert(thirdTalk);

        List<Talk> actualList;
        actualList = talkMapper.getTalksByUsername("aman king");

        List<Talk> expectedList = new ArrayList<Talk>();
        expectedList.add(firstTalk);
        expectedList.add(secondTalk);
        expectedList.add(thirdTalk);

        for (Talk t : expectedList) {
            assertTrue(actualList.contains(t));
        }
    }

    @Test
    public void shouldGetAListOfTalksByUserNameWithMultipleUsers() {
        Presentation firstPresentation = new Presentation("XConf", "Ruby Conference", "aman king");
        presentationMapper.insertPresentation(firstPresentation);

        Presentation secondPresentation = new Presentation("Pecha Kucha", "seven wise men", "Manali");
        presentationMapper.insertPresentation(secondPresentation);

        Presentation firstPresentationWithId = presentationMapper.getPresentation(firstPresentation.getTitle(), firstPresentation.getOwner());
        Presentation secondPresentationWithId = presentationMapper.getPresentation(secondPresentation.getTitle(), secondPresentation.getOwner());

        Talk firstTalk = new Talk(firstPresentationWithId, "Pune Office", new DateParser("23/08/2012", "12:01 pm").convertToDateTime(), testClock.now());
        Talk secondTalk = new Talk(firstPresentationWithId, "pune", new DateParser("22/08/2012", "04:56 AM").convertToDateTime(), testClock.now());
        Talk thirdTalk = new Talk(secondPresentationWithId, "chennai", new DateParser("15/05/1990", "11:00 PM").convertToDateTime(), testClock.now());

        talkMapper.insert(firstTalk);
        talkMapper.insert(secondTalk);
        talkMapper.insert(thirdTalk);


        List<Talk> actualList;
        actualList = talkMapper.getTalksByUsername("aman king");


        List<Talk> expectedList = new ArrayList<Talk>();
        expectedList.add(firstTalk);
        expectedList.add(secondTalk);


        for (Talk t : expectedList)
            assertTrue(actualList.contains(t));
    }

    @Test
    public void shouldGetAListOfTalksFromTheLastTwoDays() {
        //Given
        presentationMapper.insertPresentation(presentation);
        Presentation presentationWithID = presentationMapper.getPresentation(presentation.getTitle(), presentation.getOwner());
        Talk firstTalk = new Talk(presentationWithID, "Pune Office", new ApplicationClock().now().plusHours(1), testClock.now());
        Talk secondTalk = new Talk(presentationWithID, "chennai", new ApplicationClock().now().minusDays(1), testClock.now());
        Talk thirdTalk = new Talk(presentationWithID, "pune", new ApplicationClock().now().minusHours(1), testClock.now());

        talkMapper.insert(firstTalk);
        talkMapper.insert(secondTalk);
        talkMapper.insert(thirdTalk);
        //When
        List<Talk> listOfRecentTalks = talkMapper.getTalks(new ApplicationClock().now().minusDays(2), new ApplicationClock().now());
        //Then
        List<Talk> expectedList = new ArrayList<Talk>();
        expectedList.add(thirdTalk);
        expectedList.add(secondTalk);
        assertThat(listOfRecentTalks.contains(firstTalk), is(false));
        assertThat(listOfRecentTalks.contains(secondTalk), is(true));
        assertThat(listOfRecentTalks.contains(thirdTalk), is(true));
    }

    @Test
    public void shouldGetAListOfUpcomingTalksForAMonthBorder() throws Exception {
        presentationMapper.insertPresentation(presentation);
        Presentation presentationWithID = presentationMapper.getPresentation(presentation.getTitle(), presentation.getOwner());
        Talk firstTalk = new Talk(presentationWithID, "Pune Office", new ApplicationClock().now().plusHours(8), testClock.now());
        Talk secondTalk = new Talk(presentationWithID, "chennai", new ApplicationClock().now().plusDays(12), testClock.now());
        Talk thirdTalk = new Talk(presentationWithID, "pune", new ApplicationClock().now().plusDays(29), testClock.now());
        Talk fourthTalk = new Talk(presentationWithID, "pune", new ApplicationClock().now().plusDays(32), testClock.now());
        talkMapper.insert(firstTalk);
        talkMapper.insert(secondTalk);
        talkMapper.insert(thirdTalk);
        talkMapper.insert(fourthTalk);

        List<Talk> upcomingTalksList = talkMapper.getTalks(new ApplicationClock().now(), new ApplicationClock().now().plusMonths(1));

        assertThat(upcomingTalksList.contains(firstTalk), is(true));
        assertThat(upcomingTalksList.contains(secondTalk), is(true));
        assertThat(upcomingTalksList.contains(thirdTalk), is(true));
        assertThat(upcomingTalksList.contains(fourthTalk), is(false));
    }

    @Test
    public void shouldDeleteTalkById() throws Exception {
        presentationMapper.insertPresentation(presentation);
        Presentation presentationWithID = presentationMapper.getPresentation(presentation.getTitle(), presentation.getOwner());
        Talk firstTalk = new Talk(presentationWithID, "Pune Office", new ApplicationClock().now().plusHours(1), testClock.now());

        talkMapper.insert(firstTalk);
        int talkId = talkMapper.getLastId();

        assertThat(talkMapper.deleteById(talkId), is(1));
        assertThat(talkMapper.deleteById(talkId), is(0));
    }

    @Test
    public void shouldEditDescriptionOfTalk(){
        presentationMapper.insertPresentation(presentation);
        Presentation presentationWithID = presentationMapper.getPresentation(presentation.getTitle(), presentation.getOwner());
        Talk firstTalk = new Talk(presentationWithID, "Pune Office", new ApplicationClock().now().plusHours(1), testClock.now());

        talkMapper.insert(firstTalk);
        int talkId = talkMapper.getLastId();

        assertThat(talkMapper.editTalkDescription(talkId, "New Desc"), is(1));

    }

    @Test
    public void shouldEditVenueOfTalk(){
        presentationMapper.insertPresentation(presentation);
        Presentation presentationWithID = presentationMapper.getPresentation(presentation.getTitle(), presentation.getOwner());
        Talk firstTalk = new Talk(presentationWithID, "Pune Office", new ApplicationClock().now().plusHours(1), testClock.now());

        talkMapper.insert(firstTalk);
        int talkId = talkMapper.getLastId();

        assertThat(talkMapper.editTalkVenue(talkId, "New Venue"), is(1));

    }


    @Test
    public void shouldEditDateTimeOfTalk(){
        presentationMapper.insertPresentation(presentation);
        Presentation presentationWithID = presentationMapper.getPresentation(presentation.getTitle(), presentation.getOwner());
        Talk firstTalk = new Talk(presentationWithID, "Pune Office", new ApplicationClock().now().plusHours(1), testClock.now());

        talkMapper.insert(firstTalk);
        int talkId = talkMapper.getLastId();

        assertThat(talkMapper.editTalkDateTime(talkId, DateTime.now()), is(1));

    }

}
