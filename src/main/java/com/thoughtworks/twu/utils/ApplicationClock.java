package com.thoughtworks.twu.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class ApplicationClock {
    public DateTime now() {
         return DateTime.now(DateTimeZone.UTC).plusMinutes(330);

    }
}
