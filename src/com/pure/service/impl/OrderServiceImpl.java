package com.pure.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pure.common.PageInfo;
import com.pure.common.Util;
import com.pure.dao.TOrderMapper;
import com.pure.dao.TOrderRestInfoMapper;
import com.pure.db.TOrder;
import com.pure.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
  
	@Resource
	private TOrderMapper tOrderMapper;
	
	@Resource
	private TOrderRestInfoMapper tOrderRestInfoMapper;
	
	@Override
	public PageInfo getOrderPageInfo(Integer pageCurrent, Integer pageSize,
			Integer id) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		int start = (Integer.valueOf(pageCurrent) - 1)
				* Integer.valueOf(pageSize);
		param.put("start", start);
		param.put("end", Integer.valueOf(pageSize));
		param.put("id", id);
		PageInfo page = new PageInfo();
		page.setCurrPageNo(pageCurrent);
		page.setPageSize(pageSize);
		int cnt = tOrderMapper.countByParam(param);
		page.setPageTotal(cnt % page.getPageSize() == 0 ? cnt
				/ page.getPageSize() : (cnt / page.getPageSize() + 1));
		page.setTotal(cnt);
		List<Map<String, Object>> row = tOrderMapper
				.getOrderPageInfo(param);
		page.setRows(row);
		return page;
	}

	@Override
	public void orderTicket(Integer id, Integer screeningsid, String position) {
		// TODO Auto-generated method stub
		TOrder order=new TOrder();
		order.setBuytime(new Date());
		order.setPosition(position);
		order.setScreeningsid(screeningsid);
		order.setUserid(id);
		order.setStatus("已预定");
		tOrderMapper.insertSelective(order);
		//更新剩余位置
		Map<String,Object>param=new HashMap<String, Object>();
		param.put("screeningsid", screeningsid);
		param.put("position", ","+position);
		tOrderRestInfoMapper.updateRestInfo(param);
	}

	@Override
	public List<String> getRestInfo(Integer screeningsid) {
		// TODO Auto-generated method stub
		String res=tOrderRestInfoMapper.getRestInfo(screeningsid);
		List<String> resList=new ArrayList<String>();
	    if (res!=null) {
	    	String [] strs=res.split(",");
	    	for (int i = 0; i < strs.length; i++) {
				resList.add(strs[i]);
			}
	    	return resList;
		}else {
			return new ArrayList<String>();
		}
	}

}
