package com.uday.flightreservation.utility;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.uday.flightreservation.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class PdfGenerator {

    public void generatePdfFile(Reservation reservation, String filePath){
        //This is the document object we assign to pdfwriter to create a pdf

        Document document=new Document();

        try {
            //pdf writer is given an document to write and store on the location
            PdfWriter.getInstance(document,new FileOutputStream(filePath));
            document.open();
            document.add(generatePdfTable(reservation,filePath));
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private PdfPTable generatePdfTable(Reservation reservation, String filePath) {

        //This would create a table with two columns
        PdfPTable table=new PdfPTable(2);

        //using cell we will input content into each column of a row
        PdfPCell cell;

        cell=new PdfPCell();

        //A new cell of column span 2
        cell.setColspan(2);

        //This will set the content in cell
        cell.setPhrase(new Phrase("Flight Itinerary"));

        //The cell need to be added to table
        table.addCell(cell);

        cell=new PdfPCell();
        cell.setColspan(2);
        cell.setPhrase(new Phrase("light Details"));

        //This completes first row of Pdf table
        table.addCell(cell);

        table.addCell("First Name");
        table.addCell(reservation.getPassenger().getFirstName());

        table.addCell("Airlines Name");
        table.addCell(reservation.getFlight().getOperatingAirlines());

        return table;
    }

}

