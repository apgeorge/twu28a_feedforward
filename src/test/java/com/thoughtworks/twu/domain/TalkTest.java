    package com.thoughtworks.twu.domain;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TalkTest {
    private final DateTime date = new DateTime(2012, 8, 4, 12, 5, 5);

    @Test
    public void shouldVerifyThatTalkOwnerIsUser() {
        // Given
        Presentation presentation=new Presentation("Test title","test desc","test.twu");
        Talk talk=new Talk(presentation,"venue",date,null);
        // When
        Boolean result=talk.isMyTalk("test.twu");
        // Then
        assertTrue(result);
    }

}
