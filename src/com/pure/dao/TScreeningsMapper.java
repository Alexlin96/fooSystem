package com.pure.dao;

import com.pure.db.TScreenings;
import com.pure.db.TScreeningsExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TScreeningsMapper {
    int countByExample(TScreeningsExample example);

    int deleteByExample(TScreeningsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TScreenings record);

    int insertSelective(TScreenings record);

    List<TScreenings> selectByExample(TScreeningsExample example);

    TScreenings selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TScreenings record, @Param("example") TScreeningsExample example);

    int updateByExample(@Param("record") TScreenings record, @Param("example") TScreeningsExample example);

    int updateByPrimaryKeySelective(TScreenings record);

    int updateByPrimaryKey(TScreenings record);

	int countByParam(Map<String, Object> param);

	List<Map<String, Object>> getTicketPageInfo(Map<String, Object> param);

	Integer selectMaxId();
}