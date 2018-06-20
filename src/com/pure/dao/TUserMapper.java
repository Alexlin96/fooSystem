package com.pure.dao;

import com.pure.db.TUser;
import com.pure.db.TUserExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TUserMapper {
    int countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    TUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

	TUser getUserByName(String username);

	List<Map<String, Object>> getUserPageInfo(Map<String, Object> param);

	int countByParam(Map<String, Object> param);

	List<Map<String, Object>> getUserRecommend(Integer id);
}