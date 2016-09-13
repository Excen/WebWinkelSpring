/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.stereotype.Component;



/**
 *
 * @author Excen
 */
@Component
@Table(name = "ADRES", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"huisnummer","toevoeging","postcode"}) })
public class Adres implements Serializable {       
    
    
//    @GenericGenerator(name = "adresGenerator",strategy = "foreign",
//        parameters = @Parameter(name = "property", value = "klant"))
//    @Id
//    @GeneratedValue(generator = "adresGenerator")  
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "ADRES_ID")
    private Long Id;
        
    @Column(nullable = false)
    private String straatnaam;
    @Column(nullable = false, length = 6)
    private String postcode;
    private String toevoeging;
    @Column(nullable = false)
    private int huisnummer;  
    @Column(nullable = false)
    private String woonplaats;  
    //default? bij niets invullen => bezorg en factuuradres.. 
    private int adresType; 
    
//    @ManyToMany(mappedBy = "adressen")
//    protected Set<Klant> klanten = new HashSet<>();
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "pk.adres", cascade = CascadeType.ALL)
    private Set<KlantAdres> klantAdressen = new HashSet<>(0);

    
 
    public Adres(){
    }
    
    public Adres(String straatnaam, String postcode, 
            String toevoeging, int huisnummer, String woonplaats) {         
        this.straatnaam = straatnaam;
        this.postcode = postcode;
        this.toevoeging = toevoeging;
        this.huisnummer = huisnummer;
        this.woonplaats = woonplaats;         
    } 
   
       
    public Long getId(){
        return this.Id;
    }
    
    public String getStraatnaam() {
        return this.straatnaam;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public String getToevoeging() {
        return this.toevoeging;
    }

    public int getHuisnummer() {
        return this.huisnummer;
    }
    
    public String getWoonplaats() {
        return this.woonplaats;
    }
    
      /**
     * @return the adresType
     */
    public int getAdresType() {
        return this.adresType;
    }

//     public Set<Klant> getKlanten() {
//        return klanten;
//    }
    
    /**
     * @return the klantAdressen
     */
    public Set<KlantAdres> getKlantAdressen() {
        return this.klantAdressen;
    }
     
    
    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }
      
  
    /**
     * @param adresType the adresType to set
     */
    public void setAdresType(int adresType) {
        this.adresType = adresType;
    }

//    public void setKlanten(Set<Klant> klanten) {
//        this.klanten = klanten;
//    }    

    /**
     * @param klantAdressen the klantAdressen to set
     */
    public void setKlantAdressen(Set<KlantAdres> klantAdressen) {
        this.klantAdressen = klantAdressen;
    }

    

       
}
