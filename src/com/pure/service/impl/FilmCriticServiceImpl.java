package com.pure.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pure.common.PageInfo;
import com.pure.common.Util;
import com.pure.dao.TFilmCriticMapper;
import com.pure.dao.TReplyMapper;
import com.pure.dao.TUserThumbsMapper;
import com.pure.db.TFilmCritic;
import com.pure.db.TReply;
import com.pure.db.TReplyExample;
import com.pure.db.TUser;
import com.pure.db.TUserThumbsExample;
import com.pure.service.FilmCriticService;

@Service
public class FilmCriticServiceImpl implements FilmCriticService {

	@Resource
	private TFilmCriticMapper tFilmCriticMapper;

	@Resource
	private TUserThumbsMapper tUserThumbsMapper;
	
	@Resource
	private TReplyMapper tReplyMapper;

	@Override
	public Map<String, Object> getTFilmCriticById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTFilmCriticById(Integer id) {
		// TODO Auto-generated method stub
		tFilmCriticMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void saveTFilmCritic(TFilmCritic tTFilmCritic) {
		// TODO Auto-generated method stub
		tTFilmCritic.setCreatTime(Util.getCurrentDate());
		tFilmCriticMapper.insertSelective(tTFilmCritic);
	}

	@Override
	public PageInfo getTFilmCriticPageInfo(Integer pageCurrent,
			Integer pageSize, String name, String filmid, TUser user) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		int start = (Integer.valueOf(pageCurrent) - 1)
				* Integer.valueOf(pageSize);
		param.put("start", start);
		param.put("end", Integer.valueOf(pageSize));
		if (!Util.isNullString(name)) {
			param.put("name", "%" + name + "%");
		}
		if (!Util.isNullString(filmid)) {
			param.put("filmid", filmid);
		}
		PageInfo page = new PageInfo();
		page.setCurrPageNo(pageCurrent);
		page.setPageSize(pageSize);
		int cnt = tFilmCriticMapper.countByParam(param);
		page.setPageTotal(cnt % page.getPageSize() == 0 ? cnt
				/ page.getPageSize() : (cnt / page.getPageSize() + 1));
		page.setTotal(cnt);
		List<Map<String, Object>> row = tFilmCriticMapper
				.getTFilmCriticPageInfo(param);
		for (Map<String, Object> temp : row) {
			//查看是否点赞
			if (user != null) {
				TUserThumbsExample example = new TUserThumbsExample();
				example.createCriteria().andCriticidEqualTo(
						Integer.parseInt(temp.get("id") + ""));
				example.createCriteria().andUseridEqualTo(user.getId());
				int tempNum = tUserThumbsMapper.countByExample(example);
				if (tempNum > 0) {
					temp.put("status", "已点赞");
				}else {
					temp.put("status", "未点赞");	
				}
			} else {
				temp.put("status", "未点赞");
			}
			//查看是否有回复
			 List<Map<String,Object>>ls= tReplyMapper.selectReplyByCriticid(temp.get("id")+"");
			 temp.put("reply", ls);
		}
		page.setRows(row);
		return page;
	}

	@Override
	public void saveTReply(TReply tReply) {
		// TODO Auto-generated method stub
		tReply.setCreatedate(Util.getCurrentDate());
		tReplyMapper.insertSelective(tReply);
	}

}
