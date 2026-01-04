// Builder Pattern – Director-based Builder Example
// Demonstrates building PDF and XML documents using the Director pattern
// The Director (DocCreationEngine) orchestrates the construction process
// Roles:
//   - Product: Document (PDFDocument, XMLDocument)
//   - Builder: DocBuilder with concrete builders PDFDocBuilder, XMLDocBuilder
//   - Director: DocCreationEngine runs the steps in order
//   - Client: main() selects a builder and reuses the same director logic

// Interface - Document
interface Document {
    // Common interface for all document types
}

// Class PDFDocument
class PDFDocument implements Document {
    // Attributes for holding the PDFDocument
    private String text;
    private String images;

    public void setText(String text) {
        this.text = text;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String toString() {
        return "PDFDocument{text=" + text + ", images=" + images + "}";
    }
}

// Class XMLDocument
class XMLDocument implements Document {
    // Attributes for holding the XMLDocument
    private String text;
    private String images;

    public void setText(String text) {
        this.text = text;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String toString() {
        return "XMLDocument{text=" + text + ", images=" + images + "}";
    }
}

// Abstract Builder
abstract class DocBuilder {
    public abstract void createDocument();

    public abstract void createText();

    public abstract void createImages();

    public abstract Document getDocument();
}

// PDFDocBuilder
class PDFDocBuilder extends DocBuilder {
    private PDFDocument pdfDoc;

    public void createDocument() {
        this.pdfDoc = new PDFDocument();   // allocate product
        System.out.println("Creating PDF Document.");
    }

    public void createText() {
        System.out.println("Creating text for PDF Document.");
        if (pdfDoc != null) {
            pdfDoc.setText("PDF Text Content");
        }
    }

    public void createImages() {
        System.out.println("Creating images for PDF Document.");
        if (pdfDoc != null) {
            pdfDoc.setImages("PDF Images");
        }
    }

    public Document getDocument() {
        System.out.println("Fetching PDF Document.");
        return this.pdfDoc;
    }
}

// XMLDocBuilder
class XMLDocBuilder extends DocBuilder {
    private XMLDocument xmlDoc;

    public void createDocument() {
        this.xmlDoc = new XMLDocument();   // allocate product
        System.out.println("Creating XML Document.");
    }

    public void createText() {
        System.out.println("Creating text for XML Document.");
        if (xmlDoc != null) {
            xmlDoc.setText("XML Text Content");
        }
    }

    public void createImages() {
        System.out.println("Creating images for XML Document.");
        if (xmlDoc != null) {
            xmlDoc.setImages("XML Images");
        }
    }

    public Document getDocument() {
        System.out.println("Fetching XML Document.");
        return this.xmlDoc;
    }
}

// Director - DocCreationEngine
class DocCreationEngine {
    public void generateDocument(DocBuilder builder) {
        // Director orchestrates the construction process
        builder.createDocument();
        builder.createText();
        builder.createImages();
    }
}

// Client
public class DocumentBuilderDemo {
    public static void main(String[] args) {
        System.out.println("== Director-based Builder Pattern ==\n");
        DocCreationEngine engine = new DocCreationEngine();
        
        // Creating PDF Document
        System.out.println("--- Creating PDF Document ---");
        PDFDocBuilder pdfDocBuilder = new PDFDocBuilder();
        engine.generateDocument(pdfDocBuilder);          // run same sequence
        PDFDocument pdfDocument = (PDFDocument) pdfDocBuilder.getDocument();
        System.out.println("Result: " + pdfDocument);

        System.out.println("\n--- Creating XML Document ---");
        // Creating XML Document
        XMLDocBuilder xmlDocBuilder = new XMLDocBuilder();
        engine.generateDocument(xmlDocBuilder);
        XMLDocument xmlDocument = (XMLDocument) xmlDocBuilder.getDocument();
        System.out.println("Result: " + xmlDocument);

        System.out.println("\n✓ Director pattern ensures consistent construction process");
        System.out.println("✓ Same construction steps for different document types");
    }
}
