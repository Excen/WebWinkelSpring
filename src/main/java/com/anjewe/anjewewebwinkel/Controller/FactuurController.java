


package com.anjewe.anjewewebwinkel.Controller;

import com.anjewe.anjewewebwinkel.POJO.Bestelling;
import com.anjewe.anjewewebwinkel.POJO.BestellingArtikel;
import com.anjewe.anjewewebwinkel.POJO.Factuur;
import com.anjewe.anjewewebwinkel.Service.GenericServiceInterface;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author Wendy
 */
@Controller
@RequestMapping({"/", "/factuur"}) // wat doet dit specifiek?
@SessionAttributes()
public class FactuurController {

private static final Logger log = LoggerFactory.getLogger(FactuurController.class);

GenericServiceInterface <Factuur, Long> factuurService; 
GenericServiceInterface <Bestelling, Long> bestellingService; 

@Autowired
Bestelling bestelling;
@Autowired
Factuur factuur; 

@RequestMapping (value = "/", method = GET)
public String factuur(){
    return "factuur";
}


 public double berekenTotaalBedrag(Factuur factuur){
        
         double totaalBedrag = 0.0;
         long bestellingId = factuur.getBestelling().getId();
           
         bestelling = (Bestelling) bestellingService.zoekNaarBean(bestellingId);
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
    



}
