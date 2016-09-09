


package com.anjewe.anjewewebwinkel.DAOs;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.Factuur;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Wendy
 */

public class FactuurDao  extends GenericDaoImpl <Factuur, Long>{

private static final Logger log = LoggerFactory.getLogger(FactuurDao.class);


    @Autowired
    private SessionFactory sessionFactory;
    
    public FactuurDao(){
        super();
    }

}
