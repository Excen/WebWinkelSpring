
package com.anjewe.anjewewebwinkel.Service;


import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.DAOs.ArtikelDao;
import com.anjewe.anjewewebwinkel.POJO.Artikel;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ArtikelService implements GenericServiceInterface <Artikel, Long> {  
    
    private static final Logger logger = (Logger) LoggerFactory.getLogger("com.anjewe.anjewewebwinkel");
    private static final Logger errorLogger = (Logger) LoggerFactory.getLogger("com.anjewe.anjewewebwinkel.err");
    private static final Logger testLogger = (Logger) LoggerFactory.getLogger("com.anjewe.anjewewebwinkel.test");
   
    
   
    @Autowired
    Artikel artikel;   
    @Autowired
    Artikel gewijzigdArtikel;   
    @Autowired
    GenericDaoImpl<Artikel, Long> artikelDao = new ArtikelDao();
    
    
      
    @Override
    public Artikel voegNieuweBeanToe(Long Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long voegNieuweBeanToe(Artikel artikel) {
         long id = artikelDao.insert(artikel);  
         return id; 
    }
    
    @Override
    public Artikel zoekNaarBean(Long Id) {
        artikel = (Artikel)artikelDao.readById(Id);
        return artikel;
    }

    @Override
    public Long zoekNaarBean(Artikel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Artikel> zoekAlleBeans() {
        ArrayList <Artikel> artikelenLijst = 
        (ArrayList <Artikel>) artikelDao.readAll(Artikel.class);  
        return artikelenLijst;
    }

    @Override
    public Artikel wijzigBeanGegevens(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Artikel wijzigBeanGegevens(Artikel artikel) {
        
        gewijzigdArtikel= (Artikel)artikelDao.readById(artikel.getId());
        if (gewijzigdArtikel!= null){
            gewijzigdArtikel.setArtikelNaam(artikel.getArtikelNaam());
            gewijzigdArtikel.setArtikelPrijs(artikel.getArtikelPrijs());
        }
        
         artikelDao.update(gewijzigdArtikel); 
         return gewijzigdArtikel; 
    }

    @Override
    public boolean verwijderBeanGegevens(Long Id) {
        boolean verwijderd = artikelDao.deleteById(Id); 
        return verwijderd;         
    }

    @Override
    public int verwijderAlleBeans() {
        int verwijderd = artikelDao.deleteAll(Artikel.class); 
        return verwijderd;
    }
    
} // eind artikelcontroller
