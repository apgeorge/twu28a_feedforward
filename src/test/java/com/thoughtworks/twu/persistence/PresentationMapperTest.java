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
        presentationMapper.insertPresentation(new Presentation("blah", "Today at 25", "Prateek"));
        presentationMapper.insertPresentation(new Presentation("blah", "Yesterday at 26", "Manan"));

        Presentation prateeksPresentation=presentationMapper.getPresentation("blah", "Prateek");
        Presentation manansPresentation=presentationMapper.getPresentation("blah", "Manan");

        assertFalse(prateeksPresentation.getId() == manansPresentation.getId());
    }

    @Test(expected= DuplicateKeyException.class)
    public void shouldThrowExceptionIfOwnerTitleCombinationNotUnique() throws Exception {
        presentationMapper.insertPresentation(new Presentation("blah", "Today at 25", "Prateek"));
        presentationMapper.insertPresentation(new Presentation("blah", "Yesterday at 26", "Prateek"));

    }

    @Test
    public void shouldRetrievePresentationListByOwner() throws Exception {
    presentationMapper.insertPresentation(new Presentation("pechaKucha", "Today at 8", "Prateek"));
    presentationMapper.insertPresentation(new Presentation("blah", "Today at 25", "Prateek"));
    presentationMapper.insertPresentation(new Presentation("bleh", "Yesterday at 26", "Manan"));
    presentationMapper.insertPresentation(new Presentation("blee", "Today at 9", "Prateek"));

        ArrayList<Presentation> expectedPresentationList=new ArrayList<Presentation>();
        expectedPresentationList.add(new Presentation("blee", "Today at 9", "Prateek"));
        expectedPresentationList.add(new Presentation("blah", "Today at 25", "Prateek"));
        expectedPresentationList.add(new Presentation("pechaKucha", "Today at 8", "Prateek"));

    String owner = "Prateek";
        for(Presentation p:expectedPresentationList)
    assertTrue(presentationMapper.getPresentationsByOwner(owner).contains(p));
}

    /*
@Test
public void shouldInsertPresentationAndReflectItInTheObject(){

   Presentation presentation = new Presentation("pechaKucha", "Today at 8", "Prateek");
   presentation.setId(100);
   presentationMapper.insertPresentation(presentation);

   assertThat(presentation.getId(), not(100));
}     */


}
