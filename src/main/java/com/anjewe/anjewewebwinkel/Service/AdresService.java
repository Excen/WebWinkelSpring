/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.Service;


import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.Adres;
import java.util.ArrayList;
import com.anjewe.anjewewebwinkel.POJO.Klant;
import com.anjewe.anjewewebwinkel.POJO.KlantAdres;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anne
 */

@Component
public class AdresService {
     
    AdresService(){        
    }
    
    private static final Logger logger = (Logger) LoggerFactory.getLogger("com.webshop");
    private static final Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
    private static final Logger testLogger = (Logger) LoggerFactory.getLogger("com.webshop.test");
   
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
        Long adresId = (Long)adresDao.insert(adres);
        
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
    
    
    public void zoekAdresGegevens()  {
        // read()
        
        
        userInput = adresView.menuAdresZoeken();
        switch (userInput) {
            case 1:        
                // één adres zoeken
                userInput = adresView.hoeWiltUZoeken();
                switch (userInput) {
                    case 1: // zoek op adresId
                       
                        Long adresId = adresView.voerAdresIdIn();
                        adres = (Adres)adresDao.readById(adresId);
                       
                        adresView.printAdresOverzicht(adres);
                        
                        break;
//                    case 2: // zoek adressen op klantId
//                        int klantId = klantView.voerKlantIdIn();
//                        ArrayList<Adres>adressenLijst = klantAdresDao.findAdresByKlantId(klantId);
//                        adresView.printAdressenLijst(adressenLijst);
//                        break;
//                    case 3: // zoek op straatnaam
//                        String straatnaam = adresView.voerStraatnaamIn();
//                        adressenLijst = adresDao.findByStraatNaam(straatnaam);
//                        adresView.printAdressenLijst(adressenLijst);
//                        break;
//                    case 4: // zoek op postcode huisnummer
//                        String postcode = adresView.voerPostcodeIn();
//                        int huisnummer = adresView.voerHuisnummerIn();
//                        adressenLijst = adresDao.findByPostcodeHuisNummer(postcode, huisnummer);
//                        adresView.printAdressenLijst(adressenLijst);
//                        break;
//                    case 5: // zoek op woonplaats
//                        String woonplaats = adresView.voerWoonplaatsIn();
//                        adressenLijst = adresDao.findByWoonplaats(woonplaats);
//                        adresView.printAdressenLijst(adressenLijst);
//                        break;
                    case 6:
                        break; // doorsturen einde switch; terug naar adres menu
                    default: 
                        break;
                }
            break;
            case 2:
                // alle adressen zoeken
                
                ArrayList<Adres> adressenLijst = new ArrayList<>();
                    adressenLijst = (ArrayList<Adres>)adresDao.readAll(Adres.class);
                
                if (adressenLijst != null) {
                    System.out.println("Alle adressen in het adressenbestand");
                    adresView.printAdressenLijst(adressenLijst);
                }
                break; 
            case 3:
                break;
            default:
                break;
        }
       
    }
    
