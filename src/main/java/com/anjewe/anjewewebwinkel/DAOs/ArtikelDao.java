


package com.anjewe.anjewewebwinkel.DAOs;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.Artikel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




/**
 * @author Wendy
 */
@Repository
@Transactional
public class ArtikelDao extends GenericDaoImpl <Artikel, Long> {

    
private static final Logger log = LoggerFactory.getLogger(ArtikelDao.class);
 
    //Artikel artikel;
    
    public ArtikelDao(){
        super();
    }

    


   
    
    
    

}
