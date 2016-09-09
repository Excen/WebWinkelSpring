/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.View;

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
public class HoofdMenuView {
    private final static Logger LOGGER = LoggerFactory.getLogger(HoofdMenuView.class.getName());
    int userInput;
    Scanner scanner = new Scanner(System.in);
    boolean checker;
    
            
            
    public int hoofdMenu(){
        
        System.out.println("Wat wilt u doen? ");
        System.out.println("1. Werken in klantbestand");
        System.out.println("2. Werken in artikelbestand");
        System.out.println("3. Werken in bestellingbestand");
        System.out.println("4. Werken in adresbestand");
        System.out.println("5. Werken in factuurbestand");
        System.out.println("6. Werken in betalingbestand");
        System.out.println("7. Werken in accountbestand");
        System.out.println("8. Programma afsluiten");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine()); 
            
        }catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }
    
    
    public int bevestigingsVraag(){
        
        boolean doorgaan = true; 
        
        System.out.println("Weet u zeker dat u af wilt sluiten?");
        System.out.println("1. ja");
        System.out.println("2. nee");       
        
        do{              
            try{
                userInput = Integer.parseInt(scanner.nextLine());  
                //userInput = scanner.nextInt();
                if (userInput == 1 || userInput == 2){
                    if (userInput == 1){
                        System.out.println("Het programma zal sluiten");
                    }
                    else
                        System.out.println("U gaat terug naar het hoofdmenu");
                    
                    doorgaan = false;
                }
                else {   
                    System.out.println("Foute input, kies van de opties hierboven.");
                    System.out.println();
                    scanner.nextLine();
                }                
            }
            catch(InputMismatchException ex){
                LOGGER.warn("Foute input, kies van de opties hierboven.");
                System.out.println();
                scanner.nextLine();
            }
        
        } while(doorgaan == true);
        
       return userInput;        
    }
    
}// eind  hoofdmenucontroller klasse   