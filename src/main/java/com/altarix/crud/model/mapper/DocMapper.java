package com.altarix.crud.model.mapper;

import com.altarix.crud.model.entity.Doc;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Вячеслав on 20.10.2017.
 */
@Mapper
public interface DocMapper {
    @Results({@Result(property = "cardId", column = "doc_id")})
    @Select("select * from Doc where doc_id = #{id}")
    Doc getDocById(long id);

    @Results({@Result(property = "cardId", column = "doc_id")})
    @Select("select * from DOC")
    List<Doc> getDocs();

    @Insert("insert into Doc(code, date, name, kind) values(#{code}, #{date}, #{name}, #{kind})")
    void insert(Doc doc);

    @Delete("delete from Doc where doc_id = #{cardId}")
    void delete(Doc doc);

    @Update("update Doc set code = #{code}, date = #{date}, name = #{name} where doc_id = #{cardId}")
    void update(Doc doc);
}
