    
package com.anjewe.anjewewebwinkel.Controller;


import com.anjewe.anjewewebwinkel.DAOs.*;
import com.anjewe.anjewewebwinkel.POJO.*;
import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoInterface;
import com.anjewe.anjewewebwinkel.Helpers.ApplicationContextConfig;
import com.anjewe.anjewewebwinkel.View.AccountView;
import com.anjewe.anjewewebwinkel.View.AdresView;
import com.anjewe.anjewewebwinkel.View.BestellingView;
import com.anjewe.anjewewebwinkel.View.FactuurView;
import com.anjewe.anjewewebwinkel.View.HoofdMenuView;
import com.anjewe.anjewewebwinkel.View.KlantView;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anne
 */
@Component
public class KlantController {
    
    KlantController(){}
    //data fields
    private static final Logger logger = (Logger) LoggerFactory.getLogger("com.webshop");
    private static final Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
    private static final Logger testLogger = (Logger) LoggerFactory.getLogger("com.webshop.test");
     
   
   @Autowired
    GenericDaoImpl<Klant, Long> klantDao; 
   @Autowired
    GenericDaoImpl<Adres, Long> adresDao ;
   @Autowired
    GenericDaoImpl<Factuur, Long> factuurDao ;
   @Autowired
    GenericDaoImpl<Account, Long> accountDao; 
   @Autowired
    GenericDaoImpl<Bestelling, Long> bestellingDao ; 
    
   @Autowired
    Account account;
   @Autowired
   AccountController accountController;
   @Autowired
    AccountView accountView;
    @Autowired        
    KlantView klantView;  
    
    @Autowired
    Klant klant;
    @Autowired
    Klant gewijzigdeKlant;
    ArrayList<Klant> klantenLijst;    
    
    @Autowired
    AdresView adresView;
    @Autowired
    AdresController adresController;  
    @Autowired
    Adres adres; 
           
    @Autowired
    KlantAdres KA;
    @Autowired
    HoofdMenuController hoofdMenuController;
    @Autowired
    HoofdMenuView hoofdMenuView;
    @Autowired
    FactuurController factuurController;
    @Autowired
    FactuurView factuurView;
    @Autowired
    BestellingView bestellingView;
    
    EmailValidator validator = EmailValidator.getInstance(); 
    boolean isAddressValid = false;
  
    
    public void klantMenu() {
        
        int keuze = klantView.startMenuKlant();
        
        switch(keuze){
            case 1: 
                voegNieuweKlantMetAdresToe();
                break;
              case 2:
                zoekKlantGegevens();
                break;
            case 3: 
                wijzigKlantGegevens();
                break;
            case 4: 
                verwijderKlantGegevens();
                break;
            case 5: 
                voegNieuweKlantToe();
                break;
            case 6:
                voegKlantAanAdresToe();
                break;            
            case 7: 
                zoekAdresMetKlantId();
                break;
            case 8:
                zoekFacturenBijKlant();
                break;
            case 9:
                zoekBestellingenBijKlant();
                break; 
            case 10:
                zoekAccountBijKlant();
                break; 
            case 11:
                terugNaarHoofdMenu();
                break;
            default: 
                System.out.println("Deze optie is niet beschikbaar.");
                break;  
        }                
    }
    
    // public <T> List<T> read(PK id, T t, Session session)
    public void zoekAdresMetKlantId(){
       
        
        System.out.println("U gaat adressen van een klant opzoeken. Voor klantId in: ");
        long klantId = klantView.voerKlantIdIn();
        klant = (Klant) klantDao.readById(klantId);
        Set <KlantAdres> klantadressen = klant.getKlantAdressen();
        
            if (klantadressen != null){
                System.out.println("Alle klant-adres koppelingen in het bestand van klantid: " + klantId);
                klantView.printKlantAdresLijst(klantadressen);   
            }
            
        klantMenu();
    }
    
    // werkt
    public void zoekFacturenBijKlant(){
                      
        System.out.println("U gaat facturen van een klant opzoeken. Voor klantId in: ");
        long klantId = klantView.voerKlantIdIn();
        
        klant = (Klant) klantDao.readById(klantId);
       
        Set <Factuur> facturen = klant.getFacturen();
        for(Factuur factuur: facturen){
            long factuurId = factuur.getId();
            
            factuur = (Factuur) factuurDao.readById(factuurId);
            double totaalBedrag = factuurController.berekenTotaalBedrag(factuur);
            factuurView.printFactuurOverzicht(factuur, totaalBedrag);
        }
        
        klantMenu();
    }
          
