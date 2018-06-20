package com.pure.service.model;

import java.math.BigDecimal;

public class SpatialInfo {
private BigDecimal x;

    
    private BigDecimal y;
    
    private String spatialReference;
    
    public BigDecimal getx() {
        return x;
    }

    
    public void setx(BigDecimal x) {
        this.x = x;
    }

    
    public BigDecimal gety() {
        return y;
    }

    
    public void sety(BigDecimal y) {
        this.y = y;
    }
    
    public String getSpatialReference() {
        return spatialReference;
    }

    
    public void setSpatialReference(String spatialReference) {
        this.spatialReference = spatialReference;
    }

}
