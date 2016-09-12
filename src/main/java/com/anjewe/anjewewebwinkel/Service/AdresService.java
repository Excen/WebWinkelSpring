/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.Service;


import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.DAOs.AdresDao;
import com.anjewe.anjewewebwinkel.POJO.Adres;
import java.util.ArrayList;
import com.anjewe.anjewewebwinkel.POJO.Klant;
import com.anjewe.anjewewebwinkel.POJO.KlantAdres;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anne
 */

@Component
public class AdresService extends AdresDao implements GenericServiceInterface <Adres, Long>{
     
    AdresService(){        
    }
    
    private static final Logger logger = (Logger) LoggerFactory.getLogger("com.anjewe.anjewewebwinkel");
    private static final Logger errorLogger = (Logger) LoggerFactory.getLogger("com.anjewe.anjewewebwinkel.err");
    private static final Logger testLogger = (Logger) LoggerFactory.getLogger("com.anjewe.anjewewebwinkel.test");
   
   Adres adres;    
   Klant klant;
   KlantAdres KA;
   KlantService klantController;
   ArrayList<Adres> adressenLijst = new ArrayList();
       
   GenericDaoImpl <Klant, Long> klantDao ;
   GenericDaoImpl <Adres, Long> adresDao;
   
   int userInput;
   
    
    public long voegNieuwAdresToe() {
        
        klant = (Klant)klantDao.readById(klantId);
        adres = createAdres();
        
        //voeg toe in adrestabel
        
        
        System.out.println(adresId);
        
        // voeg toe in koppel - 
        // vraag daarvoor klant op en naam medewerker
         
         klant = (Klant)klantDao.readById(klantId);
         String medewerker = klantView.voerNaamMwIn();
            KA.setKlant(klant);
            KA.setAdres(adres);
            KA.setCreatedDate(new Date());
            KA.setCreatedBy(medewerker);
        
            klant.getKlantAdressen().add(KA);
        
         
        System.out.println("U heeft het volgende adres toegevoegd.");
        adresView.printAdresOverzicht(adres);
        
       
        return adresId;            
    }
    
    
    public Adres createAdres() {  
        
        String straatnaam = adresView.voerStraatnaamIn();
        int huisnummer = adresView.voerHuisnummerIn();
        String toevoeging = adresView.voerToevoegingIn();
        String postcode = adresView.voerPostcodeIn();
        String woonplaats = adresView.voerWoonplaatsIn();
              
        adres.setStraatnaam(straatnaam);
        adres.setHuisnummer(huisnummer);
        adres.setToevoeging(toevoeging);
        adres.setPostcode(postcode);
        adres.setWoonplaats(woonplaats);        
        
        return adres;
    }
    
    
    
    public Adres invoerNieuweAdresGegevens(Adres adres) {
        
       int juist = 0;
        
        String straatnaam = adres.getStraatnaam();
        juist = adresView.checkInputString(straatnaam);
        if (juist == 2) {
            straatnaam = adresView.voerStraatnaamIn();
        } 
        
        int huisnummer = adres.getHuisnummer();
        String huisnummerString = huisnummer + "";
        juist = adresView.checkInputString(huisnummerString);
        if (juist == 2) {
            huisnummer = adresView.voerHuisnummerIn();
        } 
        
        String toevoeging = adres.getToevoeging();
        juist = adresView.checkInputString(toevoeging);
        if (juist == 2) {
            toevoeging = adresView.voerToevoegingIn();
        } 
        
        String postcode = adres.getPostcode();
        juist = adresView.checkInputString(postcode);
        if (juist == 2) {
            postcode = adresView.voerPostcodeIn();
        } 
        
        String woonplaats = adres.getWoonplaats();
        juist = adresView.checkInputString(woonplaats);
        if (juist == 2) {
            woonplaats = adresView.voerWoonplaatsIn();
        } 
        
        adres.setStraatnaam(straatnaam);
        adres.setHuisnummer(huisnummer);
        adres.setToevoeging(toevoeging);
        adres.setPostcode(postcode);
        adres.setWoonplaats(woonplaats);    
        
        return adres;
    }
    
    
    public void zoekAdresKlantGegevens(){
        
        long klantId; 
        userInput = adresView.menuAdresKlantZoeken();
       
        switch(userInput){
           case 1: // adres(sen) bij klantId
               klantId = klantView.voerKlantIdIn();
               ArrayList<Adres>adressenLijst = klantAdresDao.findAdresByKlantId(klantId);
               adresView.printAdressenLijst(adressenLijst);
               break;
           case 2: // alle adressen bij klanten opzoeken
               ArrayList<KlantAdres> klantAdresLijst = klantAdresDao.findAll();
               adresView.printKlantAdresLijst(klantAdresLijst);
               int keuze = adresView.alleKoppellingenUitgeprint();
               switch(keuze){
                   case 1: // ja
                        for (int i = 0 ; i < klantAdresLijst.size(); i++){
                            long adresId = klantAdresLijst.get(i).getAdresId();
                            klantId = klantAdresLijst.get(i).getKlantId();
                            adres = adresDao.findByAdresID(adresId);
                            Klant klant = klantDao.findByKlantId(klantId);
                            System.out.println("Onderstaand adres:");
                            adresView.printAdresOverzicht(adres);                           
                            System.out.println("hoort bij: ");
                            klantView.printKlantGegevens(klant);
                            
                        }
                            break;
                    case 2: // nee   
                        break;
               
               }
               break;
           case 3:
                break;
            default:
                break;
           
       }
      
    }

    @Override
    public Adres voegNieuweBeanToe(Long Id) {         
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long voegNieuweBeanToe(Adres t) {
        Long adresId = (Long)adresDao.insert(adres);
        return adresId;
    }

    @Override
    public Adres zoekNaarBean(Long Id) {
        adres = (Adres)adresDao.readById(Id);
        return adres; 
    }

    @Override
    public Long zoekNaarBean(Adres t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Adres> zoekAlleBeans() {  
        ArrayList<Adres> adressenLijst = new ArrayList<>();
            adressenLijst = (ArrayList<Adres>)adresDao.readAll(Adres.class);
            return adressenLijst; 
    }

    @Override
    public Adres wijzigBeanGegevens(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Adres wijzigBeanGegevens(Adres t) {
        adresDao.update(t);        
        return t;
    }

    @Override
    public boolean verwijderBeanGegevens(Long Id) {
        boolean isDeletedInAdres = adresDao.deleteById(Id);
        return isDeletedInAdres;
    }

    @Override
    public int verwijderAlleBeans() {
        int rowsAffected = adresDao.deleteAll(Adres.class);
        return rowsAffected; 
    }
   
}
