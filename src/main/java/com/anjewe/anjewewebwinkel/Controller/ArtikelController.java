
package com.anjewe.anjewewebwinkel.Controller;



import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoInterface;
import com.anjewe.anjewewebwinkel.DAOs.ArtikelDao;
import com.anjewe.anjewewebwinkel.Helpers.ApplicationContextConfig;
import com.anjewe.anjewewebwinkel.Helpers.HibernateSessionFactory;
import com.anjewe.anjewewebwinkel.View.ArtikelView;
import com.anjewe.anjewewebwinkel.POJO.Artikel;
import com.anjewe.anjewewebwinkel.POJO.Bestelling;
import com.anjewe.anjewewebwinkel.View.BestellingView;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArtikelController {  
    
    public ArtikelController (){
        
    }
    
    private static final Logger logger = (Logger) LoggerFactory.getLogger("com.webshop");
    private static final Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
    private static final Logger testLogger = (Logger) LoggerFactory.getLogger("com.webshop.test");
   
    
    @Autowired
    ArtikelView artikelView ;  
    @Autowired
    Artikel artikel;   
    @Autowired
    Artikel gewijzigdArtikel;
    @Autowired 
    BestellingView bestellingView;
    @Autowired
    GenericDaoImpl<Artikel, Long> artikelDao = new ArtikelDao();
    
    
    public void artikelMenu()  {
        
        int userInput = artikelView.startArtikelMenu();
        
        switch (userInput) {
            case 1: 
                artikel = createArtikel();
                voegNieuwArtikelToe(artikel); 
                break;
            case 2: 
                zoekArtikelGegevens();
                break;
            case 3:
                wijzigArtikelGegevens();
                break;
            case 4:
                verwijderArtikelGegevens();
                break;
            case 5:
                terugNaarHoofdMenu();
                break;
            default:
                System.out.println("Deze optie is niet beschikbaar.");
                break;
        }  
    }
    
    
    public Artikel createArtikel() {
        
        System.out.println("Vul de artikel gegevens in: ");
        String artikelNaam = artikelView.voerArtikelNaamIn();
        double artikelPrijs = artikelView.voerAtrikelPrijsIn();
        
        artikel.setArtikelNaam(artikelNaam);
        artikel.setArtikelPrijs(artikelPrijs);
        return artikel;
    } 
    
    
    public void voegNieuwArtikelToe(Artikel artikel) {  
        
        long id = artikelDao.insert(artikel);        
        artikel = (Artikel) artikelDao.readById(id);        
        System.out.println("U heeft de volgende gegevens ingevoerd: " );
        artikelView.printArtikelOverzicht(artikel);         
        
        artikelMenu();
    }
         
    
    public void zoekArtikelGegevens()  {    
		
        int input = artikelView.menuArtikelZoeken();
        switch (input){
                case 1:  // naar 1 artikel zoeken
                     long artikelId = artikelView.voerArtikelIdIn();                        
                        artikel = (Artikel) artikelDao.readById(artikelId);                        
                        artikelView.printArtikelOverzicht(artikel);                     
                    break; // einde naar 1 artikel zoeken
                case 2: // alle artikelen zoeken                    
                    ArrayList <Artikel> artikelenLijst = 
                            (ArrayList <Artikel>) artikelDao.readAll(Artikel.class);                    
                    System.out.println("Alle artikelen in het bestand");
                    artikelView.printArtikelenLijst(artikelenLijst); 
                        break; 
                case 3: // naar artikelmenu
                        break; 
                default: // automatisch naar artikelmenu	
                        break; 
        }	
        
     artikelMenu();
    }
    
    
    public void wijzigArtikelGegevens() {  
        
        boolean gewijzigd;        
        long artikelId = artikelView.voerArtikelIdIn();
        artikel = (Artikel) artikelDao.readById(artikelId);
        gewijzigdArtikel = invoerNieuweArtikelGegevens(artikel);  
        artikelDao.update(gewijzigdArtikel); 
    }
    
        
    public Artikel invoerNieuweArtikelGegevens(Artikel artikel) {
                
        int juist = 0;
        
        String artikelNaam = artikel.getArtikelNaam();
        juist = artikelView.checkInputString(artikelNaam);
            if (juist == 2) {
                artikelNaam = artikelView.voerArtikelNaamIn();
            }
        double artikelPrijs = artikel.getArtikelPrijs();
        String artikelPrijsString = artikelPrijs + "";
        juist = artikelView.checkInputString(artikelPrijsString);        
            if (juist == 2) {
                artikelPrijs = artikelView.voerAtrikelPrijsIn();
            }
        
        long artikelId = artikel.getId();
        Artikel artikelNieuw = new Artikel(artikelId, artikelNaam, artikelPrijs);
        
        return artikelNieuw;        
    }
    
    
    public void verwijderArtikelGegevens()  {
       
        int userInput = artikelView.printVerwijderMenu();
        switch (userInput) {
            case 1:// 1 artikel verwijderen  
                artikelView.printArtikelenLijst((ArrayList<Artikel>) artikelDao.readAll(Artikel.class));
                long artikelId = artikelView.printDeleteArtikelById();
                artikelDao.deleteById(artikelId);//             
            case 2:// alle artikelen verwijderen                
                int x = artikelView.bevestigingsVraag();                
                    if (x == 1){ // bevestiging is ja
                        int verwijderd = artikelDao.deleteAll(Artikel.class);                    
                        System.out.println(verwijderd + " totaal aantal artikelen zijn verwijderd");                       
                    }                
                    else { // bevestiging = nee
                        System.out.println("De artikel gegevens worden NIET verwijderd.");
                    }
                break;                
            case 3:// door naar einde methode > naar artikelmenu();
                break;
            default:
                break;
        }
        artikelMenu();
    }
    
    
    public void terugNaarHoofdMenu() {
        HoofdMenuController hoofdMenu = new HoofdMenuController();
        hoofdMenu.start();
    }   
    
} // eind artikelcontroller
