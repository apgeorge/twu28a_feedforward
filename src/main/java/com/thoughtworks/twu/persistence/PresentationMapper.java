package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Presentation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PresentationMapper {

    @Select("SELECT title,description,owner,id FROM presentation where title = #{title} and owner = #{owner}")
    Presentation getPresentation(@Param("title")String title, @Param("owner")String owner);

    @Insert("INSERT INTO presentation (title, description, owner) VALUES(#{title}, #{description}, #{owner})")
    void insertPresentation(Presentation presentation);

    @Select("SELECT title,description,owner FROM presentation where owner = #{owner} ORDER BY time_stamp DESC")
    List<Presentation> getPresentationsByOwner(String owner);


}
