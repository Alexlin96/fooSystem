package com.pure.service;

import java.util.List;

import com.pure.common.PageInfo;

public interface OrderService {

	PageInfo getOrderPageInfo(Integer pageCurrent, Integer pageSize, Integer id);

	void orderTicket(Integer id, Integer screeningsid, String position);

	List<String>  getRestInfo(Integer screeningsid);

}
