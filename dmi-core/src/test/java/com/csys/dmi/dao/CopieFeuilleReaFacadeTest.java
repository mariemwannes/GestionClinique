///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.csys.dmi.dao;
//
//import com.csys.dmi.model.gclinique.VListTraitement;
//import com.csys.dmi.model.gclinique.Vtraitement;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//import static org.testng.Assert.*;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
///**
// *
// * @author ikram on 14/04/2015
// */
//@Test
//public class CopieFeuilleReaFacadeTest {
//    
//       public static final String TEST_PERSISTENCE_UNIT_NAME = FactoriesRepository.getDmiPU();
//    private EntityManagerFactory emf;
//    private ThreadLocal<EntityManager> threadLocal;
//    private CopieFeuilleReaFacade copieF_dao;
//    ListTraitementSurveillanceReaFacade lsttr_dao;
//    private EntityManager em;    
//    private EntityTransaction tr; 
// 
//   @BeforeClass
//    public void runBeforeClass()
//    {        
//        emf = Persistence.createEntityManagerFactory(TEST_PERSISTENCE_UNIT_NAME);     
//        threadLocal = new ThreadLocal<EntityManager>();
//        em = threadLocal.get();
//        if (em == null || !em.isOpen()) {
//            em = emf.createEntityManager();
//            threadLocal.set(em);
//        }
//        copieF_dao=new CopieFeuilleReaFacade();
//        copieF_dao.setEntityManager(em);  
//      tr=em.getTransaction();
//      lsttr_dao= new ListTraitementSurveillanceReaFacade();
//      lsttr_dao.setEntityManager(em);
//    }
//
//    @AfterClass
//    public void runAfterClass() {        
//        emf.close();
//    }
//     
//    public void LoadNewFeuilleReaTest()
//    {
//       String numdoss="15001234"; String DateFeuille="16/04/2015"; String heureDebut="8"; String user="csys" ;
//        List<VListTraitement> lst_sur=lsttr_dao.getlistsurv(DateFeuille, numdoss, "7");
//        if(lst_sur==null){
//       tr.begin();
//       boolean reslt=copieF_dao.LoadNewFeuilleRea(numdoss, DateFeuille, heureDebut, user);
//       tr.commit();
//         assertTrue(reslt);}
//        else{
//       lst_sur=lsttr_dao.getlistsurv(DateFeuille, numdoss, "7");
//        assertNotNull(lst_sur);
//        }
//        
//    }
//}
