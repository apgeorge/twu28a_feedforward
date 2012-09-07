package com.thoughtworks.twu.service;

import com.thoughtworks.twu.persistence.PresentationMapper;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PresentationServiceTest {

    private PresentationMapper mockPresentationMapper;
    private PresentationService presentationService;

    @Before
    public void setUp() throws Exception {
        mockPresentationMapper = mock(PresentationMapper.class);
        presentationService = new PresentationService(mockPresentationMapper);
    }

    @Test
    public void shouldGetPresentationByTitle() throws Exception {
        String title = "jgik";
        presentationService.getPresentation(title);

        verify(mockPresentationMapper).getPresentation(title, null);
    }

    @Test
    public void shouldRetrieveListOfPresentationsByAnOwner() throws Exception {
        String owner = "manali";
        presentationService.getPresentationByOwner(owner);

        verify(mockPresentationMapper).getPresentationsByOwner(owner);
    }


}
