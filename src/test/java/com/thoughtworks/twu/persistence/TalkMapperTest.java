package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.utils.ApplicationClock;
import com.thoughtworks.twu.utils.DateParser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
        talk = new Talk(presentation, "Pune Office", new DateParser("23/08/2012", "12:01 pm").convertToDateTime());

    }

    @Test
    public void shouldVerifyCorrectInsertionOfTalk() throws Exception {
        Talk secondTalk =new Talk(presentation,"sjafh",new DateParser("22/08/2012","04:56 AM").convertToDateTime());
        assertThat(talkMapper.insert(talk),is(1));
        assertThat(talkMapper.insert(secondTalk),not(0));

    }


    @Test
    public void shouldGetTalkWhenTalkIdIsGiven(){
        presentationMapper.insertPresentation(presentation);
        presentation= presentationMapper.getPresentation(presentation.getTitle(), presentation.getOwner());
        Talk secondTalk=new Talk(presentation,"venue", new DateParser("22/08/2012", "04:56 AM").convertToDateTime());
        talkMapper.insert(secondTalk);

        Talk talkQueried = talkMapper.getTalk(talkMapper.getLastId());
        assertThat(talkQueried, is(secondTalk));
    }



    @Test
    public void shouldGetAListOfTalksByUserName() {


        Presentation firstPresentation = new Presentation("XConf", "Ruby Conference", "Aman King");
        presentationMapper.insertPresentation(firstPresentation);


        Presentation secondPresentation = new Presentation("Pecha Kucha", "seven wise men", "Aman King");
        presentationMapper.insertPresentation(secondPresentation);


        Presentation firstPresentationWithId=presentationMapper.getPresentation(firstPresentation.getTitle(),firstPresentation.getOwner());
        Presentation secondPresentationWithId=presentationMapper.getPresentation(secondPresentation.getTitle(),secondPresentation.getOwner());


        Talk firstTalk = new Talk(firstPresentationWithId, "Pune Office", new DateParser("23/08/2012", "12:01 pm").convertToDateTime());
        Talk secondTalk = new Talk(firstPresentationWithId, "pune", new DateParser("22/08/2012", "04:56 AM").convertToDateTime());
        Talk thirdTalk= new Talk(secondPresentationWithId,"chennai",new DateParser("15/05/1990","11:00 PM").convertToDateTime());

        talkMapper.insert(firstTalk);
        talkMapper.insert(secondTalk);
        talkMapper.insert(thirdTalk);

        List<Talk> actualList;
        actualList= talkMapper.getTalksByUsername("Aman King");

        List<Talk> expectedList=new ArrayList<Talk>() ;
        expectedList.add(firstTalk);
        expectedList.add(secondTalk);
        expectedList.add(thirdTalk);

        for(Talk t:expectedList) {
            assertTrue(actualList.contains(t));
        }
    }

    @Test
    public void shouldGetAListOfTalksByUserNameWithMultipleUsers() {
        Presentation firstPresentation = new Presentation("XConf", "Ruby Conference", "Aman King");
        presentationMapper.insertPresentation(firstPresentation);

        Presentation secondPresentation = new Presentation("Pecha Kucha", "seven wise men", "Manali");
        presentationMapper.insertPresentation(secondPresentation);

        Presentation firstPresentationWithId=presentationMapper.getPresentation(firstPresentation.getTitle(),firstPresentation.getOwner());
        Presentation secondPresentationWithId =presentationMapper.getPresentation(secondPresentation.getTitle(),secondPresentation.getOwner());

        Talk firstTalk = new Talk(firstPresentationWithId, "Pune Office", new DateParser("23/08/2012", "12:01 pm").convertToDateTime());
        Talk secondTalk = new Talk(firstPresentationWithId, "pune", new DateParser("22/08/2012", "04:56 AM").convertToDateTime());
        Talk thirdTalk= new Talk(secondPresentationWithId,"chennai",new DateParser("15/05/1990","11:00 PM").convertToDateTime());

        talkMapper.insert(firstTalk);
        talkMapper.insert(secondTalk);
        talkMapper.insert(thirdTalk);


        List<Talk> actualList;
        actualList= talkMapper.getTalksByUsername("Aman King");


        List<Talk> expectedList=new ArrayList<Talk>() ;
        expectedList.add(firstTalk);
        expectedList.add(secondTalk);


        for(Talk t:expectedList)
            assertTrue(actualList.contains(t));
    }

    @Test
    public void shouldGetAListOfTalksFromTheLastTwoDays() {
        //Given
        presentationMapper.insertPresentation(presentation);
        Presentation presentationWithID = presentationMapper.getPresentation(presentation.getTitle(), presentation.getOwner());
        Talk firstTalk = new Talk(presentationWithID, "Pune Office", new ApplicationClock().now().plusHours(1));
        Talk secondTalk= new Talk(presentationWithID,"chennai",new ApplicationClock().now().minusDays(1));
        Talk thirdTalk = new Talk(presentationWithID, "pune", new ApplicationClock().now().minusHours(1));

        talkMapper.insert(firstTalk);
        talkMapper.insert(secondTalk);
        talkMapper.insert(thirdTalk);
        //When
        List<Talk> listOfRecentTalks = talkMapper.getListOfRecentTalks(new ApplicationClock().now().minusDays(2), new ApplicationClock().now());
        //Then
        List<Talk> expectedList=new ArrayList<Talk>();
        expectedList.add(thirdTalk);
        expectedList.add(secondTalk);
        assertThat(listOfRecentTalks.contains(firstTalk),is(false));
        assertThat(listOfRecentTalks.contains(secondTalk), is(true));
        assertThat(listOfRecentTalks.contains(thirdTalk), is(true));
    }
}
