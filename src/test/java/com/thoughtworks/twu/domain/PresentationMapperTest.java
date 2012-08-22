package com.thoughtworks.twu.domain;

import com.thoughtworks.twu.persistence.PresentationMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PresentationMapperTest extends IntegrationTest {

    @Autowired
    private PresentationMapper presentationMapper;


    @Test
    public void shouldChoosePresentationByTitle() {
        String pechaKucha = "pechaKucha";
        String description = "pecha Kucha description";
        String owner = "Teddy";
        presentationMapper.insertPresentation(new Presentation(pechaKucha, description, owner));
        Presentation presentation = presentationMapper.getPresentationByTitle(pechaKucha);
        assertEquals(presentation, (new Presentation("pechaKucha", "pecha Kucha description", "Teddy")));
    }

    @Test
    public void shouldCheckThatDifferentPresentationsHaveDifferentId() throws Exception {
        presentationMapper.insertPresentation(new Presentation("blah", "Today at 25", "Prateek"));
        presentationMapper.insertPresentation(new Presentation("bleh", "Yesterday at 26", "Manan"));

        Presentation prateeksPresentation=presentationMapper.getPresentationByTitle("blah");
        Presentation manansPresentation=presentationMapper.getPresentationByTitle("bleh");

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
    assertThat(expectedPresentationList, is(presentationMapper.getPresentationsByOwner(owner)));
}


}
