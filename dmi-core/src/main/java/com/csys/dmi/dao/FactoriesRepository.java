package com.csys.dmi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoriesRepository{

    //persistence unit of DB Gclinique
    private final static  String dmiPU="DmiPU";
  

    public static String getDmiPU() {
        return dmiPU;
    }  

    public FactoriesRepository() {
    }
    
    static EntityManagerFactory factorydmi =null;
//    static EntityManagerFactory factorylabo =null;
//    static EntityManagerFactory factoryph =null;
//    static EntityManagerFactory factory =null;
    static EntityManager em=null;  
     public static EntityManagerFactory getFactory() {
        return factory;
    }

    public static void setFactory(EntityManagerFactory factory) {
        FactoriesRepository.factory = factory;
    }

   
    static EntityManagerFactory factory = null;
    
    public  static EntityManager GetEntityManager(String PU)
    {
        if(PU.equalsIgnoreCase(getDmiPU()))
        {
            if(factorydmi==null)    
            {
                factorydmi= Persistence.createEntityManagerFactory(PU);                         
            }            
            em= factorydmi.createEntityManager();               
        }
        
                       
        return em;
    }
}