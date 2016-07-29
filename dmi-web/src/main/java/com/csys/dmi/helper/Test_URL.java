/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.dmi.helper;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Achraf
 */
public class Test_URL {
    
     public String getURL(String url_local , String url_remote) {
          String Retour_URL ="http://ErreurUrl/";
          Boolean bLocal =isValide(url_local) ;
          if(bLocal==true){ Retour_URL=url_local;}
          else{
          Boolean bRemote =isValide(url_remote);
           if(bRemote==true){ Retour_URL=url_remote;}
          }
 
         return Retour_URL;
     }
    
     private Boolean isValide(String url) {
        URLConnection urlConnection;
        InputStream httpStream;
        try {
            URL fileURL = new URL(url);
            urlConnection = fileURL.openConnection();     // open URL (HTTP query)
            httpStream = urlConnection.getInputStream();  // Open data stream
            return true;
        } catch (java.net.MalformedURLException ex) {
            return false;
        } catch (java.io.IOException ex) {
            return false;
        }
    }
    
}
