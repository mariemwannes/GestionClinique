///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.csys.dmi.dao;
//
//import com.csys.dmi.model.gclinique.Client;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
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
//public class ClientFacadeTest {
//    
//   
//    public static final String TEST_PERSISTENCE_UNIT_NAME = FactoriesRepository.getDmiPU();
//    private EntityManagerFactory emf;
//    private ThreadLocal<EntityManager> threadLocal;
//    private ClientFacade client_dao;
//    private EntityManager em;    
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
//        client_dao=new ClientFacade();
//        client_dao.setEntityManager(em);  
//    }
//
//    @AfterClass
//    public void runAfterClass() {        
//        emf.close();
//    }
//     
//    public void GetClientByNumDossTest()
//    {
//        String numdoss="14000282";
//       Client cl=client_dao.GetClientByNumDoss(numdoss);
//        assertNotNull(cl);       
//    }
//    public void ListePatientSortiByServiceTest()
//    {
//        String codEtage="RP"; String user="k.romdhane"; String codMed="anre030"; String search="14";
//        List <Client> clients=client_dao.ListePatientSortiByService(codEtage, user, codMed, search);
//        assertNotNull(clients);
//        codEtage="RP";  user="ml";  codMed="anre030";  search="14";
//        clients=client_dao.ListePatientSortiByService(codEtage, user, codMed, search);
//        assertNotNull(clients); 
////        codEtage="RP";  user="admin";  codMed="anre030";  search="14";
////        clients=client_dao.ListePatientSortiByService(codEtage, user, codMed, search);
////        assertNull(clients);   
//    }
//    
//}
