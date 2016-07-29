/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.csys.dmi.service;

import com.csys.dmi.dao.MedecinDao;
import com.csys.dmi.model.Medecin;
import com.csys.dmi.model.Patient;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author CYBER INFO
 */
@WebService(serviceName = "MedecinEventWS")
public class MedecinEventWS {

    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "findallmedecin")
    public List<Medecin> findallmedecin(){
        return new MedecinDao().findallmedecin();
    }
       
    @PersistenceContext
	private EntityManager entityManager;

	public MedecinEventWS() {
		
	}

	
	public void createPatient(Patient patient) {
		entityManager.persist(patient);
	}

	
	public void updatePatient(Patient patient) {
		try {
			entityManager.merge(patient);
		} catch (Exception e) {
		}

	}

	
	public void removePatient(Patient patient) {
		try {
			entityManager.remove(patient);
		} catch (Exception e) {
		}
	}

	
	public Patient findPatientById(Long idMedecin) {
		return entityManager.find(Patient.class, idMedecin);
	}

}
