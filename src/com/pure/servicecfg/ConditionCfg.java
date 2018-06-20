package com.pure.servicecfg;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConditionCfg {
private static Map<String,Map<String,Map<String,String>>> conditionCfgs;
	
	static{
		try{
			conditionCfgs = new HashMap<String,Map<String,Map<String,String>>>();
			
			SAXReader saxReader = new SAXReader(); 
			
			java.net.URL xmlURL = ConditionCfg.class.getResource("conditionCfg.xml");
			Document doc = saxReader.read(xmlURL);  
			List conditionEleList = doc.selectNodes("//configuration/condition");  
			
			for(Iterator it = conditionEleList.iterator();it.hasNext();){  
	            Element conditioncfgEle = (Element)it.next();
	            String sname = conditioncfgEle.attributeValue("name");
	            Map<String,Map<String,String>> map= new  HashMap<String,Map<String,String>>();
	            conditionCfgs.put(sname, map);
	            List filedEleList = (List)conditioncfgEle.elements("filed");
	            for(Iterator filedIt = filedEleList.iterator();filedIt.hasNext();){
	                Element filedEle = (Element)filedIt.next();
	                String name = filedEle.attributeValue("name");
	                String columnName = filedEle.attributeValue("columnName");
	                String dataType = filedEle.attributeValue("dataType");
	                Map<String,String> condition=new HashMap<String,String>();
	                condition.put("columnName", columnName);
	                condition.put("dataType", dataType);
	                map.put(name, condition);
	            }
	        }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static Map<String,Map<String,String>> getCondtionCfg(String conditionName){
		return conditionCfgs.get(conditionName);
	}
}