    public void zoekBestellingenBijKlant(){
        
        
        System.out.println("U gaat bestellingen van een klant opzoeken. Voor klantId in: ");
        long klantId = klantView.voerKlantIdIn();
        
        klant = (Klant) klantDao.readById(klantId);
        
        Set <Bestelling> bestellingen = klant.getBestellingen();
        for(Bestelling bestelling: bestellingen){
            long bestellingId = bestelling.getId();
            bestelling = (Bestelling) bestellingDao.readById(bestellingId);
            bestellingView.printBestellingInfo(bestelling);
        }
        
       klantMenu(); 
       
    }
    
    public void zoekAccountBijKlant(){             
        
        System.out.println("U gaat accounts van een klant opzoeken. Voor klantId in: ");
        long klantId = klantView.voerKlantIdIn();        
        klant = (Klant) klantDao.readById(klantId);       
        account = klant.getAccount();
        accountView.printAccountGegevens(account);        
        
        klantMenu();
    }
    
    
//    public void voegAccountAanKlantToe(){
//       
//        
//        System.out.println("U gaat een account toevoegen. Voer uw klantId in: ");
//        long klantId = klantView.voerKlantIdIn();
//        
//        klant = (Klant) session.get(Klant.class, klantId);
//        Account account = new Account();
//        // nieuwe account aanmaken.
//        // account toevoegen in database
//        klant.getAccounts().add(account);
//        //update gevevens van klant
//        session.getTransaction().commit();
//        session = getSession();
//        Set <Account> accounts = klant.getAccounts();
//        
//        // uitprinten van de set account  
//    }    
     
    
    public long voegNieuweKlantToe(){        
        System.out.println("KLANTDAO = "+ klantDao.hashCode());
        System.out.println("U gaat een klant toevoegen. Voer de gegevens in.");
        klant = createKlant(); 
        Long klantId = (Long)klantDao.insert(klant);            
       
        System.out.println("U heeft de klantgegevens toegevoegd met klantId: " 
            + klantId); 
        System.out.println();        
        
        klantMenu();
        
        return klantId;
    }
    
    
    public long voegNieuweKlantMetAdresToe() {
        
         // naar andere plek in bestand
        String medewerker = klantView.voerNaamMwIn();
        
        System.out.println("U gaat een klant toevoegen. Voer de gegevens in.");
        klant = createKlant();   
        
            System.out.println("Voer uw adres in: ");
            adres = adresController.createAdres();
            // als adres al bestaat naar gebruik dan adres id en voeg adres toe
            Long adresId = (Long)adresDao.insert(adres);
           
           
            KA.setKlant(klant);
            KA.setAdres(adres);
            KA.setCreatedDate(new Date());
            KA.setCreatedBy(medewerker);
            
            klant.getKlantAdressen().add(KA);
            Long klantId = (Long)klantDao.insert(klant);
            
            System.out.println("U heeft de klant- en adresgegevens toegevoegd van klantId: " 
                + klantId + " en adresId " + adresId); 
            System.out.println();       
        
         
        klantMenu();
        
      return klantId;
    } // eind methode voegNieuweKlantMetAdresToe

    
    // nog niet helemaal logisch
    public long voegKlantAanAdresToe(){        
        
        System.out.println("U gaat een klant toevoegen. Voer de gegevens in.");
        klant = createKlant();  
        
        Long adresId = adresView.voerAdresIdIn();        
        Adres adresBestaand = (Adres) adresDao.readById(adresId);
        
        // naar andere plek in bestand
        String medewerker = klantView.voerNaamMwIn();
        
        KA.setAdres(adresBestaand);
        KA.setCreatedBy(medewerker); 
        KA.setCreatedDate(new Date());
        KA.setKlant(klant);
        
        klant.getKlantAdressen().add(KA);
        Long klantId = (Long)klantDao.insert(klant);

        
        System.out.println("U heeft de klantegevens toegevoegd van klantId: " 
            + klantId + " aan adresId " + adresId); 
        System.out.println();

       
        klantMenu();
        
      return klantId;        
    }
    
    
    public Klant createKlant(){
        
        //int klantId = 0;   
        String achternaam = klantView.voerAchterNaamIn();
        String voornaam = klantView.voerVoorNaamIn();
        String tussenvoegsel = klantView.voerTussenVoegselIn();
        String email = klantView.voerEmailIn();                             
        isAddressValid = validator.isValid(email);
            while (isAddressValid == false) {
                System.out.println
                    ("Ongeldig emailadres. Vul opnieuw uw emailadress in (bijv. hallo@hallo.com)");
                email = klantView.voerEmailIn();
                validator = EmailValidator.getInstance();
                isAddressValid = validator.isValid(email);
            }                       
        
        klant.setVoornaam(voornaam);
        klant.setAchternaam(achternaam);
        klant.setEmail(email);
        klant.setTussenvoegsel(tussenvoegsel);        

        return klant;        
    }    
    
