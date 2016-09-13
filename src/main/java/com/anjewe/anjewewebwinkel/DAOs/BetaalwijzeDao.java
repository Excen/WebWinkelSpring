


package com.anjewe.anjewewebwinkel.DAOs;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.Betaalwijze;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wendy
 */
@Transactional
@Repository
public class BetaalwijzeDao  extends GenericDaoImpl <Betaalwijze, Long>{

private static final Logger log = LoggerFactory.getLogger(Betaalwijze.class);
    
    public BetaalwijzeDao(){
        super();
    }

}
