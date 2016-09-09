/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.DAOs;


import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.Klant;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anne
 */
@Transactional
public class KlantDao extends GenericDaoImpl <Klant, Long> {
    
    private static final Logger log = LoggerFactory.getLogger(ArtikelDao.class);

    @Override
    public int deleteAll(Class<Klant> type) {
        return super.deleteAll(type); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(Long id) {
        return super.deleteById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Klant t) {
        return super.delete(t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Klant t) {
        super.update(t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> List<T> readAll(Class<T> type) {
        return super.readAll(type); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> List<T> read(Long id, Class<T> type) {
        return super.read(id, type); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Klant readById(Long id) {
        return super.readById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public long insert(Klant t) {
        return super.insert(t); //To change body of generated methods, choose Tools | Templates.
    }
    
       
    
    
    
     
    
}
