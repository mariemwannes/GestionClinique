//package com.csys.dmi.servlet;
//
//import com.csys.dmi.generic.WS;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.xml.datatype.XMLGregorianCalendar;
//import service.AccessControl;
//import service.Tracecnx;
//
//public class Authentification extends HttpServlet {
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) 
//        {
//            if(WS.portWebServiceMedecinEvents == null)
//            {
//                WS webService = new WS();
//                webService.DossierSoinWS();
//                webService.ReaWS();
//                webService.WebServiceMedecinEventsWS(); 
//                webService.UrgentWSWS();
//            }
//
//            
//            HttpSession session = request.getSession(true);
//            String user = "";
//            String password = "";
//            String codeMed = "";
//            String codePin = "";
//            
//            String mode = request.getParameter("mode");
//            if(mode.equalsIgnoreCase("normal"))
//            {
//                user = request.getParameter("user");
//                password = request.getParameter("password");
//            }
//            else if(mode.equalsIgnoreCase("pin"))
//            {
//                codeMed = request.getParameter("codeMed");
//                codePin = request.getParameter("codePin");
//            }
//
//            //Récupération Adresse IP
//            String adresseip = request.getHeader("X-FORWARDED-FOR");
//            if (adresseip == null) {
//                adresseip = request.getRemoteAddr();
//            }
//            
//            List<Tracecnx> ListeConnexion = null;
//            if(mode.equalsIgnoreCase("normal"))
//            {
//                ListeConnexion = WS.portDossierSoinWS.getTracecnxByLoginIP(user, adresseip);
//            }
//            else if(mode.equalsIgnoreCase("pin"))
//            {
//                ListeConnexion = WS.portDossierSoinWS.getTracecnxByLoginIP(codeMed, adresseip);
//            }
//            
//            if (ListeConnexion.isEmpty() == false) 
//            {
//                XMLGregorianCalendar actuelle = WS.portDossierSoinWS.getFullDate(); 
//                XMLGregorianCalendar datecnx = ListeConnexion.get(0).getDateCnx();
//                java.util.Date dtactuelle = actuelle.toGregorianCalendar().getTime();
//                java.util.Date dtdatecnx = datecnx.toGregorianCalendar().getTime();
//                long interval = dtactuelle.getTime() - dtdatecnx.getTime();
//                long diffminute = TimeUnit.MINUTES.convert(interval, TimeUnit.MILLISECONDS);
//                int temps_attente = 10;
//                
//                if (ListeConnexion.get(0).isEtat() == false && diffminute < temps_attente) 
//                {
//                    long reste = temps_attente - diffminute;
//                    out.print("reste:"+reste);
//                } 
//                else
//                {
//                    AccessControl clientUser = null;
//                    if(mode.equalsIgnoreCase("normal"))
//                    {
//                        clientUser = WS.portDossierSoinWS.authentification(user, password);
//                    }
//                    else if(mode.equalsIgnoreCase("pin"))
//                    {
//                        String codePin1=String.valueOf(codePin);
//                        clientUser = WS.portDossierSoinWS.verifierPinMed(codeMed, codePin1);
//                    }
//                    
//                    if(clientUser != null)
//                    {
//                        WS.portDossierSoinWS.updateUserConnected(ListeConnexion.get(0).getId(), 0, true);
//                        session.setAttribute("user", clientUser.getUserName());
//                        session.setAttribute("groupe", clientUser.getGrp());
//                        session.setAttribute("codemed", clientUser.getCodeMedecinInfirmier());
//                        
//                        out.print("succes:"+clientUser.getCodeMedecinInfirmier());
//                    }
//                    else if(clientUser == null)
//                    {
//                        int compteur = ListeConnexion.get(0).getCompteur();
//                        if(compteur <= 3) //Pas de limitation
//                        {
//                            WS.portDossierSoinWS.updateUserConnected(ListeConnexion.get(0).getId(), compteur+1, true);
//                            out.print("echec:"+(4-compteur));
//                        }
//                        else if(compteur > 3 && compteur < 7) //Captcha
//                        {
//                            WS.portDossierSoinWS.updateUserConnected(ListeConnexion.get(0).getId(), compteur+1, true);
//                            out.print("captcha:"+(7-compteur));
//                        }
//                        else if(compteur >= 7) //Blocage
//                        {
//                            WS.portDossierSoinWS.updateUserConnected(ListeConnexion.get(0).getId(), 0, false);
//                            out.print("bloque");
//                        }
//                    }
//                }
//            }
//            else if (ListeConnexion.isEmpty() == true) 
//            {
//                Boolean creationTrace = WS.portDossierSoinWS.createNewUserConnected(user, adresseip);
//                if(creationTrace == true)
//                {
//                    ListeConnexion = WS.portDossierSoinWS.getTracecnxByLoginIP(user, adresseip);
//                    int compteur = ListeConnexion.get(0).getCompteur() + 1;
//                    int id = ListeConnexion.get(0).getId();
//                    Boolean etat = ListeConnexion.get(0).isEtat();
//                            
//                    AccessControl clientUser = null;
//                    if(mode.equalsIgnoreCase("normal"))
//                    {
//                        clientUser = WS.portDossierSoinWS.authentification(user, password);
//                    }
//                    else if(mode.equalsIgnoreCase("pin"))
//                    {
//                        String codePin1=String.valueOf(codePin);
//                        clientUser = WS.portDossierSoinWS.verifierPinMed(codeMed, codePin1);
//                    }
//                    
//                    if(clientUser != null)
//                    {
//                        session.setAttribute("user", clientUser.getUserName());
//                        session.setAttribute("groupe", clientUser.getGrp());
//                        session.setAttribute("codemed", clientUser.getCodeMedecinInfirmier());
//                        
//                        out.print("succes:"+clientUser.getCodeMedecinInfirmier());
//                    }
//                    else if(clientUser == null)
//                    {
//                        WS.portDossierSoinWS.updateUserConnected(id, compteur, etat);
//                        out.print("echec:4");
//                    }
//                }
//                else
//                    out.print("erreur");
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