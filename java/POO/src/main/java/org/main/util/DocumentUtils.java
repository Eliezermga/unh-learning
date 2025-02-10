package org.main.util;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

public class DocumentUtils {
    private String[] columnNames;
    private DefaultTableModel model;
    private String fileName;
    private float[] columnWidths;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private static Font fontColumns = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);

    public DocumentUtils(String[] columnNames, DefaultTableModel model, String fileName, float[] columnWidths) {
        this.columnNames = columnNames;
        this.model = model;
        this.fileName = fileName;
        this.columnWidths = columnWidths;
    }

    public void print() {
        Document document = new Document();
        String path = getDownloadsFolder();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path + this.fileName + ".pdf"));
            document.open();
            addPageTitle(document);
            PdfPTable table = new PdfPTable(this.columnNames.length);
            table.setWidths(this.columnWidths);
            addTableHeader(table);
            addRows(table);
            document.add(table);
            File pdfFile = new File(path + this.fileName + ".pdf");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(pdfFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of(this.columnNames)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle, fontColumns));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table) {
        Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < columnNames.length; j++) {
                String value = model.getValueAt(i, j).toString();
                PdfPCell cell = new PdfPCell(new Paragraph(value, font));
                table.addCell(cell);
            }
        }

    }

    private String getDownloadsFolder() {
        String downloadsPath = null;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            downloadsPath = System.getenv("USERPROFILE") + "\\Downloads\\";
        } else if (os.contains("mac")) {
            downloadsPath = System.getProperty("user.home") + "/Downloads/";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            downloadsPath = System.getProperty("user.home") + "/Downloads/";
        } else if (os.contains("sunos")) {
            downloadsPath = System.getProperty("user.home") + "/Downloads/";
        }

        return downloadsPath;
    }
    private  void addPageTitle(Document document) throws DocumentException, IOException {
        ClassLoader classLoader = DocumentUtils.class.getClassLoader();
        String path = classLoader.getResource("unh.jpg").getPath();
        Image img = Image.getInstance(path);
        img.scaleToFit(100, 100);
        document.add(img);
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Faculté d'informatique", headerFont));
        preface.add(new Paragraph("Programmation orienté objet", smallBold));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Liste des "+this.fileName+" générée en " + dateFormat.format(new Date()), smallBold));
        addEmptyLine(preface, 1);
        document.add(preface);
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}

