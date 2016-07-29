///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.csys.dmi.dao;
//
//import com.csys.dmi.model.gclinique.AccessControl;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
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
//public class AccessControlFacadeTest {
//    
//    public static final String TEST_PERSISTENCE_UNIT_NAME = FactoriesRepository.getDmiPU();
//    private EntityManagerFactory emf;
//    private ThreadLocal<EntityManager> threadLocal;
//    private AccessControlFacade accesControl;
//    private EntityManager em;    
//    private static Log log = LogFactory.getLog(AccessControlFacadeTest.class);
//   @BeforeClass
//    public void runBeforeClass()
//    {
//        
//        log.info("retrieving EntityManager: " + TEST_PERSISTENCE_UNIT_NAME);
//        emf = Persistence.createEntityManagerFactory(TEST_PERSISTENCE_UNIT_NAME);
//        log.info(emf);
//        threadLocal = new ThreadLocal<EntityManager>();
//        em = threadLocal.get();
//        if (em == null || !em.isOpen()) {
//            em = emf.createEntityManager();
//            threadLocal.set(em);
//        }
//        accesControl=new AccessControlFacade();
//        accesControl.setEntityManager(em);
//    }
//
//    @AfterClass
//    public void runAfterClass() {
//        log.info("closing EntityManagerFactory");
//        emf.close();
//    }
//     
//    public void AuthentificationTest()
//    {
//        String username="k.romdhane";
//        String password="seifoun";
//        AccessControl user_connect=accesControl.Authentification(username, password);
//        assertNotNull(user_connect.getCodeMedecinInfirmier(), "Le code médecin ne devrait pas etre null");
//        assertEquals(user_connect.getGrp(), "admin");
//    }
//
//   
//}
