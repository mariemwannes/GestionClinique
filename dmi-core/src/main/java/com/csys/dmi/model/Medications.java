/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.dmi.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "medications")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medications.findAll", query = "SELECT m FROM Medications m"),
    @NamedQuery(name = "Medications.findByIdMedication", query = "SELECT m FROM Medications m WHERE m.idMedication = :idMedication"),
    @NamedQuery(name = "Medications.findByDrug", query = "SELECT m FROM Medications m WHERE m.drug = :drug"),
    @NamedQuery(name = "Medications.findByStrength", query = "SELECT m FROM Medications m WHERE m.strength = :strength"),
    @NamedQuery(name = "Medications.findByDosage", query = "SELECT m FROM Medications m WHERE m.dosage = :dosage"),
    @NamedQuery(name = "Medications.findByDuration", query = "SELECT m FROM Medications m WHERE m.duration = :duration")})
public class Medications implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMedication")
    private Integer idMedication;
    @Size(max = 2147483647)
    @Column(name = "drug")
    private String drug;
    @Size(max = 2147483647)
    @Column(name = "strength")
    private String strength;
    @Size(max = 2147483647)
    @Column(name = "dosage")
    private String dosage;
    @Size(max = 2147483647)
    @Column(name = "duration")
    private String duration;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne(optional = false)
    private Patient idPatient;

    public Medications() {
    }

    public Medications(Integer idMedication) {
        this.idMedication = idMedication;
    }

    public Integer getIdMedication() {
        return idMedication;
    }

    public void setIdMedication(Integer idMedication) {
        this.idMedication = idMedication;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedication != null ? idMedication.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medications)) {
            return false;
        }
        Medications other = (Medications) object;
        if ((this.idMedication == null && other.idMedication != null) || (this.idMedication != null && !this.idMedication.equals(other.idMedication))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.dmi.model.Medications[ idMedication=" + idMedication + " ]";
    }
    
}
