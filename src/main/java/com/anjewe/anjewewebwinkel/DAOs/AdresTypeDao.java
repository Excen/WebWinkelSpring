


package com.anjewe.anjewewebwinkel.DAOs;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.AdresType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class AdresTypeDao extends GenericDaoImpl <AdresType, Long> {

private static final Logger log = LoggerFactory.getLogger(AdresTypeDao.class);

    public AdresTypeDao(){
        super();
    }
}
