package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface FeedbackMapper {
    @Insert("INSERT INTO feedback (feedback_comment,talk_id,attendee,attendee_mail) VALUES(#{feedbackComment},#{talkId},#{attendee},#{attendeeMail})")
    int insertFeedback(Feedback feedback);

    @Select("SELECT feedback_comment,talk_id,attendee,attendee_mail FROM feedback WHERE feedback_id=IDENTITY()")
    @Results(value = {
            @Result(property = "feedbackComment", column = "feedback_comment"),
            @Result(property = "talkId", column = "talk_id"),
            @Result(property = "attendee", column = "attendee"),
            @Result(property = "attendeeMail", column = "attendee_mail")
    })
    Feedback getLastEnteredFeedback();
}
