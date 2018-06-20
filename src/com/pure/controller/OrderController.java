package com.pure.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pure.common.PageInfo;
import com.pure.common.ReturnData;
import com.pure.common.SysCodeMsg;
import com.pure.db.TFilm;
import com.pure.db.TUser;
import com.pure.service.OrderService;

@Controller
public class OrderController {
	  
	@Resource
	 private OrderService orderService;
	
	@RequestMapping(value = "getOrderPageInfo", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData getOrderPageInfo(HttpServletRequest request,Integer pageCurrent, Integer pageSize) {
		ReturnData returnData = new ReturnData();
		TUser user=(TUser) request.getSession().getAttribute("user");
		try {
			PageInfo res=orderService.getOrderPageInfo(pageCurrent,pageSize,user.getId());
			returnData.setData(res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	
	@RequestMapping(value = "orderTicket", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData orderTicket(HttpServletRequest request,Integer screeningsid,String position) {
		ReturnData returnData = new ReturnData();
		try {
			TUser user=(TUser) request.getSession().getAttribute("user");
			if (user!=null) {
				orderService.orderTicket(user.getId(),screeningsid,position);
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
	
	/*
	 * 座位
	 * */
	@RequestMapping(value = "getRestInfo", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData getRestInfo(HttpServletRequest request,Integer screeningsid) {
		ReturnData returnData = new ReturnData();
		try {
			List<String> res= orderService.getRestInfo(screeningsid);
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
