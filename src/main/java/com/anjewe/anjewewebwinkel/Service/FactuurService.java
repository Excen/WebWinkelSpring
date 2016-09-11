


package com.anjewe.anjewewebwinkel.Service;

import com.anjewe.anjewewebwinkel.Service.BetalingService;
import com.anjewe.anjewewebwinkel.Service.BestellingService;
import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.DAOs.FactuurDao;
import com.anjewe.anjewewebwinkel.POJO.Bestelling;
import com.anjewe.anjewewebwinkel.POJO.BestellingArtikel;
import com.anjewe.anjewewebwinkel.POJO.Betaling;
import com.anjewe.anjewewebwinkel.POJO.Factuur;
import com.anjewe.anjewewebwinkel.POJO.Klant;
import com.anjewe.anjewewebwinkel.View.FactuurView;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Wendy
 */
@Component
public class FactuurService {

private static final Logger log = LoggerFactory.getLogger(FactuurService.class);

    Bestelling bestelling;
    Betaling betaling; 
    Klant klant; 
    Factuur factuur; 
    FactuurView factuurView = new FactuurView();
    
    GenericDaoImpl<Factuur, Long> factuurDao; 
    GenericDaoImpl <Klant, Long> klantDao; 
    GenericDaoImpl<Bestelling, Long> bestellingDao;
    GenericDaoImpl<Betaling, Long> betalingDao;
    
    BestellingService bestellingController;
    BetalingService betalingController; 
    KlantService klantController;
    HoofdMenuController hoofdMenuController; 
    
   
       
    
    public Factuur createFactuur(){        
        
        String factuurnummer = factuurView.voerFactuurNummerIn();
        
        // Klant: @mto = fetchtype.lazy
        // long klantId; > klant vanuit klant automatisch gekoppeld?        
        
        //@oto  > fetchtype.lazy, optional false, cascade.persist
        // deze vanuit bestelling automatisch gekoppeld?
           
        long bestellingId = factuurView.voerBestellingIdIn(); 
        bestelling = (Bestelling) bestellingDao.readById(bestellingId);
        klant = bestelling.getKlant();        
        
        factuur.setFactuurnummer(factuurnummer);       
        factuur.setFactuurdatum(new Date());
        factuur.setKlant(klant);
        //factuur.setBetalingset(betalingset);
        factuur.setBestelling(bestelling);   
//          betaling: @otm: mapped bij factuur  > eerst factuur hebben waaraan betaling kan worden toegevoegd
        
        return factuur;
    }
    
    
    public long voegNieuweFactuurToe(){
         
        System.out.println("U gaat een factuur toevoegen. Voer de gegevens in.");
        factuur = createFactuur(); 
        Long factuurId = (Long)factuurDao.insert(factuur); 
        
        factuur = (Factuur) factuurDao.readById(factuurId);
        
        double totaalBedrag = berekenTotaalBedrag(factuur);
        factuurView.printFactuurOverzicht(factuur, totaalBedrag);   
                
        System.out.println("U heeft de factuurgegevens toegevoegd met factuurId: " 
            + factuurId); 
        System.out.println();        
        
        factuurMenu();
        
        return factuurId;
    }
    
    
    private void zoekFactuurGegevens() {
        factuurDao = new FactuurDao();
        factuur = new Factuur();
        
        int input = factuurView.menuFactuurZoeken();
        switch (input){
                case 1:  
                    Long factuurId = factuurView.voerFactuurIdIn();
                    factuur = (Factuur) factuurDao.readById(factuurId);
                    // op deze plek totaalbedrag berekenen
                    double totaalBedrag = berekenTotaalBedrag(factuur);
                    factuurView.printFactuurOverzicht(factuur, totaalBedrag);                     
                    break; // einde naar 1 factuur zoeken
                case 2: // alle facturen zoeken
                    ArrayList <Factuur> facturenLijst = 
                            (ArrayList <Factuur>) factuurDao.readAll(Factuur.class);
                    System.out.println("Alle artikelen in het bestand");
                    factuurView.printFacturenLijst(facturenLijst); 
                        break; 
                case 3: // naar factuurmenu
                        break; 
                default: // automatisch naar factuurmenu	
                        break; 
        }	
     factuurMenu();
        
    }   