    public void wijzigAdresGegevens() {      
                
        userInput = adresView.hoeWiltUZoeken();
        switch (userInput) {
            case 1: 
                updateOpAdresId();
                break;
//            case 2:
//                updateOpStraatnaam();
//                break;
//            case 3:
//                updateOpPostcodeHuisnummer();
//                break;
//            case 4:
//                updateOpWoonplaats();
//                break;
            case 5:
                break; // doorsturen einde switch; terug naar adres menu
            default:
                System.out.println("Die optie is niet beschikbaar, je keert terug naar het bestelling menu.");
                break;
        }
        
    }
    
    
    public void updateOpAdresId() {
                
        Adres gewijzigdAdres;
        long adresId = adresView.voerAdresIdIn();        
        
        adres = (Adres)adresDao.readById(adresId);
        gewijzigdAdres = invoerNieuweAdresGegevens(adres);
        
        adresDao.update(adres);
       
        gewijzigdAdres = (Adres)adresDao.readById(adresId);
        
        System.out.println("Oude adresgegevens: ");
        adresView.printAdresOverzicht(adres);
        System.out.println("Nieuwe adresgegevens: ");                  
        adresView.printAdresOverzicht(gewijzigdAdres);
        
       
    }
//    
//    public void updateOpStraatnaam() {
//        
//        
//        adres = new Adres();
//        Adres gewijzigdAdres = new Adres();
//        String straatnaam = adresView.voerStraatnaamIn();
//        
//        adressenLijst = adresDao.findByStraatNaam(straatnaam);
//        updateOpAdresId();
//        int adresId = adresView.voerAdresIdIn();
//        adres = adresDao.findByAdresID(adresId);
//        gewijzigdAdres = invoerNieuweAdresGegevens(adres);
//        gewijzigdAdres = adresDao.updateGegevens(gewijzigdAdres);
//        
//        System.out.println("Oude adresgegevens: ");
//        adresView.printAdresOverzicht(adres);
//        System.out.println("Nieuwe adresgegevens: ");                  
//        adresView.printAdresOverzicht(gewijzigdAdres);
        
//    }
//    
//    public void updateOpPostcodeHuisnummer() {
//        
//        
//        adres = new Adres();
//        Adres gewijzigdAdres = new Adres();
//        int huisnummer = adresView.voerHuisnummerIn();
//        String postcode = adresView.voerPostcodeIn();
//        
//        adressenLijst = adresDao.findByPostcodeHuisNummer(postcode, huisnummer);
//        updateOpAdresId();
////        int adresId = adresView.voerAdresIdIn();
////        adres = adresDao.findByAdresID(adresId);
////        gewijzigdAdres = invoerNieuweAdresGegevens(adres);
////        gewijzigdAdres = adresDao.updateGegevens(gewijzigdAdres);
////        
////        System.out.println("Oude adresgegevens: ");
////        adresView.printAdresOverzicht(adres);
////        System.out.println("Nieuwe adresgegevens: ");                  
////        adresView.printAdresOverzicht(gewijzigdAdres);
//    }
    
    
//    public void updateOpWoonplaats() {
//     
//        
//        adres = new Adres();
//        Adres gewijzigdAdres = new Adres();
//        String woonplaats = adresView.voerWoonplaatsIn();
//        
//        adressenLijst = adresDao.findByWoonplaats(woonplaats);
//        updateOpAdresId();
////        int adresId = adresView.voerAdresIdIn();
////        adres = adresDao.findByAdresID(adresId);
////        gewijzigdAdres = invoerNieuweAdresGegevens(adres);
////        gewijzigdAdres = adresDao.updateGegevens(gewijzigdAdres);
////        
////        System.out.println("Oude adresgegevens: ");
////        adresView.printAdresOverzicht(adres);
////        System.out.println("Nieuwe adresgegevens: ");                  
////        adresView.printAdresOverzicht(gewijzigdAdres);
//    }
    
    
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
    
    
    public void verwijderAdresGegevens() {        
              
        boolean isDeletedInAdres = false;
        boolean isDeletedInKlantAdres = false;
        
        userInput = adresView.printVerwijderAdresMenu();
        switch (userInput) {
            case 1:                
                System.out.println("Voer het adres id in: ");
                Long adresId = adresView.voerAdresIdIn();
                
                isDeletedInAdres = adresDao.deleteById(adresId);
               
                System.out.println("verwijderen van adres: " + isDeletedInAdres);               
                    break;           
//                isDeletedInKlantAdres = klantAdresDao.deleteKlantAdresByAdresId(adresId);
//                if (isDeletedInAdres == true && isDeletedInKlantAdres == true) {
//                    System.out.println("Het volgende adres is verwijderd uit het bestand: ");
//                    System.out.println();
//                    adresView.printAdresOverzicht(adres);
//                    System.out.println("Alle koppelingen van klant met adres zijn ook verwijderd.");
//                }
//                else {
//                    System.out.println("Het volgende adres is NIET verwijderd uit het bestand: ");
//                    System.out.println();
//                    adresView.printAdresOverzicht(adres);
//                }   
                
            case 2:
                userInput = adresView.bevestigingsVraag();
                if (userInput == 1) {
                   
                    int rowsAffected = adresDao.deleteAll(Adres.class);
                    
                    //klantAdresDao.deleteAll();
                    System.out.print(rowsAffected);
                    System.out.println(" totaal aantal adressen zijn verwijderd"); 
                   
                   // adresDao.deleteAll(session);
//                    isDeletedInKlantAdres = klantAdresDao.deleteAll();
//                    System.out.println("Alle adressen zijn verwijderd.");                    
//                    System.out.println("alle koppelingen van klant en adres zijn verwijderd");
                }
                if (userInput ==2) {
                    System.out.println("De adresgegevens zijn niet verwijderd.");
                    break;
                }                
            case 3:
                break;
            default:
                break;
        }
                     
    }
    
//    public void zoekAdresKlantGegevens(){
//        
//        long klantId; 
//        userInput = adresView.menuAdresKlantZoeken();
//       
//        switch(userInput){
//           case 1: // adres(sen) bij klantId
//               klantId = klantView.voerKlantIdIn();
//               ArrayList<Adres>adressenLijst = klantAdresDao.findAdresByKlantId(klantId);
//               adresView.printAdressenLijst(adressenLijst);
//               break;
//           case 2: // alle adressen bij klanten opzoeken
//               ArrayList<KlantAdres> klantAdresLijst = klantAdresDao.findAll();
//               adresView.printKlantAdresLijst(klantAdresLijst);
//               int keuze = adresView.alleKoppellingenUitgeprint();
//               switch(keuze){
//                   case 1: // ja
//                        for (int i = 0 ; i < klantAdresLijst.size(); i++){
//                            long adresId = klantAdresLijst.get(i).getAdresId();
//                            klantId = klantAdresLijst.get(i).getKlantId();
//                            adres = adresDao.findByAdresID(adresId);
//                            Klant klant = klantDao.findByKlantId(klantId);
//                            System.out.println("Onderstaand adres:");
//                            adresView.printAdresOverzicht(adres);                           
//                            System.out.println("hoort bij: ");
//                            klantView.printKlantGegevens(klant);
//                            
//                        }
//                            break;
//                    case 2: // nee   
//                        break;
//               
//               }
//               break;
//           case 3:
//                break;
//            default:
//                break;
//           
//       }
//       adresMenu();
//    }
//    
//    
   
}
