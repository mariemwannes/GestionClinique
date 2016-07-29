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
@Table(name = "medecin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medecin.findAll", query = "SELECT m FROM Medecin m"),
    @NamedQuery(name = "Medecin.findByIdMedecin", query = "SELECT m FROM Medecin m WHERE m.idMedecin = :idMedecin"),
    @NamedQuery(name = "Medecin.findByNomMedecin", query = "SELECT m FROM Medecin m WHERE m.nomMedecin = :nomMedecin")})
public class Medecin implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMedecin")
    private Collection<Visite> visiteCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMedecin")
    private Integer idMedecin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomMedecin")
    private String nomMedecin;

    public Medecin() {
    }

    public Medecin(Integer idMedecin) {
        this.idMedecin = idMedecin;
    }

    public Medecin(Integer idMedecin, String nomMedecin) {
        this.idMedecin = idMedecin;
        this.nomMedecin = nomMedecin;
    }

    public Integer getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Integer idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getNomMedecin() {
        return nomMedecin;
    }

    public void setNomMedecin(String nomMedecin) {
        this.nomMedecin = nomMedecin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedecin != null ? idMedecin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medecin)) {
            return false;
        }
        Medecin other = (Medecin) object;
        if ((this.idMedecin == null && other.idMedecin != null) || (this.idMedecin != null && !this.idMedecin.equals(other.idMedecin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.dmi.model.Medecin[ idMedecin=" + idMedecin + " ]";
    }

    @XmlTransient
    public Collection<Visite> getVisiteCollection() {
        return visiteCollection;
    }

    public void setVisiteCollection(Collection<Visite> visiteCollection) {
        this.visiteCollection = visiteCollection;
    }
    
}
