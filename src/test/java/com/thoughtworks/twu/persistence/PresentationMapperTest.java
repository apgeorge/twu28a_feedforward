package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Presentation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class PresentationMapperTest extends IntegrationTest {

    @Autowired
    private PresentationMapper presentationMapper;



    @Test
    public void shouldCheckThatDifferentPresentationsHaveDifferentId() throws Exception {
        presentationMapper.insertPresentation(new Presentation("blah", "Today at 25", "prateek"));
        presentationMapper.insertPresentation(new Presentation("blah", "Yesterday at 26", "manan"));

        Presentation prateeksPresentation=presentationMapper.getPresentation("blah", "prateek");
        Presentation manansPresentation=presentationMapper.getPresentation("blah", "manan");

        assertFalse(prateeksPresentation.getId() == manansPresentation.getId());
    }

    @Test(expected= DuplicateKeyException.class)
    public void shouldThrowExceptionIfOwnerTitleCombinationNotUnique() throws Exception {
        presentationMapper.insertPresentation(new Presentation("blah", "Today at 25", "prateek"));
        presentationMapper.insertPresentation(new Presentation("blah", "Yesterday at 26", "prateek"));

    }

    @Test
    public void shouldRetrievePresentationListByOwner() throws Exception {
    presentationMapper.insertPresentation(new Presentation("pechaKucha", "Today at 8", "prateek"));
    presentationMapper.insertPresentation(new Presentation("blah", "Today at 25", "prateek"));
    presentationMapper.insertPresentation(new Presentation("bleh", "Yesterday at 26", "manan"));
    presentationMapper.insertPresentation(new Presentation("blee", "Today at 9", "prateek"));

        ArrayList<Presentation> expectedPresentationList=new ArrayList<Presentation>();
        expectedPresentationList.add(new Presentation("blee", "Today at 9", "prateek"));
        expectedPresentationList.add(new Presentation("blah", "Today at 25", "prateek"));
        expectedPresentationList.add(new Presentation("pechaKucha", "Today at 8", "prateek"));

    String owner = "prateek";
        for(Presentation p:expectedPresentationList)
    assertTrue(presentationMapper.getPresentationsByOwner(owner).contains(p));
}

    /*
@Test
public void shouldInsertPresentationAndReflectItInTheObject(){

   Presentation presentation = new Presentation("pechaKucha", "Today at 8", "prateek");
   presentation.setId(100);
   presentationMapper.insertPresentation(presentation);

   assertThat(presentation.getId(), not(100));
}     */


}
