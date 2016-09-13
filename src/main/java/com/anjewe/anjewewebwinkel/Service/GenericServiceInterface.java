/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.Service;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Wendy
 * @param <T>
 * @param <PK>
 * @param <Long>
 */
@Service
@Transactional
public interface GenericServiceInterface <T, PK extends Serializable> {
    
     
    //invoer - opslaan
    
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
