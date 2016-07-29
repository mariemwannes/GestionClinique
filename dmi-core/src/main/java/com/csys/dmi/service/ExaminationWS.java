/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.dmi.service;

import com.csys.dmi.dao.MedecinDao;
import com.csys.dmi.dao.PatientDao;
import com.csys.dmi.model.Medecin;
import com.csys.dmi.model.Patient;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ahmed
 */
@WebService(serviceName = "ExaminationWS")
public class ExaminationWS {

     /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "findallmedecin")
    public List<Medecin> findallmedecin(){
        return new MedecinDao().findallmedecin();
    }
    
     /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "GetListPatient")
    public List<Patient> GetListPatient(){
        return new PatientDao().GetListPatient();
    }
}
