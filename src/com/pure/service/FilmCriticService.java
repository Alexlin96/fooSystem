package com.pure.service;

import java.util.Map;

import com.pure.common.PageInfo;
import com.pure.db.TFilmCritic;
import com.pure.db.TReply;
import com.pure.db.TUser;

public interface FilmCriticService {

	Map<String, Object> getTFilmCriticById(Integer id);

	void deleteTFilmCriticById(Integer id);

	void saveTFilmCritic(TFilmCritic tTFilmCritic);

	PageInfo getTFilmCriticPageInfo(Integer pageCurrent, Integer pageSize,
			String name,String filmid,TUser user);

	void saveTReply(TReply tReply);

}