    public void zoekKlantGegevens() {

        long klantId;   
        int x = 0;
        
        int input = klantView.menuKlantZoeken();
        switch (input) {
            case 1: 
                klantId = klantView.voerKlantIdIn();             

                klant = (Klant)klantDao.readById(klantId);
              
                klantView.printKlantGegevens(klant);
                    // ook de adressen uitdraaien?
                    break;
//                case 2:
//                    int keuze = klantView.hoeWiltUZoeken();
//                    switch (keuze) {
//                        case 1: verwijderd - zoeken op voor en achternaam
//                        case 2: //zoeken op email
//                            String email = klantView.voerEmailIn();                             
//                            isAddressValid = validator.isValid(email);
//                            while (isAddressValid == false) {
//                                System.out.println
//                                    ("Ongeldig emailadres. Vul opnieuw uw emailadress in (bijv. hallo@hallo.com)");
//                                email = klantView.voerEmailIn();
//                                validator = EmailValidator.getInstance();
//                                isAddressValid = validator.isValid(email);
//                            }
//                                                       
//                            klantenLijst = klantDAO.findByEmail(email);
//                            if (klantenLijst != null)
//                                klantView.printKlantenLijst(klantenLijst);   
//                            else 
//                                klantView.printGeenKlanten(email);                                                  
//                            break;
//                        case 3: // zoek met adresId
//                            int adresId = adresView.voerAdresIdIn();
//                            ArrayList<Klant>klantenLijst = klantAdresDAO.findKlantByAdresId(adresId);
//                            break;
//                        case 4: // direct door naar einde switch: methode naar inlogschermklant()
//                            break;
//                        default:
//                            break;
//                    }   
//                default:
//                    break;                    
//            } 
            // eind zoeken naar 1 klant
//            break;
            case 2: // zoeken naar alle klanten
                
                ArrayList <Klant> klantenLijst = (ArrayList<Klant>) klantDao.readAll(Klant.class);
                
                if (klantenLijst != null){
                    System.out.println("Alle klanten in het bestand");
                    klantView.printKlantenLijst(klantenLijst);   
                }
                else { 
                    String naam = "alle klanten";
                    klantView.printGeenKlanten(naam);
                }                  
                break; 
            case 3: // direct door naar einde switch: methode naar inlogschermklant()
                break;
            default: 
                break; 
        }
       
        klantMenu();
        } // eind zoeken naar 1 klant of alle klanten
 
   
    public void wijzigKlantGegevens(){
       
       long klantId = 0;
        
       int input = klantView.isKlantIdBekend();
        // klantid is bekend:
        switch (input) {
            case 1:
                klantId = klantView.voerKlantIdIn();
                klant = (Klant)klantDao.readById(klantId);
                gewijzigdeKlant = voerWijzigingenKlantIn(klant);
                klantDao.update(gewijzigdeKlant);                 
                gewijzigdeKlant= (Klant)klantDao.readById(klantId);
                
                System.out.println("Nieuwe klantgegevens:");                
                klantView.printKlantGegevens(gewijzigdeKlant);
                break;
            case 2:
                int keuze = klantView.hoeWiltUZoeken();
                switch (keuze) {
                    case 1: // wijzigen op basis van voor en achternaam is verwijderd
                        break;                        
                    case 2: // wijzigen op basis van email                        
                        String email = klantView.voerEmailIn();                             
                        isAddressValid = validator.isValid(email);
                            while (isAddressValid == false) {
                                System.out.println
                                    ("Ongeldig emailadres. Vul opnieuw uw emailadress in (bijv. hallo@hallo.com)");
                                email = klantView.voerEmailIn();
                                validator = EmailValidator.getInstance();
                                isAddressValid = validator.isValid(email);
                            }
                        // deze methode bestaat nog niet findByemail();
                        //klantenLijst = klantDao.findByEmail(email);
                        klantView.printKlantenLijst(klantenLijst); 
                        klantId = klantView.voerKlantIdIn();
                        klant = (Klant)klantDao.readById(klantId);
                        
                        gewijzigdeKlant = voerWijzigingenKlantIn(klant);                         
                        klantDao.update(gewijzigdeKlant);  
                        System.out.println("Nieuwe klantgegevens:");                        
                        klantView.printKlantGegevens(gewijzigdeKlant);          
                        break;
                    case 3: // direct door naar einde switch: methode naar inlogschermklant()
                        break;
                    default:
                        break;
                }
            default:
                break;    
        }//       
        klantMenu();//                          
    } // einde methode wijzigKlantGegevens
    
       
    public void verwijderKlantGegevens() {       
        
        boolean deleted = false;
        Long klantId;
        int x = 0;
        
        int userInput = klantView.menuKlantVerwijderen();
        
        switch (userInput) {
            case 1:     
                ArrayList <Klant> klantenLijst = (ArrayList<Klant>) klantDao.readAll(Klant.class);
                    //session.getTransaction().commit();
                    klantView.printKlantenLijst(klantenLijst); 
                    System.out.println("Welke klant wilt u verwijderen?");                        
                    klantId = klantView.voerKlantIdIn();                          
                    //session = getSession();
                    deleted = klantDao.deleteById(klantId);  
                   
                    System.out.println("verwijderen van klant: " + deleted);
                     break;           
            case 2: // alle klanten verwijderen
                x = klantView.bevestigingsVraag();
                // bevestiging is ja
                if (x == 1){                    
                    int rowsAffected = klantDao.deleteAll(Klant.class);
                  
                    //klantAdresDao.deleteAll();
                    System.out.print(rowsAffected);
                    System.out.println(" totaal aantal klanten zijn verwijderd");                    
                    System.out.println("alle koppelingen van klant en adres zijn verwijderd");
                }
                // bevestiging = nee
                else {
                    System.out.println("De klantgegevens worden niet verwijderd.");
                }
                break;
            case 3: // terug naar klantenmenu - dmv break direct naar inlogschermklant()
                break;
            default:
                break;
        }
        klantMenu();
    }// eind methode verwijderKlantGegevens


