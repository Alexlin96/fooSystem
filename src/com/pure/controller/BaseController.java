package com.pure.controller;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pure.common.ReturnData;
import com.pure.common.SysCodeMsg;

/**
 * 基本Controller类，主要负责处理请求参数缺少的情况，并返回对应的状态码以及简要说明 <br>
 * 获取新的ReturnData对象<br>
 * 获取登录用户

 */

public class BaseController {

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public @ResponseBody ReturnData handleMyException(
			MissingServletRequestParameterException exception) {

		ReturnData returnData = new ReturnData(SysCodeMsg.CODE_10005,
				SysCodeMsg.MSG_10005, null);

		return returnData;
	}

	/**
	 * 获取登录用户对象
	 * 
	 * @param request
	 * @return
	 */
/*	protected SysUsers getUser(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("user");
		if (obj == null) {
			return null;
		}
		return (SysUsers) obj;
	}*/

	/**
	 * 返回一个新的ReturnData对象
	 * 
	 * @return
	 */
	protected ReturnData getNewReturnData() {
		return new ReturnData();
	}

	/**
	 * 获取合法的fetchNum值，默认值为1
	 * 
	 * @param fetchNum
	 * @return
	 */
	protected Integer getLegalFetchNum(Integer fetchNum) {
		return fetchNum == null || fetchNum == 0 ? 1 : fetchNum;
	}
	
}
