


package com.anjewe.anjewewebwinkel.View;

import com.anjewe.anjewewebwinkel.Controller.FactuurController;
import com.anjewe.anjewewebwinkel.POJO.Factuur;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Wendy
 */
@Component
public class FactuurView {
    
FactuurController factuurController;    

private static final Logger log = LoggerFactory.getLogger(FactuurView.class);

Scanner scanner = new Scanner(System.in);
int userInput; 
Long factuurId;
   

    public int startMenuFactuur() {        
        
        System.out.println();        
        System.out.println("Maak uw keuze:");
        System.out.println("1. Nieuwe factuur toevoegen.");        
        System.out.println("2. Factuurgegevens opzoeken.");
        System.out.println("3. Factuurgegevens wijzigen.");
        System.out.println("4. Factuurgegevens verwijderen.");  
        System.out.println("5. Voeg betaling toe aan factuur");
        System.out.println("6. Terug naar hoofdmenu");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            
            
        }
        catch(InputMismatchException ex){
            log.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;        
    
    }

 public String voerFactuurNummerIn() {
        String factuurnummer; 
        System.out.println("Wat is het factuurnummmer: ");
        factuurnummer = scanner.nextLine();
        return factuurnummer;
    }
 
    public int menuFactuurZoeken() {        
        
        System.out.println("Wat wilt u zoeken in het factuurbestand?");
        System.out.println("1. Één factuur opzoeken.");
        System.out.println("2. Alle facturen opzoeken.");
        System.out.println("3. Terug naar factuur menu.");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            

        }
        catch(InputMismatchException ex){
           log.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }

    public Long voerFactuurIdIn() {
        
        Long factuurId; 
        System.out.println("Wat is het factuurId: ");
        factuurId = Long.parseLong(scanner.nextLine());
        return factuurId;
    }

    public void printFactuurOverzicht(Factuur factuur, double totaalBedrag) {
        
        System.out.println(factuur.toString());
        System.out.println("totaal bedrag van de factuur  is €");
        System.out.println(totaalBedrag);
    }

    public void printFacturenLijst(ArrayList<Factuur> facturenLijst) {
        
        for(Factuur factuur : facturenLijst){
            System.out.println(factuur.toString());
            System.out.println("totaal bedrag van de factuur  is €");
            //System.out.println(factuurController.berekenTotaalBedrag(factuur)); 
                    }
    
    }

     public int checkInputString(String input) {
         
        System.out.println("Is het volgende gegeven juist: " + input + " ?");
        System.out.println("1. Ja.");
        System.out.println("2. Nee.");
        
        try {
            userInput = Integer.parseInt(scanner.nextLine());
        }
        catch(InputMismatchException ex){
            log.warn("Foute input, kies van de opties hierboven.");
        }
        return userInput;
    }

    
    public long voerKlantIdIn() {
        
        Long klantId; 
        System.out.println("Wat is het klantId: ");
        klantId = Long.parseLong(scanner.nextLine());
        return klantId;
    }

    public long voerBestellingIdIn() {
        
        Long Id; 
        System.out.println("Wat is het bestellingId: ");
        Id = Long.parseLong(scanner.nextLine());
        return Id;
    }

    
       public int printVerwijderMenu() {           
        
        System.out.println("Wat wilt u verwijderen uit het factuurbestand?");
        System.out.println("1. één factuur.");
        System.out.println("2. alle facturen.");
        System.out.println("3. terug naar het factuurmenu.");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            
        }
        catch(InputMismatchException ex){
            log.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }

    public int bevestigingsVraag(){
        
        System.out.println("Wilt u alle facturen definitief verwijderen?");
        System.out.println("1. ja");
        System.out.println("2. nee");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());        
        }
        catch(InputMismatchException ex){
            log.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
        
    } 

    public long printDeleteFactuurById() {
        
        System.out.println("Voer het factuur id in van het artikel dat u wilt verwijderen.");
        
        try{
            factuurId = scanner.nextLong();  
            scanner.nextLine();
        }
        catch(InputMismatchException ex){
            log.warn("Foute input, voer het artikel id in.");
        }
        
        return factuurId;
    }

   


}
