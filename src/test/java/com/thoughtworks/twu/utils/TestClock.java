package com.thoughtworks.twu.utils;

import org.joda.time.DateTime;

public class TestClock extends ApplicationClock {
    @Override
    public DateTime now(){
        return new DateTime(1991,7,28,9,0);}
}
