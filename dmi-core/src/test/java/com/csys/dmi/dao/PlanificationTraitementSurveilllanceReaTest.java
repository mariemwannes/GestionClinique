///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.csys.dmi.dao;
//import com.csys.dmi.model.gclinique.DetailsTraitement;
//import com.csys.dmi.model.gclinique.DetailsTraitementPK;
//import com.csys.dmi.model.gclinique.VListTraitement;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//@Test
//public class PlanificationTraitementSurveilllanceReaTest {
//    
//    
//    public static final String TEST_PERSISTENCE_UNIT_NAME = FactoriesRepository.getDmiPU();
//    private EntityManagerFactory emf;
//    private ThreadLocal<EntityManager> threadLocal;
//    private PlanificationTraitementSurveilllanceRea planif_TSFacade;
//     private ListTraitementSurveillanceReaFacade ListTSFacade;
// //    private TraitementSurveillanceReaFacade ListTS;
//    private EntityManager em;
//    private EntityTransaction transaction;
//    private static Log log = LogFactory.getLog(PlanificationTraitementSurveilllanceReaTest.class);
//    @BeforeClass
//    public void runBeforeClass() {
//        log.info("retrieving EntityManager: " + TEST_PERSISTENCE_UNIT_NAME);
//        emf = Persistence.createEntityManagerFactory(TEST_PERSISTENCE_UNIT_NAME);
//        log.info(emf);
//        threadLocal = new ThreadLocal<EntityManager>();
//        em = threadLocal.get();
//        if (em == null || !em.isOpen()) {
//            em = emf.createEntityManager();
//            threadLocal.set(em);
//        }
//        planif_TSFacade = new PlanificationTraitementSurveilllanceRea();       
//        ListTSFacade=new ListTraitementSurveillanceReaFacade();
//        // ListTS=new TraitementSurveillanceReaFacade();
//        planif_TSFacade.setEntityManager(em);  
//        this.transaction = em.getTransaction();
//        ListTSFacade.setEntityManager(em);
//
//    }
//
//    @AfterClass
//    public void runAfterClass() {
//        log.info("closing EntityManagerFactory");
//        emf.close();
//    }
//    
//    public void TestAjoutUneOuPlusieursPlanification() throws ParseException
//    {
//        
//        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        List<VListTraitement> lst_surv=ListTSFacade.getlistsurv( "15/04/2015","14000282", "7");         
//        int size_surv=lst_surv.size();           
//        List<DetailsTraitement> details=new ArrayList<DetailsTraitement>();
//        DetailsTraitement detail =new DetailsTraitement();
//        detail.setCodeInfirmier("ikram");
//        detail.setHeureRealisation("");
//        detail.setObservation("");
//        detail.setQuantite("");
//        detail.setRetourn(false);
//        detail.setType("7");
//        DetailsTraitementPK pk=new DetailsTraitementPK();
//       try{pk.setDatePrise(formatter.parse("15/04/2015"));}catch(Exception ex){}
//        pk.setHeurePrise("10");
//        pk.setNum(lst_surv.get(0).getNumTraitement());
//        detail.setDetailsTraitementPK(pk);
//        details.add(detail);  
//      //  transaction.begin();
//      //  String result= planif_TSFacade.CreateDetailPlanification(details, "");
//      //  transaction.commit();
//        //Assert.assertEquals("true", result);
//       // lst_surv=ListTSFacade.getlistsurv("15/04/2015","14000282", "7");
//       // Assert.assertEquals(size_surv+1,lst_surv.size());
//    }
//
//}
