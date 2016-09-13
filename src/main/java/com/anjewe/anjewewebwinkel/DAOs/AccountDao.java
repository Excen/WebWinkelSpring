


package com.anjewe.anjewewebwinkel.DAOs;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wendy
 * @param <Account>
 * @param <Long>
 */
@Repository
@Transactional
public class AccountDao  extends GenericDaoImpl <Account, Long>{

private static final Logger log = LoggerFactory.getLogger(AccountDao.class);

    
    public AccountDao(){
        super();
    }

    
    public Account readByUsername(){
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//
//    @Override
//    public int deleteAll(Class<Account> type) {
//        return super.deleteAll(type); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean deleteById(Long id) {
//        return super.deleteById(id); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean delete(Account t) {
//        return super.delete(t); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void update(Account t) {
//        super.update(t); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public <T> List<T> readAll(Class<T> type) {
//        return super.readAll(type); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public <T> List<T> read(Long id, Class<T> type) {
//        return super.read(id, type); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Account readById(Long id) {
//        return super.readById(id); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public long insert(Account t) {
//        return super.insert(t); //To change body of generated methods, choose Tools | Templates.
//    }
//


}
