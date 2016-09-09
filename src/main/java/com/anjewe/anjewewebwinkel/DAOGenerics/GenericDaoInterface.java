/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.DAOGenerics;


import java.io.Serializable;
import java.util.List;


/**
 *
 * @author Wendy
 * @param <T>
 * @param <PK>
 */

// PK primary key

public interface GenericDaoInterface <T, PK extends Serializable>{
   
    // alle dao methoden? 
    long insert(T t);
    
    T readById(PK id);
    <T>List<T> read(PK id, Class<T> type);
    <T>List<T> readAll(Class<T> type);
    
    void update(T t);
    
    boolean delete(T t);
    boolean deleteById(PK id);
    int deleteAll(Class<T> type);
    
    
}
