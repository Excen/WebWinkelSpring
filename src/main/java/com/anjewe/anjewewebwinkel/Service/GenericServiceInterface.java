/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.Service;

import com.anjewe.anjewewebwinkel.Controller.*;
import java.util.List;

/**
 *
 * @author Wendy
 * @param <T>
 */
public interface GenericServiceInterface <T > {
    
    //invoer
    public T voegNieuweBeanToe(Long Id);
    public Long voegNieuweBeanToe(T t);
    
    //zoeken
    public T zoekNaarBean(Long Id);
    public Long zoekNaarBean(T t);
    public List<T> zoekAlleBeans();
    
    //update
    public T wijzigBeanGegevens(Long id);
    public T wijzigBeanGegevens(T t);
    
    //verwijder
    public boolean verwijderBeanGegevens(Long Id);
    public int verwijderAlleBeans();
    
    
    
    
    
    
    
    
    
    
    
}
