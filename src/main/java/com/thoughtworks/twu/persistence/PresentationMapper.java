package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Presentation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PresentationMapper {

    @Select("SELECT title,description,owner,id FROM presentation where title = #{title}")
    Presentation getPresentationByTitle(String title);

    @Insert("INSERT INTO presentation (title, description, owner) VALUES(#{title}, #{description}, #{owner})")
    void insertPresentation(Presentation presentation);

    @Select("SELECT title,description,owner FROM presentation where owner = #{owner} ORDER BY time_stamp DESC")
    List<Presentation> getPresentationsByOwner(String owner);

}
