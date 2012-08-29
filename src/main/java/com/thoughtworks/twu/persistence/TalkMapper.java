package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import org.apache.ibatis.annotations.*;

public interface TalkMapper {

    @Insert("INSERT INTO talk (presentation_id, venue, \"date\",\"time\") VALUES(#{presentation.id}, #{venue}, #{date}, #{time})")
    @Options(useGeneratedKeys=true)
    int insert(Talk talk);


    @Select("SELECT talk_id, presentation_id, venue, \"date\", \"time\" FROM talk WHERE talk_id =  #{talkId}")
    @Results(value = {
            @Result(property="talkId", column="talk_id"),
            @Result(property="venue", column="venue"),
            @Result(property="date", column="date"),
            @Result(property="time", column="time"),
            @Result(property="presentation", column="presentation_id", javaType=Presentation.class, one=@One(select="selectPresentation"))
    })
    Talk getTalk(int talkId);

    @Select("SELECT id,title,description,owner FROM presentation where id = #{presentationId}")
    Presentation selectPresentation(int presentationId);

}
