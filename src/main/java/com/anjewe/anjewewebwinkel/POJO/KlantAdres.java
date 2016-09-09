

package com.anjewe.anjewewebwinkel.POJO;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Transient;
import org.springframework.stereotype.Component;

/**
 *
 * @author Wendy
 */
@Component
@Entity
@Table (name ="KLANTADRES")
@AssociationOverrides({
		@AssociationOverride(name = "pk.klant",
			joinColumns = @JoinColumn(name = "KLANT_ID")),
		@AssociationOverride(name = "pk.adres",
			joinColumns = @JoinColumn(name = "ADRES_ID")) })


public class KlantAdres implements Serializable {

        @EmbeddedId
	private KlantAdresId pk = new KlantAdresId();
        private Date createdDate;
	private String createdBy;
        
   
        public KlantAdres(){            
        }
        
        
        public KlantAdresId getPk() {
            return pk;
        }

        public void setPk(KlantAdresId pk) {
            this.pk = pk;
        }
        
        @Transient
        public Klant getKlant(){
            return getPk().getKlant();
        }
        
        public void setKlant(Klant klant){
            getPk().setKlant(klant);
        }
        
        @Transient
         public Adres getAdres(){
            return getPk().getAdres();
        }
        
        public void setAdres(Adres adres){
            getPk().setAdres(adres);
        }
        
        @Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", nullable = false, length = 10)
	public Date getCreatedDate() {
		return this.createdDate;
	}
        
        public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CREATED_BY", nullable = false, length = 10)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
        
        public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		KlantAdres that = (KlantAdres) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
        
        @Override
        public String toString(){
            String output = "klant: " + this.getKlant() + "\nadres: " + 
                    this.getAdres();
            return output; 
        }
}  
