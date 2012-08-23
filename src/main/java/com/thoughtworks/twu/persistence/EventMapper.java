package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Event;
import com.thoughtworks.twu.domain.Presentation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

public interface EventMapper {

    @Insert("INSERT INTO event (presentation_id, venue, date_time) VALUES(#{event.presentation.id}, #{event.venue}, #{event.when})")
    int insertEvent(@Param("event")Event event);
}
