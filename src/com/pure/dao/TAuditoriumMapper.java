package com.pure.dao;

import com.pure.db.TAuditorium;
import com.pure.db.TAuditoriumExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TAuditoriumMapper {
    int countByExample(TAuditoriumExample example);

    int deleteByExample(TAuditoriumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuditorium record);

    int insertSelective(TAuditorium record);

    List<TAuditorium> selectByExample(TAuditoriumExample example);

    TAuditorium selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuditorium record, @Param("example") TAuditoriumExample example);

    int updateByExample(@Param("record") TAuditorium record, @Param("example") TAuditoriumExample example);

    int updateByPrimaryKeySelective(TAuditorium record);

    int updateByPrimaryKey(TAuditorium record);

	int countByParam(Map<String, Object> param);

	List<Map<String, Object>> getAuditoriumPageInfo(Map<String, Object> param);
}