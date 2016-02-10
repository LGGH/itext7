package com.itextpdf.kernel.pdf.navigation;

import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfObjectWrapper;
import com.itextpdf.kernel.pdf.PdfString;

import java.util.Map;


public abstract class PdfDestination<T extends PdfObject> extends PdfObjectWrapper<T> {

    public PdfDestination(T pdfObject) {
        super(pdfObject);
    }

    public abstract PdfObject getDestinationPage(Map<Object, PdfObject> names);

    public abstract PdfDestination replaceNamedDestination(Map<Object, PdfObject> names);

    public static PdfDestination makeDestination(PdfObject pdfObject) {

        if (pdfObject.getType() == PdfObject.String)
            return  new PdfStringDestination((PdfString) pdfObject);
        else if (pdfObject.getType() == PdfObject.Name)
            return new PdfNamedDestination((PdfName) pdfObject);
        else if (pdfObject.getType() == PdfObject.Array)
            return new PdfExplicitDestination((PdfArray) pdfObject);
        else
            throw new UnsupportedOperationException();
    }
}