package com.pure.service;

import java.util.Map;

import com.pure.common.PageInfo;
import com.pure.db.TAuditorium;

public interface AuditoriumService {

	PageInfo getAuditoriumPageInfo(Integer pageCurrent, Integer pageSize,
			TAuditorium tAuditorium);

	Map<String, Object> getAuditoriumById(Integer id);

	void deleteAuditoriumById(Integer id);

	void saveAuditorium(TAuditorium tAuditorium);
   
	
}
