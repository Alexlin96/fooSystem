package com.pure.dao;

import com.pure.db.TFilm;
import com.pure.db.TFilmExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TFilmMapper {
    int countByExample(TFilmExample example);

    int deleteByExample(TFilmExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TFilm record);

    int insertSelective(TFilm record);

    List<TFilm> selectByExample(TFilmExample example);

    TFilm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TFilm record, @Param("example") TFilmExample example);

    int updateByExample(@Param("record") TFilm record, @Param("example") TFilmExample example);

    int updateByPrimaryKeySelective(TFilm record);

    int updateByPrimaryKey(TFilm record);

	List<Map<String, Object>> getFilmPageInfo(Map<String, Object> param);

	int countByParam(Map<String, Object> param);

	List<Map<String, Object>> getHotFilm();
}