/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.Service;


import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.DAOs.BestellingDao;
import com.anjewe.anjewewebwinkel.POJO.Bestelling;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Excen
 */

@Transactional
@Service
public class BestellingService implements GenericServiceInterface <Bestelling, Long> {
    
       
    // Data fields
    private static final Logger logger = (Logger) LoggerFactory.getLogger("com.webshop");
    private static final Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
    private static final Logger testLogger = (Logger) LoggerFactory.getLogger("com.webshop.test");
    
    @Autowired
    protected GenericDaoImpl<Bestelling, Long> bestellingDao = new BestellingDao(); 
    
    @Autowired
    Bestelling bestelling;
    
    @Autowired
    Bestelling gewijzigdeBestelling;
    
    // ----
    
    @Override
    public Bestelling voegNieuweBeanToe(Long Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long voegNieuweBeanToe(Bestelling t) {
        Long bestellingId = bestellingDao.insert(t);
        return bestellingId;
    }

    @Override
    public Bestelling zoekNaarBean(Long Id) {
        bestelling = (Bestelling)bestellingDao.readById(Id);
        return bestelling;
    }

    @Override
    public Long zoekNaarBean(Bestelling t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bestelling> zoekAlleBeans() {
        ArrayList <Bestelling> bestellingLijst = (ArrayList<Bestelling>) bestellingDao.readAll(Bestelling.class);
        return bestellingLijst;
    }

    @Override
    public Bestelling wijzigBeanGegevens(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bestelling wijzigBeanGegevens(Bestelling t) {
        gewijzigdeBestelling = (Bestelling)bestellingDao.readById(t.getId());
        if (gewijzigdeBestelling != null){
            gewijzigdeBestelling.setFactuur(t.getFactuur());
            gewijzigdeBestelling.setId(t.getId());
            gewijzigdeBestelling.setKlant(t.getKlant());
            gewijzigdeBestelling.setDatum(t.getDatum());
            gewijzigdeBestelling.setBestellingDatum(t.getBestellingDatum());
            gewijzigdeBestelling.setBestellingArtikellen(t.getBestellingArtikellen());
        }
        bestellingDao.update(gewijzigdeBestelling);
        return gewijzigdeBestelling;
    }
    
    @Override
    public boolean verwijderBeanGegevens(Long Id) {
        boolean deleted = bestellingDao.deleteById(Id);
        return deleted;
    }

    @Override
    public int verwijderAlleBeans() {
        int rowsAffected = bestellingDao.deleteAll(Bestelling.class);
        return rowsAffected;
    }


    /*
    
    Scanner scanner = new Scanner(System.in);
    int userInput;
    Long artikelId;
    int artikelAantal;
    
  
    
    public void plaatsBestelling() {            

       
        // Bestaande klant opzoeken en toevoegen aan bestelling
        long klantId = bestellingView.voerKlantIdIn();
        Klant klant = (Klant) klantDao.readById(klantId);
        
        Bestelling bestelling = new Bestelling();
        bestelling.setKlant(klant);
        bestelling.setDatum(new java.util.Date());
       
        // Tonen beschikbare artikellen
        
        System.out.println("Beschikbare artikellen:");
        
        ArrayList<Artikel> artikelLijst = (ArrayList<Artikel>)artikelDao.readAll(Artikel.class);
        
        bestellingView.printArtikelLijst(artikelLijst);

        // BestellingArtikel toevoeg loop

        int anotherOne = 0;
        boolean checker = true;
        
        do{
            try{
                
            // welk artikel
            artikelId = bestellingView.voerArtikelIdIn();
            
            // hoe vaak
            artikelAantal = bestellingView.voerAantalIn();
            
            }catch (InputMismatchException ex){
                System.out.println("Voer een integer in.");
            }
            
            Artikel artikel = (Artikel) artikelDao.readById(artikelId);
            
            BestellingArtikel bestellingArtikel = new BestellingArtikel();
            bestellingArtikel.setArtikelAantal(artikelAantal);
            bestellingArtikel.setArtikel(artikel);
            
            BestellingArtikelId bestellingArtikelId = new BestellingArtikelId();
            bestellingArtikelId.setArtikel(artikel);
            bestellingArtikelId.setBestelling(bestelling);
            bestellingArtikel.setPk(bestellingArtikelId);
            
            bestelling.getBestellingArtikellen().add(bestellingArtikel);
            
            System.out.println("Wilt u nog een artikel aan de bestelling toevoegen?\n1 - Ja\n2 - Nee");
            anotherOne = scanner.nextInt();
            
            if (anotherOne == 1){
                checker = true;
            }
            else {
                checker = false;
            }
 
        }while(checker);

        // DB insert
        
        Long bestellingId = (Long)bestellingDao.insert(bestelling);
        System.out.println("Uw bestelling is toegevoegd en met bestellingId: " + bestellingId);
        
    }
    
    public void haalBestellingInfoOp() {
        
        
        int zoekKeuze;
        
        zoekKeuze = bestellingView.hoeWiltUZoeken();
        
        if (zoekKeuze == 1){
        
        
        Long bestellingId;
        bestellingId = bestellingView.voerBestellingIdIn();
        
        Bestelling bestelling = (Bestelling) bestellingDao.readById(bestellingId);
        bestellingView.printBestellingInfo(bestelling);
        System.out.println("---");
        Set <BestellingArtikel> artikelLijst = bestelling.getBestellingArtikellen();
            System.out.println("Artikellen in Bestelling " + bestellingId + ":");
        for (BestellingArtikel BS: artikelLijst){
            System.out.println(BS.getArtikel().getId() + " - " + BS.getArtikel().getArtikelNaam() + ": " + BS.getArtikelAantal() + " keer");
        }
       
        
        }
        else if (zoekKeuze == 2){
        
        
        Long klantId;
        klantId = bestellingView.voerKlantIdIn();
        
        System.out.println("De bestellingen van Klant ID: " + klantId + " zijn:");
        Klant klant = (Klant) klantDao.readById(klantId);
        Set <Bestelling> bestellingen = klant.getBestellingen();
        for (Bestelling best: bestellingen){
            System.out.println("Bestelling ID: " + best.getId());
        }
        
        
       
        }
        else{
        
            System.out.println("U gaat terug naar BestellingMenu");    
            
        }
 
    }
    
    public void wijzigBestelling() {
        
        Bestelling bestelling;
        boolean checker = false;
        Long bestellingId;
       
        // Bestelling ophalen
        
        bestellingId = bestellingView.voerBestellingIdIn();
        
        bestelling = (Bestelling)bestellingDao.readById(bestellingId);
        bestellingView.printBestellingInfo(bestelling);
        
        // Artikellen in bestelling tonen
        Set <BestellingArtikel> artikelLijst = bestelling.getBestellingArtikellen();
            System.out.println("Artikellen in Bestelling " + bestellingId + ":");
        for (BestellingArtikel BS: artikelLijst){
            System.out.println(BS.getArtikel().getId() + " - " + BS.getArtikel().getArtikelNaam() + ": " + BS.getArtikelAantal() + " keer");
        }
        
       
        // keuze geven welk artikel aangepast moeten worden
        
        do {
        
        int artikelKeus;
        int verwijderWijzig;
            
        artikelKeus = bestellingView.voerArtikelId();
        verwijderWijzig = bestellingView.wijzigBestellingKeuze();
        
        if (verwijderWijzig == 1){
            // artikel uit bestelling verwijderen
            
           
            Bestelling wijzigBestelling;
            wijzigBestelling = (Bestelling) bestellingDao.readById(bestellingId);
            Set<BestellingArtikel>bestellingArtikellen;
            Set<BestellingArtikel>bestellingArtikellenClone = new HashSet<>();
            bestellingArtikellen = (Set<BestellingArtikel>) wijzigBestelling.getBestellingArtikellen();
            
            // 2 verschillende sets, 1 (bestellingArtikellen) om de te verwijderen bestellingartikellen 
            // toe te voegen aan de ander (bestellingArtikellenClone) en vervolgens ook uit de orignele set zelf
            for (BestellingArtikel BS: bestellingArtikellen){
                if(BS.getArtikel().getId() == artikelKeus){
                    bestellingArtikellenClone.add(BS);
                }
            }
            
            for (BestellingArtikel BSC: bestellingArtikellenClone){
               // deze delete methode is nog niet getest > niet eerder gebruikt
                bestellingArtikelDao.delete(BSC);
                bestellingArtikellen.remove(BSC);
            }

           
            
            // Extra check om te kijken of de bestelling leeg is, zoja word ie verwijderd
            if (bestellingArtikellen.isEmpty()){
                
                System.out.println("Bestelleng ID: " + bestellingId + " heeft geen artikellen meer, bestelling word verwijderd");
                bestellingDao.deleteById(bestellingId);
                
            }
                   
        }
        
        else if (verwijderWijzig == 2){
            // artikel aantal wijzigen
            
            int nieuwAantal;
          
            
            Bestelling wijzigBestelling;
            wijzigBestelling = (Bestelling) bestellingDao.readById( bestellingId);
            Set<BestellingArtikel>bestellingArtikellen;
            bestellingArtikellen = (Set<BestellingArtikel>) wijzigBestelling.getBestellingArtikellen();
            
            
            nieuwAantal = bestellingView.wijzigAantal();
            
            for (BestellingArtikel BS: bestellingArtikellen){
                if (BS.getArtikel().getId() == artikelKeus){
                    
                    BS.setArtikelAantal(nieuwAantal);
                    bestellingArtikelDao.update(BS);
                    
                    // nog een sout met nieuwe artikel info
                }
            }
            
        }
        
        checker = bestellingView.nogEenArtikelWijzigen();
        
        }while(checker);
        
        
        // Aantal wijzigen of verwijderen?
        
        // Optioneel: nog een artikel
        
        // Done
        
        
        
        
        
        
        
    }
    
    public void verwijderBestelling() {
        
       
        boolean deleted = false;
        Long bestellingId;
        
        ArrayList<Bestelling> bestellingLijst = (ArrayList<Bestelling>) bestellingDao.readAll(Bestelling.class);
        bestellingView.printBestellingLijst(bestellingLijst);
        System.out.println("Welke bestelling wilt u verwijderen?");
        bestellingId = bestellingView.voerBestellingIdIn();
        deleted = bestellingDao.deleteById(bestellingId);
        System.out.println("Verwijderprocess succes:" + deleted);
        
        System.out.println(bestellingId + " is verwijderd."); 
        
        bestellingMenu();        
    }
    

    public void toonAlleBestellingen() {
        ArrayList<Bestelling> bestellingLijst = (ArrayList<Bestelling>) bestellingDao.readAll(Bestelling.class);
        bestellingView.printBestellingLijst(bestellingLijst);
        
    }
    
    public void verwijderAlleBestellingen() {
        
      
        int verwijderConfirmatie = bestellingView.verwijderConfirmatie();
        
        if (verwijderConfirmatie == 1){
            
            bestellingDao.deleteAll(Bestelling.class);
            
            System.out.println("Alle bestellingen zijn verwijderd.\n");  
        }
        
        else {
            System.out.println("Verwijderen afgebroken.\n");
        }
        
    }
    
    // Optionele methoden
    
    public BestellingArtikel createBestellingArtikel(){
        
        long artikelId = bestellingView.voerArtikelIdIn();
        int artikelAantal = bestellingView.voerAantalIn();
                
        BestellingArtikel BS = new BestellingArtikel();
        // BS.setArtikelId(artikelId);
        BS.setArtikelAantal(artikelAantal);        
        
        return BS;
        
    }

*/    
  
}

