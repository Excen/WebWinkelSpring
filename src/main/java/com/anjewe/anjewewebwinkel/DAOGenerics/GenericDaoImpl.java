


package com.anjewe.anjewewebwinkel.DAOGenerics;




import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wendy
 * @param <T>
 * @param <PK>
 */
@Repository
@Transactional
public abstract class GenericDaoImpl <T, PK extends Serializable> { 
    
    private static final Logger log = LoggerFactory.getLogger(GenericDaoImpl.class);
    
    // data fields //
    protected Class<T> beanType;
    
    
    @Autowired 
    private SessionFactory sessionFactory;
    
    @Bean(name="sessionBean") 
    protected Session getSession() {        
        return sessionFactory.getCurrentSession();
    }
    
    
    // constructor //
    @SuppressWarnings("unchecked")
    public GenericDaoImpl(){
        this.beanType = ((Class) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0]);          
    }
    
    
    // **CRUD methodes** //     
    
     // -- werkt
    public long insert(T t) {        
        log.info(beanType.getSimpleName() + " creeeren in de database. Return id");
        
        long id = (Long) getSession().save(t);      
        
        return id;           
    }
    
    
     // -- werkt
    public T readById(PK id) {
        System.out.println(beanType.getSimpleName() + " via Id lezen uit de database");
        
        T instantie = (T)getSession().get(beanType, id);
        
        return (T)instantie; 
    }
    
    
     // -- nog niet getest  >> bv adressen behorende bij klant ophalen
   
    public <T> List<T> read(PK id, Class<T> type) {         
        log.info(beanType.getSimpleName() + " via Id lijst opvragen uit de database");
        
        Query query = getSession().createSQLQuery("select from " + type + " where " + beanType + "_ID" + id );
        
        final List<T> lijst = (List<T>)query.list();
        
        if (lijst.size() < 1 ){
            return null;
        } else {
            return lijst;
        } 
    }

    @SuppressWarnings("unchecked")
     // -- werkt
   
    public <T> List<T> readAll(Class<T> type){ 
        log.info(beanType.getSimpleName()+ ": Lijst met alle objecten ophalen");       
        
        final Criteria crit = getSession().createCriteria(type);
        return crit.list();        
    }  
   

   // -- werkt
  
    public void update(T t) {        
        log.info(beanType.getSimpleName() + " Object update.");  
        
        getSession().update(t);                 
    }

    
     // -- nog  neit getest. overbodig??
    
    public boolean delete(T t) {        
       log.info(beanType.getSimpleName() + " Object delete.");   
       
       if (t != null){
         getSession().delete(t); 
         return true;
       }       
        return false;  
    }

     //-- werkt 
  
    public boolean deleteById(PK id) {        
        log.info(beanType.getSimpleName() + " Object delete."); 
        
        Object persistanceInstance = getSession().get(beanType, id);
        if (persistanceInstance != null){
           getSession().delete(persistanceInstance); 
           return true;
        }       
            return false;       
    }    

    // -- werkt
   
    public int deleteAll(Class<T> type) {
        log.info(beanType.getSimpleName() + "delete all"); 
        int count = 0; 
               
         final List<T> instances = getSession().createCriteria(type).list();
            for (Object object : instances) {
                count++;
            getSession().delete(object);
            }        
        return count;         
    }

    

  


    


}
