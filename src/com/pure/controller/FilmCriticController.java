package com.pure.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pure.common.PageInfo;
import com.pure.common.ReturnData;
import com.pure.common.SysCodeMsg;
import com.pure.db.TFilmCritic;
import com.pure.db.TReply;
import com.pure.db.TUser;
import com.pure.service.FilmCriticService;

@Controller
public class FilmCriticController {

	@Resource
	private FilmCriticService filmCriticService;
	
	/*
	 * 影评
	 * */
	@RequestMapping(value = "getTFilmCriticPageInfo", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData getTFilmCriticPageInfo(HttpServletRequest request,
			Integer pageCurrent, Integer pageSize, String name, String filmid) {
		ReturnData returnData = new ReturnData();
		try {
			TUser user = (TUser) request.getSession().getAttribute("user");
			PageInfo res = filmCriticService.getTFilmCriticPageInfo(
					pageCurrent, pageSize, name, filmid, user);
			returnData.setData(res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}

	@RequestMapping(value = "getTFilmCriticById", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData getTFilmCriticById(Integer id) {
		ReturnData returnData = new ReturnData();
		try {
			Map<String, Object> res = filmCriticService.getTFilmCriticById(id);
			returnData.setData(res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}

	@RequestMapping(value = "deleteTFilmCriticById", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData deleteTFilmCriticById(Integer id) {
		ReturnData returnData = new ReturnData();
		try {
			filmCriticService.deleteTFilmCriticById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	/*
	 * 提交评论
	 * */
	@RequestMapping(value = "saveTFilmCritic", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData saveTFilmCritic(TFilmCritic tTFilmCritic) {
		ReturnData returnData = new ReturnData();
		try {
			filmCriticService.saveTFilmCritic(tTFilmCritic);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	/*
	 * 回复评论
	 * */
	@RequestMapping(value = "saveTReply", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData saveTReply(TReply tReply,HttpServletRequest request) {
		ReturnData returnData = new ReturnData();
		TUser user=(TUser) request.getSession().getAttribute("user");
		try {
			tReply.setUserid(user.getId());
			filmCriticService.saveTReply(tReply);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
}
