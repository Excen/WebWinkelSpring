


package com.anjewe.anjewewebwinkel.DAOs;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.Betaalwijze;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class BetaalwijzeDao  extends GenericDaoImpl <Betaalwijze, Long>{

private static final Logger log = LoggerFactory.getLogger(Betaalwijze.class);
    
    public BetaalwijzeDao(){
        super();
    }

}
