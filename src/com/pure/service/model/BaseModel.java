package com.pure.service.model;

import java.util.*;

public class BaseModel {
	
	public Map<String,String> fieldMap;
	
	public Map<String,String> readFeildMap(String feilds){
		
		Map<String,String> fMap = new HashMap<String,String>();
		if(feilds==null||feilds.equals("")){
			return fieldMap;
		}
		
		try{
			String[] feildsArr = feilds.split(",");
			for(int i=0;i<feildsArr.length;i++){
				String fld = feildsArr[i];
				if(fieldMap.containsKey(fld)){
					fMap.put(fld, fieldMap.get(fld));
				}
			}
		}catch(Exception e){
			return fieldMap;
		}
		
		
		if(fMap.isEmpty()){
			return fieldMap;
		}else{
			return fMap;
		}
    	
    }
	
	public void createFeildMap(){
		
	}
	
	public BaseModel(){
		this.createFeildMap();
	}
	
	
}
