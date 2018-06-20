package com.pure.common;
/*
 * 返回状态码
 * */
public class SysCodeMsg {
	public final static String CODE_10000 = "10000";
	public final static String CODE_10001 = "10001";
	public final static String CODE_10002 = "10002";
	public final static String CODE_10003 = "10003";
	public final static String CODE_10004 = "10004";
	public final static String CODE_10005 = "10005";
	public final static String CODE_10006 = "10006";
	public final static String CODE_10007 = "10007";
	public final static String CODE_10008 = "10008";
	public final static String CODE_10009 = "10009";
	public final static String CODE_10101 = "10101";
	public final static String CODE_10102 = "10102";

	public final static String CODE_20000 = "20000";
	public final static String CODE_20001 = "20001";

	/*public final static String MSG_10000 = "System error";
	public final static String MSG_10001 = "Permission denied";
	public final static String MSG_10002 = "Not login or login timeout";
	public final static String MSG_10003 = "Login name or password is empty";
	public final static String MSG_10004 = "Login password or account is not correct";
	public final static String MSG_10005 = "No required parameters";
	public final static String MSG_10006 = "Parameter value is illega";
	public final static String MSG_10007 = "%1$s parameter value cannot be empty";
	public final static String MSG_10008 = "The user ID does not exist";
	public final static String MSG_10009 = "%1$s not exist";
	public final static String MSG_10101 = "Department name already exists";
	public final static String MSG_10102 = "User name already exists";
	public final static String MSG_10403 = "Role name already exists";
	public final static String MSG_10404 = "Resource name already exists";

	public final static String MSG_20000 = "Success";
	public final static String MSG_20001 = "Failed";*/
	public final static String MSG_10000 = "系统错误";
	public final static String MSG_10001 = "无操作权限";
	public final static String MSG_10002 = "未登录或登录超时";
	public final static String MSG_10003 = "用户名或密码为空";
	public final static String MSG_10004 = "用户名或密码错误";
	public final static String MSG_10005 = "必填参数未填";
	public final static String MSG_10006 = "参数不符合规定";
	public final static String MSG_10007 = "%1$s 参数不能为空";
	public final static String MSG_10008 = "用户ID不存在";
	public final static String MSG_10009 = "%1$s 不存在";
	public final static String MSG_10101 = "部门名称已存在";
	public final static String MSG_10102 = "用户名已存在";
	
	public final static String MSG_20000 = "成功";
	public final static String MSG_20001 = "失败";

	public final static ReturnData SYSTEM_ERROR = new ReturnData(
			SysCodeMsg.CODE_10000, SysCodeMsg.MSG_10000, null);

}
