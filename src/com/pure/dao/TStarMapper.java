package com.pure.dao;

import com.pure.db.TStar;
import com.pure.db.TStarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TStarMapper {
    int countByExample(TStarExample example);

    int deleteByExample(TStarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TStar record);

    int insertSelective(TStar record);

    List<TStar> selectByExample(TStarExample example);

    TStar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TStar record, @Param("example") TStarExample example);

    int updateByExample(@Param("record") TStar record, @Param("example") TStarExample example);

    int updateByPrimaryKeySelective(TStar record);

    int updateByPrimaryKey(TStar record);
}