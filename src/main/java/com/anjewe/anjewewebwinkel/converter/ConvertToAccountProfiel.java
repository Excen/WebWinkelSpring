


package com.anjewe.anjewewebwinkel.converter;

import com.anjewe.anjewewebwinkel.Service.AccountService;
import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.POJO.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Wendy
 */


// AANPASSEN NAAR METHODEN IN AANGEPASTE CONTROLLERS

public class ConvertToAccountProfiel {

private static final Logger log = LoggerFactory.getLogger(ConvertToAccountProfiel.class);

@Autowired // vraag is of dit de juiste klasse is
AccountService accountController;

@Autowired
GenericDaoImpl<Account, Long> accountDao;  

/**
     * Gets accountprofiel by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */

    public Account convert(Object element) {
        Long id = Long.parseLong((String)element);
        Account account = (Account) accountDao.readById(id);
        // aanpassen in controller - nu nog met keuze menu voor 1 of alle
       accountController.zoekAccountGegevens();
        return account;
    }

}
