package com.csys.dmi.helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.hwpf.converter.HtmlDocumentFacade;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHyperlink;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DocxConverter {

    public static void main(String[] args) throws Exception {

    }

    private HtmlDocumentFacade htmlDocumentFacade;
    private Element page;
    private Element window;
    private XWPFDocument docx;
    private String imgFolderPath;
    private boolean bConfraternellement;
    private final String imgPath;

    public DocxConverter(InputStream input) throws IOException, InvalidFormatException, ParserConfigurationException {

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        {
            imgFolderPath = "C:\\Users\\dell\\Desktop\\";

            File folder = new File(imgFolderPath);
            if (!folder.canRead()) {
                folder.mkdirs();
            }
            folder = null;
        }

        imgPath = "";

        OPCPackage container = OPCPackage.open(input);
        docx = new XWPFDocument(container);


        this.htmlDocumentFacade = new HtmlDocumentFacade(document);
        window = htmlDocumentFacade.createBlock();
        page = htmlDocumentFacade.createBlock();

        Element center = createElement("center");
        this.htmlDocumentFacade.getBody().appendChild(window);
        center.appendChild(window);
        htmlDocumentFacade.getBody().appendChild(window);
        window.appendChild(page);


    }

    private DocxConverter() {
        imgPath = "";
    }

    public static String convert(InputStream input) throws Exception {

        DocxConverter converter = new DocxConverter(input);



        List<IBodyElement> elements = converter.docx.getBodyElements();
        for (IBodyElement element : elements) {

            if (element instanceof XWPFParagraph) {
                converter.processParagraph((XWPFParagraph) element, converter.page);



            } else if (element instanceof XWPFTable) {
                converter.processTable((XWPFTable) element, converter.page);
            }
        }

        converter.htmlDocumentFacade.updateStylesheet();

        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            transformer.transform(new DOMSource(converter.htmlDocumentFacade.getDocument()), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }

    }

    private void processTable(XWPFTable t, Element container) {
        // TODO Auto-generated method stub
        Element p = htmlDocumentFacade.createParagraph();
        Element table = htmlDocumentFacade.createTable();

        List<XWPFTableRow> rows = t.getRows();
        for (XWPFTableRow row : rows) {
            processRow(row, table);
        }
        p.appendChild(table);
        table.setAttribute("border", "1");
        table.setAttribute("cellspacing", "0");
        table.setAttribute("cellpadding", "3");
        table.setAttribute("style", "border-collapse: collapse;");
        container.appendChild(p);
    }

    private void processRow(XWPFTableRow row, Element table) {
        Element tr = htmlDocumentFacade.createTableRow();
        List<XWPFTableCell> cells = row.getTableCells();
        for (XWPFTableCell cell : cells) {
            processCell(cell, tr);
        }
        // resolve row style.
        {
            StringBuilder sb = new StringBuilder();
            if (row.getHeight() != 0) {
                sb.append("height:").append(row.getHeight() / 1440.0).append("in");
            }
            addStyle(tr, sb.toString());
        }
        table.appendChild(tr);
    }

    private void processCell(XWPFTableCell cell, Element tr) {
        Element td = htmlDocumentFacade.createTableCell();
        htmlDocumentFacade.createTableColumn();

        List<XWPFParagraph> paragraphs = cell.getParagraphs();
        for (XWPFParagraph p : paragraphs) {
            try {
                processTableParagraph(p, td);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //resolve cell styles
        StringBuilder sb = new StringBuilder();
        {
            CTTc c = cell.getCTTc();
            sb.append("width:").append(c.getTcPr().getTcW().getW().doubleValue() / 1440.0).append("in");
            if (cell.getColor() != null) {
                sb.append(";background-color:#").append(cell.getColor());
            }
            // resovel text alignment
            CTJc jc = c.getPArray(0).getPPr().getJc();


            if (jc != null) {


                switch (jc.getVal().intValue()) {
                    case STJc.INT_RIGHT:
                        sb.append(";text-align:").append("right");
                        break;
                    case STJc.INT_CENTER:
                        sb.append(";text-align:").append("center");
                        break;

                }
                try {
                    // if the verticalAlignment set
                    XWPFTableCell.XWPFVertAlign valign = cell.getVerticalAlignment();
                    switch (valign) {
                        case BOTTOM:
                            sb.append(";vertical-align:").append("bottom");
                            break;
                        case CENTER:
                            sb.append(";vertical-align:").append("middle");
                            break;
                        case TOP:
                            sb.append(";vertical-align:").append("top");
                            break;
                        default:
                            sb.append(";vertical-align:").append("top");
                            break;
                    }
                } catch (NullPointerException ex) {
                    sb.append(";vertical-align:").append("top");
                }
            }
            addStyle(td, sb.toString());
            tr.appendChild(td);
        }

    }

    private void processTableParagraph(XWPFParagraph paragraph, Element page) throws IOException {
        processParagraphMulti(paragraph, page, true);
    }

    private void processParagraph(XWPFParagraph paragraph, Element page) throws IOException {
        processParagraphMulti(paragraph, page, false);
    }


    private void processParagraphMulti(XWPFParagraph paragraph, Element page, boolean isTableParagraph) throws IOException {

        Element ctner = null;
        Borders bor = paragraph.getBorderTop();

        if (paragraph.getText().length() == 0 && paragraph.getRuns().size() == 0) {

            ctner = htmlDocumentFacade.createParagraph();
            ctner.setTextContent("\u00a0");
            page.appendChild(ctner);
            return;
        } else if (paragraph.getStyle() != null && !"-1".equals(paragraph.getStyle())) {

            String typeStr = new String(paragraph.getStyle());
            Integer type;
            try {
                // title
                type = Integer.parseInt(typeStr);
                switch (type) {
                    case 1:
                        ctner = createElement("h1");
                        break;
                    case 2:
                        ctner = createElement("h2");
                        break;
                    case 3:
                        ctner = createElement("h3");
                        break;
                    case 4:
                        ctner = createElement("h4");
                        break;
                    case 5:
                        ctner = createElement("h5");
                        break;
                    default:
                        ctner = htmlDocumentFacade.createParagraph();
                }
            } catch (NumberFormatException ex) {
                // list
                ctner = createElement("li");
                if (isTableParagraph) {
                    ctner.setAttribute("style", "list-style:none");
                }
            }
        }

        if (ctner == null) {
            if (isTableParagraph) {
                ctner = htmlDocumentFacade.createBlock();
            } else {
                ctner = htmlDocumentFacade.createParagraph();
            }
        }

        List<XWPFRun> runs = paragraph.getRuns();
        for (XWPFRun r : runs) {

            processRun(ctner, r);

        }
        // resolve paragraph styles;
        if (paragraph.getAlignment() != ParagraphAlignment.LEFT) {
            switch (paragraph.getAlignment()) {
                case CENTER:
                    addStyle(ctner, "text-align:center;");
                    break;
                case RIGHT:
                    addStyle(ctner, "text-align:right");
                    break;
            }
        }

        if (!paragraph.getBorderBottom().toString().equals("NONE")) {

            addStyle(ctner, "border-style:solid;");
        }
        if (("Confraternellement".equals(paragraph.getText().trim())) || (bConfraternellement == true)) {
            addStyle(ctner, "text-align:right;");

            if (bConfraternellement) {
                bConfraternellement = false;
            } else {
                bConfraternellement = true;
            }
        }

        page.appendChild(ctner);
    }

    private void processRun(Element container, XWPFRun r) throws IOException {

        List<XWPFPicture> pics = r.getEmbeddedPictures();
        if (!pics.isEmpty()) {
            processImage(container, pics);
        }

        Element runCtner = null;
        switch (r.getSubscript()) {
            case SUBSCRIPT:
                runCtner = createElement("sub");
                break;
            case SUPERSCRIPT:
                runCtner = createElement("sup");
                break;
            default:
                runCtner = htmlDocumentFacade.getDocument().createElement("span");
        }

        StringBuilder sb = new StringBuilder();
        if (r.getColor() != null) {
            sb.append("color:#").append(r.getColor());
        }
        if (r.getFontSize() != -1) {
            sb.append(";font-size:").append(r.getFontSize()).append("pt");
        }
        if (r.getFontFamily() != null) {
            sb.append(";font-family:'").append(r.getFontFamily()).append("'");
        }
        if (r.isBold()) {
            sb.append(";font-weight:").append(800);
        }
        if (r.isItalic()) {
            sb.append(";font-style:").append("italic ");
        }
        if (r.getUnderline() != UnderlinePatterns.NONE) {
            switch (r.getUnderline()) {
                case DOUBLE:
                    sb.append(";border-bottom:").append("4px double");
                    break;
                case DOTTED:
                    sb.append(";border-bottom:").append("1px dotted");
                    break;
                case DASH:
                    sb.append(";border-bottom:").append("1px dashed");
                    break;
                default:
                    sb.append(";text-decoration:").append("underline");
                    break;
            }
        }

        try {

            XWPFHyperlinkRun hlRun = (XWPFHyperlinkRun) r;
            XWPFHyperlink hyperlink = hlRun.getHyperlink(docx);
            Element a = htmlDocumentFacade.createHyperlink(hyperlink.getURL());
            a.setAttribute("name", hyperlink.getId());
            a.setTextContent(hlRun.getText(0));
            runCtner.appendChild(a);
            if (sb.length() != 0) {
                addStyleClass(a, "a", sb.toString());
            }
        } catch (Exception ex) {

            if (r.getText(0) == null) 
            {

            } 
            else 
            {
                runCtner.setTextContent(r.getText(0));
            }

            if (sb.length() != 0) 
            {
                addStyleClass(runCtner, runCtner.getTagName(), sb.toString());
            }
        }

        container.appendChild(runCtner);
    }

    private void processImage(Element wrap, List<XWPFPicture> pics) throws IOException {

        for (XWPFPicture pic : pics) {

            XWPFPictureData data = pic.getPictureData();

            ByteArrayInputStream is = new ByteArrayInputStream(data.getData());
            BufferedImage image = ImageIO.read(is);
            String filename = data.getFileName();
            String imgFullPath = "C:\\Users\\dell\\Desktop\\" + filename;

            {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(new File(imgFullPath));
                    fos.write(data.getData());
                } catch (FileNotFoundException e) {
                } finally {
                    if (fos != null) {
                        fos.close();
                    }
                }
            }
            {
                Element img = htmlDocumentFacade.createImage(imgPath.concat(data.getFileName()));

                if (image != null && image.getWidth() > 600) 
                {
                    img.setAttribute("width", "600px");
                }
                img.setAttribute("align", "center");
                wrap.appendChild(img);

            }
        }
    }

    private void addStyleClass(Element element, String className, String style) {
        htmlDocumentFacade.addStyleClass(element, className, style);
    }

    private void addStyle(Element element, String style) {
        String exist = element.getAttribute("style");
        if (exist.equals("")) {
            element.setAttribute("style", style);
        } else {
            if (exist.endsWith(";")) {
                element.setAttribute("style", exist.concat(style));
            } else {
                element.setAttribute("style", exist.concat(";").concat(style));
            }
        }
    }

    private Element createElement(String tagName) {
        return htmlDocumentFacade.getDocument().createElement(tagName);
    }
}