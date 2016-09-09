


package com.anjewe.anjewewebwinkel.Controller;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoInterface;
import com.anjewe.anjewewebwinkel.DAOs.BetaalwijzeDao;
import com.anjewe.anjewewebwinkel.DAOs.BetalingDao;
import com.anjewe.anjewewebwinkel.DAOs.FactuurDao;
import com.anjewe.anjewewebwinkel.Helpers.ApplicationContextConfig;
import com.anjewe.anjewewebwinkel.Helpers.HibernateSessionFactory;
import com.anjewe.anjewewebwinkel.POJO.Bestelling;
import com.anjewe.anjewewebwinkel.POJO.Betaalwijze;
import com.anjewe.anjewewebwinkel.POJO.Betaling;
import com.anjewe.anjewewebwinkel.POJO.Factuur;
import com.anjewe.anjewewebwinkel.POJO.Klant;
import com.anjewe.anjewewebwinkel.View.BetalingView;
import com.anjewe.anjewewebwinkel.View.FactuurView;
import java.util.ArrayList;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Wendy
 */
@Component
public class BetalingController {

private static final Logger log = LoggerFactory.getLogger(BetalingController.class);

    FactuurView  factuurView = new FactuurView();
    BetalingView betalingView = new BetalingView();
    
    HoofdMenuController hoofdMenuController = new HoofdMenuController();
     
    GenericDaoImpl <Betaling, Long> betalingDao; 
    GenericDaoImpl<Factuur, Long> factuurDao ;
    GenericDaoImpl<Betaalwijze, Long> betaalwijzeDao;
    
    Betaling betaling; 
    Klant klant; 
    Factuur factuur; 
    Betaalwijze betaalwijze; 
    // int Id - String betaalwijze - String[] betaalWijzes = {"GoogleWallet","iDeal","Creditcard","PayPal","MoneyBookers","Natura"};
    
    
    ApplicationContextConfig acc = new ApplicationContextConfig();
    
    
    public void betalingMenu() {
        
        int keuze = betalingView.startMenuBetaling();
        
        switch(keuze){
            case 1: 
                voegNieuweBetalingToe();
                break;
            case 2:
                zoekBetalingGegevens();
                break;  
            case 3: 
                wijzigingenInBetaling();
                break;
            case 4: 
                verwijderenVanBetaling();
                break; 
            //case 5:
                // 
            case 5:
                terugNaarHoofdMenu();
                break;            
            default: 
                System.out.println("Deze optie is niet beschikbaar.");
                break;            
            
        }                
    }
        
    
        
    public Betaling createBetaling(Long factuurId){
        
        betaling = new Betaling();  
        factuur = new Factuur();
        factuurDao = new FactuurDao();
         betaalwijzeDao = new BetaalwijzeDao();
        
        // klantId komt bij factuur vandaan
        klant = new Klant();
        Date betaaldatum; 
        int betaalw = betalingView.kiesBetaalWijze();
        //EnumMap bw = new EnumMap(Betaalwijze.class);
        
        switch (betaalw){
            case 1:
                betaalwijze = Betaalwijze.GOOGLEWALLET;
                break;
            case 2:
                 betaalwijze = Betaalwijze.IDEAL;
                break;
            case 3:
                 betaalwijze = Betaalwijze.PAYPAL;
                break;
            case 4:
                 betaalwijze = Betaalwijze.MONEYBOOKERS;
                break;
            case 5:
                 betaalwijze = Betaalwijze.CREDITCARD;
                break;   
            default:
                return null;         
                
        }
        
        
        String betalingsGegevens = betalingView.voegBetalingsGevensToe(); 
        // factuur en betaling gekoppeld, begint bij factuur
        
        factuur = (Factuur) session.get(Factuur.class, factuurId);
        session.getTransaction().commit();
        klant = factuur.getKlant();
        long klantId = klant.getId();
        
        klant = (Klant) session.get(Klant.class, klantId);        
        
        betaling.setBetaalwijze(betaalwijze);
        betaling.setBetaaldatum(new Date());        
        betaling.setFactuur(factuur);        
        betaling.setBetalingsGegevens(betalingsGegevens);  
        betaling.setKlant(klant);

        return betaling;          
    }
    
    public void voegNieuweBetalingToe(){
         
        
        System.out.println("U gaat een betaling toevoegen, behorende bij welk factuurId?");
        long factuurId = factuurView.voerFactuurIdIn();
        
        voegNieuweBetalingToe(factuurId);
    }
    
