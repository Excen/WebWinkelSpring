


package com.anjewe.anjewewebwinkel.Controller;

import com.anjewe.anjewewebwinkel.DAOGenerics.FactoryDao;
import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoInterface;
import com.anjewe.anjewewebwinkel.DAOs.AccountDao;
import com.anjewe.anjewewebwinkel.DAOs.KlantDao;
import com.anjewe.anjewewebwinkel.POJO.*;
import com.anjewe.anjewewebwinkel.View.*;
import java.util.ArrayList;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Wendy
 */

@Component
public class AccountController {

private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    
    public AccountController(){        
    }
    
//    @Autowired
//    FactoryDao factoryDao;
    
    @Autowired
    GenericDaoImpl<Account, Long> accountDao;  
    @Autowired
    GenericDaoImpl<Klant, Long> klantDao;   
    
    @Autowired
    KlantController klantController; 
    @Autowired
    HoofdMenuController hoofdMenuController; 
    @Autowired
    AccountView accountView;  
    @Autowired
    KlantView klantView;
    @Autowired
    Account account;
    @Autowired
    Account gewijzigdAccount; 
    @Autowired 
    Klant klant;
    @Autowired
    Klant gewijzigdeKlant;
    
    
    
    public void accountMenu() {
        
        int keuze = accountView.startMenuAccount();
        
        switch(keuze){
            case 1:                 
                voegNieuwAccountToe();
                break;
            case 2:
                zoekAccountGegevens();
                break;  
            case 3:
                wijzigAccountGegevens();
                break;
            case 4: 
                verwijderAccountGegevens();
                break; 
            case 5:
                terugNaarHoofdMenu();
                break;
            
            default: 
                System.out.println("Deze optie is niet beschikbaar.");
                break;            
            
        }                
    }
        
    
    public Account createAccount(){
           
        Date creatieDatum;         
        String gebruikersnaam = accountView.voerGebruikersnaamIn();
        String password = accountView.voerPasswordIn();
        
        //long klantId = klantView.voerKlantIdIn();
        //long klantId = klantController.voegNieuweKlantToe();
        // Klant klant = (Klant) session.get(Klant.class, klantId);   
       
        klant = klantController.createKlant();
        long klantId = (Long)klantDao.insert(klant);
        
        account.setUsername(gebruikersnaam);
        account.setPassword(password);
        account.setCreatieDatum(new Date());
        account.setKlant(klant);
//        factuur.setBestelling(bestelling);  
        return account;  
    }
    
    public long voegNieuwAccountToe(){
        
            System.out.println("U gaat een klant toevoegen. Voer de gegevens in.");
            account = createAccount(); 
            Long accountId = (Long)accountDao.insert(account);  

            System.out.println("U heeft de factuurgegevens toegevoegd met factuurId: " 
                + accountId); 
            System.out.println();        

            accountMenu();
        
        return accountId;
    }
    
    
    private void zoekAccountGegevens() {       
        
        int userInput = accountView.menuAccountZoeken();
        switch(userInput) {
            case 1: 
                // zoeken naar een account
                 long accountId = accountView.voerAccountIdIn();
                 account = (Account)accountDao.readById(accountId);  
                 accountView.printAccountGegevens(account);                
                 break;
            case 2:
                // zoeken naar alle gegevens
                ArrayList <Account> accountenLijst = (ArrayList<Account>) accountDao.readAll(Account.class);                
                if (accountenLijst != null){
                    System.out.println("Alle klanten in het bestand");
                    accountView.printAccountenLijst(accountenLijst);   
                }
                break;
        }
        accountMenu();
    }       

    private void wijzigAccountGegevens() {
        
        long accountId = accountView.voerAccountIdIn();
        account = (Account)accountDao.readById(accountId);
        gewijzigdAccount = voerNieuweAccountGegevensIn(account);
        accountDao.update(gewijzigdAccount);        
        
        gewijzigdAccount = (Account)accountDao.readById(accountId);
        System.out.println("Nieuwe accountgegevens");
        accountView.printAccountGegevens(gewijzigdAccount);
        
        accountMenu();
    }

    private void verwijderAccountGegevens() {
        boolean deleted = false;
        
        int userInput = accountView.menuAccountVerwijderen();
        switch (userInput) {
            case 1: 
                // een account verwijderen
                long accountId = accountView.voerAccountIdIn();
                deleted = accountDao.deleteById(accountId);               
                System.out.println("Verwijderen van account: " + deleted);
                break;
            case 2:
                // verwijderen alle klanten
                int keuze = accountView.bevestigingsVraag(); 
                if (keuze == 1) {                    
                    int rowsAffected = accountDao.deleteAll(Account.class);                    
                    System.out.print(rowsAffected);
                    System.out.println(" totaal aantal accounts zijn verwijderd");                    
                    System.out.println("alle koppelingen van klant en account zijn verwijderd");
                }
                else if (keuze == 2) {
                    System.out.println("De accountgegevens worden niet verwijderd.");
                }
                break;
        }
        accountMenu();
    }

    
    public void terugNaarHoofdMenu() {
        hoofdMenuController = new HoofdMenuController();
        hoofdMenuController.start();
    } 

    
    public Account voerNieuweAccountGegevensIn(Account account) {
        int juist = 0;
        
        String username = account.getUsername();
        juist = accountView.checkInputString(username);
        if (juist == 2) {
            username = accountView.voerGebruikersnaamIn();
        }         
        
        // later splitsen in klant controller, minder duplicate code
        klant = account.getKlant();
        gewijzigdeKlant = klantController.voerWijzigingenKlantIn(klant);
        klantDao.update(gewijzigdeKlant);         
        account.setKlant(klant);
        account.setUsername(username);
        
        return account;
        } 
}
