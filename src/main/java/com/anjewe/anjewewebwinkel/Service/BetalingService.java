


package com.anjewe.anjewewebwinkel.Service;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.DAOs.BetalingDao;
import com.anjewe.anjewewebwinkel.POJO.Betaling;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Wendy
 */
@Component
public class BetalingService extends BetalingDao implements GenericServiceInterface<Betaling, Long>{

private static final Logger log = LoggerFactory.getLogger(BetalingService.class);

    @Autowired 
    GenericDaoImpl <Betaling, Long> betalingDao;  
    @Autowired
    Betaling betaling;  
    @Autowired
    Betaling gewijzigdeBetaling; 
    boolean gewijzigd;
    Long betalingId;    
    

    @Override
    public Betaling voegNieuweBeanToe(Long Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long voegNieuweBeanToe(Betaling betaling) {
         betalingId = (Long)betalingDao.insert(betaling); 
         return betalingId;
    }

    @Override
    public Betaling zoekNaarBean(Long Id) {
        betaling = (Betaling) betalingDao.readById(betalingId);
        return betaling;
    }

    @Override
    public Long zoekNaarBean(Betaling betaling) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Betaling> zoekAlleBeans() {
        ArrayList <Betaling> betalingenLijst = 
                            (ArrayList <Betaling>) betalingDao.readAll(Betaling.class);
        return betalingenLijst;
    }

    @Override
    public Betaling wijzigBeanGegevens(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Betaling wijzigBeanGegevens(Betaling betaling) {
        
        gewijzigdeBetaling = betalingDao.readById(betaling.getId());
        if (gewijzigdeBetaling!= null){
            gewijzigdeBetaling.setBetalingsGegevens(betaling.getBetalingsGegevens());
            gewijzigdeBetaling.setFactuur(betaling.getFactuur());
            gewijzigdeBetaling.setKlant(betaling.getKlant());
            gewijzigdeBetaling.setBetaalwijze(betaling.getBetaalwijze());
        }
        betalingDao.update(gewijzigdeBetaling);
        return gewijzigdeBetaling;
    
    }

    @Override
    public boolean verwijderBeanGegevens(Long Id) {
         boolean verwijderd = betalingDao.deleteById(betalingId);
         return verwijderd; 
    }

    @Override
    public int verwijderAlleBeans() {
        int verwijderd = betalingDao.deleteAll(Betaling.class); 
        return verwijderd; 
    }
    
   

}
