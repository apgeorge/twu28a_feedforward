package com.thoughtworks.twu.domain;


import com.thoughtworks.twu.utils.TestClock;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class FeedbackTest {

    @Test
    public void shouldTestTheCreationOfFeedbackAtCorrectTime() throws Exception {
        TestClock testClock = new TestClock();
        Feedback feedback = new Feedback(4, "feedbackComment", "attendee", "attendeeMail", testClock.now());

        Assert.assertThat(feedback.getTimeAtCreation(), is(testClock.now()));
    }

}

