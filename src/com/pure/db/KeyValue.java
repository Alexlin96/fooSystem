package com.pure.db;

public class KeyValue {
	
	private String key;
	
	private String value;
	
	
	public String getkey() {
        return key;
    }

    
    public void setkey(String key) {
    	this.key = key == null ? null : key.trim();
    }
    
    
    public String getvalue() {
        return value;
    }

    
    public void setvalue(String value) {
    	this.value = value == null ? value : value.trim();
    }
}