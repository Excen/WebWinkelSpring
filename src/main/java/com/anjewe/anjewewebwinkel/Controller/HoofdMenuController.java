/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.Controller;




import com.anjewe.anjewewebwinkel.Helpers.HibernateSessionFactory;
import com.anjewe.anjewewebwinkel.View.HoofdMenuView;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 *
 * @author Excen
 */
@Component
public class HoofdMenuController {
   
public HoofdMenuController(){
    
}

// data fields 
    private static final Logger logger = (Logger) LoggerFactory.getLogger("com.webshop");
    private static final Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
    private static final Logger testLogger = (Logger) LoggerFactory.getLogger("com.webshop.test");
   
   @Autowired 
   KlantController klantController; 
   @Autowired
   ArtikelController artikelController;
   @Autowired
   BestellingController bestellingController;
   @Autowired
   AdresController adresController;
   @Autowired
   AccountController accountController; 
   @Autowired
   BetalingController betalingController; 
   @Autowired
   FactuurController factuurController; 
   @Autowired
   HoofdMenuView hoofdMenuView; 
      
   Scanner scanner = new Scanner(System.in);
   int userInput;
   
   

    // beginpunt
    public void start()  {  
                
        userInput = hoofdMenuView.hoofdMenu();
        
            switch (userInput) {
                case 1: // ga naar klantMenu
                    klantController.klantMenu();
                    break;
                case 2: // ga naar artikelMenu
                    artikelController.artikelMenu();                    
                    break;
                case 3: // ga naar bestellingmenu
                    bestellingController.bestellingMenu();                                 
                    break;
                case 4:// ga naar adresmenu
                    adresController.adresMenu();                
                    break;
                case 5: // ga naar factuurmenu
                    factuurController.factuurMenu();                    
                    break;
                case 6: 
                    betalingController.betalingMenu();
                    break;
                case 7:
                    accountController.accountMenu();
                    break; 
//                case 8:    
//                    afsluiten();
//                    break;
                default:
                    break;
            }
        } 
    
   
    
//    public void afsluiten(){
//       userInput = hoofdMenuView.bevestigingsVraag();
//       
//       if (userInput == 1){ // wel afsluiten
//           HibernateSessionFactory.shutdown();
//           System.exit(0);    
//       }
//       else if (userInput == 2){ // niet afsluiten
//           userInput = hoofdMenuView.hoofdMenu();
//       }    
//
//       
//        
//    }
}
