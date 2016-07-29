//package com.csys.dmi.generic;
//
//import java.net.Authenticator;
//import java.net.MalformedURLException;
//import java.net.PasswordAuthentication;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import service.*;
//
//public class WS {
//
//    public static ReaWS portReaWS;
//    public static DossierSoinWS portDossierSoinWS;
//    public static WebServiceMedecinEvents portWebServiceMedecinEvents;
//    public static UrgentWS portUrgentWS;
//
//    private String getWSDLPort() {
//        MyConnection myconn = new MyConnection();
//        Connection conn = myconn.getConnection();
//        String WSDLPort = "";
//        try (Statement stm = conn.createStatement()) {
//            String query = "select top(1) url_ws_dmi from clinique";
//            ResultSet rs = stm.executeQuery(query);
//            while (rs.next()) {
//                WSDLPort = rs.getString("url_ws_dmi");
//            }
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        } finally {
//            // Release connection back to the pool
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//
//                }
//            }
//        }
//        return WSDLPort;
//    }
//
//    public ReaWS ReaWS() throws MalformedURLException {
//
//        Authenticator myAuth = new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                MyConnection myconn = new MyConnection();
//                Connection conn = myconn.getConnection();
//                String user = "";
//                String psw = "";
//                try (Statement stm = conn.createStatement()) {
//                    String query = "select top(1)rolet.user_name,user_pass from users_tomcat usert inner join  user_roles rolet on rolet.user_name=usert.user_name where role_name='tomcat'";
//                    ResultSet rs = stm.executeQuery(query);
//                    while (rs.next()) {
//                        user = rs.getString("user_name");
//                        psw = rs.getString("user_pass");
//                    }
//                } catch (SQLException e) {
//                    System.err.println(e.getMessage());
//                } finally {
//                    // Release connection back to the pool
//                    if (conn != null) {
//                        try {
//                            conn.close();
//                        } catch (SQLException ex) {
//
//                        }
//                    }
//                    conn = null; // prevent any future access
//                }
//                return new PasswordAuthentication(user, psw.toCharArray());
//            }
//        };
//
//        Authenticator.setDefault(myAuth);
//        String WSDLport = getWSDLPort();
//        if (WSDLport.equalsIgnoreCase("")) {
//            System.out.println("erreur WSDl");
//            return null;
//        } else {
//            ReaWSService service = new ReaWSService(new URL("http://" + WSDLport + "/dmi-core/ReaWSService?wsdl"));
//            portReaWS = service.getReaWSPort();
//            return portReaWS;
//        }
//    }
//
//    public DossierSoinWS DossierSoinWS() throws MalformedURLException {
//        Authenticator myAuth = new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                MyConnection myconn = new MyConnection();
//                Connection conn = myconn.getConnection();
//                String user = "";
//                String psw = "";
//                try (Statement stm = conn.createStatement()) {
//                    String query = "select top(1)rolet.user_name,user_pass from users_tomcat usert inner join  user_roles rolet on rolet.user_name=usert.user_name where role_name='tomcat'";
//                    ResultSet rs = stm.executeQuery(query);
//                    // Store and return result of the query            
//                    while (rs.next()) {
//                        user = rs.getString("user_name");
//                        psw = rs.getString("user_pass");
//                    }
//                } catch (SQLException e) {
//                    System.err.println(e.getMessage());
//                } finally {
//                    // Release connection back to the pool
//                    if (conn != null) {
//                        try {
//                            conn.close();
//                        } catch (SQLException ex) {
//
//                        }
//                    }
//                    conn = null; // prevent any future access
//                }
//                return new PasswordAuthentication(user, psw.toCharArray());
//            }
//        };
//
//        Authenticator.setDefault(myAuth);
//        String WSDLport = getWSDLPort();
//        if (WSDLport.equalsIgnoreCase("")) {
//            System.out.println("erreur WSDl");
//            return null;
//        } else {
//            DossierSoinWSService service = new DossierSoinWSService(new URL("http://" + WSDLport + "/dmi-core/DossierSoinWSService?wsdl"));
//            portDossierSoinWS = service.getDossierSoinWSPort();
//
//            return portDossierSoinWS;
//        }
//    }
//
//    public WebServiceMedecinEvents WebServiceMedecinEventsWS() throws MalformedURLException {
//        Authenticator myAuth = new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                MyConnection myconn = new MyConnection();
//                Connection conn = myconn.getConnection();
//                String user = "";
//                String psw = "";
//                try (Statement stm = conn.createStatement()) {
//                    String query = "select top(1)rolet.user_name,user_pass from users_tomcat usert inner join  user_roles rolet on rolet.user_name=usert.user_name where role_name='tomcat'";
//                    ResultSet rs = stm.executeQuery(query);
//                    // Store and return result of the query            
//                    while (rs.next()) {
//                        user = rs.getString("user_name");
//                        psw = rs.getString("user_pass");
//                    }
//                } catch (SQLException e) {
//                    System.err.println(e.getMessage());
//                } finally {
//                    // Release connection back to the pool
//                    if (conn != null) {
//                        try {
//                            conn.close();
//                        } catch (SQLException ex) {
//
//                        }
//                    }
//                    conn = null; // prevent any future access
//                }
//                return new PasswordAuthentication(user, psw.toCharArray());
//            }
//        };
//
//        Authenticator.setDefault(myAuth);
//        String WSDLport = getWSDLPort();
//        if (WSDLport.equalsIgnoreCase("")) {
//            System.out.println("erreur WSDl");
//            return null;
//        } else {
//            WebServiceMedecinEventsService service = new WebServiceMedecinEventsService(new URL("http://" + WSDLport + "/dmi-core/WebServiceMedecinEventsService?wsdl"));
//            portWebServiceMedecinEvents = service.getWebServiceMedecinEventsPort();
//
//            return portWebServiceMedecinEvents;
//        }
//    }
//
//    public UrgentWS UrgentWSWS() throws MalformedURLException {
//        Authenticator myAuth = new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                MyConnection myconn = new MyConnection();
//                Connection conn = myconn.getConnection();
//                String user = "";
//                String psw = "";
//                try (Statement stm = conn.createStatement()) {
//                    String query = "select top(1)rolet.user_name,user_pass from users_tomcat usert inner join  user_roles rolet on rolet.user_name=usert.user_name where role_name='tomcat'";
//                    ResultSet rs = stm.executeQuery(query);
//                    // Store and return result of the query            
//                    while (rs.next()) {
//                        user = rs.getString("user_name");
//                        psw = rs.getString("user_pass");
//                    }
//                } catch (SQLException e) {
//                    System.err.println(e.getMessage());
//                } finally {
//                    // Release connection back to the pool
//                    if (conn != null) {
//                        try {
//                            conn.close();
//                        } catch (SQLException ex) {
//
//                        }
//                    }
//                    conn = null; // prevent any future access
//                }
//                return new PasswordAuthentication(user, psw.toCharArray());
//            }
//        };
//
//        Authenticator.setDefault(myAuth);
//        String WSDLport = getWSDLPort();
//        if (WSDLport.equalsIgnoreCase("")) {
//            System.out.println("erreur WSDl");
//            return null;
//        } else {
//            UrgentWSService service = new UrgentWSService(new URL("http://" + WSDLport + "/dmi-core/UrgentWSService?wsdl"));
//            portUrgentWS = service.getUrgentWSPort();
//
//            return portUrgentWS;
//        }
//    }
//}