package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Talk;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface TalkMapper {

    @Insert("INSERT INTO talk (presentation_id, venue, date_,time_) VALUES(#{talk.presentation.id}, #{talk.venue}, #{talk.date_}, #{talk.time_})")
    int insert(@Param("talk") Talk talk);


    Talk getTalk(int talkId);
}
