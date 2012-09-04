package com.thoughtworks.twu.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DateParserTest {

    String testDate;
    String testTime;

    @Before
    public void setUp() {
        testDate="04/09/2012";
        testTime="09:48 PM";
    }

    @Test
    public void shouldGetDateTimeObjectFromDateAndTimeString() {
        //Given
        DateParser dateParser = new DateParser(testDate, testTime);
        //When
        DateTime dateTime = dateParser.convertToDateTime();
        //Then
        assertThat(dateTime, is(new DateTime(2012,9,4,21,48, DateTimeZone.UTC)));
    }
}