    public long voegNieuweBetalingToe(Long factuurId){
          
        session = getSession();
        betalingDao = new BetalingDao(); 
        
        System.out.println("U gaat een betaling toevoegen. Voer de gegevens in.");
        betaling = createBetaling(factuurId); 
        
        session = getSession();
        Long betalingId = (Long)betalingDao.insert(betaling);            
        session.getTransaction().commit();
        
        System.out.println("U heeft de gegevens van de betaling toegevoegd met betalingId: " 
            + betalingId); 
        System.out.println();        
        
        betalingMenu();
        
        return betalingId;
    }
    
    
     private void zoekBetalingGegevens() {
     
        betalingDao = new BetalingDao();
        betaling = new Betaling();         
	session = getSession();
        
        int input = betalingView.menuBetalingZoeken();
        switch (input){
                case 1:  // naar 1 betaling zoeken
                     long betalingId = betalingView.voerBetalingIdIn();
                        
                        betaling = (Betaling) betalingDao.readById(betalingId);
                        session.getTransaction().commit();
                        betalingView.printBetalingOverzicht(betaling);                     
                    break; // einde naar 1 betaling zoeken
                case 2: // alle betalingen zoeken
                    
                    ArrayList <Betaling> betalingenLijst = 
                            (ArrayList <Betaling>) betalingDao.readAll(Betaling.class);
                    session.getTransaction().commit();
                    System.out.println("Alle artikelen in het bestand");
                    betalingView.printBetalingLijst(betalingenLijst); 
                        break; 
                case 3: // naar betalingmenu
                        break; 
                default: // automatisch naar betalingmenu	
                        break; 
        }	
        closeSession(session);
     betalingMenu();
    
     
     }

    //id, betaaldatum, betaalwijze, klant, factuur, betalingsgegevens
     
    private void wijzigingenInBetaling() {
           
        session = getSession();
        betalingDao = new BetalingDao();
        
        Betaling gewijzigdeBetaling = new Betaling();
        boolean gewijzigd;
        
        long betalingId = betalingView.voerBetalingIdIn();
        betaling = (Betaling) betalingDao.readById(betalingId);
        session.getTransaction().commit();
        closeSession(session);
        session = getSession();
        gewijzigdeBetaling = invoerNieuweBetalingGegevens(betaling);
        
        betalingDao.update(betaling);
        session.getTransaction().commit();
        betaling = betalingDao.readById(betalingId);
        betalingView.printBetalingOverzicht(betaling);
        closeSession(session); 
    
        betalingMenu();
    }

    
    
     public Betaling invoerNieuweBetalingGegevens(Betaling betaling) {
                // niet wijzigen: id, datum, klant, factuur
        int juist = 0;
        
//         get BetaalWijze betaalwijze, daar de String betaalwijze
        Betaalwijze bw = betaling.getBetaalwijze();
        String betaalwijze = bw.getBetaalwijze();        
        juist = betalingView.checkInputString(betaalwijze);
        if (juist == 2) {
            int betaalw = betalingView.voerBetaalwijzeIn(); 
            
            switch (betaalw){
            case 1:
                bw = Betaalwijze.GOOGLEWALLET;
                break;
            case 2:
                 bw = Betaalwijze.IDEAL;
                break;
            case 3:
                 bw = Betaalwijze.PAYPAL;
                break;
            case 4:
                 bw = Betaalwijze.MONEYBOOKERS;
                break;
            case 5:
                 bw = Betaalwijze.CREDITCARD;
                break;   
            default:
                return null;    
        }
            
            
            
        }
        
        String betalingsGegevens = betaling.getBetalingsGegevens();        
        juist = betalingView.checkInputString(betalingsGegevens);
        if (juist == 2) {
            // aanpassen
            betalingsGegevens = betalingView.aanpassenBetalingsGegevens(betalingsGegevens);
        }
        // setters betaalwijze, betalingsGegevens
        betaling.setBetaalwijze(bw);
        betaling.setBetalingsGegevens(betalingsGegevens);          
               
        return betaling;        
    }
     
         
    private void verwijderenVanBetaling() {
        
        betalingDao = new BetalingDao();
        session = getSession();
                
        int userInput = betalingView.printVerwijderMenu();
        switch (userInput) {
            case 1:// 1 betaling verwijderen  
                betalingView.printBetalingLijst((ArrayList<Betaling>) betalingDao.readAll(Betaling.class));
                long betalingId = betalingView.printDeleteBetalingById();
                betalingDao.deleteById(betalingId);
                session.getTransaction().commit();   
                break;
            case 2:// alle betalingen verwijderen                
                int x = betalingView.bevestigingsVraag();                
                    if (x == 1){ // bevestiging is ja
                        int verwijderd = betalingDao.deleteAll(Betaling.class);  
                        session.getTransaction().commit();
                        System.out.println(verwijderd + " totaal aantal artikelen zijn verwijderd");                       
                    }                
                    else { // bevestiging = nee
                        System.out.println("De artikel gegevens worden NIET verwijderd.");
                    }
                break;                
            case 3:// door naar einde methode > naar betalingmenu();
                break;
            default:
                break;
        }
        betalingMenu(); }
    
    
    public void terugNaarHoofdMenu() {
        hoofdMenuController = new HoofdMenuController();
        hoofdMenuController.start();
    }  

   

   




}