    private void wijzigFactuurGegevens() {
        Factuur gewijzigdeFactuur; 
        boolean gewijzigd;
        
        long factuurId = factuurView.voerFactuurIdIn();
        factuur = (Factuur) factuurDao.readById(factuurId);
        gewijzigdeFactuur = invoerNieuweFactuurGegevens(factuur);
        
        factuurDao.update(gewijzigdeFactuur); 
        factuurMenu();
    
    }
    
    public Factuur invoerNieuweFactuurGegevens(Factuur factuur) {
                
        int juist = 0;
        
        String factuurNummer = "factuurnummer "+ factuur.getFactuurnummer();
        juist = factuurView.checkInputString(factuurNummer);
            if (juist == 2) {
                factuurNummer = factuurView.voerFactuurNummerIn();
            }
        long bestellingId =  factuur.getBestelling().getId();
        //Bestelling bestelling = factuur.getBestelling();
        bestelling = (Bestelling) bestellingDao.readById(bestellingId );
        juist = factuurView.checkInputString(bestelling.toString());
            if (juist == 2) {
                bestellingId = factuurView.voerBestellingIdIn();
                bestelling =(Bestelling) bestellingDao.readById(bestellingId );
            }
        // wat willen we hier graag updaten? 
//        Set<Betaling> betalingen = factuur.getBetalingset();
//            juist = factuurView.checkInputString(betalingen.toString());
//            if (juist == 2) {//            
//            }
               
        factuur.setFactuurnummer(factuurNummer);
        factuur.setBestelling(bestelling);
//        factuur.setBetalingset(betalingen);
        
        return factuur;        
    }
    

    private void verwijderFactuurGegevens() {
        
        int x; 
        int userInput = factuurView.printVerwijderMenu();
        switch (userInput) {
            case 1:// 1 factuur verwijderen  
                ArrayList<Factuur> factuurlijst = (ArrayList<Factuur>) factuurDao.readAll(Factuur.class);
                factuurView.printFacturenLijst(factuurlijst);
                long factuurId = factuurView.printDeleteFactuurById();
                // bevestiging vragen  
                 x = factuurView.bevestigingsVraag();                
                    if (x == 1){ // bevestiging is ja
                        boolean verwijderd = factuurDao.deleteById(factuurId);
                        System.out.println(": factuur met id " + factuurId + " is verwijderd: " +  verwijderd);                       
                    }                
                    else { // bevestiging = nee
                        System.out.println( "De factuurgegevens worden NIET verwijderd.");
                    }
                break;
            case 2:// alle facturen verwijderen                
                x = factuurView.bevestigingsVraag();                
                    if (x == 1){ // bevestiging is ja
                        int verwijderd = factuurDao.deleteAll(Factuur.class);   
                        System.out.println(verwijderd + " totaal aantal facturen zijn verwijderd");                       
                    }                
                    else { // bevestiging = nee
                        System.out.println("De facturen gegevens worden NIET verwijderd.");
                    }
                break;                
            case 3:;
                break;
            default:
                break;
        }
        factuurMenu(); 
    }
 
    
    public double berekenTotaalBedrag(Factuur factuur){
        
         double totaalBedrag = 0.0;
         long bestellingId = factuur.getBestelling().getId();
         System.out.println("bestellingid: " + bestellingId);
         bestelling = (Bestelling) bestellingDao.readById(bestellingId);
         Set<BestellingArtikel> ba = bestelling.getBestellingArtikellen();
         for(BestellingArtikel bestelArtikel: ba){
             int aantal  = bestelArtikel.getArtikelAantal();
             //moet artikel uit db gehaal worden? of fetchtype.eager?
             double artPrijs = bestelArtikel.getArtikel().getArtikelPrijs();
             double bedrag = aantal * artPrijs; 
       
         totaalBedrag += bedrag;          
         }
         return totaalBedrag;
    }
    

    
    public void voegBetalingToe(){
        System.out.println("U gaat een betaalwijze toevoegen");
        long factuurId = factuurView.voerFactuurIdIn();
        
        long betalingId = betalingController.voegNieuweBetalingToe(factuurId);
        betaling = (Betaling) betalingDao.readById(betalingId);
        
        factuur = (Factuur) factuurDao.readById(factuurId);
        factuur.getBetalingset().add(betaling);
        
        factuurDao.update(factuur);
    }

}
