/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.DAOs;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.Adres;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anne
 */
@Component
public class AdresDao extends GenericDaoImpl <Adres, Long> {
    
    public AdresDao() {
        super();
    }
    
    
}
