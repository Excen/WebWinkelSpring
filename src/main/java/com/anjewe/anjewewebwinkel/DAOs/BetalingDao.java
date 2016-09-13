


package com.anjewe.anjewewebwinkel.DAOs;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.Betaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
/**
 * @author Wendy
 */
@Component
public class BetalingDao  extends GenericDaoImpl <Betaling, Long> {

private static final Logger log = LoggerFactory.getLogger(BetalingDao.class);

    public BetalingDao(){
        super();
    }

}
