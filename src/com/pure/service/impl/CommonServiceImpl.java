package com.pure.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pure.common.JsonUtil;
import com.pure.common.PageInfo;
import com.pure.db.KeyValue;
import com.pure.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {
	private static final Logger log = Logger.getLogger(CommonServiceImpl.class);
	@Resource(name = "sqlSessionFactory")
	private DefaultSqlSessionFactory fb;
	
	public PageInfo getPage(String detailSqlId, String countSqlId, Object obj,
			int pageNo, int pageSize){
		return getPage(detailSqlId, countSqlId, obj, pageNo, pageSize, 1);
	}
	
	public PageInfo getPage(String detailSqlId, String countSqlId, Object obj,
			int pageNo, int pageSize, int fetchNum) {
		PageInfo page = new PageInfo();
		page.setCurrPageNo(pageNo);
		page.setPageSize(pageSize);
		SqlSession ss = null;

		int cruuPageNo = page.getCurrPageNo();

		try {
			ss = fb.openSession();
			if (cruuPageNo < 1) {
				cruuPageNo = 1;
			}
			Object oo = ss.selectOne(countSqlId, obj);
			int cnt = new Integer(oo.toString());
			int pageTotal = cnt % page.getPageSize() == 0 ? cnt
					/ page.getPageSize() : (cnt / page.getPageSize() + 1);
			page.setTotal(cnt);
			page.setPageTotal(pageTotal);
			int offset = (cruuPageNo - 1) * page.getPageSize();
			RowBounds bounds = new RowBounds(offset, page.getPageSize()*fetchNum);
			page.setCurrPageNo(cruuPageNo);
			page.setRows(ss.selectList(detailSqlId, obj, bounds));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		log.debug(JsonUtil.object2json(page));
		return page;
	}
	
	public PageInfo getPageByCount(String detailSqlId, int count, Object obj,
			int pageNo, int pageSize, int fetchNum) {
		// TODO Auto-generated method stub
		PageInfo page = new PageInfo();
		page.setCurrPageNo(pageNo);
		page.setPageSize(pageSize);
		SqlSession ss = null;

		int cruuPageNo = page.getCurrPageNo();

		try {
			ss = fb.openSession();
			if (cruuPageNo < 1) {
				cruuPageNo = 1;
			}
			int cnt = new Integer(count);
			int pageTotal = cnt % page.getPageSize() == 0 ? cnt
					/ page.getPageSize() : (cnt / page.getPageSize() + 1);
			page.setTotal(cnt);
			page.setPageTotal(pageTotal);
			int offset = (cruuPageNo - 1) * page.getPageSize();
			RowBounds bounds = new RowBounds(offset, page.getPageSize()*fetchNum);
			page.setCurrPageNo(cruuPageNo);
			page.setRows(ss.selectList(detailSqlId, obj, bounds));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ss.close();
		}
		log.debug(JsonUtil.object2json(page));
		return page;
	}
	
	public String KVList2ObjectJson(List<KeyValue> kvList){
		Map<String,String> rlt = new HashMap<String,String>();
		if(kvList!=null&&kvList.size()>0){
			for(int i=0;i<kvList.size();i++){
				KeyValue kv = kvList.get(i);
				rlt.put(kv.getkey(), kv.getvalue());
			}
		}
		return JsonUtil.map2json(rlt);
	}
}
