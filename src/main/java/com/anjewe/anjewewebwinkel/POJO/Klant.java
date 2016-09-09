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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Component
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
     
    @OneToOne  (mappedBy = "klant")
    protected Account account;    
    
    // deze is niet of wel nodig?
    @OneToMany (mappedBy = "klant", fetch = FetchType.LAZY)
    protected Set<Betaling> betalingen = new HashSet<>();     
    
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "pk.klant", cascade = CascadeType.ALL)
    private Set<KlantAdres> klantAdressen = new HashSet<>(0);    
    
    @OneToMany (mappedBy = "klant", fetch = FetchType.LAZY)
    protected Set <Bestelling> bestellingen = new HashSet<>();

    @OneToMany (mappedBy = "klant", fetch = FetchType.LAZY)
    protected Set <Factuur> facturen = new HashSet<>();
    
    //protected Map<Adres, AdresType> adresType; 
//(of andere manieren om de ternaire relatie

    

    
    // -- constructors --
    public Klant(){        
    }
    
    public Klant(String voornaam, String achternaam, 
             String tussenvoegsel, String email){ 
        
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.tussenvoegsel = tussenvoegsel;
        this.email = email;        
    }
        
     public Klant(String voornaam, String achternaam, 
             String tussenvoegsel, String email, Set<Bestelling> bestellingen, Set<Adres> adressen, Set<KlantAdres> klantAdressen){   
        
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.tussenvoegsel = tussenvoegsel;
        this.email = email;   
        this.bestellingen = bestellingen; 
        this.klantAdressen = klantAdressen;
    }
    
   
//    public void setAdressen(Set<Adres> adressen) {
//        this.adressen = adressen;
//    }
    
    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setEmail(String email) {
        this.email = email;
    }    
    
    public void setBestellingen(Set<Bestelling> bestellingen) {
        this.bestellingen = bestellingen;
    }   

    /**
     * @param klantNummer the klantNummer to set
     */
    public void setKlantNummer(String klantNummer) {
        this.klantNummer = klantNummer;
    }   

    /**
     * @param klantAdressen the klantAdressen to set
     */
    public void setKlantAdressen(Set<KlantAdres> klantAdressen) {
        this.klantAdressen = klantAdressen;
    }
    
    public void setFacturen(Set<Factuur> facturen) {
        this.facturen = facturen;
    }
   
     public void setBetalingen(Set<Betaling> betalingen) {
        this.betalingen = betalingen;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    public Long getId() {
        return this.Id;
    }

    public String getVoornaam() {
        return this.voornaam;
    }

    public String getAchternaam() {
        return this.achternaam;
    }

    public String getTussenvoegsel() {
        return this.tussenvoegsel;
    }

    public String getEmail() {
        return this.email;
    }    
  
    
      public Set<Betaling> getBetalingen() {
        return betalingen;
    }

   

    public Set<Factuur> getFacturen() {
        return facturen;
    }
    
    public Set<Bestelling> getBestellingen() {
        return this.bestellingen;
    }

     /**
     * @return the klantAdressen
     */
    public Set<KlantAdres> getKlantAdressen() {
        return this.klantAdressen;
    }
    
    /**
     * @return the klantNummer
     */
    public String getKlantNummer() {
        return this.klantNummer;
    }
    
      
    @Override
    public String toString(){
         String output = "Naam: " + this.getVoornaam() + " " + this.getAchternaam() +
                 " en id: " +  this.getId();
         return output;
    }


   
    
     
}

    
    
