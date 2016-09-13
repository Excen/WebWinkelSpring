


package com.anjewe.anjewewebwinkel.DAOs;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.Factuur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

/**
 * @author Wendy
 */
@Component
public class FactuurDao  extends GenericDaoImpl <Factuur, Long>{

private static final Logger log = LoggerFactory.getLogger(FactuurDao.class);


    
    public FactuurDao(){
        super();
    }

}
