package com.thoughtworks.twu.domain;

import com.thoughtworks.twu.persistence.PresentationMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class PresentationMapperTest extends IntegrationTest {

    @Autowired
    private PresentationMapper presentationMapper;


    @Test
    public void shouldChoosePresentationByTitle() {
        String pechaKucha = "pechaKucha";
        String description = "pecha Kucha description";
        String owner = "Teddy";
        presentationMapper.insertPresentation(new Presentation(pechaKucha, description, owner));
        Presentation presentation = presentationMapper.getPresentation(pechaKucha);
        assertEquals(presentation, (new Presentation("pechaKucha", "pecha Kucha description", "Teddy")));
    }


}
