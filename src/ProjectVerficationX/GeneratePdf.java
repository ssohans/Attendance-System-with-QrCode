/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectverificationx;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author SSohan
 */
public class GeneratePdf {

    GeneratePdf(String s[],File pp) throws FileNotFoundException, DocumentException, BadElementException, IOException {

        Font redFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new BaseColor(255, 0, 0));
        Font small = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD, BaseColor.BLACK);
        
        String path = pp.getAbsolutePath();
        
        Rectangle pagesize = new Rectangle(432, 252);
        //pagesize.setBackgroundColor(new BaseColor(255, 253, 208));
        Document document = new Document(pagesize);
        document.setMargins(15, 10, 0, 0);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("res/"+s[4]+".pdf"));
        document.open();

        Paragraph p = new Paragraph("Mawlana Bhashani Science & Technology University", redFont);
        document.add(p);
        p = new Paragraph("                                   Tangail,Santosh-1902", small);
        document.add(p);
        small = FontFactory.getFont(FontFactory.COURIER, 5, Font.BOLD, BaseColor.GREEN);
        p = new Paragraph("______________________________________________________________________________________________________________________________________", small);
        document.add(p);
        redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new BaseColor(0, 0, 255));
        p = new Paragraph("\n");
        document.add(p);
        p = new Paragraph("");
        document.add(new Chunk("                               Name:   "));
        document.add(new Chunk(s[0],redFont));
        document.add(p);
        document.add(new Chunk("                               Date Of Birth:  "));
        document.add(new Chunk(s[1],redFont));
        document.add(p);
        document.add(new Chunk("                               Department:   "));
        document.add(new Chunk(s[2],redFont));
        document.add(p);
        document.add(new Chunk("                               Session:   "));
        document.add(new Chunk(s[3],redFont));
        document.add(p);
        document.add(new Chunk("                               ID:   "));
        document.add(new Chunk(s[4],redFont));
        document.add(p);
        document.add(new Chunk("                               Phone:   "));
        document.add(new Chunk(s[5],redFont));
        document.add(p);
        document.add(new Chunk("                               E-mail:   "));
        document.add(new Chunk(s[6],redFont));
        document.add(p);
        document.add(new Chunk("                               Address:   "));
        document.add(new Chunk(s[7],redFont));
        document.add(p);

        p = new Paragraph("\n",small);
        document.add(p);
        p = new Paragraph("_____________                                                                    ____________");
        document.add(p);
        small = FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD, BaseColor.BLACK);
        p = new Paragraph("Student's Signataure                                                       Faculty's Signature", small);
        document.add(p);
        
        Image image1 = Image.getInstance(path);
        image1.setAbsolutePosition(10, 70);
        image1.scaleAbsolute(100, 110);
        document.add(image1);

        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = Image.getInstance("res/logo.jpg");
        image.scaleAbsolute(250, 150);
        image.setAbsolutePosition(100, 50);
        
        canvas.saveState();
        PdfGState state = new PdfGState();
        state.setFillOpacity(0.3f);
        canvas.setGState(state);
        canvas.addImage(image);
        canvas.restoreState();
        
        document.newPage();
       
        
        image = Image.getInstance("res/logo.jpg");
        image.scaleAbsolute(100, 100);
        image.setAbsolutePosition(100, 110);
        document.add(image);
        image1 = Image.getInstance("qr.png");
        image1.setAbsolutePosition(215, 100);
        image1.scaleAbsolute(100, 100);
        document.add(image1);
        
        p = new Paragraph("\n");
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        redFont = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD, BaseColor.BLACK);
        p = new Paragraph("If found please mail it to Mawlana Bhashani Science and Technology University, Tangail,"
                + "Santosh-1902.",redFont);
        
        document.add(p);
        document.close();
        
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Open Created ID Card?", "Update", dialogButton);
        
        if(dialogResult==0){
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File("res/"+s[4]+".pdf");
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                    // no application registered for PDFs
                }
            }
        }
        

        
        
        

    }
}
