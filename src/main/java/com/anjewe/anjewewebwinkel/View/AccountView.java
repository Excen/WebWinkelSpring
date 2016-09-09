


package com.anjewe.anjewewebwinkel.View;

import com.anjewe.anjewewebwinkel.POJO.Account;
import com.anjewe.anjewewebwinkel.POJO.Klant;
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
public class AccountView {

private static final Logger log = LoggerFactory.getLogger(AccountView.class);
Scanner scanner = new Scanner(System.in);
int userInput;

    public int startMenuAccount() {
         int userInput = 0; 
        
        System.out.println();        
        System.out.println("Maak uw keuze:");
        System.out.println("1. Nieuw Account toevoegen.");        
        System.out.println("2. Accountgegevens opzoeken.");
        System.out.println("3. Accountgegevens wijzigen.");
        System.out.println("4. Accountgegevens verwijderen.");        
        System.out.println("5. Terug naar hoofdmenu");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            
            
        }
        catch(InputMismatchException ex){
            log.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;      
    }
    
    public long voerAccountIdIn() {
        System.out.println("Voer het accountId in.");
        Long userInput = scanner.nextLong();
        scanner.nextLine();
        return userInput;
    }
   
    public String voerGebruikersnaamIn() {
        
        String gebruikersnaam;  
        System.out.println("Kies een geruikersnaam: ");
        gebruikersnaam = scanner.nextLine();
        return gebruikersnaam;
    }

    public String voerPasswordIn() {
    
           String password;
           System.out.println("kies een password: ");
           password = scanner.nextLine();
           return password; 
           
    }
    public int menuAccountZoeken() {
        
        System.out.println("Wat wilt u zoeken in het accountbestand?");
        System.out.println("1. naar één account.");
        System.out.println("2. naar alle accounts.");
        System.out.println("3. terug naar het menu Account.");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            

        }
        catch(InputMismatchException ex){
            log.warn("Foute input, kies van de opties hierboven.");
        }
        return userInput;
    }
    
    public void printAccountGegevens(Account account) {
        System.out.println("De huidige gegevens van het account met accountId: " + account.getId());
        System.out.println("Username: " + account.getUsername());
        System.out.println("Naam: " + account.getKlant().getVoornaam() + " " + 
                account.getKlant().getTussenvoegsel() + " " + account.getKlant().getAchternaam());
        System.out.println("Emailadres " + account.getKlant().getEmail());
        
    }
    
    public void printAccountenLijst(ArrayList<Account> lijst){
        System.out.println();
        System.out.println("Lijst met opgevraagde accounts");
       
        for(Account account: lijst){
            System.out.println("Username: " + account.getUsername());
            System.out.println("Naam: " + account.getKlant().getVoornaam() + " " + 
                account.getKlant().getTussenvoegsel() + " " + account.getKlant().getAchternaam());
        }
    }
    
     public int checkInputString(String string){
                
        System.out.println(string + ". Is dit juist? : " );
        System.out.println("1. ja");
        System.out.println("2. nee");
        
        // make do while loop
        try{
            userInput = Integer.parseInt(scanner.nextLine());            


            if (userInput == 1 || userInput == 2){
                //scanner.nextLine();
            }
            else {
                System.out.println("Foutieve input, voer 1 of 2 in.");
            } 
        }
        catch(InputMismatchException ex){
            log.warn("", ex);
        }
        
        return userInput; 
        
     }
     
    public int menuAccountVerwijderen() {
        System.out.println("Maak uw keuze: ");
        System.out.println("1. Één account verwijderen.");
        System.out.println("2. Alle accounts verwijderen.");
        System.out.println("3. Terug naar klantmenu.");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            

        }
        catch(InputMismatchException ex){
            log.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }
    
    public int bevestigingsVraag(){
        
        System.out.println("Weet u zeker dat u alle accountgegevens definitief verwijderen wil?");
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
     
}

