///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.csys.dmi.dao;
//import com.csys.dmi.model.gclinique.Client;
//import com.csys.dmi.model.gclinique.CodeFeuille;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
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
//public class CodeFeuilleFacadeTest {
//    
//    public static final String TEST_PERSISTENCE_UNIT_NAME = FactoriesRepository.getDmiPU();
//    private EntityManagerFactory emf;
//    private ThreadLocal<EntityManager> threadLocal;
//    private CodeFeuilleFacade cf_dao;
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
//        cf_dao=new CodeFeuilleFacade();
//        cf_dao.setEntityManager(em);  
//      tr=em.getTransaction();
//    }
//
//    @AfterClass
//    public void runAfterClass() {        
//        emf.close();
//    }
//   
//    public void GetCodeFeuilleByTypeTest()
//    {
//        String typ_feuille="TrSr";
//        CodeFeuille cf=cf_dao.GetCodeFeuilleByType(typ_feuille);
//        assertNotNull(cf); 
//        tr.begin();
//        boolean reslt= cf_dao.UpdateCodeFeuilleNew(cf, 7, em);
//        tr.commit();
//        assertTrue(reslt);
// 
//    }
//}
