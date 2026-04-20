import java.util.HashMap;
import java.util.Map;

// Base Interface extending Cloneable
interface Document extends Cloneable {
    Document clone();
    void open();
    String getType();
}

// Concrete Implementation: PdfDocument
class PdfDocument implements Document {
    private String fileName;
    private String author;
    private int pageCount;
    private String name; // From diagram

    public PdfDocument() {}

    public void setDetails(String fileName, String author, int pageCount) {
        this.fileName = fileName;
        this.author = author;
        this.pageCount = pageCount;
    }

    @Override
    public Document clone() {
        PdfDocument clone = new PdfDocument();
        clone.setDetails(this.fileName, this.author, this.pageCount);
        return clone;
    }

    @Override
    public void open() {
        System.out.println("Opening PDF Document: " + fileName + " by " + author + " (" + pageCount + " pages)");
    }

    @Override
    public String getType() { return "PDF"; }

    @Override
    public String toString() {
        return "Type: PDF, File: " + fileName + ", Author: " + author + ", Pages: " + pageCount;
    }
}

// Concrete Implementation: TextDocument
class TextDocument implements Document {
    private Integer filePath; // Typed as Integer in diagram
    private String fileNameStr; // For output logic
    private String encoding;
    private int wordCount;

    public TextDocument() {}

    public void setDetails(String fileNameStr, String encoding, int wordCount) {
        this.fileNameStr = fileNameStr;
        this.encoding = encoding;
        this.wordCount = wordCount;
    }

    @Override
    public Document clone() {
        TextDocument clone = new TextDocument();
        clone.setDetails(this.fileNameStr, this.encoding, this.wordCount);
        return clone;
    }

    @Override
    public void open() {
        System.out.println("Opening Text Document: " + fileNameStr + " with encoding: " + encoding + " (" + wordCount + " words)");
    }

    @Override
    public String getType() { return "Text"; }

    @Override
    public String toString() {
        return "Type: Text, Path: " + fileNameStr + ", Encoding: " + encoding + ", Words: " + wordCount;
    }
}

// Concrete Implementation: SpreadsheetDocument
class SpreadsheetDocument implements Document {
    private String spreadsheetName;
    private int rowCount;
    private int columnCount;

    public SpreadsheetDocument() {}

    public void setDetails(String spreadsheetName, int rowCount, int columnCount) {
        this.spreadsheetName = spreadsheetName;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    @Override
    public Document clone() {
        SpreadsheetDocument clone = new SpreadsheetDocument();
        clone.setDetails(this.spreadsheetName, this.rowCount, this.columnCount);
        return clone;
    }

    @Override
    public void open() {
        System.out.println("Opening Spreadsheet Document: " + spreadsheetName + " (" + rowCount + " rows, " + columnCount + " columns)");
    }

    @Override
    public String getType() { return "Spreadsheet"; }

    @Override
    public String toString() {
        return "Type: Spreadsheet, Name: " + spreadsheetName + ", Rows: " + rowCount + ", Columns: " + columnCount;
    }
}

// Registry to manage prototypes
class DocumentRegistry {
    private Map<String, Document> prototypes = new HashMap<>();

    public DocumentRegistry() {
        System.out.println("Creating a PDF Document prototype.");
        prototypes.put("PDF", new PdfDocument());

        System.out.println("Creating a Text Document prototype.");
        prototypes.put("Text", new TextDocument());

        System.out.println("Creating a Spreadsheet Document prototype.");
        prototypes.put("Spreadsheet", new SpreadsheetDocument());
    }

    public Document createDocument(String type) {
        return prototypes.get(type).clone();
    }
}

// Main Execution Class
public class ProcessedDocument {
    public static void main(String[] args) {
        DocumentRegistry registry = new DocumentRegistry();

        // 1. Annual Report PDF
        PdfDocument doc1 = (PdfDocument) registry.createDocument("PDF");
        doc1.setDetails("annual_report_2024.pdf", "Acme Corp", 150);
        doc1.open();
        System.out.println(doc1);

        // 2. Meeting Notes Text
        TextDocument doc2 = (TextDocument) registry.createDocument("Text");
        doc2.setDetails("meeting_notes.txt", "UTF-8", 250);
        doc2.open();
        System.out.println(doc2);

        // 3. Sales Data Spreadsheet
        SpreadsheetDocument doc3 = (SpreadsheetDocument) registry.createDocument("Spreadsheet");
        doc3.setDetails("sales_data_q1.xlsx", 1000, 20);
        doc3.open();
        System.out.println(doc3);

        // 4. Summary Report PDF
        PdfDocument doc4 = (PdfDocument) registry.createDocument("PDF");
        doc4.setDetails("summary_report.pdf", "Acme Corp", 30);
        doc4.open();
    }
}