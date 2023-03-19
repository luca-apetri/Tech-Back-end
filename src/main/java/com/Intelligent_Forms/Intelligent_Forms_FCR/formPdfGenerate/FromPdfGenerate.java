package com.Intelligent_Forms.Intelligent_Forms_FCR.formPdfGenerate;


import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.Form;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Data
@Service
@RequiredArgsConstructor
public class FromPdfGenerate {

    private static final Font TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    private static final Font TEXT_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12);


    public FileSystemResource generatePdf(User user, Form form) throws DocumentException, IOException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("form.pdf"));
        document.open();
        //TODO generate a proper pdf. This is a prototype one for testing
        Paragraph p = new Paragraph("CERERE");
        p.setAlignment(Element.ALIGN_CENTER);
        p.setFont(FontFactory.getFont(FontFactory.COURIER_OBLIQUE));
        document.add(p);
        document.add(new Paragraph("Subsemnatul _____________ "
                + user.getName() + " C.N.P. "
                + form.getFormSubmissions().get(0).getCnp() + user.getAddress() + " solicit inmatriculare masina. "));
        document.add(new Paragraph());
        document.add(new Paragraph(" e-mail " + user.getEmail()));
        document.add(new Paragraph(" solicit: "));


        document.close();
        return new FileSystemResource("F:\\New folder\\Tech-Back-end\\form.pdf");
    }
}
