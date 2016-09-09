
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.POJO;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.stereotype.Component;
/**
 *
 * @author Excen
 */
@Component
@Entity
@Table(name = "BETALING" )
public class Betaling implements Serializable {

@Id
@GeneratedValue(strategy = AUTO)
@Column(unique = true, nullable = false)
private long Id;

@Temporal(javax.persistence.TemporalType.DATE)
private java.util.Date betaaldatum;


@Enumerated(EnumType.STRING)
public Betaalwijze betaalwijze;    

@ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
@JoinColumn (name = "KLANT_ID")
private Klant klant;       

@ManyToOne (fetch = FetchType.EAGER, optional = false)
@JoinColumn (name = "FACTUUR_ID")
private Factuur factuur;

private String betalingsGegevens;



    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

 
    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    /**
     * @return the betaaldatum
     */
    public java.util.Date getBetaaldatum() {
        return betaaldatum;
    }

    /**
     * @param betaaldatum the betaaldatum to set
     */
    public void setBetaaldatum(java.util.Date betaaldatum) {
        this.betaaldatum = betaaldatum;
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

    public Betaalwijze getBetaalwijze() {
        return betaalwijze;
    }

    public void setBetaalwijze(Betaalwijze betaalwijze) {
        this.betaalwijze = betaalwijze;
    }

      
    
    public String getBetalingsGegevens() {
        return betalingsGegevens;
    }

    public void setBetalingsGegevens(String betalingsGegevens) {
        this.betalingsGegevens = betalingsGegevens;
    }
}



