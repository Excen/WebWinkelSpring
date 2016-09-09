package com.anjewe.anjewewebwinkel.POJO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "ARTIKEL", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ARTIKELNR"),
                @UniqueConstraint(columnNames = "ARTIKELNAAM")})
public class Artikel implements Serializable{
    
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique = true, nullable = false, name = "ARTIKEL_ID")
    private Long Id; 
    @Column (name = "ARTIKELNR")
    private String artikelNummmer; 
    @Column (name = "ARTIKELNAAM")
    private String artikelNaam;
    private String omschrijving; 
    private double artikelPrijs;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.artikel", cascade = CascadeType.ALL)
    private Set<BestellingArtikel> bestellingArtikellen = new HashSet<>();
    
    // constructors
    public Artikel(){
    }
    
    public Artikel(Long artikelId, String artikelNaam, double artikelPrijs) {
        this.Id = artikelId;
        this.artikelNaam = artikelNaam;
        this.artikelPrijs = artikelPrijs;
    }

    public Long getId() {
        return Id;
    }

    public String getArtikelNaam() {
        return artikelNaam;
    }

    public double getArtikelPrijs() {
        return artikelPrijs;
    }

    public void setId(Long artikelId) {
        this.Id = artikelId;
    }

    public void setArtikelNaam(String artikelNaam) {
        this.artikelNaam = artikelNaam;
    }

    public void setArtikelPrijs(double artikelPrijs) {
        this.artikelPrijs = artikelPrijs;
    }

    /**
     * @return the artikelNummmer
     */
    public String getArtikelnummmer() {
        return artikelNummmer;
    }

    /**
     * @param artikelnummmer the artikelNummmer to set
     */
    public void setArtikelnummmer(String artikelnummmer) {
        this.artikelNummmer = artikelnummmer;
    }

    /**
     * @return the omschrijving
     */
    public String getOmschrijving() {
        return omschrijving;
    }

    /**
     * @param omschrijving the omschrijving to set
     */
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    /**
     * @return the bestellingen
     */
    public Set<BestellingArtikel> getBestellingen() {
        return bestellingArtikellen;
    }

    /**
     * @param bestellingen the bestellingen to set
     */
    public void setBestellingen(Set<BestellingArtikel> bestellingen) {
        this.bestellingArtikellen = bestellingen;
    }
    
    
    @Override
    public String toString(){
         String output = "Artikelnaam: " + this.getArtikelNaam() +" en id: " +  this.getId();
         return output;
    }
}
