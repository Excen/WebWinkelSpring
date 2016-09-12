


package com.anjewe.anjewewebwinkel.Service;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Wendy
 */

@Component
public class AccountService implements GenericServiceInterface {

private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    
    public AccountService(){        
    }
    
//    @Autowired
//    FactoryDao factoryDao;
    
    @Autowired
    GenericDaoImpl<Account, Long> accountDao;  
    @Autowired
    GenericDaoImpl<Klant, Long> klantDao;       
    @Autowired
    KlantService klantController; 
    @Autowired
    Account account;
    @Autowired
    Account gewijzigdAccount; 
    @Autowired 
    Klant klant;
    @Autowired
    Klant gewijzigdeKlant;
   

    @Override
    public Object voegNieuweBeanToe(Long Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long voegNieuweBeanToe(Object t) {   
        
        Long accountId = (Long)accountDao.insert(account);  
            
        return accountId;
    }

    @Override
    public Object zoekNaarBean(Long Id) {
        account = (Account)accountDao.readById(Id); 
        return account; 
    }

    @Override
    public Long zoekNaarBean(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List zoekAlleBeans() {
        ArrayList <Account> accountenLijst = (ArrayList<Account>) accountDao.readAll(Account.class);  
        return accountenLijst;
    }

    @Override
    public Object wijzigBeanGegevens(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public Object wijzigBeanGegevens(Object t){
        accountDao.update((Account) t);
        return t;
    }
  

    @Override
    public boolean verwijderBeanGegevens(Long Id) {
       boolean deleted = accountDao.deleteById(Id);    
       return deleted;
    }

    @Override
    public int verwijderAlleBeans() {               
        int rowsAffected = accountDao.deleteAll(Account.class);   
        return rowsAffected;
    }

   
    
}

    
