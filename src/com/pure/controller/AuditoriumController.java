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
import com.pure.db.TAuditorium;
import com.pure.service.AuditoriumService;

@Controller
public class AuditoriumController {
    
	@Resource
	private  AuditoriumService  auditoriumService;
	
	
	@RequestMapping(value = "getAuditoriumPageInfo", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData getAuditoriumPageInfo(HttpServletRequest request,Integer pageCurrent, Integer pageSize,TAuditorium tAuditorium) {
		ReturnData returnData = new ReturnData();
		try {
			PageInfo res=auditoriumService.getAuditoriumPageInfo(pageCurrent,pageSize,tAuditorium);
			returnData.setData(res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	@RequestMapping(value = "getAuditoriumById", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData getAuditoriumById(Integer id) {
		ReturnData returnData = new ReturnData();
		try {
			Map<String,Object> res=auditoriumService.getAuditoriumById(id);
			returnData.setData(res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	@RequestMapping(value = "deleteAuditoriumById", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData deleteAuditoriumById(Integer id) {
		ReturnData returnData = new ReturnData();
		try {
			auditoriumService.deleteAuditoriumById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	@RequestMapping(value = "saveAuditorium", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData saveAuditorium(TAuditorium tAuditorium) {
		ReturnData returnData = new ReturnData();
		try {
			auditoriumService.saveAuditorium(tAuditorium);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
} 
