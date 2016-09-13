


package com.anjewe.anjewewebwinkel.Service;


import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.DAOs.FactuurDao;
import com.anjewe.anjewewebwinkel.POJO.Factuur;
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
public class FactuurService implements GenericServiceInterface <Factuur, Long>{

private static final Logger log = LoggerFactory.getLogger(FactuurService.class);

   
    @Autowired
    Factuur factuur; 
    @Autowired
    Factuur gewijzigdeFactuur;   
    @Autowired
    GenericDaoImpl<Factuur, Long> factuurDao = new FactuurDao();  
    
   
       
    
    @Override
    public Factuur voegNieuweBeanToe(Long Id) {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long voegNieuweBeanToe(Factuur factuur) {
       Long factuurId = factuurDao.insert(factuur); 
       return factuurId; 
    }

    @Override
    public Factuur zoekNaarBean(Long Id) {
        factuur = (Factuur) factuurDao.readById(Id);
         return factuur;
    }

    @Override
    public Long zoekNaarBean(Factuur t) {
        Long factuurId = (Long)factuurDao.insert(factuur); 
        return factuurId;
    }

    @Override
    public List<Factuur> zoekAlleBeans() {
        ArrayList <Factuur> facturenLijst = 
                            (ArrayList <Factuur>) factuurDao.readAll(Factuur.class);
        return facturenLijst; 
    }

    @Override
    public Factuur wijzigBeanGegevens(Long Id) {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    @Override // wat hier wel niet willen en kunnen wijzigen. ook ivm onderlinge relaties
    public Factuur wijzigBeanGegevens(Factuur factuur) {
       gewijzigdeFactuur = (Factuur)factuurDao.readById(factuur.getId());
        if (gewijzigdeFactuur!= null){
            gewijzigdeFactuur.setKlant(factuur.getKlant());
            gewijzigdeFactuur.setFactuurnummer(factuur.getFactuurnummer());
            gewijzigdeFactuur.setBestelling(factuur.getBestelling());
            gewijzigdeFactuur.setBetalingset(factuur.getBetalingset());
        }
        factuurDao.update(gewijzigdeFactuur);
        return gewijzigdeFactuur; 
    }

    @Override
    public boolean verwijderBeanGegevens(Long Id) {
        boolean verwijderd = factuurDao.deleteById(Id);
       return verwijderd;}

    @Override
    public int verwijderAlleBeans() {
        int verwijderd = factuurDao.deleteAll(Factuur.class);   
        return verwijderd;
    }

}
