


package com.anjewe.anjewewebwinkel.Service;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.DAOs.BetaalwijzeDao;
import com.anjewe.anjewewebwinkel.DAOs.BetalingDao;
import com.anjewe.anjewewebwinkel.DAOs.FactuurDao;
import com.anjewe.anjewewebwinkel.Helpers.ApplicationContextConfig;
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
public class BetalingService {

private static final Logger log = LoggerFactory.getLogger(BetalingService.class);

    FactuurView  factuurView = new FactuurView();
    BetalingView betalingView = new BetalingView();
    
    HoofdMenuController hoofdMenuController = new HoofdMenuController();
     
    GenericDaoImpl <Betaling, Long> betalingDao; 
    GenericDaoImpl<Factuur, Long> factuurDao ;
    GenericDaoImpl<Betaalwijze, Long> betaalwijzeDao;
    GenericDaoImpl <Klant, Long> klantDao;
    
    Betaling betaling; 
    Klant klant; 
    Factuur factuur; 
    Betaalwijze betaalwijze; 
    Betaling gewijzigdeBetaling; 
    boolean gewijzigd;
    Long betalingId;
    int betaalw;
    // int Id - String betaalwijze - String[] betaalWijzes = {"GoogleWallet","iDeal","Creditcard","PayPal","MoneyBookers","Natura"};
    
    
    ApplicationContextConfig acc = new ApplicationContextConfig();
    
    
        
    public Betaling createBetaling(Long factuurId){
        
        Date betaaldatum; 
        betaalw = betalingView.kiesBetaalWijze();
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
        
        factuur = (Factuur) factuurDao.readById(factuurId);
        klant = factuur.getKlant();
        long klantId = klant.getId();
        
        klant = (Klant) klantDao.readById(klantId);        
        
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
        
        System.out.println("U gaat een betaling toevoegen. Voer de gegevens in.");
        betaling = createBetaling(factuurId); 
        
        betalingId = (Long)betalingDao.insert(betaling);   
        
        System.out.println("U heeft de gegevens van de betaling toegevoegd met betalingId: " 
            + betalingId); 
        System.out.println();        
        
        betalingMenu();
        
        return betalingId;
    }
    
    
     private void zoekBetalingGegevens() {
     
        int input = betalingView.menuBetalingZoeken();
        switch (input){
                case 1:  // naar 1 betaling zoeken
                     betalingId = betalingView.voerBetalingIdIn();
                        
                        betaling = (Betaling) betalingDao.readById(betalingId);
                        betalingView.printBetalingOverzicht(betaling);                     
                    break; // einde naar 1 betaling zoeken
                case 2: // alle betalingen zoeken
                    
                    ArrayList <Betaling> betalingenLijst = 
                            (ArrayList <Betaling>) betalingDao.readAll(Betaling.class);
                    System.out.println("Alle artikelen in het bestand");
                    betalingView.printBetalingLijst(betalingenLijst); 
                        break; 
                case 3: // naar betalingmenu
                        break; 
                default: // automatisch naar betalingmenu	
                        break; 
        }	
     betalingMenu();   
     
     }

    //id, betaaldatum, betaalwijze, klant, factuur, betalingsgegevens     
    private void wijzigingenInBetaling() {
        
        betalingId = betalingView.voerBetalingIdIn();
        betaling = (Betaling) betalingDao.readById(betalingId);
        gewijzigdeBetaling = invoerNieuweBetalingGegevens(betaling);
        
        betalingDao.update(betaling);
        betaling = betalingDao.readById(betalingId);
        betalingView.printBetalingOverzicht(betaling);
    
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
        
        int userInput = betalingView.printVerwijderMenu();
        switch (userInput) {
            case 1:// 1 betaling verwijderen  
                betalingView.printBetalingLijst((ArrayList<Betaling>) betalingDao.readAll(Betaling.class));
                long betalingId = betalingView.printDeleteBetalingById();
                betalingDao.deleteById(betalingId);
                break;
            case 2:// alle betalingen verwijderen                
                int x = betalingView.bevestigingsVraag();                
                    if (x == 1){ // bevestiging is ja
                        int verwijderd = betalingDao.deleteAll(Betaling.class); 
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
        
    }
    
   

}
