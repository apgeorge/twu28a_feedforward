package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface FeedbackMapper {
    @Insert("INSERT INTO feedback (feedback_comment,talk_id,attendee,attendee_mail,time_at_creation) VALUES(#{feedbackComment},#{talkId},#{attendee},#{attendeeMail},#{timeAtCreation})")
    int insertFeedback(Feedback feedback);

    @Select("SELECT feedback_comment,talk_id,attendee,attendee_mail,time_at_creation FROM feedback WHERE feedback_id=IDENTITY()")
    @Results(value = {
            @Result(property = "feedbackComment", column = "feedback_comment"),
            @Result(property = "talkId", column = "talk_id"),
            @Result(property = "attendee", column = "attendee"),
            @Result(property = "attendeeMail", column = "attendee_mail"),
            @Result(property = "timeAtCreation",column ="time_at_creation", typeHandler = DateTimeTypeHandler.class)
    })
    Feedback getLastEnteredFeedback();

    @Select("SELECT talk_id,feedback_comment,attendee,attendee_mail,time_at_creation FROM feedback WHERE talk_id = #{talk_id} ORDER BY time_at_creation DESC")
    @Results(value = {
            @Result(property = "feedbackComment", column = "feedback_comment"),
            @Result(property = "talkId", column = "talk_id"),
            @Result(property = "attendee", column = "attendee"),
            @Result(property = "attendeeMail", column = "attendee_mail"),
            @Result(property = "timeAtCreation",column ="time_at_creation", typeHandler = DateTimeTypeHandler.class)
})
    ArrayList<Feedback> getFeedbackByTalkId(int talk_id);

}
