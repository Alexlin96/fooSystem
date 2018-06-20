package com.pure.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pure.common.PageInfo;
import com.pure.db.TFllow;
import com.pure.db.TUser;

public interface UserService {

	boolean login(String username, String password, HttpServletRequest request);

	boolean regist(TUser user);

	PageInfo getUserPageInfo(Integer pageCurrent, Integer pageSize, TUser user);

	void saveUser(TUser user);

	void deleteUser(Integer id);

	String userFollow(Integer id, Integer filmid);

	void playStar(Integer id, Integer filmid, String star);

	List<Map<String, Object>> getUserFollow(Integer id);

	String thumbsUp(Integer id, int criticid);

	List<Map<String, Object>> getUserRecommend(TUser user);

	int getUserFollowByFilmId(Integer id);

}
