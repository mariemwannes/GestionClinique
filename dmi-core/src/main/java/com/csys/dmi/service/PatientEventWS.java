/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.dmi.service;

import com.csys.dmi.dao.PatientDao;
import com.csys.dmi.model.Patient;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.sql.Connection;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ASUS
 */
@WebService(serviceName = "PatientEventWS")
public class PatientEventWS {

    private static Object DriverManager;

    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "GetListPatient")
    public List<Patient> GetListPatient(){
        return new PatientDao().GetListPatient();
    }
//    @WebMethod(operationName = "InsertPatient")
//    public static void InsertPatient(String nomPatient, int age , string occupation , boolean  male , boolean female)
//    {
//        try{ 
//      //lien vers la base de données 
//      Connection connection = DriverManager.getConnection("url","user","password"); 
//  
//      //préparation de l'instruction SQL 
//      String sql = "INSERT INTO Patient VALUES (?, ?)"; 
//      PreparedStatement statement = connection.prepareStatement(sql); 
//  
//      //insertion de l'image 
//      statement.setString(1, nomPatient); 
//      
//  
//    }catch(Exception e){ 
//       //traitement des erreurs SQL, IO, etc. 
//    }finally { 
//       //fermeture de la connexion, du flux, etc. 
//    } 
//}
}
