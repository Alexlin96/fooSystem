package com.pure.dao;

import com.pure.db.TUserThumbs;
import com.pure.db.TUserThumbsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserThumbsMapper {
    int countByExample(TUserThumbsExample example);

    int deleteByExample(TUserThumbsExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(TUserThumbs record);

    int insertSelective(TUserThumbs record);

    List<TUserThumbs> selectByExample(TUserThumbsExample example);

    TUserThumbs selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") TUserThumbs record, @Param("example") TUserThumbsExample example);

    int updateByExample(@Param("record") TUserThumbs record, @Param("example") TUserThumbsExample example);

    int updateByPrimaryKeySelective(TUserThumbs record);

    int updateByPrimaryKey(TUserThumbs record);
}