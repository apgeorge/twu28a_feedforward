package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import org.apache.ibatis.annotations.*;
import org.joda.time.DateTime;

import java.util.List;

public interface TalkMapper {

    @Insert("INSERT INTO talk (presentation_id,venue, time_of_talk, last_modified_at) VALUES(#{presentation.id}, #{venue}, #{dateTime},#{lastModifiedAt})")
    int insert(Talk talk);


    @Select("SELECT talk_id, presentation_id, venue, time_of_talk, last_modified_at FROM talk WHERE talk_id =  #{talkId}")
    @Results(value = {
            @Result(property="talkId", column="talk_id"),
            @Result(property="venue", column="venue"),
            @Result(property="dateTime", column="time_of_talk"),
            @Result(property="lastModifiedAt", column = "last_modified_at"),
            @Result(property="presentation", column="presentation_id", javaType=Presentation.class, one=@One(select="selectPresentation"))
    })
    Talk getTalk(int talkId);

    @Select("SELECT id,title,description,owner FROM presentation where id = #{presentationId}")
    Presentation selectPresentation(int presentationId);

    @Select("SELECT talk_id FROM talk WHERE talk_id=IDENTITY()")
    int getLastId();

    @Select("SELECT talk_id,title,description,owner,venue,time_of_talk FROM presentation JOIN talk ON presentation.id=talk.presentation_id WHERE presentation.owner=#{owner} ORDER BY time_stamp DESC")
    @Results(value = {
            @Result(property="talkId", column="talk_id"),
            @Result(property="presentation.title", column="title"),
            @Result(property = "presentation.description",column="description"),
            @Result(property = "presentation.owner",column="owner"),
            @Result(property="venue", column="venue"),
            @Result(property="dateTime", column="time_of_talk")
    })
    List<Talk> getTalksByUsername(String owner);

    @Select("SELECT talk_id,title,description,owner,venue,time_of_talk " +
            "FROM presentation " +
            "JOIN talk ON presentation.id=talk.presentation_id " +
            "WHERE talk.time_of_talk > #{since} and talk.time_of_talk < #{to}" +
            "ORDER BY talk.time_of_talk DESC")
    @Results(value = {
            @Result(property="talkId", column="talk_id"),
            @Result(property="presentation.title", column="title"),
            @Result(property = "presentation.description",column="description"),
            @Result(property = "presentation.owner",column="owner"),
            @Result(property="venue", column="venue"),
            @Result(property="dateTime", column="time_of_talk")
    })
    List<Talk> getTalks(@Param("since") DateTime since, @Param("to") DateTime to);


    @Delete("DELETE FROM talk WHERE talk_id=#{talkId}")
    int deleteById(int talkId);

    @Update("UPDATE presentation SET description=#{newDescription} WHERE id=(SELECT presentation_id FROM talk WHERE talk_id=#{talkId})")
    int editTalkDescription(@Param("talkId") int talkId, @Param("newDescription") String newDescription);

    @Update("UPDATE talk SET venue=#{newVenue} WHERE talk_id=#{talkId}")
    int editTalkVenue(@Param("talkId") int talkId, @Param("newVenue") String newVenue);

    @Update("UPDATE talk SET time_of_talk=#{newDateTime} WHERE talk_id=#{talkId}")
    int editTalkDateTime(@Param("talkId") int talkId, @Param("newDateTime") DateTime newDateTime);

    @Update("UPDATE talk SET last_modified_at=#{newLastModified} WHERE talk_id=#{talkId}")
    int updateLastModified(@Param("talkId") int talkId,@Param("newLastModified") DateTime newLastModified);
}