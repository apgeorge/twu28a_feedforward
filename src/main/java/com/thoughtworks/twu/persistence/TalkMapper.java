package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TalkMapper {

    @Insert("INSERT INTO talk (presentation_id,venue, talk_date,talk_time) VALUES(#{presentation.id}, #{venue}, #{date},#{time})")
    int insert(Talk talk);


    @Select("SELECT talk_id, presentation_id, venue, talk_date, talk_time FROM talk WHERE talk_id =  #{talkId}")
    @Results(value = {
            @Result(property="talkId", column="talk_id"),
            @Result(property="venue", column="venue"),
            @Result(property="date", column="talk_date"),
            @Result(property="time", column="talk_time"),
            @Result(property="presentation", column="presentation_id", javaType=Presentation.class, one=@One(select="selectPresentation"))
    })
    Talk getTalk(int talkId);

    @Select("SELECT id,title,description,owner FROM presentation where id = #{presentationId}")
    Presentation selectPresentation(int presentationId);

    @Select("SELECT talk_id FROM talk WHERE talk_id=IDENTITY()")
    int getLastId();

    @Select("SELECT talk_id,title,description,owner,venue,talk_date,talk_time FROM presentation JOIN talk ON presentation.id=talk.presentation_id WHERE presentation.owner=#{owner}")
    @Results(value = {
            @Result(property="talkId", column="talk_id"),
            @Result(property="presentation.title", column="title"),
            @Result(property = "presentation.description",column="description"),
            @Result(property = "presentation.owner",column="owner"),
            @Result(property="venue", column="venue"),
            @Result(property="date", column="talk_date"),
            @Result(property="time", column="talk_time")
    })
    List<Talk> getTalksByUsername(String owner);
}
