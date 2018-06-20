package com.pure.dao;

import com.pure.db.TOrderRestInfo;
import com.pure.db.TOrderRestInfoExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TOrderRestInfoMapper {
    int countByExample(TOrderRestInfoExample example);

    int deleteByExample(TOrderRestInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TOrderRestInfo record);

    int insertSelective(TOrderRestInfo record);

    List<TOrderRestInfo> selectByExample(TOrderRestInfoExample example);

    TOrderRestInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TOrderRestInfo record, @Param("example") TOrderRestInfoExample example);

    int updateByExample(@Param("record") TOrderRestInfo record, @Param("example") TOrderRestInfoExample example);

    int updateByPrimaryKeySelective(TOrderRestInfo record);

    int updateByPrimaryKey(TOrderRestInfo record);

	void updateRestInfo(Map<String, Object> param);

	String getRestInfo(Integer screeningsid);
}