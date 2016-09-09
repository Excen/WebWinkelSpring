
package com.anjewe.anjewewebwinkel.POJO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Wendy
 */



    
    public enum Betaalwijze {
    
    GOOGLEWALLET{
        @Override
        public String getBetaalwijze() {
            return "googlewallet"; 
        }
    }, 
    
    IDEAL{
        @Override
        public String getBetaalwijze() {
            return "Ideal"; 
        }
    }, 
    
    PAYPAL{
        @Override
        public String getBetaalwijze() {
            return "Paypal"; 
        }
    }, 
    
    CREDITCARD{
        @Override
        public String getBetaalwijze() {
           return "creditcard"; 
        }
    }, 
    
    MONEYBOOKERS{
        @Override
        public String getBetaalwijze() {
           return "moneybookers";
        }
    } ; 
 
    
    
    private long betaalwijzeId; 

    public long getBetaalwijzeId() {
        return betaalwijzeId;
    }

    public void setBetaalwijzeId(long betaalwijzeId) {
        this.betaalwijzeId = betaalwijzeId;
    }
    
   
    public abstract String getBetaalwijze();
    
    
   
   
}