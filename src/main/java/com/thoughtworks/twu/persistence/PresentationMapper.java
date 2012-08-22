package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Presentation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PresentationMapper {

   @Select("SELECT title,description,owner FROM presentation where title = #{title}")
    Presentation getPresentation(String title);

    @Insert("INSERT INTO presentation (title, description, owner) VALUES(#{title}, #{description}, #{owner})")
    void insertPresentation(Presentation presentation);

    @Select("SELECT title,description,owner FROM presentation where owner = #{owner}")
    List<Presentation> getPresentationsByOwner(String owner);


}
