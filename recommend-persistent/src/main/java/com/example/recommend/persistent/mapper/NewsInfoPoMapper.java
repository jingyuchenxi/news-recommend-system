package com.example.recommend.persistent.mapper;

import com.example.recommend.persistent.entity.NewsInfoPo;
import com.example.recommend.persistent.entity.NewsInfoPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface NewsInfoPoMapper {
    @SelectProvider(type=NewsInfoPoSqlProvider.class, method="countByExample")
    long countByExample(NewsInfoPoExample example);

    @DeleteProvider(type=NewsInfoPoSqlProvider.class, method="deleteByExample")
    int deleteByExample(NewsInfoPoExample example);

    @Delete({
        "delete from news_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into news_info (id, title, ",
        "keywords, url, author, ",
        "date_published, gmt_create, ",
        "gmt_modified, content)",
        "values (#{id,jdbcType=BIGINT}, #{title,jdbcType=VARCHAR}, ",
        "#{keywords,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, #{author,jdbcType=VARCHAR}, ",
        "#{datePublished,jdbcType=TIMESTAMP}, #{gmtCreate,jdbcType=TIMESTAMP}, ",
        "#{gmtModified,jdbcType=TIMESTAMP}, #{content,jdbcType=LONGVARCHAR})"
    })
    int insert(NewsInfoPo record);

    @InsertProvider(type=NewsInfoPoSqlProvider.class, method="insertSelective")
    int insertSelective(NewsInfoPo record);

    @SelectProvider(type=NewsInfoPoSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="date_published", property="datePublished", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<NewsInfoPo> selectByExampleWithBLOBs(NewsInfoPoExample example);

    @SelectProvider(type=NewsInfoPoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="date_published", property="datePublished", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    List<NewsInfoPo> selectByExample(NewsInfoPoExample example);

    @Select({
        "select",
        "id, title, keywords, url, author, date_published, gmt_create, gmt_modified, ",
        "content",
        "from news_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="date_published", property="datePublished", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    NewsInfoPo selectByPrimaryKey(Long id);

    @UpdateProvider(type=NewsInfoPoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") NewsInfoPo record, @Param("example") NewsInfoPoExample example);

    @UpdateProvider(type=NewsInfoPoSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") NewsInfoPo record, @Param("example") NewsInfoPoExample example);

    @UpdateProvider(type=NewsInfoPoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") NewsInfoPo record, @Param("example") NewsInfoPoExample example);

    @UpdateProvider(type=NewsInfoPoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(NewsInfoPo record);

    @Update({
        "update news_info",
        "set title = #{title,jdbcType=VARCHAR},",
          "keywords = #{keywords,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "author = #{author,jdbcType=VARCHAR},",
          "date_published = #{datePublished,jdbcType=TIMESTAMP},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(NewsInfoPo record);

    @Update({
        "update news_info",
        "set title = #{title,jdbcType=VARCHAR},",
          "keywords = #{keywords,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "author = #{author,jdbcType=VARCHAR},",
          "date_published = #{datePublished,jdbcType=TIMESTAMP},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(NewsInfoPo record);
}