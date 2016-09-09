/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.View;

import com.anjewe.anjewewebwinkel.POJO.Adres;
import com.anjewe.anjewewebwinkel.POJO.KlantAdres;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anne
 */
@Component
public class AdresView {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(AdresView.class.getName());
    int userInput;
    Scanner scanner = new Scanner(System.in);
    
    public int startAdresMenu() {
        System.out.println();
        System.out.println("Maak uw keuze: ");
        System.out.println("1. Nieuw adres toevoegen. ");
        System.out.println("2. Adresgegevens opzoeken.");
        System.out.println("3. Adresgegevens wijzigen.");
        System.out.println("4. Adresgegevens verwijderen.");
        System.out.println("5. Adres, klantkoppelingen zoeken");
        System.out.println("6. Terug naar het hoofdmenu.");
        
        try {
            userInput = Integer.parseInt(scanner.nextLine());
        }
        catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        return userInput;
        
    }
    
    public int bentUNieuweKlant() {
        System.out.println("Bent u een nieuwe klant?");
        System.out.println("1. ja.");
        System.out.println("2. nee");
        
        try {
             userInput = Integer.parseInt(scanner.nextLine());
        }
        catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        return userInput;
    }
    
     public Long voerKlantIdIn() {
        System.out.println("Klant id: ");
        Long klantId = Long.parseLong(scanner.nextLine());
        return klantId;
    }
    
     public Long voerAdresIdIn() {
        System.out.println("Adres id: ");
        Long adresId = Long.parseLong(scanner.nextLine());
        return adresId;
    }
    
    public String voerStraatnaamIn() {
        System.out.println("Straatnaam: ");
        String straatnaam = scanner.nextLine();
        return straatnaam;
    }
    
    public int voerHuisnummerIn() {
        System.out.println("Huisnummer: ");
        int huisnummer = Integer.parseInt(scanner.nextLine());
        return huisnummer;
    }
    public String voerToevoegingIn() {
        String toevoeging;
        
        System.out.println("Huisnummer toevoeging: ");
        toevoeging = scanner.nextLine().trim(); 
        return toevoeging;
    }
    
    public String voerPostcodeIn() {
        System.out.println("Postcode: ");
        String postcode = scanner.nextLine();
        return postcode;
    }
    public String voerWoonplaatsIn() {
        System.out.println("Woonplaats: ");
        String woonplaats = scanner.nextLine();
        return woonplaats;
    }
    
    public void printAdresOverzicht(Adres adres) {
        System.out.println("Het adres heeft de volgende gegevens:");
        System.out.println("Adres id: " + adres.getId());
        System.out.println("Straatnaam: " + adres.getStraatnaam());
        System.out.println("Huisnummer: " + adres.getHuisnummer());
        System.out.println("Toevoeging: " + adres.getToevoeging());
        System.out.println("Postcode: " + adres.getPostcode());
        System.out.println("Woonplaats: " + adres.getWoonplaats());
        System.out.println();
    }
    
    public int menuAdresZoeken(){
        
        System.out.println("Wat wilt u zoeken in het adressenbestand?");
        System.out.println("1. Één adres opzoeken.");
        System.out.println("2. Alle adressen opzoeken.");
        System.out.println("3. Terug naar adres menu.");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            

        }
        catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }
    
     public int menuAdresKlantZoeken(){
        
        System.out.println("Wat wilt u zoeken in het adres-klantbestand?");
        System.out.println("1. Adres(sen) bij klant Id opzoeken.");
        System.out.println("2. Alle adressen bij klanten opzoeken.");
        System.out.println("3. Terug naar adres menu.");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            

        }
        catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }
    
    
    public int hoeWiltUZoeken() {
        System.out.println("Kies met wat u wilt zoeken.");
        System.out.println("1. Adres id.");
        System.out.println("2. Klant id");
        System.out.println("3. Straatnaam.");
        System.out.println("4. Postcode en huisnummer");
        System.out.println("5. Woonplaats.");
        System.out.println("6. Terug naar adres menu.");
        
        try {
            userInput = Integer.parseInt(scanner.nextLine());
        }
        catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        return userInput;
    }
    
    public void printAdressenLijst(ArrayList<Adres> adressenLijst) {
        System.out.println();
        System.out.println("Lijst met adressen");
        System.out.printf("%-10s%-28s%-12s%-12s%-12s%-18s%n","AdresId", "Straatnaam", "Huisnummer", "Toevoeging", "Postcode", "Woonplaats");
        
        for (int i = 0; i< adressenLijst.size(); i++){

            System.out.printf ("%-10s%-28s%-12s%-12s%-12s%-18s%n",(adressenLijst.get(i)).getId(),
            (adressenLijst.get(i)).getStraatnaam(),(adressenLijst.get(i)).getHuisnummer(),
            (adressenLijst.get(i)).getToevoeging(),(adressenLijst.get(i)).getPostcode(),
            (adressenLijst.get(i)).getWoonplaats());            
           
        }
    }
    
    
    public void printKlantAdresLijst(ArrayList<KlantAdres> klantAdresLijst){
        System.out.println();
        System.out.println("AdresId\t\tKlantId");
        for (int i = 0; i < klantAdresLijst.size(); i++){
            
            System.out.println(klantAdresLijst.toString());
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
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        return userInput;
    }
    
    public int printVerwijderAdresMenu() {
        System.out.println("Wat wilt u verwijderen uit het adressenbestand?");
        System.out.println("1. één adres.");
        System.out.println("2. alle adressen.");
        System.out.println("3. terug naar het adres menu.");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            
        }
        catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }
    
    public int alleKoppellingenUitgeprint(){
        System.out.println("Wilt u alle adres-klant koppelingen uitgeprint hebben?");
        System.out.println("1. ja");
        System.out.println("2. nee");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());        
        }
        catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }
    
    
    public int bevestigingsVraag(){
        
        System.out.println("Wilt u alle adressen definitief verwijderen?");
        System.out.println("1. ja");
        System.out.println("2. nee");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());        
        }
        catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
        
    }
        
}
