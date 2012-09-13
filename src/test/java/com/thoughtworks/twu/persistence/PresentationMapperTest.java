package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Presentation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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


    @Test
    public void shouldBeAbleToEditPresentation(){
        Presentation presentation = new Presentation("pechaKucha", "Today at 8", "prateek");
        presentationMapper.insertPresentation(presentation);
        Presentation presentationWithID = presentationMapper.getPresentation(presentation.getTitle(), presentation.getOwner());

        Presentation presentationToEdit = new Presentation("new title", "new desc", "");
        presentationToEdit.setId(presentationWithID.getId());

        presentationMapper.editPresentation(presentationToEdit);

        Presentation edittedPresentation = presentationMapper.getPresentation(presentationToEdit.getTitle(), presentation.getOwner());

        assertThat(edittedPresentation.getTitle(), is(presentationToEdit.getTitle()));
        assertThat(edittedPresentation.getDescription(), is(presentationToEdit.getDescription()));
    }

}
