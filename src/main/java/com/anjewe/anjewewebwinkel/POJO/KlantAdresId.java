


package com.anjewe.anjewewebwinkel.POJO;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */
@Embeddable
public class KlantAdresId implements Serializable{

private static final Logger log = LoggerFactory.getLogger(KlantAdresId.class);
       
    
        @ManyToOne
        private Klant klant; 
        @ManyToOne
        private Adres adres;
    
        public Klant getKlant() {
            return klant;
        }

        public void setKlant(Klant klant) {
            this.klant = klant;
        }

        public Adres getAdres() {
            return adres;
        }

        public void setAdres(Adres adres) {
            this.adres = adres;
        }
     
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KlantAdresId that = (KlantAdresId) o;

        if (klant != null ? !klant.equals(that.klant) : that.klant != null) return false;
        if (adres != null ? !adres.equals(that.adres) : that.adres != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (klant != null ? klant.hashCode() : 0);
        result = 31 * result + (adres != null ? adres.hashCode() : 0);
        return result;
    }
    
}


