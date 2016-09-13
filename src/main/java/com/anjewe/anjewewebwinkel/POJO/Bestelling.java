package com.anjewe.anjewewebwinkel.POJO;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.stereotype.Component;
/**
 *
 * @author Excen
 */
@Component
@Table(name = "BESTELLING")
public class Bestelling implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column (unique = true, nullable = false, name = "BESTELLING_ID")
    private long Id;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "KLANT_ID")
    private Klant klant;   
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date bestellingDatum;
    
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "pk.bestelling", cascade = CascadeType.ALL)
    private Set<BestellingArtikel> bestellingArtikellen = new HashSet<>();
    
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Factuur factuur; 

    /*
    
    @Entity
    @Table(name = "KLANT")
    public class Klant implements Serializable {
    
    //    @GenericGenerator(name = "klantGenerator",strategy = "foreign",
    //        parameters = @Parameter(name = "property", value = "adres"))
    //    @Id
    //    @GeneratedValue(generator = "klantGenerator") 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (unique = true, nullable = false, name = "KLANT_ID")
    private Long Id;
    
    private String klantNummer;
    private String voornaam;
    @Column(nullable = false)
    private String achternaam;
    private String tussenvoegsel;
    @Column(nullable = false)
    private String email;     
     
    @OneToMany 
    protected Set<Account> accounts = new HashSet<>();    
    
    @OneToMany (fetch = FetchType.LAZY)
    protected Set<Betaling> betalingen = new HashSet<>();     
    
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "pk.klant", cascade = CascadeType.ALL)
    private Set<KlantAdres> klantAdressen = new HashSet<>(0);    
    
    @OneToMany (mappedBy = "klant", fetch = FetchType.LAZY)
    protected Set <Bestelling> bestellingen = new HashSet<>();
    
    */


    // Constructor
    
    public Bestelling(){
        bestellingDatum = new Date();
        Date sqlDatum = new java.sql.Date(getBestellingDatum().getTime());
    } 
    
    public Bestelling(long bestellingId, Klant klant){
        this.Id = bestellingId;
        this.klant = klant;
        bestellingDatum = new Date();
        Date sqlDatum = new java.sql.Date(getBestellingDatum().getTime());
    }
    
    public Bestelling(long bestellingId, Klant klant, Set<BestellingArtikel> bestellingArtikellen, Factuur factuur){
        this.Id = bestellingId;
        this.klant = klant;
        bestellingDatum = new Date();
        Date sqlDatum = new java.sql.Date(getBestellingDatum().getTime());
        this.bestellingArtikellen = bestellingArtikellen;
        this.factuur = factuur;
    }  
    
    /**
     * @return the Id
     */
    public long getId() {
        return Id;
    }

    /**
     * @param bestellingId the Id to set
     */
    public void setId(long Id) {
        this.Id = Id;
    }

    /**
     * @return the klantId
     */
   

    /**
     * @return the datum
     */
    public Date getDatum() {
        return getBestellingDatum();
    }

    /**
     * @param datum the datum to set
     */
    public void setDatum(Date datum) {
        this.setBestellingDatum(datum);
    }

    /**
     * @return the klant
     */
    public Klant getKlant() {
        return klant;
    }

    /**
     * @param klant the klant to set
     */
    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    /**
     * @return the bestellingDatum
     */
    public Date getBestellingDatum() {
        return bestellingDatum;
    }

    /**
     * @param bestellingDatum the bestellingDatum to set
     */
    public void setBestellingDatum(Date bestellingDatum) {
        this.bestellingDatum = bestellingDatum;
    }

    /**
     * @return the bestellingArtikellen
     */
    public Set<BestellingArtikel> getBestellingArtikellen() {
        return bestellingArtikellen;
    }

    /**
     * @param bestellingArtikellen the bestellingArtikellen to set
     */
    public void setBestellingArtikellen(Set<BestellingArtikel> bestellingArtikellen) {
        this.bestellingArtikellen = bestellingArtikellen;
    }

    /**
     * @return the factuur
     */
    public Factuur getFactuur() {
        return factuur;
    }

    /**
     * @param factuur the factuur to set
     */
    public void setFactuur(Factuur factuur) {
        this.factuur = factuur;
    }
    
    @Override
    public String toString(){
         String output = "Bestelling ID: " + this.getId() + "\n Klant ID: " + this.getKlant().getKlantNummer();
         return output;
    }
    
    
   




}
