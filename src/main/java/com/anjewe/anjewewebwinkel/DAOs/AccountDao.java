


package com.anjewe.anjewewebwinkel.DAOs;

import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.Account;
import com.anjewe.anjewewebwinkel.POJO.Klant;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author Wendy
 */
@Component
public class AccountDao extends GenericDaoImpl <Account, Long>{

private static final Logger log = LoggerFactory.getLogger(AccountDao.class);

    @Override
    public int deleteAll(Class<Account> type) {
        return super.deleteAll(type); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(Long id) {
        return super.deleteById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Account t) {
        return super.delete(t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Account t) {
        super.update(t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> List<T> readAll(Class<T> type) {
        return super.readAll(type); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> List<T> read(Long id, Class<T> type) {
        return super.read(id, type); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account readById(Long id) {
        return super.readById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long insert(Account t) {
        return super.insert(t); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    protected Session getSession() {
//        return super.getSession(); //To change body of generated methods, choose Tools | Templates.
//    }
//
//
//
//
//    public AccountDao(){
//        super();
//    }

}
