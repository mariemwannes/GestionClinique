//package com.csys.dmi.servlet;
//
//import com.csys.dmi.generic.WS;
//import com.csys.dmi.helper.RtftoHtml;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.Reader;
//import java.io.StringReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import org.apache.commons.codec.binary.Base64;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import service.AccessControl;
//import service.Client;
//import service.DiagnostiqueEntreeModel;
//
//public class Acceuil extends HttpServlet {
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            Gson gson = new GsonBuilder().serializeNulls().create();
//            
//            HttpSession session = request.getSession(true);
//
//            String function = request.getParameter("function");
//            String type = request.getParameter("type");
//
//            if (type.equals("consult")) {
//                if (function.equals("getAllMedecinEventsByNumDoss")) {
//
//                    String numdoss = request.getParameter("numdoss");
//                    String dateFeuille = request.getParameter("dateFeuille");
//                    List<service.VuAllMedecinEvents> listEvenements = WS.portWebServiceMedecinEvents.getAllMedecinEventsByNumDoss(numdoss, dateFeuille);
//                    for (service.VuAllMedecinEvents evenement : listEvenements) {
//                        if (evenement.getDetails().contains("{\\rtf")) {
//                            String text = RtftoHtml.convertRtfToHtml(evenement.getDetails());
//                            evenement.setDetails(text);
//                        }
//                    }
//                    out.println(gson.toJson(listEvenements));
//                } else if (function.equals("getPlanificationTacheInfirmierByNumDoss")) {
//                    String numdoss = request.getParameter("numdoss");
//                    out.println(gson.toJson(WS.portWebServiceMedecinEvents.getPlanificationTacheInfirmierByNumDoss(numdoss)));
//                } else if (function.equals("getAllMotifHospitalisationByNumDoss")) {
//                    String numdoss = request.getParameter("numdoss");
//                    List<service.DiagnostiqueEntree> listDiagEntre = WS.portWebServiceMedecinEvents.getAllMotifHospitalisationByNumDoss(numdoss);
//                    for (service.DiagnostiqueEntree diagEntre : listDiagEntre) {
//                        diagEntre.setMotifhospitalisation(diagEntre.getMotifhospitalisation().replace("''", "'"));
//                        if (diagEntre.getMotifhospitalisation().contains("{\\rtf")) {
//                            String text = RtftoHtml.convertRtfToHtml(diagEntre.getMotifhospitalisation());
//                            diagEntre.setMotifhospitalisation(diagEntre.getMotifhospitalisation());
//                        }
//                        if (diagEntre.getExamenClinique() != null) {
//                            if (diagEntre.getExamenClinique().contains("{\\rtf")) {
//                                String text = RtftoHtml.convertRtfToHtml(diagEntre.getExamenClinique());
//                                diagEntre.setExamenClinique(text);
//                            }
//                        } else {
//                            diagEntre.setExamenClinique("");
//                        }
//                        if (diagEntre.getHistoiremaladie().contains("{\\rtf")) {
//                            String text = RtftoHtml.convertRtfToHtml(diagEntre.getHistoiremaladie());
//                            diagEntre.setHistoiremaladie(text);
//                        }
//
//                        if (diagEntre.getTraitementHabituelle().contains("{\\rtf")) {
//                            String text = RtftoHtml.convertRtfToHtml(diagEntre.getTraitementHabituelle());
//                            diagEntre.setTraitementHabituelle(text);
//                        }
//                        if (diagEntre.getObservationSejour().contains("{\\rtf")) {
//                            String text = RtftoHtml.convertRtfToHtml(diagEntre.getObservationSejour());
//                            diagEntre.setObservationSejour(text);
//                        }
//
//                        if (diagEntre.getTraitementSejour().contains("{\\rtf")) {
//                            String text = RtftoHtml.convertRtfToHtml(diagEntre.getTraitementSejour());
//                            diagEntre.setTraitementSejour(text);
//                        }
//                        if (diagEntre.getTraitementSortie().contains("{\\rtf")) {
//                            String text = RtftoHtml.convertRtfToHtml(diagEntre.getTraitementSortie());
//                            diagEntre.setTraitementSortie(text);
//                        }
//
//                        if (diagEntre.getConclusion().contains("{\\rtf")) {
//                            String text = RtftoHtml.convertRtfToHtml(diagEntre.getConclusion());
//                            diagEntre.setConclusion(text);
//                        }
//                    }
//                    out.println(gson.toJson(listDiagEntre));
//                } else if (function.equals("getPatientByNumDoss")) {
//                    String numDoss = request.getParameter("numDoss");
//                    
//                    Client client = WS.portDossierSoinWS.getClientByNumDoss(numDoss);
//                    if(client != null)
//                    {
//                        byte[] image = client.getPhoto();
//                        String imageDataString = Base64.encodeBase64String(image);
//                        client.setPhotoSRC("data:image/png;base64,"+imageDataString);
//                    }
//                    
//                    out.println(gson.toJson(client));
//                } else if (function.equals("getCodeFeuilleByType")) {
//                    String suffixeCodeFeuille = request.getParameter("suffixeCodeFeuille");
//                    out.println(gson.toJson(WS.portDossierSoinWS.getCodeFeuilleByType(suffixeCodeFeuille)));
//                } else if (function.equals("getMedecin")) {
//                    String param = request.getParameter("param");
//                    out.println(gson.toJson(WS.portDossierSoinWS.getListMedecinLikeNomMed(param)));
//                } else if (function.equals("getSession")) {
//                    String numdoss = (String) session.getAttribute("numdoss");
//                    String codemed = (String) session.getAttribute("codemed");
//                    String datefeuil = (String) session.getAttribute("datefeuil");
//                    String user = (String) session.getAttribute("user");
//                    String groupe = (String) session.getAttribute("groupe");
//                    String[] listSession = new String[5];
//                    listSession[0] = numdoss;
//                    listSession[1] = codemed;
//                    listSession[2] = datefeuil;
//                    listSession[3] = user;
//                    listSession[4] = groupe;
//                    out.println(gson.toJson(listSession));
//                } else if (function.equals("getAntecedentAllergieByIdentifiant")) {
//                    String identifiant = request.getParameter("identifiant");
//                    out.println(gson.toJson(WS.portWebServiceMedecinEvents.getAntecedentAllergieByIdentifiant(identifiant)));
//                } else if (function.equals("getListAntecedantBySearchedText()")) {
//                    String param = request.getParameter("param");
//                    out.println(gson.toJson(WS.portWebServiceMedecinEvents.getListAntecedantBySearchedText(param)));
//                } else if (function.equals("getListAntecedant")) {
//                    out.println(gson.toJson(WS.portWebServiceMedecinEvents.getListAntecedant()));
//                } else if (function.equals("getListAllergie")) {
//                    out.println(gson.toJson(WS.portWebServiceMedecinEvents.getListAllergie()));
//                } else if (function.equals("getICD10BySearchedText")) {
//                    String param = request.getParameter("param");
//                    out.println(gson.toJson(WS.portWebServiceMedecinEvents.getICD10BySearchedText(param)));
//                } else if (function.equals("getMotifHospitalisationBySearchedText")) {
//                    String param = request.getParameter("param");
//                    out.println(gson.toJson(WS.portWebServiceMedecinEvents.getMotifHospitalisationBySearchedText(param)));
//                } else if (function.equals("distinctMotif")) {
//                    String param = request.getParameter("param");
//                    out.println(gson.toJson(WS.portWebServiceMedecinEvents.distinctMotif()));
//                } else if (function.equals("getMedecinByCode")) {
//                    String codMed = request.getParameter("codMed");
//                    out.println(gson.toJson(WS.portDossierSoinWS.getMedecinByCode(codMed)));
//                } else if (function.equals("getAllMedecin")) {
//                    String param = request.getParameter("param");
//                    if ((param != null) && param.compareTo("") != 0) {
//                        out.println(gson.toJson(WS.portDossierSoinWS.getListMedecinLikeNomMed(param)));
//                    } else {
//                        out.println(gson.toJson(WS.portDossierSoinWS.getAllMedecin()));
//                    }
//                } else if (function.equals("authentification")) {
//                    String user = request.getParameter("user");
//                    String password = request.getParameter("password");
//                    
//                    AccessControl client = WS.portDossierSoinWS.authentification(user, password);
//                    if(client != null)
//                    {
//                        session.setAttribute("user", client.getUserName());
//                        session.setAttribute("groupe", client.getGrp());
//                    }
//
//                        out.println(gson.toJson(client));
//                } else if (function.equals("getClientByChambre")) {
//                    String numcha = request.getParameter("numcha");
//                    
//                    Client client = WS.portDossierSoinWS.getClientByChambre(numcha);
//                    if(client != null)
//                    {
//                        byte[] image = client.getPhoto();
//                        String imageDataString = Base64.encodeBase64String(image);
//                        client.setPhotoSRC("data:image/png;base64,"+imageDataString);
//                    }
//                    
//                    out.println(gson.toJson(client));
//                    
//                } else if (function.equals("ListeChambreReelles")) {
//                    out.println(gson.toJson(WS.portDossierSoinWS.listeChambreReelles()));
//                }
//                
//                // Alertes
//                else if (function.equals("getAllergieByIdentifiant")) 
//                {
//                    String identifiant = request.getParameter("identifiant");
//                    out.println(gson.toJson(WS.portDossierSoinWS.getAllergieByIdentifiant(identifiant)));
//                }
//                else if (function.equals("getListAntecedentByIdentifiant")) 
//                {
//                    String identifiant = request.getParameter("identifiant");
//                    out.println(gson.toJson(WS.portDossierSoinWS.getListAntecedentByIdentifiant(identifiant)));
//                }
//                else if (function.equals("getBMRByDoss")) 
//                {
//                    String numdoss = request.getParameter("numdoss");
//                    out.println(gson.toJson(WS.portWebServiceMedecinEvents.getBMRByDoss(numdoss)));
//                } 
//                else if (function.equals("findByCode")) 
//                {
//                    String param = request.getParameter("param");
//                    out.println(gson.toJson(WS.portDossierSoinWS.findByCode(param).getValeur()));
//                }
//                else if (function.equals("getParamByModule")) 
//                {
//                    String code = request.getParameter("code");
//                    out.println(gson.toJson(WS.portDossierSoinWS.getParamByModule(code)));
//                }
//                else if (function.equals("getOptionVersionByCode")) 
//                {
//                    Integer code1 = Integer.parseInt(request.getParameter("code"));
//                    out.println(gson.toJson(WS.portDossierSoinWS.getOptionVersionByCode(code1)));
//                }
//                else if(function.equals("getListAutorisationPatientPrestations"))
//                {
//                    String numdoss = request.getParameter("numdoss");
//                    
//                    out.println(gson.toJson(WS.portDossierSoinWS.getListAutorisationPatientPrestations(numdoss)));
//                }
//                else if(function.equals("getListAutorisationPatientExamens"))
//                {
//                    String numdoss = request.getParameter("numdoss");
//                    
//                    out.println(gson.toJson(WS.portDossierSoinWS.getListAutorisationPatientExamens(numdoss)));
//                }
//            } 
//            else if (type.equals("update")) 
//            {
//                if (function.equals("addSession")) {
//                    String numdoss = request.getParameter("numdoss");
//                    String codemed = request.getParameter("codemed");
//                    String datefeuil = request.getParameter("datefeuil");
//                    String user = request.getParameter("user");
//                    String groupe =  request.getParameter("groupe");
//                    
//                    session.setAttribute("numdoss", numdoss);
//                    session.setAttribute("codemed", codemed);
//                    session.setAttribute("datefeuil", datefeuil);
//                    session.setAttribute("user", user);
//                    session.setAttribute("groupe", groupe);
//                    out.println(gson.toJson(true));
//                } else if (function.equals("createPlanificationTacheInfirmiere")) {
//
//                    String body = request.getParameter("planification");
//
//                    List<service.PlanificationTacheModel> planificationTacheModelList = new ArrayList<service.PlanificationTacheModel>();
//                    JSONArray json = new JSONArray();
//                    try {
//                        json = (JSONArray) new JSONParser().parse(body);
//                    } catch (ParseException ex) {
//                    }
//                    for (int i = 0; i < json.size(); i++) {
//                        service.PlanificationTacheModel planif = new service.PlanificationTacheModel();
//                        JSONObject object = (JSONObject) json.get(i);
//                        planif.setType(String.valueOf(object.get("type")));
//                        planif.setCodeMedecin(String.valueOf(object.get("codemed")));
//                        planif.setDetails(String.valueOf(object.get("listDesExam")));
//                        planif.setUserCreate(String.valueOf(object.get("userCreate")));
//                        planif.setNumeroDossier(String.valueOf(object.get("numdoss")));
//                        planif.setListCode(String.valueOf(object.get("listCodeExam")));
//                        planif.setObservation(String.valueOf(object.get("observation")));
//                        planif.setDateTache(String.valueOf(object.get("date")));
//                        planif.setHeureTache(String.valueOf(object.get("heure")));
//                        planificationTacheModelList.add(planif);
//
//                    }
//
//                    out.println(gson.toJson(WS.portWebServiceMedecinEvents.createPlanificationTacheInfirmiere(planificationTacheModelList.get(0))));
//                } else if (function.equals("createPlanificationTacheInfirmiereRadioLabo")) {
//
//                    String body = request.getParameter("planification");
//
//                    List<service.PlanificationTacheModel> planificationTacheModelList = new ArrayList<service.PlanificationTacheModel>();
//                    JSONArray json = new JSONArray();
//                    try {
//                        json = (JSONArray) new JSONParser().parse(body);
//                    } catch (ParseException ex) {
//                    }
//                    for (int i = 0; i < json.size(); i++) {
//                        service.PlanificationTacheModel planif = new service.PlanificationTacheModel();
//                        JSONObject object = (JSONObject) json.get(i);
//                        planif.setType(String.valueOf(object.get("type")));
//                        planif.setCodeMedecin(String.valueOf(object.get("codemed")));
//                        planif.setDetails(String.valueOf(object.get("listDesExam")));
//                        planif.setUserCreate(String.valueOf(object.get("userCreate")));
//                        planif.setNumeroDossier(String.valueOf(object.get("numdoss")));
//                        planif.setListCode(String.valueOf(object.get("listCodeExam")));
//                        planif.setObservation(String.valueOf(object.get("observation")));
//                        planif.setDateTache(String.valueOf(object.get("date")));
//                        planif.setHeureTache(String.valueOf(object.get("heure")));
//                        planificationTacheModelList.add(planif);
//
//                    }
//
//                    out.println(gson.toJson(WS.portWebServiceMedecinEvents.createPlanificationTacheInfirmiereRadioLabo(planificationTacheModelList.get(0))));
//                } else if (function.equals("updateRapportMedical")) {
//
//                    String body = request.getParameter("diagnostique");
//
//                    List<DiagnostiqueEntreeModel> diagnostiqueEntreeModelList = new ArrayList<service.DiagnostiqueEntreeModel>();
//                    JSONArray json = new JSONArray();
//                    try {
//                        json = (JSONArray) new JSONParser().parse(body);
//                    } catch (ParseException ex) {
//                    }
//                    for (int i = 0; i < json.size(); i++) {
//                        DiagnostiqueEntreeModel diagnostiqueEntreeModel = new DiagnostiqueEntreeModel();
//                        JSONObject object = (JSONObject) json.get(i);
//                        Reader reader;
//                        try {
//                            diagnostiqueEntreeModel.setNumdoss(String.valueOf(object.get("numdoss")));
//
//                            diagnostiqueEntreeModel.setMotifHospit(String.valueOf(object.get("motifHospit")).replace("'", "''"));
//                            
//                            String htmlStr1 = String.valueOf(object.get("traitHabit"));
//                            htmlStr1 = htmlStr1.replaceAll("<br>", "\\\\line");
//                            htmlStr1 = htmlStr1.replaceAll("<div>", "\\\\line");
//                            reader = new StringReader(htmlStr1);
//                            diagnostiqueEntreeModel.setTraitHabit(RtftoHtml.HTMLTortf(reader));
//                            
//                            String htmlStr2 = String.valueOf(object.get("traitementSejour")).replace("'", "''");
//                            htmlStr2 = htmlStr2.replaceAll("<br>", "\\\\line");
//                            htmlStr2 = htmlStr2.replaceAll("<div>", "\\\\line");
//                            reader = new StringReader(htmlStr2);
//                            diagnostiqueEntreeModel.setTraitementSejour(RtftoHtml.HTMLTortf(reader));
//                            
//                            String htmlStr3 = String.valueOf(object.get("observationSejour")).replace("'", "''");
//                            htmlStr3 = htmlStr3.replaceAll("<br>", "\\\\line");
//                            htmlStr3 = htmlStr3.replaceAll("<div>", "\\\\line");
//                            reader = new StringReader(htmlStr3);
//                            diagnostiqueEntreeModel.setObservationSejour(RtftoHtml.HTMLTortf(reader));
//
//                            String htmlStr4 = String.valueOf(object.get("traitementSortie")).replace("'", "''");
//                            htmlStr4 = htmlStr4.replaceAll("<br>", "\\\\line");
//                            htmlStr4 = htmlStr4.replaceAll("<div>", "\\\\line");
//                            reader = new StringReader(htmlStr4);
//                            diagnostiqueEntreeModel.setTraitementSortie(RtftoHtml.HTMLTortf(reader));
//                            
//                            String htmlStr5 = String.valueOf(object.get("histMaladi")).replace("'", "''");
//                            htmlStr5 = htmlStr5.replaceAll("<br>", "\\\\line");
//                            htmlStr5 = htmlStr5.replaceAll("<div>", "\\\\line");
//                            reader = new StringReader(htmlStr5);
//                            diagnostiqueEntreeModel.setHistMaladi(RtftoHtml.HTMLTortf(reader));
//                            
//                            String htmlStr6 = String.valueOf(object.get("conclusion")).replace("'", "''");
//                            htmlStr6 = htmlStr6.replaceAll("<br>", "\\\\line");
//                            htmlStr6 = htmlStr6.replaceAll("<div>", "\\\\line");
//                            reader = new StringReader(htmlStr6);
//                            diagnostiqueEntreeModel.setConclusion(RtftoHtml.HTMLTortf(reader));
//                            
//                            String htmlStr7 = String.valueOf(object.get("examCliniq")).replace("'", "''");
//                            htmlStr7 = htmlStr7.replaceAll("<br>", "\\\\line");
//                            htmlStr7 = htmlStr7.replaceAll("<div>", "\\\\line");
//                            reader = new StringReader(htmlStr7);
//                            diagnostiqueEntreeModel.setExamCliniq(RtftoHtml.HTMLTortf(reader));
//
//                            diagnostiqueEntreeModel.setUserCreat(String.valueOf(object.get("userCreat")));
//
//                            diagnostiqueEntreeModelList.add(diagnostiqueEntreeModel);
//                        } catch (Exception ex) {
//                            Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//
//                    }
//
//                    out.println(gson.toJson(WS.portWebServiceMedecinEvents.updateRapportMedical(diagnostiqueEntreeModelList.get(0))));
//                } else if (function.equals("addAntecedant2")) {
//                    String antecedant = request.getParameter("antecedant");
//                    String numDoss = request.getParameter("numDoss");
//                    String obs = request.getParameter("obs");
//                    String user = request.getParameter("user");
//                    out.println(gson.toJson(WS.portWebServiceMedecinEvents.addAntecedant2(antecedant, numDoss, obs, user)));
//                } else if(function.equals("addAntecedantAllergie")){
//                    String antecedant = request.getParameter("antecedant");
//                    String allergie = request.getParameter("allergie");
//                    String numDoss = request.getParameter("numDoss");
//                    String user = request.getParameter("user");
//                    String libelleAntec = request.getParameter("libelleAntec");
//                    String libelleAlrg = request.getParameter("libelleAlrg");
//                     out.println(gson.toJson(WS.portDossierSoinWS.addAntecedantAllergie(antecedant,allergie, numDoss, user, libelleAntec,libelleAlrg)));
//                }  
//                else if(function.equals("updateImagePatient"))
//                {
//                    
//                    String numDoss = request.getParameter("numDoss");
//                    String data = request.getParameter("image");
//                    data = data.replace("data:image/png;base64,", "");
//
//                    byte[] image = Base64.decodeBase64(data.getBytes());
//                    String path = "c://decode//"+numDoss+".png";
//                    try (FileOutputStream stream = new FileOutputStream(new File(path))) 
//                    {
//                        stream.write(image);
//                        stream.close();
//                        
//                     out.println(gson.toJson(WS.portDossierSoinWS.updatePhoto(path, numDoss)));
//                    }
//                    
//                }
//                else if(function.equals("updateCodePin"))
//                {
//                    String codeMedecinInfirmier = request.getParameter("codeMedecinInfirmier");
//                    String codePin = request.getParameter("codePin");
//                    
//                    out.println(gson.toJson(WS.portDossierSoinWS.updateCodePin(codeMedecinInfirmier, codePin)));
//                }
//                else if(function.equals("autorisationPatient"))
//                {
//                    String numdoss = request.getParameter("numdoss");
//                    String user = request.getParameter("user");
//                    
//                    out.println(gson.toJson(WS.portDossierSoinWS.autorisationPatient(numdoss, user)));
//                }
//            }
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//}