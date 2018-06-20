package com.pure.common;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * json帮助类
 * 
 * @author alex
 *
 */
public class JsonUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("code", "1");
		// map.put("message", "服务器错误");
		// System.out.println(map2json(map));

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", "success");
		// Object[] o = new Object[] { contribution, null, null, null, null,
		// null,
		// null, null };
		System.out.println(map2json(map));

	}

	public static String object2json(Object obj) {
		StringBuilder json = new StringBuilder();
		if (obj == null) {
			json.append("\"\"");
		} else if (obj instanceof String || obj instanceof Integer
				|| obj instanceof Float || obj instanceof Boolean
				|| obj instanceof Short || obj instanceof Double
				|| obj instanceof Long || obj instanceof BigDecimal
				|| obj instanceof BigInteger || obj instanceof Byte) {
			json.append("\"").append(string2json(obj.toString())).append("\"");
		} else if (obj instanceof Object[]) {
			json.append(array2json((Object[]) obj));
		} else if (obj instanceof List) {
			json.append(list2json((List<?>) obj));
		} else if (obj instanceof Map) {
			json.append(map2json((Map<?, ?>) obj));
		} else if (obj instanceof Set) {
			json.append(set2json((Set<?>) obj));
		} else {
			json.append(bean2json(obj));
		}
		return json.toString();
	}

	public static String bean2json(Object bean) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class)
					.getPropertyDescriptors();
		} catch (IntrospectionException e) {
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = object2json(props[i].getName());
					String value = object2json(props[i].getReadMethod().invoke(
							bean));
					if (null != value && !"\"\"".equals(value)) {
						json.append(name);
						json.append(":");
						json.append(value);
						json.append(",");
					}
				} catch (Exception e) {
				}
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	public static String list2json(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String array2json(Object[] array) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (array != null && array.length > 0) {
			for (Object obj : array) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String map2json(Map<?, ?> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				json.append(object2json(key));
				json.append(":");
				json.append(object2json(map.get(key)));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	public static String set2json(Set<?> set) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String string2json(String s) {
		if (s == null)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}

	/*
	 * public static String date2json(Date date) { if (date == null) { return
	 * ""; } else { SimpleDateFormat formatter = new SimpleDateFormat(
	 * "yyyy-MM-dd HH:mm:ss"); StringBuilder sb = new
	 * StringBuilder("\"").append( formatter.format(date)).append("\""); return
	 * sb.toString(); } }
	 */
 
	   /**
	    *  
	    * @param list
	    * @return
	    */
	public static List<List<Map<String, Object>>> list2Json(List<Map<String, Object>> list) {
		List<List<Map<String, Object>>> result = new ArrayList<List<Map<String,Object>>>();
		for (Map<String, Object> map : list) {
			List<Map<String, Object>> result0 = new ArrayList<Map<String, Object>>();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				Map<String, Object> temp = new HashMap<String, Object>();
				String key = entry.getKey().toString();
				String value = entry.getValue().toString();
				temp.put("name", key);
				temp.put("value", value);
				result0.add(temp);
			}
			result.add(result0);
		}
		return result;
	}
	/**
	 * 前端循环显示
	 * @param list
	 * @return
	 */
	public static List<Map<String,Object>> map2List(Map<String,Object>map) {
		List<Map<String,Object>>list=new ArrayList<Map<String,Object>>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			Map<String, Object> temp = new HashMap<String, Object>();
			String key = entry.getKey().toString();
			String value = entry.getValue().toString();
			temp.put("name", key);
			temp.put("value", value);
			list.add(temp);
		}
		return list;
            
	}
}
