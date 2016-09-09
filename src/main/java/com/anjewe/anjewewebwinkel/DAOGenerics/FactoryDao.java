


package com.anjewe.anjewewebwinkel.DAOGenerics;

import com.anjewe.anjewewebwinkel.DAOs.*;
import com.anjewe.anjewewebwinkel.POJO.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author Wendy
 */
@Component
public class FactoryDao {
    
    FactoryDao(){}

private static final Logger log = LoggerFactory.getLogger(FactoryDao.class);

   @Bean
public GenericDaoImpl<Klant, Long> getKlantDao(){
    return new KlantDao();    
}

@Bean
public GenericDaoImpl<Factuur, Long> getFactuurDao(){
    return new FactuurDao();    
}


@Bean
public GenericDaoImpl<KlantAdres, Long> getKlantAdresDao(){
    return new KlantAdresDao();    
}


@Bean
public GenericDaoImpl<Betaling, Long> getBetalingDao(){
    return new BetalingDao();    
}


@Bean
public GenericDaoImpl<Bestelling, Long> getBestellingDao(){
    return new BestellingDao();    
}

           
            @Bean
            public BestellingArtikelDao getBestellingArtikelDao(){
                return new BestellingArtikelDao();    
            }


@Bean
public GenericDaoImpl<Artikel, Long> getArtikelDao(){
    return new ArtikelDao();    
}


@Bean
public GenericDaoImpl<Adres, Long> getAdresDao(){
    return new AdresDao();    
}


@Bean
public GenericDaoImpl<Account, Long> getAccountDao(){
    return new AccountDao();    
}

   

}
