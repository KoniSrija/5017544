package designs;


 interface Document {
    void open();
    void save();
    void close();
}

 interface WordDocument extends Document {
    void print();
}


 interface PdfDocument extends Document {
    void encrypt();
}

 
 interface ExcelDocument extends Document {
    void calculate();
}

 
 class WordDocumentImpl implements WordDocument {
    @Override
    public void open() {
        System.out.println("Opening Word document...");
    }

    @Override
    public void save() {
        System.out.println("Saving Word document...");
    }

    @Override
    public void close() {
        System.out.println("Closing Word document...");
    }

    @Override
    public void print() {
        System.out.println("Printing Word document...");
    }
}

 
class PdfDocumentImpl implements PdfDocument {
    @Override
    public void open() {
        System.out.println("Opening PDF document...");
    }

    @Override
    public void save() {
        System.out.println("Saving PDF document...");
    }

    @Override
    public void close() {
        System.out.println("Closing PDF document...");
    }

    @Override
    public void encrypt() {
        System.out.println("Encrypting PDF document...");
    }
}

 
 class ExcelDocumentImpl implements ExcelDocument {
    @Override
    public void open() {
        System.out.println("Opening Excel document...");
    }

    @Override
    public void save() {
        System.out.println("Saving Excel document...");
    }

    @Override
    public void close() {
        System.out.println("Closing Excel document...");
    }

    @Override
    public void calculate() {
        System.out.println("Calculating Excel document...");
    }
}

 
 abstract class DocumentFactory {
    public abstract Document createDocument();
}

 
class PDFDocumentFactory extends DocumentFactory {
    @Override
    public PdfDocument createDocument() {
        return new PdfDocumentImpl();
    }
}

 
class WordDocumentFactory extends DocumentFactory {
    @Override
    public WordDocument createDocument() {
        return new WordDocumentImpl();
    }
}

 
 class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public ExcelDocument createDocument() {
        return new ExcelDocumentImpl();
    }
}

 class FactoryMethod {
    public static void main(String[] args) {
        DocumentFactory pdfFactory = new PDFDocumentFactory();
        Document pdfDocument = pdfFactory.createDocument();
        pdfDocument.open();
        pdfDocument.save();
        pdfDocument.close();
        if (pdfDocument instanceof PdfDocument) {
            ((PdfDocument) pdfDocument).encrypt();
        }

        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDocument = wordFactory.createDocument();
        wordDocument.open();
        wordDocument.save();
        wordDocument.close();
        if (wordDocument instanceof WordDocument) {
            ((WordDocument) wordDocument).print();
        }

        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDocument = excelFactory.createDocument();
        excelDocument.open();
        excelDocument.save();
        excelDocument.close();
        if (excelDocument instanceof ExcelDocument) {
            ((ExcelDocument) excelDocument).calculate();
        }
    }
}
