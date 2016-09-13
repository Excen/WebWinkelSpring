


package com.anjewe.anjewewebwinkel.DAOs;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.Bestelling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author Wendy
 */



@Transactional
@Repository
public class BestellingDao  extends GenericDaoImpl <Bestelling, Long> {

private static final Logger log = LoggerFactory.getLogger(BestellingDao.class);

    public BestellingDao(){
        super();
    }
}
