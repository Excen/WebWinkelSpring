


package com.anjewe.anjewewebwinkel.View;

import com.anjewe.anjewewebwinkel.POJO.Betaalwijze;
import com.anjewe.anjewewebwinkel.POJO.Betaling;
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
public class BetalingView {

private static final Logger log = LoggerFactory.getLogger(BetalingView.class);
Scanner scanner = new Scanner (System.in);

String gegevens; 
int userInput; 
long betalingId; 

    public int startMenuBetaling() {
          int userInput = 0; 
        
        System.out.println();        
        System.out.println("Maak uw keuze:");
        System.out.println("1. Nieuwe Betaling toevoegen.");        
        System.out.println("2. Betalingsgegevens opzoeken.");
        System.out.println("3. Betalingsgegevens wijzigen.");
        System.out.println("4. Betalingsgegevens verwijderen.");        
        System.out.println("5. Terug naar hoofdmenu");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            
            
        }
        catch(InputMismatchException ex){
            log.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;      }

     public Long voerBetalingIdIn() {
        System.out.println("Betaling id: ");
        Long betalingId = Long.parseLong(scanner.nextLine());
        return betalingId;
    }
    
    public int menuBetalingZoeken(){
        
        System.out.println("Wat wilt u zoeken in het betalingenbestand?");
        System.out.println("1. Één betaling opzoeken.");
        System.out.println("2. Alle betalingen opzoeken.");
        System.out.println("3. Terug naar betaling menu.");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            

        }
        catch(InputMismatchException ex){
            log.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }

    

     public void printBetalingOverzicht(Betaling betaling) {
        System.out.println("De betaling heeft de volgende gegevens:");
        System.out.println("Betaling id: " + betaling.getId());
        System.out.println("Betaaldatum: " + betaling.getBetaaldatum());
        System.out.println("Betaalwijze: " + betaling.getBetaalwijze());
        System.out.println("Aanvullende betaalgegevens: " + betaling.getBetalingsGegevens());
        System.out.println("Gekoppeld factuurId: " + betaling.getFactuur().getId());
        System.out.println("Gekoppeld klantId: " + betaling.getKlant().getId());
        System.out.println();
    }

      public void printBetalingLijst(ArrayList<Betaling> betalingenLijst) {
        System.out.println();
        System.out.println("Lijst met betaling");
        System.out.printf("%-10s%-28s%-12s%-12s%-12s%-18s%n","BetalingId",
                "Betaalwijze", "Betaaldatum", "FactuurId", "KlantId", "Betaalgegevens");
        
        for (int i = 0; i< betalingenLijst.size(); i++){

            System.out.printf ("%-10d%-28s%-12s%-12d%-12d%-18s%n",
                (betalingenLijst.get(i)).getId(),
                (betalingenLijst.get(i)).getBetaalwijze(),
                (betalingenLijst.get(i)).getBetaaldatum(),
                ((betalingenLijst.get(i)).getFactuur()).getId(),
                ((betalingenLijst.get(i)).getKlant()).getId(),
                (betalingenLijst.get(i)).getBetalingsGegevens()
            );            
           
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
    
    public int voerBetaalwijzeIn() {
        
        Betaalwijze betaalwijze; 
        int bw; 
        System.out.println("Wat is de nieuwe betaalwijze?");
        
        bw = kiesBetaalWijze();         
        
        return bw;     
    }
    
      public Long voerFactuurIdIn() {
        
        Long factuurId; 
        System.out.println("Wat is het factuurId: ");
        factuurId = Long.parseLong(scanner.nextLine());
        return factuurId;
    }  
    
    
    public int printVerwijderMenu() {
        
        System.out.println("Wat wilt u verwijderen uit het betalingbestand?");
        System.out.println("1. één betaling.");
        System.out.println("2. alle betalingen.");
        System.out.println("3. terug naar het betaling menu.");
        
        try{
            userInput = scanner.nextInt();  
            scanner.nextLine();
        }
        catch(InputMismatchException ex){
            log.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }
    
    
   public Long printDeleteBetalingById() {
       
        System.out.println("Voer het betaling id in van het artikel dat u wilt verwijderen.");
        
        try{
            betalingId = scanner.nextLong();  
            scanner.nextLine();
        }
        catch(InputMismatchException ex){
            log.warn("Foute input, voer het artikel id in.");
        }
        
        return betalingId;
    }
    
      
    
       public int bevestigingsVraag(){
        
        System.out.println("Wilt u alle betalingen definitief verwijderen?");
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

    public String voegBetalingsGevensToe() {
        System.out.println("u kunt hier betaalgegevens toevoegen: ");
        gegevens = scanner.nextLine();        
                
            return gegevens; 
    }

    public String aanpassenBetalingsGegevens(String betalingsGegevens) {
        System.out.println("u kunt hier betaalgegevens aanpassen: ");
        System.out.println("huidige gegevens zijn: " + betalingsGegevens);
        System.out.println("nieuwe betaalgegevens invoeren: ");
        gegevens = scanner.nextLine();
        
                
                return gegevens; 
                

    }

    public int kiesBetaalWijze() {
        System.out.println("u gaat een betaalwijze kiezen: ");    
        
        System.out.println();        
        System.out.println("Maak uw keuze:");
        System.out.println("1. GoogleWallet");        
        System.out.println("2. Ideal");
        System.out.println("3. Paypal");
        System.out.println("4. Moneybookers.");        
        System.out.println("5. Creditcard");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            
            
        }
        catch(InputMismatchException ex){
            log.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
        
        
        
        
    }
}
