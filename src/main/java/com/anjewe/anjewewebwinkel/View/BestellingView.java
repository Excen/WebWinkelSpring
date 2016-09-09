/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.View;

import com.anjewe.anjewewebwinkel.POJO.Artikel;
import com.anjewe.anjewewebwinkel.POJO.Bestelling;
import com.anjewe.anjewewebwinkel.POJO.BestellingArtikel;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Excen
 */
@Component
public class BestellingView {
    
    
    private final static Logger LOGGER = LoggerFactory.getLogger(KlantView.class.getName());
    // data fields
    Scanner scanner = new Scanner(System.in);
    int userInput;
    long artikelId;
    long bestellingId;
    int artikelAantal;
    
    
    public int startBestellingMenu(){
        
        try{
            System.out.println("Maak een keuze:");
            System.out.println("1 bestelling plaatsen");
            System.out.println("2 bestellingsinformatie ophalen");
            System.out.println("3 bestelling wijzigen");
            System.out.println("4 bestelling verwijderen");
            System.out.println("5 toon alle bestellingen");
            System.out.println("6 verwijder alle bestellingen");
            System.out.println("7 terug naar hoofdmenu");       

            userInput = Integer.parseInt(scanner.nextLine());
            
        } catch (InputMismatchException ex){
            System.out.print("Foute input, kies één van de opties hierboven.");
        }    

    return userInput;
    }
    
    
    public long voerKlantIdIn (){
        
        System.out.println("U wilt een nieuwe bestelling aanmaken.");
        System.out.println("Wat is uw klant ID?");
        long klantId = Long.parseLong(scanner.nextLine());
        return klantId;
    }

    
    public long voerBestellingIdIn(){
        
        System.out.println("Wat is het bestelling ID?");        
        
        try{
            bestellingId = Long.parseLong(scanner.nextLine());         
        } catch (InputMismatchException ex){
            System.out.println("Vul een getal in.");
        }
        
        return bestellingId;
    }
    
    
    public long voerArtikelIdIn(){
        
        System.out.println("Welk artikel wilt u toevoegen aan uw bestelling?");
        artikelId = Long.parseLong(scanner.nextLine());
        
        return artikelId;
    }
    
    public int voerArtikelId(){
        
        int artikelId = 1;
        
        System.out.println("Welk artikel wilt u wijzigen?");
        try{
            artikelId = scanner.nextInt();
        }catch(InputMismatchException ex){
            System.out.println("Voer een integer in.");
        }
        
        
        return artikelId;
        
    }
    
    public int voerAantalIn(){
        
        System.out.println("Hoe vaak wil je dit artikel toevoegen?");
        artikelAantal = Integer.parseInt(scanner.nextLine());
        
        return artikelAantal;
    }
    
    
    public long wijzigBestellingInfo(ArrayList<Artikel>artikelLijst, int bestellingId){
        
        artikelId = 0;        
        
        System.out.println("Artikellen aanwezig in bestelling " + bestellingId);
        
        for (Artikel ar: artikelLijst){
            System.out.println(ar.toString());
        } 
        
        System.out.println("Welk artikel ID wil je wijzigen?");
        try{
            artikelId = Long.parseLong(scanner.nextLine());  
        } catch (InputMismatchException ex){
            System.out.println("Voer een getal in.");
        }

        return artikelId;

    }
    
    public int wijzigBestellingKeuze (){
        
        System.out.println("Wat wil je doen met dit artikel?\n1. Verwijderen\n2. Aantal wijzigen");
        userInput = 0;
        
        try{
            userInput = scanner.nextInt();
        } catch (InputMismatchException ex){
            System.out.println("Vul een getal in.");
        }

        return userInput;
        
    }
    
    public int wijzigAantal() {
        
        System.out.println("Wat moet het nieuwe aantal worden?");
        artikelAantal = 0;
        
        try{
        artikelAantal = scanner.nextInt();
        } catch (InputMismatchException ex){
            System.out.println("Vul een getal in.");
        }

        return artikelAantal;
        
    }
    
    public void printBestellingLijst(ArrayList<Bestelling>lijst){      
       
        System.out.println("Aanwezige bestellingen: ");
        for (Bestelling best: lijst){            
            System.out.println(best.toString());
        }

    }
    
//    public void printBestellingLijstUitgebreid(ArrayList<Bestelling>lijst){
//        ArrayList<Artikel>artikellijst = new ArrayList<>();
//        for (Bestelling best: lijst){
//            System.out.println("Aanwezige bestelling voor klant ID: " + best.getKlant().getId()) 
//                    + " met bestelling ID: " + best.getId());         
//        
//                artikellijst = bestellingArtikelDAO.findByBestellingId(best.getId());
//                System.out.println("Artikellen in bestelling: ");
//                    for (Artikel artikel: artikellijst){
//                        System.out.println(artikel.getArtikelNaam() + ": " 
//                                + bestellingArtikelDAO.findAantalByArtikelID(best.getId(),
//                                        artikel.getArtikelId()) + " keer");
//                        System.out.println();
//                    }
//        }
//        
//    }
//    
    public void printBestellingInfo(Bestelling bestelling){
            
            System.out.println("Bestelling ID: " + bestelling.getId());
            System.out.println("Klant ID: " + (bestelling.getKlant()).getId());
            System.out.println("Bestelling Datum: " + bestelling.getDatum());
            
        }
    
    public int verwijderConfirmatie(){
        int userChoice = 0;
        boolean checker = false;
        
        System.out.println("Weet je zeker dat je alles wilt verwijderen?\n1 ja\n2 nee");
        do{
            
        
        try{
           userChoice = scanner.nextInt();
           checker = true; 
           
        } catch (InputMismatchException ex){
           System.out.println("Voer een integer in.");
           checker = false;
        }
        } while (checker == false);
        return userChoice;
    }
    
     public int hoeWiltUZoeken(){
        
        System.out.println("Hoe wilt u zoeken?");
        System.out.println("1. Zoek met bestelling ID");
        System.out.println("2. Zoek met klant ID");
        System.out.println("3. Terug naar bestellingmenu");
        System.out.println("Maak een keuze:");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());    
        //scanner.nextLine();

        //checker = false;
        
        } catch (InputMismatchException ex){
            LOGGER.warn("Foutieve input, kies uit de opties 1,2,3.");
            //scanner.nextLine();
        }
         
       // } while (checker);
        
        return userInput;
        
    }
     
    public void printArtikelLijst(ArrayList<Artikel>artikelLijst){
        for (Artikel ar: artikelLijst){
            System.out.println(ar.getId() + " - " + ar.getArtikelNaam() + ": €" + ar.getArtikelPrijs());
        }
    } 
    
    public boolean nogEenArtikelWijzigen(){
        boolean checker = false;
        int userInput = 1;
        
        
        System.out.println("Wil je nog een artikel wijzigen?\n1 - Ja\n2 - Nee");
        try{
            userInput = scanner.nextInt();
        }catch(InputMismatchException ex){
            System.out.println("Voer een integer in");
        }
        
        if (userInput == 1){
            checker = true;
        }
        else if (userInput != 1){
            checker = false;
        }
        
        return checker;
    }
    
    
}