    // andere wijzigingen toevoegen
    public Klant voerWijzigingenKlantIn(Klant klant){
        int juist = 0 ;
        
	String voornaam = klant.getVoornaam();
        System.out.println("Uw voornaam: ");
	juist = klantView.checkInputString(voornaam); // iets dergelijks als "is dit juist?: "+ voormaam 1/true 2/false
            if (juist == 2) { 
                voornaam = klantView.voerVoorNaamIn();
            }
                
	String achternaam = klant.getAchternaam();
        System.out.println("Achternaam: ");
	juist = klantView.checkInputString(achternaam);  // code schrijven voor methode iets als hierboven
            if (juist == 2) {
                achternaam = klantView.voerAchterNaamIn();
            }
                
	String tussenvoegsel = klant.getTussenvoegsel();
        System.out.println("Tussenvoegsel(s):");
	juist = klantView.checkInputString(tussenvoegsel); // zie hierboven
            if(juist == 2) {
                tussenvoegsel = klantView.voerTussenVoegselIn(); 
            }
                
	String email = klant.getEmail();
        System.out.println("emailadres:");
	juist = klantView.checkInputString(email);
            if (juist == 2){ 
                email = klantView.voerEmailIn();                             
                isAddressValid = validator.isValid(email);
                    while (isAddressValid == false) {
                        System.out.println
                            ("Ongeldig emailadres. Vul opnieuw uw emailadress in (bijv. hallo@hallo.com)");
                        email = klantView.voerEmailIn();
                        validator = EmailValidator.getInstance();
                        isAddressValid = validator.isValid(email);
                    }
            }  
	
        klant.setVoornaam(voornaam);
        klant.setAchternaam(achternaam);
        klant.setTussenvoegsel(tussenvoegsel);
        klant.setEmail(email);
        // iets doen met de gegevens die niet veranderen
	return klant;  
    } // eind methode voerWijzigingenKlantIn

    
    public boolean isAdresGoed(String email) {
        
        EmailValidator validator = EmailValidator.getInstance(); 
        validator = EmailValidator.getInstance();
        boolean isAddressValid = validator.isValid(email);
        return isAddressValid;
    } 
     
    public void terugNaarHoofdMenu() {       
        hoofdMenuController.start();
    }  
     
     
    
}  // end class KlantController
