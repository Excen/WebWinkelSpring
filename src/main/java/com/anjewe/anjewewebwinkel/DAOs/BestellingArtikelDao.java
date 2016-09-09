


package com.anjewe.anjewewebwinkel.DAOs;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.BestellingArtikel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author Wendy
 */
//@Repository
public class BestellingArtikelDao  extends GenericDaoImpl <BestellingArtikelDao, Long>{

private static final Logger log = LoggerFactory.getLogger(BestellingArtikelDao.class);

    public BestellingArtikelDao(){
        super();
    }

}
