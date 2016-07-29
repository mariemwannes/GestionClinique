/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.dmi.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "patient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p"),
    @NamedQuery(name = "Patient.findByIdPatient", query = "SELECT p FROM Patient p WHERE p.idPatient = :idPatient"),
    @NamedQuery(name = "Patient.findByPatientName", query = "SELECT p FROM Patient p WHERE p.patientName = :patientName"),
    @NamedQuery(name = "Patient.findByAge", query = "SELECT p FROM Patient p WHERE p.age = :age"),
    @NamedQuery(name = "Patient.findByMale", query = "SELECT p FROM Patient p WHERE p.male = :male"),
    @NamedQuery(name = "Patient.findByFemale", query = "SELECT p FROM Patient p WHERE p.female = :female"),
    @NamedQuery(name = "Patient.findByOccupation", query = "SELECT p FROM Patient p WHERE p.occupation = :occupation")})
public class Patient implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPatient")
    private Collection<Visite> visiteCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPatient")
    private Integer idPatient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "patientName")
    private String patientName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age")
    private int age;
    @Basic(optional = false)
    @NotNull
    @Column(name = "male")
    private boolean male;
    @Basic(optional = false)
    @NotNull
    @Column(name = "female")
    private boolean female;
    @Size(max = 50)
    @Column(name = "occupation")
    private String occupation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPatient")
    private Collection<Medications> medicationsCollection;

    public Patient() {
    }

    public Patient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public Patient(Integer idPatient, String patientName, int age, boolean male, boolean female) {
        this.idPatient = idPatient;
        this.patientName = patientName;
        this.age = age;
        this.male = male;
        this.female = female;
    }

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public boolean getFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @XmlTransient
    public Collection<Medications> getMedicationsCollection() {
        return medicationsCollection;
    }

    public void setMedicationsCollection(Collection<Medications> medicationsCollection) {
        this.medicationsCollection = medicationsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPatient != null ? idPatient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.idPatient == null && other.idPatient != null) || (this.idPatient != null && !this.idPatient.equals(other.idPatient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.dmi.model.Patient[ idPatient=" + idPatient + " ]";
    }

    @XmlTransient
    public Collection<Visite> getVisiteCollection() {
        return visiteCollection;
    }

    public void setVisiteCollection(Collection<Visite> visiteCollection) {
        this.visiteCollection = visiteCollection;
    }
    
}
