package com.pure.common;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * json帮助类
 * @author alex
 *
 */
public class MapJsonUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String,String>();
		map.put("av", "111");
		map.put("av1", "111");
		map.put("av2", "111");
		map.put("av3", "111");
		System.out.println(map2json(map,null));

	}
	
	public static String object2json(Object obj,Map<String,String> beanfeildMap) {
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
			json.append(array2json((Object[]) obj,beanfeildMap));
		} else if (obj instanceof List) {
			json.append(list2json((List<?>) obj,beanfeildMap));
		} else if (obj instanceof Map) {
			json.append(map2json((Map<?, ?>) obj,beanfeildMap));
		} else if (obj instanceof Set) {
			json.append(set2json((Set<?>) obj,beanfeildMap));
		} else {
			json.append(bean2json(obj,beanfeildMap));
		}
		return json.toString();
	}
	
	public static String bean2json(Object bean,Map<String,String> beanfeildMap) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		//
		//boolean hasFieldMap = false;
		Map<String,String> fieldMap = null;
		String fieldStr = "";
		//
		try {
			//类字段映射map中不包含该类的feilds，不进行中文字段映射
			if(beanfeildMap!=null && beanfeildMap.containsKey(bean.getClass().getName())){
				fieldStr = beanfeildMap.get(bean.getClass().getName());
				Method[] mothods = bean.getClass().getMethods();
				for(int k=0;k<mothods.length;k++){
					if(mothods[k].getName().equals("readFeildMap")){
						//hasFieldMap = true;
						try{
							fieldMap = (Map<String,String>)mothods[k].invoke(bean,fieldStr);
						}catch(Exception e){
							
						}
						
						break;
					}
				}
			}
			
			//
			props = Introspector.getBeanInfo(bean.getClass(), Object.class)
					.getPropertyDescriptors();
		} catch (IntrospectionException e) {
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					//String filedname = object2json(props[i].getName());//standard
					String filedname = props[i].getName();
					String name;
					if(fieldMap!=null && fieldMap.containsKey(filedname)){
						String zhname = fieldMap.get(filedname);
						name = object2json(zhname,beanfeildMap);
					}else{
						name = object2json(filedname,beanfeildMap);
					}
					String value = object2json(props[i].getReadMethod().invoke(
							bean),beanfeildMap);
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
					//
					
				} catch (Exception e) {
				}
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}
	
	
	
	


	public static String list2json(List<?> list,Map<String,String> beanfeildMap) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(object2json(obj,beanfeildMap));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String array2json(Object[] array,Map<String,String> beanfeildMap) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (array != null && array.length > 0) {
			for (Object obj : array) {
				json.append(object2json(obj,beanfeildMap));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String map2json(Map<?, ?> map,Map<String,String> beanfeildMap) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				json.append(object2json(key,beanfeildMap));
				json.append(":");
				json.append(object2json(map.get(key),beanfeildMap));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	public static String set2json(Set<?> set,Map<String,String> beanfeildMap) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				json.append(object2json(obj,beanfeildMap));
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
}
