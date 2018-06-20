package com.pure.dao;

import com.pure.db.TFllow;
import com.pure.db.TFllowExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TFllowMapper {
    int countByExample(TFllowExample example);

    int deleteByExample(TFllowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TFllow record);

    int insertSelective(TFllow record);

    List<TFllow> selectByExample(TFllowExample example);

    TFllow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TFllow record, @Param("example") TFllowExample example);

    int updateByExample(@Param("record") TFllow record, @Param("example") TFllowExample example);

    int updateByPrimaryKeySelective(TFllow record);

    int updateByPrimaryKey(TFllow record);

	List<Map<String, Object>> getUserFollow(Integer id);
}