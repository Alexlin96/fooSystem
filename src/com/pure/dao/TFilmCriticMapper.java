package com.pure.dao;

import com.pure.db.TFilmCritic;
import com.pure.db.TFilmCriticExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TFilmCriticMapper {
    int countByExample(TFilmCriticExample example);

    int deleteByExample(TFilmCriticExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TFilmCritic record);

    int insertSelective(TFilmCritic record);

    List<TFilmCritic> selectByExample(TFilmCriticExample example);

    TFilmCritic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TFilmCritic record, @Param("example") TFilmCriticExample example);

    int updateByExample(@Param("record") TFilmCritic record, @Param("example") TFilmCriticExample example);

    int updateByPrimaryKeySelective(TFilmCritic record);

    int updateByPrimaryKey(TFilmCritic record);

	int countByParam(Map<String, Object> param);

	List<Map<String, Object>> getTFilmCriticPageInfo(Map<String, Object> param);
}