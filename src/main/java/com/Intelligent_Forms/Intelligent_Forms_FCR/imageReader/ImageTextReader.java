package com.Intelligent_Forms.Intelligent_Forms_FCR.imageReader;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.io.File;

public class ImageTextReader {
    public String ImageReader(File image) throws TesseractException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("Tess4J/tessdata");
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        String result = tesseract.doOCR(image, new Rectangle(672, 158));
        System.out.println(result);
        return result;

    }
}
