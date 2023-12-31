package es.ufv.dis.final2022.back;
import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

public class PDFcreator {



        public void generarPDF(List<Persona> productoList) {
            try {
                Document doc = new Document(PageSize.A4, 50, 50, 100, 72);
                PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.dir") + "/persona/backup.pdf"));//"../../../../../../../../../productos/backup.pdf"));
                doc.open();
                // Convertimos la lista en un String con formato JSON
                String text = new Gson().toJson(productoList);
                Paragraph p = new Paragraph(text);
                p.setAlignment(Element.ALIGN_JUSTIFIED);
                doc.add(p);
                doc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
