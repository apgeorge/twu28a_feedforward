package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Feedback;
import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExportServiceTest {
    private FeedbackService feedbackService;
    private TalkService talkService;
    private ExportService exportService;


    @Before
    public void setUp() {
        feedbackService = mock(FeedbackService.class);
        talkService = mock(TalkService.class);
        exportService = new ExportService(feedbackService, talkService);
    }

    @Test
    public void shouldExportATalkWithFeedback() {
        final DateTime date = new DateTime(2012, 8, 4, 12, 5, 5);
        Talk talk = new Talk(new Presentation("Talk title",null,"test.twu"),"Ajanta Ellora",date, null);
        when(talkService.getTalk(42)).thenReturn(talk);

        ArrayList<Feedback> feedbackList = new ArrayList<Feedback>() {{
           add(new Feedback(42, "hi how are you\ndone", "testUser", "testUser@example.com", date));
           add(new Feedback(42, "not so bad!\nactually great!", "anotherUser", "anotherUser@example.com", date));
        }};
        when(feedbackService.retrieveFeedbackByTalkId(42)).thenReturn(feedbackList);

        String result = exportService.exportTalkWithFeedback(42);

        String expectedText = "Talk title by test.twu on " + date.toString("dd/MM/yyyy") + " at Ajanta Ellora\n"
            + "\"hi how are you\ndone\", \"testuser\", \"" + date.toString("dd/MM/yyyy") + "\"\n"
            + "\"not so bad!\nactually great!\", \"anotheruser\", \"" + date.toString("dd/MM/yyyy") + "\"\n";
        assertThat(result, is(expectedText));
    }

}
