


package com.anjewe.anjewewebwinkel.Service;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.DAOs.AccountDao;
import com.anjewe.anjewewebwinkel.POJO.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

/**
 * @author Wendy
 */

@Transactional
@Service
public class AccountService implements GenericServiceInterface <Account, Long>{

private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    
      
    @Autowired 
    protected GenericDaoImpl<Account, Long> accountDao;
    @Autowired
    Account account; 

    public AccountService() {
        this.accountDao = new AccountDao();
    }

    
    @Override
    public Account voegNieuweBeanToe(Long Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long voegNieuweBeanToe(Account account) {  
        
        Long accountId = accountDao.insert(account);              
        return accountId;
    }

  
    @Override
    public Account zoekNaarBean(Long Id) {
        account = (Account)accountDao.readById(Id); 
        return account; 
    }

    @Override
    public Long zoekNaarBean(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public Account zoeknaarBean(String username){
//        account = (Account)accountDao.readByUsername();
//        return account;
//    }
    
    
    @Override
    public List zoekAlleBeans() {
        
        ArrayList <Account> accountenLijst = (ArrayList<Account>)accountDao.readAll(Account.class);  
        return accountenLijst;
    }

    @Override
    public Account wijzigBeanGegevens(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends. 
     */
    
    @Override
    public Account wijzigBeanGegevens(Account account){
        Account gewijzigdAccount = new Account();
        gewijzigdAccount = (Account)accountDao.readById(account.getId());
        if (gewijzigdAccount!= null){
            gewijzigdAccount.setKlant(account.getKlant());
            gewijzigdAccount.setPassword(account.getPassword());
            gewijzigdAccount.setUsername(account.getUsername());
        }
        accountDao.update(gewijzigdAccount);
        return gewijzigdAccount;
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

//    boolean isUsernameUniek(String username, Long id){
//        account = findByUsername(username); 
//        return ( account == null || ((id != null) && (account.getId() == id)));
//    } 

}
   
    


    
