package com.thoughtworks.twu.domain;

import com.thoughtworks.twu.persistence.FeedbackMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FeedbackMapperTest extends IntegrationTest {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Test
    public void shouldInsertFeedbackIntoDatabase() {
        //Given
        String feedback = "Fantastic";
        //When
        int result = feedbackMapper.insertFeedback(feedback);
        //Then
        assertThat(result, is(1));
    }
}
