package org.gearset;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Font fontUsed = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    private static final Paragraph preface = new Paragraph();
    private static final Paragraph subPreface = new Paragraph();
    private static boolean afterCommandSpaceEnabled = false;
    private static boolean subPrefaceEnabled = false;

    public static void main(String[] args) {
        try {
            readAndEvaluationTxt();
            Document document = new Document();
            String FILE = "output.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addContentToPDF(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readAndEvaluationTxt() {
        try {

            File myObject = new File("input.txt");
            Scanner myReader = new Scanner(myObject);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.charAt(0) == '.') {
                    afterCommandSpaceEnabled = true;
                    switch (data) {
                        case ".large":
                            fontUsed = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.NORMAL);
                            break;
                        case ".normal":
                            fontUsed = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
                            break;
                        case ".paragraph":
                            afterCommandSpaceEnabled = false;
                            preface.add(new Paragraph("\n"));
                            break;
                        case ".italics":
                            fontUsed = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC);
                            break;
                        case ".regular":
                            fontUsed = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
                            break;
                        case ".underline":
                            fontUsed = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE);
                            break;
                        case ".bold":
                            fontUsed = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
                            break;
                        case ".fill":
                            subPreface.setAlignment(Element.ALIGN_JUSTIFIED);
                            break;
                        case ".nofill":
                            preface.add(subPreface);
                            subPrefaceEnabled = false;
                            break;
                        default:
                            subPreface.setSpacingBefore(-16f);
                            afterCommandSpaceEnabled = false;
                            if (data.contains(".indent")) {
                                String[] splitIndent = data.split("\\s+");
                                int indentValue = Integer.parseInt(splitIndent[1]);
                                if (indentValue > 0) {
                                    subPrefaceEnabled = true;
                                    subPreface.setIndentationLeft(indentValue * 10);
                                } else {
                                    preface.add(new Paragraph("\n"));
                                }

                            }
                    }
                } else {
                    Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(data.substring(0, 1));

                    if (!matcher.find() && afterCommandSpaceEnabled)
                        preface.add(" ");

                    if (subPrefaceEnabled)
                        subPreface.add(new Phrase(data, fontUsed));
                    else
                        preface.add(new Phrase(data, fontUsed));

                }

                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found!");
            e.printStackTrace();
        } catch (Exception ee) {
            System.out.println("Generic error occurred!!!");
            ee.printStackTrace();
        }
    }

    private static void addMetaData(Document document) {
        document.addTitle("PDF generated from instruction in TXT file");
        document.addSubject("Using iText to generate it.");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Sanjeev Kumar");
        document.addCreator("Sanjeev Kumar");
    }

    private static void addContentToPDF(Document document) throws DocumentException {
        int totalNumberOfPdfPages = 3;
        for (int i = 0; i < totalNumberOfPdfPages; i++) {
            Paragraph forPaging = new Paragraph();
            String pageNumber = Integer.toString(i + 1);
            fontUsed = new Font(Font.FontFamily.COURIER, 8);
            forPaging.add(Element.HEADER,
                    new Paragraph("Page: " + pageNumber + " of " + totalNumberOfPdfPages + "\n\n", fontUsed));
            document.add(forPaging);
            document.add(preface);
            document.newPage();
        }
    }
}
