


package com.anjewe.anjewewebwinkel.DAOs;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.Bestelling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class BestellingDao  extends GenericDaoImpl <Bestelling, Long> {

private static final Logger log = LoggerFactory.getLogger(BestellingDao.class);

    public BestellingDao(){
        super();
    }
}
