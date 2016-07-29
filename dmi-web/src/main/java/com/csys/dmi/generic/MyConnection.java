package com.csys.dmi.generic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MyConnection {
 
    private DataSource dataSource;
 
    public MyConnection() {
        try {
 
            InitialContext context = new InitialContext();
            
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/dmi");
 
        } catch (NamingException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}