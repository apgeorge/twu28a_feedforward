package com.thoughtworks.twu.persistence;

import org.apache.ibatis.annotations.Insert;

public interface FeedbackMapper {

    @Insert("INSERT INTO feedback (feedback_comment) VALUES(#{feedbackComment})")
    int insertFeedback(String feedbackComment);
}
