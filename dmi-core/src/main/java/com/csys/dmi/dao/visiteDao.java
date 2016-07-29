/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.dmi.dao;

import com.csys.dmi.model.Medecin;
import com.csys.dmi.model.Visite;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ASUS
 */
public class visiteDao {
    EntityManager em2  =FactoriesRepository.GetEntityManager(FactoriesRepository.getDmiPU());
    
    public List<Visite> findallvisite(){
        Query q2=em2.createNamedQuery("visite.findAll", Visite.class);
        return q2.getResultList();
        
    }
    
}
