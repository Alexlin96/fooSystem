package com.pure.common;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import oracle.net.aso.l;
import oracle.net.aso.r;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import com.pure.service.model.SpatialInfo;
import com.pure.servicecfg.ConditionCfg;

public class Util {
	public static final String spatialReference = "4490";

	public static Document readXml() {
		try {
			// Document doc = DocumentHelper.parseText("");
			SAXReader saxReader = new SAXReader();
			File configFile = new File("/WEB-INFclassesServiceCfg.xml");
			Document document = saxReader.read(configFile);
			return document;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 在list中添加map，如果mapKeyName值与mapKeyValue相同,就替换该元素,否则就新加入一个元素
	 * 
	 * @param list
	 * @param mapKeyName
	 *            用来判断是否相同的字段名
	 * @param mapKeyValue
	 *            用来判断是否相同的字段名的值
	 * @return
	 */
	public static void addMapToListByMapKey(List<Map<String, String>> list, String mapKeyName,
			Map<String, String> cmap) {
		if (list == null || list.size() == 0 || mapKeyName == null || cmap == null) {
			return;
		}
		boolean isAdd = false;
		// 循环查找是否已经有该元素,如果有就替换
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = list.get(i);
			if (cmap.get(mapKeyName).equals(map.get(mapKeyName))) {// 获取替换的map
				list.remove(i);
				list.add(i, cmap);
				isAdd = true;
				break;
			}
		}
		// 如果没有该元素,就新加入
		if (!isAdd) {
			list.add(cmap);
		}
	}

	/***
	 * 根据指定的数字字符串,获取自增1后,指定位数lenth长度的字符串(位数不够的用0补充)
	 * 如果自增后的数字大于需要返回的位数,则返回自增后的数字字符窜
	 * 
	 * @param numStr
	 *            数字字符串
	 * @param lenth
	 *            需要的返回的字符串长度
	 * @return
	 */
	public static String getStrForNumStr(String numStr, int lenth) {
		if (numStr == null || numStr.equals("")) {
			return "";
		}
		Integer num = Integer.parseInt(numStr) + 1;
		String str = num + "";
		if (str.length() >= lenth) {// 如果数字长度大于等于需要返回的长度,则直接返回该数字
			return str;
		} else {// 如果小于需要返回的长度,则用零来补充
			for (int i = str.length(); i < lenth; i++) {
				str = "0" + str;
			}
		}
		return str;
	}

	/**
	 * 根据给定的条件组装查询的where条件
	 * 
	 * @param conditionType
	 *            查询条件配置配型
	 * @param str
	 *            搜索条件,多个条件用逗号隔开,每个条件格式为 名称-运算描述-值
	 * @return
	 */
	public static String getWhereCondition(String conditionType, String str) {
		String strWhere = "";
		if (str == null || str.trim().equals("")) {
			return strWhere;
		}
		Map<String, Map<String, String>> map = ConditionCfg.getCondtionCfg(conditionType);// 获取配置的查询内容
		if (map == null || map.size() == 0) {
			return strWhere;
		}
		String[] strs = str.split(",");
		for (int i = 0; i < strs.length; i++) {
			String strCon = strs[i];
			String[] values = strCon.split("-");
			Map<String, String> condition = map.get(values[0]);
			if (condition == null) {
				continue;
			}
			String dataType = condition.get("dataType");
			String columnName = condition.get("columnName");
			String conditionStr = splitCondtion(dataType, values[1], columnName, values[2]);
			if (conditionStr != null && !conditionStr.equals("")) {
				strWhere += " and " + conditionStr;
			}

		}

		return strWhere;
	}

	public static float string2Float(String data) {
		try {
			return Float.valueOf(data);
		} catch (Exception e) {
			return 0;
		}
	}

	/***
	 * 解析匹配具体查询条件
	 * 
	 * @param dataType
	 *            字段数据类型
	 * @param operationDesc
	 *            条件运算描述
	 * @param column
	 *            数据库匹配字段名
	 * @param value
	 *            条件值
	 * @return
	 */
	public static String splitCondtion(String dataType, String operationDesc, String column, String value) {
		String str = "";
		String operation = getOperationCondition(operationDesc);
		if ("String".equalsIgnoreCase(dataType)) {// 字符窜类型
			if (operationDesc.contains("包含")) {// 包含或不包含
				str = " " + column + " " + operation + " '%" + value + "%'";
			} else {
				str = " " + column + " " + operation + " '" + value + "'";
			}
		} else if ("Date".equalsIgnoreCase(dataType)) {// 日期类型

		} else if ("Number".equalsIgnoreCase(dataType)) {// 数字窜类型
			str = " " + column + " " + operation + " " + value;
		}
		return str;
	}

	/**
	 * 根据运算符描述获取运算符
	 * 
	 * @param operationDesc
	 *            运算符描述
	 * @return
	 */
	public static String getOperationCondition(String operationDesc) {
		String operation = "";
		if ("大于".equalsIgnoreCase(operationDesc)) {
			operation = ">";
		} else if ("大于等于".equalsIgnoreCase(operationDesc)) {
			operation = ">=";
		} else if ("小于".equalsIgnoreCase(operationDesc)) {
			operation = "<";
		} else if ("小于等于".equalsIgnoreCase(operationDesc)) {
			operation = "<=";
		} else if ("等于".equalsIgnoreCase(operationDesc)) {
			operation = "=";
		} else if ("不等于".equalsIgnoreCase(operationDesc)) {
			operation = "!=";
		} else if ("包含".equalsIgnoreCase(operationDesc)) {
			operation = "like";
		} else if ("不包含".equalsIgnoreCase(operationDesc)) {
			operation = "not like";
		}
		return operation;
	}

	/**
	 * @param type
	 *            0 获取原始的uuid,1 获取不带中间-的32位的纯字符的uuid
	 * @return
	 */
	public static String getUuid(String type) {
		String uuid = UUID.randomUUID().toString();
		if ("1".equals(type)) {
			uuid = uuid.replace("-", "");
		}
		return uuid;
	}

	/***
	 * 获取数组中,n个不重复的随机元素
	 * 
	 * @param obj
	 * @param n
	 * @return
	 */
	public static String[] randomArray(String[] obj, int n) {
		if (obj == null || obj.length == 0) {
			return null;
		}
		if (n >= obj.length) {
			return obj;
		}
		List<String> list = new LinkedList<String>();
		Collections.addAll(list, obj);
		Random r = new Random();
		String[] strarray = new String[n];
		int index = 0;
		for (int i = 0; i < n; i++) {
			// 随着循环的继续,逐渐抛弃后面的元素
			index = r.nextInt(list.size());
			strarray[i] = list.get(index);
			// 删除list中的该元素
			list.remove(index);
		}
		return strarray;
	}

	public static void main(String[] args) {
		String[] data = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20" };
		data = randomArray(data, 19);
		for (String str : data) {
			System.out.println(str);
		}
	}

	/**
	 * 字符串是否为空串，包括本身字符串为null以及""
	 * 
	 * @param target
	 *            目标串
	 * @return 目标串是否为空串
	 * @author huangzhen
	 */
	public static boolean isNullString(String target) {
		if (target == null || "".equals(target.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * Date转换为字符串
	 * 
	 * @param date
	 * @return
	 * @author Huangzhen
	 */
	public static String dateFormat(Date date, String pattern) {
		if (date == null || isNullString(pattern)) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	// 去除rownum_
	public static void removeRownum(List<Map<String, Object>> rows) {
		for (Map<String, Object> row : rows) {
			row.remove("ROWNUM_");
		}
	}

	// 组装坐标
	public static void installCoordinate(List<Map<String, Object>> rows) {
		if (rows != null && rows.size() > 0) {// 组装坐标以及格式化时间
			for (int i = 0; i < rows.size(); i++) {
				// System.out.println(rows.get(i));
				if (rows.get(i) != null) {
					Map map = (Map) rows.get(i);
					// 组装坐标
					if (map.get("fLongitude") != null || map.get("fLatitude") != null) {
						SpatialInfo sInfo = new SpatialInfo();
						sInfo.setx(map.get("fLongitude") == null ? null : (BigDecimal) map.get("fLongitude"));
						sInfo.sety(map.get("fLatitude") == null ? null : (BigDecimal) map.get("fLatitude"));
						sInfo.setSpatialReference("4490");
						map.remove("fLongitude");
						map.remove("fLatitude");
						map.put("sInfo", sInfo);
					}

				}
			}
		}
	}

	/**
	 * 主体类型数量统计
	 * 
	 * @param data
	 * @return
	 */
	public static List<Map<String, Object>> dataFormatProcessing(List<Map<String, Object>> data) {
		String[] level = { "A", "B", "C", "D" };
		String[] type = { "食品", "药品", "保健食品", "化妆品", "医疗器械" };
		String[] type2 = { "food", "medic", "healthFood", "makeup", "medicInstrument" };
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < level.length; i++) {
			Map<String, Object> lv = new HashMap<String, Object>();
			lv.put("fSuperviseLevel", level[i]);
			int tol = 0;
			for (int j = 0; j < data.size(); j++) {
				// 按等级匹配查询结果
				if (level[i].equals(data.get(j).get("fSuperviseLevel"))) {
					for (int k = 0; k < type.length; k++) {
						// 按类别匹配查询结果
						if (type[k].equals(data.get(j).get("fEntityType"))) {
							lv.put(type2[k], data.get(j).get("num"));
							tol += Integer.parseInt(data.get(j).get("num").toString());
						}
					}
				}
			}
			lv.put("num", tol);
			result.add(lv);
		}
		return result;
	}

	/**
	 * 将字符串分割，组装成一个数组 
	 */
	public static String[] stringToList(String s) {
		// List<String> list=new ArrayList<String>();
		String[] ss = s.split(",");
		if (ss.length == 0) {
			ss[0] = s;
		}
		return ss;
	}

	/**
	 * 匹配系统用户设置 
	 */
	public static List<Map<String, Object>> toResult(List<Map<String, Object>> list) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			Map<String, Object> temp = new HashMap<String, Object>();
			if (1 == Integer.parseInt((map.get("fTimeType")) + "")) {
				temp.put("fTimeType", "天");
			}
			if (2 == Integer.parseInt((map.get("fTimeType")) + "")) {
				temp.put("fTimeType", "周");
			}
			if (3 == Integer.parseInt((map.get("fTimeType")) + "")) {
				temp.put("fTimeType", "月");
			}
			temp.put("fTimeNum", map.get("fTimeNum"));
			temp.put("fUserName", map.get("fUsername"));
			temp.put("fConfigType", map.get("fConfigType"));
			result.add(temp);
		}
		return result;
	}

	public static Map<String, Object> toTempResult(List<Map<String, Object>> list) {
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println(list);
		for (Map<String, Object> map : list) {
			Map<String, Object> tempresult = new HashMap<String, Object>();
			if (1 == Integer.parseInt((map.get("fTimeType")) + "")) {
				tempresult.put("fTimeType", "1");
				tempresult.put("fTimeNum", map.get("fTimeNum"));
				result.put(map.get("fConfigType") + "", tempresult);
			}
			if (2 == Integer.parseInt((map.get("fTimeType")) + "")) {
				tempresult.put("fTimeType", "2");
				int temp = Integer.parseInt((map.get("fTimeNum") + "")) * 7;
				tempresult.put("fTimeNum", temp);
				result.put(map.get("fConfigType") + "", tempresult);
			}
			if (3 == Integer.parseInt((map.get("fTimeType")) + "")) {
				tempresult.put("fTimeType", "3");
				tempresult.put("fTimeNum", map.get("fTimeNum"));
				result.put(map.get("fConfigType") + "", tempresult);
			}
		}
		return result;
	}

	/**
	 * 首页处理许可变更、许可注销、许可新增 当没有数据时处理
	 */
	public static List<Map<String, Object>> licHandle(List<Map<String, Object>> list) {
		Map<String, Object> temp = new HashMap<String, Object>();
		List<String> tempList = new ArrayList<String>();
		for (Map<String, Object> map : list) {
			for (String key : map.keySet()) {
				tempList.add((String) map.get(key));
			}
		}

		if (!tempList.contains("餐饮服务")) {
			Map<String, Object> tempResult = new HashMap<String, Object>();
			tempResult.put("fLicType", "餐饮服务");
			tempResult.put("num", 0);
			list.add(tempResult);
		}
		if (!tempList.contains("器械生产")) {
			Map<String, Object> tempResult = new HashMap<String, Object>();
			tempResult.put("fLicType", "器械生产");
			tempResult.put("num", 0);
			list.add(tempResult);
		}
		if (!tempList.contains("药品批发")) {
			Map<String, Object> tempResult = new HashMap<String, Object>();
			tempResult.put("fLicType", "药品批发");
			tempResult.put("num", 0);
			list.add(tempResult);
		}
		if (!tempList.contains("食品生产")) {
			Map<String, Object> tempResult = new HashMap<String, Object>();
			tempResult.put("fLicType", "食品生产");
			tempResult.put("num", 0);
			list.add(tempResult);
		}
		if (!tempList.contains("药品生产")) {
			Map<String, Object> tempResult = new HashMap<String, Object>();
			tempResult.put("fLicType", "药品生产");
			tempResult.put("num", 0);
			list.add(tempResult);
		}
		if (!tempList.contains("食品流通")) {
			Map<String, Object> tempResult = new HashMap<String, Object>();
			tempResult.put("fLicType", "食品流通");
			tempResult.put("num", 0);
			list.add(tempResult);
		}
		if (!tempList.contains("药品零售")) {
			Map<String, Object> tempResult = new HashMap<String, Object>();
			tempResult.put("fLicType", "药品零售");
			tempResult.put("num", 0);
			list.add(tempResult);
		}
		if (!tempList.contains("器械经营")) {
			Map<String, Object> tempResult = new HashMap<String, Object>();
			tempResult.put("fLicType", "器械经营");
			tempResult.put("num", 0);
			list.add(tempResult);
		}

		return list;
	}

	/**
	 * List<Map<>> 转list<list<>>
	 * @author husl
	 */
	public static List<List<Object>> toList(List<Map<String, Object>> listMap) {
		List<List<Object>> result = new ArrayList<>();
		for (Map<String, Object> map : listMap) {
			List<Object> listTemp = new ArrayList<>();
		    for(String key:map.keySet()){
		    	listTemp.add(map.get(key));
		    }
		    result.add(listTemp);
		}
		return result;

	}
	
	public static Map<String, Object> objectToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<?, ?> data = new org.apache.commons.beanutils.BeanMap(obj);

		Map<String, Object> result = new HashMap<String, Object>();
		result.putAll((Map<String, Object>) data);
		result.remove("class");
		return result;
	}

	public static Date StrToDate(String str, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}