package com.pure.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pure.common.PageInfo;
import com.pure.common.Util;
import com.pure.dao.TOrderMapper;
import com.pure.dao.TOrderRestInfoMapper;
import com.pure.dao.TScreeningsMapper;
import com.pure.db.TOrderRestInfo;
import com.pure.db.TScreenings;
import com.pure.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Resource
	private TScreeningsMapper tScreeningsMapper;
	
	@Resource
	private TOrderMapper tOrderMapper ;
	
	@Resource
	private TOrderRestInfoMapper tOrderRestInfoMapper;

	@Override
	public PageInfo getTicketPageInfo(Integer pageCurrent, Integer pageSize,
			TScreenings tScreenings) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		int start = (Integer.valueOf(pageCurrent) - 1)
				* Integer.valueOf(pageSize);
		param.put("start", start);
		param.put("end", Integer.valueOf(pageSize));
		if (!Util.isNullString(tScreenings.getType())) {
			param.put("filmname", tScreenings.getType());
		}
		if (tScreenings.getFilmid()!=null) {
			param.put("filmId", tScreenings.getFilmid());
		}
		PageInfo page = new PageInfo();
		page.setCurrPageNo(pageCurrent);
		page.setPageSize(pageSize);
		int cnt = tScreeningsMapper.countByParam(param);
		page.setPageTotal(cnt % page.getPageSize() == 0 ? cnt
				/ page.getPageSize() : (cnt / page.getPageSize() + 1));
		page.setTotal(cnt);
		List<Map<String, Object>> row = tScreeningsMapper
				.getTicketPageInfo(param);
		page.setRows(row);
		return page;
	}

	@Override
	public Map getTicketById(Integer id) {
		// TODO Auto-generated method stub
		return Util.objectToMap(tScreeningsMapper.selectByPrimaryKey(id));
	}

	@Override
	public void deleteTicletById(Integer id) {
		// TODO Auto-generated method stub
		tScreeningsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void saveTiclet(TScreenings tScreenings) {
		// TODO Auto-generated method stub
		if (tScreenings.getId() != null) {
			tScreeningsMapper.updateByPrimaryKeySelective(tScreenings);
		} else {
			Integer max= tScreeningsMapper.selectMaxId();
			if(max == null ){
				max = 1;
			}else{
				max += 1;
			}
			
			tScreenings.setId(max);
			tScreeningsMapper.insertSelective(tScreenings);
			TOrderRestInfo restInfo= new TOrderRestInfo();
			restInfo.setHallid(max);
			tOrderRestInfoMapper.insertSelective(restInfo);
		}
	}

	@Override
	public void cancleTiclet(Integer id) {
		// TODO Auto-generated method stub
		tOrderMapper.cancleTiclet(id);
	}

}
