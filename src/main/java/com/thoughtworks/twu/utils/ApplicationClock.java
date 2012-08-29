package com.thoughtworks.twu.utils;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class ApplicationClock {
    public DateTime now() {
        return DateTime.now();
    }
}
