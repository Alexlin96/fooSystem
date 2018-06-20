package com.pure.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.pure.common.PageInfo;
import com.pure.common.Util;
import com.pure.dao.TFilmCriticMapper;
import com.pure.dao.TFilmMapper;
import com.pure.dao.TFllowMapper;
import com.pure.dao.TStarMapper;
import com.pure.dao.TUserMapper;
import com.pure.dao.TUserThumbsMapper;
import com.pure.db.TFilmCritic;
import com.pure.db.TFllow;
import com.pure.db.TFllowExample;
import com.pure.db.TStar;
import com.pure.db.TUser;
import com.pure.db.TUserExample;
import com.pure.db.TUserThumbs;
import com.pure.db.TUserThumbsExample;
import com.pure.service.UserService;

@Service
public class UserServiceImpl extends CommonServiceImpl implements UserService {

	@Resource
	private TUserMapper tUserMapper;
	
	@Resource
	private TFllowMapper tFllowMapper;
	
	@Resource
	private  TStarMapper tStarMapper;
   
	@Resource
	private  TUserThumbsMapper tUserThumbsMapper;
	
	
	@Resource
	private TFilmCriticMapper tFilmCriticMapper;
	
	@Resource
	private TFilmMapper  tFilmMapper;
	/***
	 * 用户登录校验
	 */
	@Override
	public boolean login(String username, String password,
			HttpServletRequest request) {
		boolean flag = false;
		TUser user = tUserMapper.getUserByName(username);
		// 判断用户是否存在
		if (user != null) {
			// 如果存在则验证密码
			if (user.getPassword().equals(password)) {// 验证成功
				request.getSession().setMaxInactiveInterval(-200);
				request.getSession().setAttribute("user", user);
				flag = true;
			}
		}
		return flag;
	}

	/***
	 * 用户注册
	 */
	@Override
	public boolean regist(TUser user) {
		// TODO Auto-generated method stub
		boolean flag = false;
		// 判断用户是否已经注册
		TUser usertemp = tUserMapper.getUserByName(user.getUsername());
		if (usertemp != null) {
			// 如果注册 则返回 用户已注册
			return flag;
		} else {
			// 未注册则注册成功！
			tUserMapper.insertSelective(user);
			return true;
		}
	}

	/***
	 * 分页查询用户列表
	 */
	@Override
	public PageInfo getUserPageInfo(Integer pageCurrent, Integer pageSize,
			TUser user) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		int start = (Integer.valueOf(pageCurrent) - 1)
				* Integer.valueOf(pageSize);
		param.put("start", start);
		param.put("end", Integer.valueOf(pageSize));
		if (Util.isNullString(user.getRealname())) {
			param.put("realname", user.getRealname());
		}
		PageInfo page = new PageInfo();
		page.setCurrPageNo(pageCurrent);
		page.setPageSize(pageSize);
		int cnt = tUserMapper.countByParam(param);
		page.setPageTotal(cnt % page.getPageSize() == 0 ? cnt
				/ page.getPageSize() : (cnt / page.getPageSize() + 1));
		page.setTotal(cnt);
		List<Map<String, Object>> row = tUserMapper.getUserPageInfo(param);
		page.setRows(row);
		return page;
	}

	@Override
	public void saveUser(TUser user) {
		// TODO Auto-generated method stub
		if (!Util.isNullString(user.getId() + "")) {
			tUserMapper.updateByPrimaryKeySelective(user);
		} else {
			tUserMapper.insertSelective(user);

		}
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		tUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public String userFollow(Integer id, Integer filmid) {
		// TODO Auto-generated method stub
		TFllowExample example=new TFllowExample();
		example.createCriteria().andFilmidEqualTo(filmid);
		example.createCriteria().andUseridEqualTo(id);
		 List<TFllow> userthumbs= tFllowMapper.selectByExample(example);
		 if (userthumbs.size()>0) {//已经收藏
			 tFllowMapper.deleteByExample(example);
			 return  "取消收藏成功！";

		 }else {
				TFllow  fllow=new TFllow();
				fllow.setFilmid(filmid);
				fllow.setUserid(id);
				tFllowMapper.insertSelective(fllow);
			 return  "收藏成功！";
		}
	}

	@Override
	public void playStar(Integer id, Integer filmid, String star) {
		// TODO Auto-generated method stub
		TStar tstar=new TStar();
		tstar.setFilmid(filmid);
		tstar.setUserid(id);
		tstar.setStar(star);
		tStarMapper.insertSelective(tstar);
	}

	@Override
	public List<Map<String, Object>> getUserFollow(Integer id) {
		// TODO Auto-generated method stub
		return 	tFllowMapper.getUserFollow(id);
	}

	@Override
	public String thumbsUp(Integer id, int criticid) {
		// TODO Auto-generated method stub
		TUserThumbsExample example=new TUserThumbsExample();
		example.createCriteria().andCriticidEqualTo(criticid);
		example.createCriteria().andUseridEqualTo(id);
		 List<TUserThumbs> userthumbs= tUserThumbsMapper.selectByExample(example);
		 TFilmCritic critic=tFilmCriticMapper.selectByPrimaryKey(criticid);
		 if (userthumbs.size()>0) {//已经点赞
			 critic.setThumbs(critic.getThumbs()-1);
			 tFilmCriticMapper.updateByPrimaryKeySelective(critic);
			 tUserThumbsMapper.deleteByExample(example);
			 return  "取消点赞成功！";

		 }else {
			 critic.setThumbs(critic.getThumbs()+1);
			 tFilmCriticMapper.updateByPrimaryKeySelective(critic);
			 TUserThumbs thumbs=new TUserThumbs();
			 thumbs.setCriticid(criticid);
			 thumbs.setUserid(id);
			 tUserThumbsMapper.insertSelective(thumbs);
			 return  "点赞成功！";
		}
	}

	@Override
	public List<Map<String, Object>> getUserRecommend(TUser user) {
		// TODO Auto-generated method stub
	   Map<String,Object> param=new HashMap<String, Object>();
	   if (null != user) {
		   return tUserMapper.getUserRecommend(user.getId());
       }else {
    	   return tFilmMapper.getHotFilm();
       }
	}

	@Override
	public int getUserFollowByFilmId(Integer id) {
		// TODO Auto-generated method stub
		TFllowExample example=new TFllowExample();
		example.createCriteria().andFilmidEqualTo(id);
		return tFllowMapper.countByExample(example);
	}
}
