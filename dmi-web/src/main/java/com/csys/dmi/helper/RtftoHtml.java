package com.csys.dmi.helper;

import javax.swing.*;
import javax.swing.text.*;
import java.io.*;

public class RtftoHtml {

    public static String HTMLTortf(Reader html) throws IOException, BadLocationException {
        JEditorPane p = new JEditorPane();
        p.setContentType("text/html");
        EditorKit kitHtml = p.getEditorKitForContentType("text/html");
        try {
            kitHtml.read(html, p.getDocument(), 0);
            kitHtml = null;
            EditorKit kitRtf = p.getEditorKitForContentType("text/rtf");
            OutputStream writer = new ByteArrayOutputStream();
            kitRtf.write(writer, p.getDocument(), 0, p.getDocument().getLength());
            String result = writer.toString();
            result = result.replaceAll("\\\\line", "\\line ");
            result = result.replaceAll("''", "'");
            return result;
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertRtfToHtml(String txt) throws IOException {
        JTextPane p = new JTextPane();
        p.setContentType("text/rtf");
        EditorKit kitRtf = p.getEditorKitForContentType("text/rtf");
        try {
            txt = txt.replaceAll("\\\\line", " <br> ");
            kitRtf.read(new StringReader(txt), p.getDocument(), 0);
            kitRtf = null;
            EditorKit kitHtml = p.getEditorKitForContentType("text/html");
            Writer writer = new StringWriter();
            kitHtml.write(writer, p.getDocument(), 0, p.getDocument().getLength());
            String result = writer.toString();
//            result = result.
            return result;
        } catch (BadLocationException e) {
            return "";
        }
}
};
