package com.pure.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pure.common.PageInfo;
import com.pure.common.Util;
import com.pure.dao.TFilmMapper;
import com.pure.db.TFilm;
import com.pure.service.FilmService;

@Service
public class FilmServiceImpl implements FilmService {

	@Resource
	private TFilmMapper tFilmMapper;

	@Override
	public PageInfo getFilmPageInfo(Integer pageCurrent, Integer pageSize,
			TFilm tFilm) {
		System.out.println(tFilm.getBigtype()+", "); 
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		int start = (Integer.valueOf(pageCurrent) - 1)
				* Integer.valueOf(pageSize);
		param.put("start", start);
		param.put("end", Integer.valueOf(pageSize));
		if (!Util.isNullString(tFilm.getFilmname())) {
			param.put("filmname", "%" + tFilm.getFilmname() + "%");
		}
		if (!Util.isNullString(tFilm.getType())) {
			param.put("type", tFilm.getType());
		}
		if (!Util.isNullString(tFilm.getArea())) {
			param.put("area", tFilm.getArea());
		}
		if (!Util.isNullString(tFilm.getYear())) {
			param.put("year", tFilm.getYear());
		}
		PageInfo page = new PageInfo();
		page.setCurrPageNo(pageCurrent);
		page.setPageSize(pageSize);
		int cnt = tFilmMapper.countByParam(param);
		page.setPageTotal(cnt % page.getPageSize() == 0 ? cnt
				/ page.getPageSize() : (cnt / page.getPageSize() + 1));
		page.setTotal(cnt);
		List<Map<String, Object>> row = tFilmMapper.getFilmPageInfo(param);
		page.setRows(row);
		return page;
	}

	@Override
	public Map<String, Object> getFilmById(Integer id) {
		// TODO Auto-generated method stub
		return Util.objectToMap(tFilmMapper.selectByPrimaryKey(id));
	}

	@Override
	public void deleteFilmById(Integer id) {
		// TODO Auto-generated method stub
		tFilmMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void saveFilm(TFilm tFilm) {
		// TODO Auto-generated method stub
		if (tFilm.getId() != null) {
			tFilmMapper.updateByPrimaryKeySelective(tFilm);
		} else {
			tFilmMapper.insertSelective(tFilm);
		}

	}

}
