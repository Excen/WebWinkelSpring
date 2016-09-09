/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.POJO;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author Excen
 */

@Embeddable
public class BestellingArtikelId implements Serializable {
    
        @ManyToOne
        private Bestelling bestelling; 
        @ManyToOne
        private Artikel artikel;
        
        public Bestelling getBestelling() {
            return bestelling;
        }
        
        public void setBestelling(Bestelling bestelling){
            this.bestelling = bestelling;
        }
        
        public Artikel getArtikel() {
            return artikel;
        }
        
        public void setArtikel (Artikel artikel){
            this.artikel = artikel;
        }
       
        @Override
        public boolean equals(Object o) {
            
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BestellingArtikelId that = (BestellingArtikelId) o;

        if (bestelling != null ? !bestelling.equals(that.bestelling) : that.bestelling != null) return false;
        if (artikel != null ? !artikel.equals(that.artikel) : that.artikel != null)
            return false;

        return true;
    }

        @Override
    public int hashCode() {
        int result;
        result = (bestelling != null ? bestelling.hashCode() : 0);
        result = 31 * result + (bestelling != null ? bestelling.hashCode() : 0);
        return result;
    }
}
