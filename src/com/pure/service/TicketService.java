package com.pure.service;

import java.util.Map;

import com.pure.common.PageInfo;
import com.pure.db.TFilm;
import com.pure.db.TScreenings;

public interface TicketService {

	PageInfo getTicketPageInfo(Integer pageCurrent, Integer pageSize,
			TScreenings tScreenings);

	Map getTicketById(Integer id);

	void deleteTicletById(Integer id);

	void saveTiclet(TScreenings tScreenings);

	void cancleTiclet(Integer id);

}
