package com.pure.dao;

import com.pure.db.TReply;
import com.pure.db.TReplyExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TReplyMapper {
    int countByExample(TReplyExample example);

    int deleteByExample(TReplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TReply record);

    int insertSelective(TReply record);

    List<TReply> selectByExample(TReplyExample example);

    TReply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TReply record, @Param("example") TReplyExample example);

    int updateByExample(@Param("record") TReply record, @Param("example") TReplyExample example);

    int updateByPrimaryKeySelective(TReply record);

    int updateByPrimaryKey(TReply record);

	List<Map<String, Object>> selectReplyByCriticid(String id);
}