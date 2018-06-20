package com.pure.common;

import java.util.List;

import com.shcm.bean.BalanceResultBean;
import com.shcm.bean.SendResultBean;
import com.shcm.send.DataApi;
import com.shcm.send.OpenApi;

/**
 * @author 
 *
 */
public class Sampler
{
	private static String sOpenUrl = "http://smsapi.c123.cn/OpenPlatform/OpenApi";
	private static String sDataUrl = "http://smsapi.c123.cn/DataPlatform/DataApi";
	
	// 接口帐号
	private static final String account = "1001@501329190001";
	
	// 接口密钥
	private static final String authkey = "3AC7B2C2311865FF6214D394A23CF515";
	
	// 通道组编号
	private static final int cgid = 52;
	
	// 默认使用的签名编号(未指定签名编号时传此值到服务器)
	private static final int csid = 540;
	
	public static List<SendResultBean> sendOnce(String mobile, String content) throws Exception
	{
		// 发送参数
		OpenApi.initialzeAccount(sOpenUrl, account, authkey, cgid, csid);
		
		// 状态及回复参数
		DataApi.initialzeAccount(sDataUrl, account, authkey);
		// 发送短信
		return OpenApi.sendOnce(mobile, content, 0, 0, null);
	
	}
	

}
