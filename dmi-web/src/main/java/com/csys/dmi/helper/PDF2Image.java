package com.csys.dmi.helper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

@SuppressWarnings("unchecked")
public class PDF2Image {
    public static List<String> convert (String pdfPath,String num)throws IOException
    {
        List<String> imageList = new ArrayList<String>();
        PDDocument document = PDDocument.load(pdfPath+".pdf");
        List<PDPage> list = document.getDocumentCatalog().getAllPages();
          
        int pageNumber = 1;
        for (PDPage page : list) 
        {
            BufferedImage image = page.convertToImage();
            File outputfile = new File(pdfPath+pageNumber+".png");               
            ImageIO.write(image, "png", outputfile);
            imageList.add(num+pageNumber+".png");
            pageNumber++;
        }
        document.close();            
        
        return imageList;
    }
}
