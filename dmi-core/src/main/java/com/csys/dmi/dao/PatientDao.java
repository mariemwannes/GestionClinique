/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.dmi.dao;

import com.csys.dmi.model.Patient;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ASUS
 */
public class PatientDao 
{
    EntityManager em1  =FactoriesRepository.GetEntityManager(FactoriesRepository.getDmiPU());
    
    public List<Patient> GetListPatient(){
        Query query= em1 .createNamedQuery("Patient.findAll", Patient.class);
      return   query . setFirstResult(0).setMaxResults(50).getResultList() ;
    }
    
}
