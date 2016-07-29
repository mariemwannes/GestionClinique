/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.dmi.helper;

import java.io.IOException;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;

/**
 *
 * @author Achraf
 */
public class PdfPath {
     public static String   parseXmlFile()
    {
        String pdfPath ="";
         Document dom;
      
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            dom = db.parse("C://CliniSYS//Config.xml");
            
             //get the root element
            Element docEle = dom.getDocumentElement();

        //get a nodelist of elements

        NodeList nl = docEle.getElementsByTagName("Configuration" );
        if (nl != null && nl.getLength() > 0)
        {
            for (int i = 0; i < nl.getLength(); i++) {

                Element el = (Element) nl.item(i);
		String idConfig = el.getAttribute("id");
                if(idConfig.equals("Conversion"))
                {
                    pdfPath = getTextValue(el,"PdfPath");
                }                
            }
        }         
        }
        catch (ParserConfigurationException pce) {
        } catch (SAXException se) {
        } catch (IOException ioe) {
        }
        return pdfPath;
    }

    
    private static  String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
    
}
