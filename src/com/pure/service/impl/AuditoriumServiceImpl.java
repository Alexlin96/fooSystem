package com.pure.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pure.common.PageInfo;
import com.pure.common.Util;
import com.pure.dao.TAuditoriumMapper;
import com.pure.db.TAuditorium;
import com.pure.service.AuditoriumService;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

	@Resource
	private TAuditoriumMapper tAuditoriumMapper;

	@Override
	public PageInfo getAuditoriumPageInfo(Integer pageCurrent,
			Integer pageSize, TAuditorium tAuditorium) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		int start = (Integer.valueOf(pageCurrent) - 1)
				* Integer.valueOf(pageSize);
		param.put("start", start);
		param.put("end", Integer.valueOf(pageSize));
		if (Util.isNullString(tAuditorium.getName())) {
			param.put("name", tAuditorium.getName());
		}
		PageInfo page = new PageInfo();
		page.setCurrPageNo(pageCurrent);
		page.setPageSize(pageSize);
		int cnt = tAuditoriumMapper.countByParam(param);
		page.setPageTotal(cnt % page.getPageSize() == 0 ? cnt
				/ page.getPageSize() : (cnt / page.getPageSize() + 1));
		page.setTotal(cnt);
		List<Map<String, Object>> row = tAuditoriumMapper
				.getAuditoriumPageInfo(param);
		page.setRows(row);
		return page;
	}

	@Override
	public Map<String, Object> getAuditoriumById(Integer id) {
		// TODO Auto-generated method stub
		return Util.objectToMap(tAuditoriumMapper.selectByPrimaryKey(id));
	}

	@Override
	public void deleteAuditoriumById(Integer id) {
		// TODO Auto-generated method stub
		tAuditoriumMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void saveAuditorium(TAuditorium tAuditorium) {
		// TODO Auto-generated method stub
		if (tAuditorium.getId() !=null) {
			tAuditoriumMapper.updateByPrimaryKeySelective(tAuditorium);
		} else {
			tAuditoriumMapper.insertSelective(tAuditorium);
		}
	}

}
