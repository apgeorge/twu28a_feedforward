package com.thoughtworks.twu.service;

import com.thoughtworks.twu.persistence.PresentationMapper;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PresentationServiceTest {

    @Test
    public void shouldGetPresentationByTitle() throws Exception {
        PresentationMapper mockPresentationMapper=mock(PresentationMapper.class);

        PresentationService presentationService=new PresentationService(mockPresentationMapper);

        String title = "jgik";
        presentationService.getPresentation(title);

        verify(mockPresentationMapper).getPresentationByTitle(title);
    }

    @Test
    public void shouldRetrieveListOfPresentationsByAnOwner() throws Exception {
        PresentationMapper mockPresentationMapper=mock(PresentationMapper.class);

        PresentationService presentationService=new PresentationService(mockPresentationMapper);

        String owner = "Manali";
        presentationService.getPresentationByOwner(owner);

        verify(mockPresentationMapper).getPresentationsByOwner(owner);
    }
}
