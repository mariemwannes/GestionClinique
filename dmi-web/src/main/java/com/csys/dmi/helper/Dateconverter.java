package com.csys.dmi.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateconverter {
     public static String DatetoDateString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public static String DatetoHeureString(Date date) {
        SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm");
        return formatter1.format(date);
    }
    
       public static String DatetoHeureDateString(Date date) {
        
        return "le  " +DatetoDateString(date)+" à "+DatetoHeureString(date);
    }
}