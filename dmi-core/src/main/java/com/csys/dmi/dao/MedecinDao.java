/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.dmi.dao;

import com.csys.dmi.model.Medecin;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ERRAYHAN
 */
public class MedecinDao {
    EntityManager em  =FactoriesRepository.GetEntityManager(FactoriesRepository.getDmiPU());
    
    public List<Medecin> findallmedecin(){
        Query q=em.createNamedQuery("Medecin.findAll", Medecin.class);
        return q.getResultList();
        
    }
}