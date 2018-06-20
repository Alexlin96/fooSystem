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
import com.pure.common.Util;
import com.pure.db.TScreenings;
import com.pure.service.TicketService;

@Controller
public class TicketController  extends BaseController{
	
	@Resource
	private  TicketService ticketService;
   
	/*
	 * 影厅
	 * */
	@RequestMapping(value = "getTicketPageInfo", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData getTicketPageInfo(HttpServletRequest request,Integer pageCurrent, Integer pageSize,TScreenings tScreenings) {
		ReturnData returnData = new ReturnData();
		try {
			PageInfo res=ticketService.getTicketPageInfo(pageCurrent,pageSize,tScreenings);
			returnData.setData(res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	   
		@RequestMapping(value = "getTicketById", produces = { "application/json;charset=UTF-8" })
		@ResponseBody
		public ReturnData getTicketById(Integer id) {
			ReturnData returnData = new ReturnData();
			try {
				Map res=ticketService.getTicketById(id);
				returnData.setData(res);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				returnData.setCode(SysCodeMsg.CODE_10000);
				returnData.setMessage(SysCodeMsg.MSG_10000);
			}
			return returnData;
		}
		
		@RequestMapping(value = "deleteTicletById", produces = { "application/json;charset=UTF-8" })
		@ResponseBody
		public ReturnData deleteTicletById(Integer id) {
			ReturnData returnData = new ReturnData();
			try {
				ticketService.deleteTicletById(id);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				returnData.setCode(SysCodeMsg.CODE_10000);
				returnData.setMessage(SysCodeMsg.MSG_10000);
			}
			return returnData;
		}
		
		/*
		 * 新增场次
		 * */
		@RequestMapping(value = "saveTiclet", produces = { "application/json;charset=UTF-8" })
		@ResponseBody
		public ReturnData saveTiclet(TScreenings tScreenings,String playTime1) {
			ReturnData returnData = new ReturnData();
			try {
				if (!Util.isNullString(playTime1)) {
					tScreenings.setPlayTime(Util.StrToDate(playTime1, "yyyy-MM-dd HH:mm:ss"));
				}
				ticketService.saveTiclet(tScreenings);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				returnData.setCode(SysCodeMsg.CODE_10000);
				returnData.setMessage(SysCodeMsg.MSG_10000);
			}
			return returnData;
		}
		
		
		
		@RequestMapping(value = "cancleTiclet", produces = { "application/json;charset=UTF-8" })
		@ResponseBody
		public ReturnData cancleTiclet(Integer id) {
			ReturnData returnData = new ReturnData();
			try {
				ticketService.cancleTiclet(id);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				returnData.setCode(SysCodeMsg.CODE_10000);
				returnData.setMessage(SysCodeMsg.MSG_10000);
			}
			return returnData;
		}
}
