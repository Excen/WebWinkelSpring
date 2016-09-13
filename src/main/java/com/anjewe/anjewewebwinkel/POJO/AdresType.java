/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.anjewe.anjewewebwinkel.POJO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Wendy
 */

@Table(name= "ADRESTYPE")
public class AdresType implements Serializable{
    
    @Id
    @GeneratedValue (strategy = IDENTITY)
    @Column (unique = true, nullable = false, name = "ADRESTYPE_ID")
    private Long Id;
    private String type;
    private static String[] types = {"Bezorgadres", "Factuuradres", "Kadoadres"};
    
    
    public Long getId(){
        return this.Id;
    }
    
    public void setId(Long id){
        this.Id = id;    }
    
    
    public String getType(){
        return this.type;
    }
    
    protected void setType(String type){
        this.type = type;
    }
    
    public void setType(int Id){
        this.type = types[Id];
    }
    
    public String[] getAllTypes(){
        return types;
    }
    
    private void setAllTypes(String[] allTypes){
        types = allTypes;
    }
}

