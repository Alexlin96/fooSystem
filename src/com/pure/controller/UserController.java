package com.pure.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pure.common.PageInfo;
import com.pure.common.ReturnData;
import com.pure.common.SysCodeMsg;
import com.pure.db.TUser;
import com.pure.service.UserService;

@Controller
public class UserController extends BaseController {

	@Resource
	private UserService userService;

	/***
	 * 用户登录校验
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "login", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData login(String username, String password,
			HttpServletRequest request) {
		ReturnData returnData = new ReturnData();
		try {
			boolean flag = userService.login(username, password, request);
			if (flag) {
				returnData.setData(request.getSession().getAttribute("user"));
			} else {
				returnData.setCode(SysCodeMsg.CODE_10004);
				returnData.setMessage(SysCodeMsg.MSG_10004);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}

	@RequestMapping(value = "logout", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData logout(HttpServletRequest request) {
		ReturnData returnData = new ReturnData();
		request.getSession().removeAttribute("user");
		return returnData;

	}

	@RequestMapping(value = "regist", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData regist(HttpServletRequest request, TUser user) {
		ReturnData returnData = new ReturnData();
		try {
			if (!userService.regist(user)) {
				returnData.setCode(SysCodeMsg.CODE_10102);
				returnData.setMessage(SysCodeMsg.MSG_10102);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	@RequestMapping(value = "getUserInfo", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData getUserInfo(HttpServletRequest request) {
		ReturnData returnData = new ReturnData();
		try {
			TUser user=(TUser) request.getSession().getAttribute("user");
			returnData.setData(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	
	@RequestMapping(value = "getUserPageInfo", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData getUserPageInfo(HttpServletRequest request,Integer pageCurrent, Integer pageSize,TUser user) {
		ReturnData returnData = new ReturnData();
		try {
			PageInfo res=userService.getUserPageInfo(pageCurrent,pageSize,user);
			returnData.setData(res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	
	@RequestMapping(value = "saveUser", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData saveUser(TUser user) {
		ReturnData returnData = new ReturnData();
		try {
			userService.saveUser(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	@RequestMapping(value = "deleteUser", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData deleteUser(Integer id) {
		ReturnData returnData = new ReturnData();
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	/***
	 * 电影收藏
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "userFollow", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData userFollow(Integer filmid,HttpServletRequest request) {
		ReturnData returnData = new ReturnData();
		try {
			TUser user=(TUser) request.getSession().getAttribute("user");
			if (user!=null) {
				String res= userService.userFollow(user.getId(),filmid);
				returnData.setData(res);
			}else {
				returnData.setCode(SysCodeMsg.CODE_10002);
				returnData.setMessage(SysCodeMsg.MSG_10002);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	/**
	 * 用户评星
	 * @param filmid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "playStar", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData playStar(Integer filmid,String star,HttpServletRequest request) {
		ReturnData returnData = new ReturnData();
		try {
			TUser user=(TUser) request.getSession().getAttribute("user");
			if (user!=null) {
				userService.playStar(user.getId(),filmid,star);
			}else {
				returnData.setCode(SysCodeMsg.CODE_10002);
				returnData.setMessage(SysCodeMsg.MSG_10002);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	/**
	 * 查询个人收藏列表
	 * @param filmid
	 * @param star
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getUserFollow", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData getUserFollow(Integer filmid,String star,HttpServletRequest request) {
		ReturnData returnData = new ReturnData();
		try {
			TUser user=(TUser) request.getSession().getAttribute("user");
			if (user!=null) {
				List<Map<String,Object>> res= userService.getUserFollow(user.getId());
				returnData.setData(res);
			}else {
				returnData.setCode(SysCodeMsg.CODE_10002);
				returnData.setMessage(SysCodeMsg.MSG_10002);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
    /***
     * 点赞
     * @param filmid
     * @param star
     * @param request
     * @return
     */
	
	@RequestMapping(value = "thumbsUp", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData thumbsUp(int criticid,HttpServletRequest request) {
		ReturnData returnData = new ReturnData();
		try {
			TUser user=(TUser) request.getSession().getAttribute("user");
			if (user!=null) {
				String res= userService.thumbsUp(user.getId(),criticid);
				returnData.setData(res);
			}else {
				returnData.setCode(SysCodeMsg.CODE_10002);
				returnData.setMessage(SysCodeMsg.MSG_10002);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	@RequestMapping(value = "getUserRecommend", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData getUserRecommend(HttpServletRequest request) {
		ReturnData returnData = new ReturnData();
		try {
			TUser user=(TUser) request.getSession().getAttribute("user");
			List<Map<String,Object>> res= userService.getUserRecommend(user);
			returnData.setData(res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
}
