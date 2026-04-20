# Lab: Prototype Design Pattern Implementation

This project demonstrates the **Prototype Design Pattern** in Java. The pattern allows for creating new document objects by copying an existing "prototype" instance, which avoids the overhead of manual initialization.

## 📐 Design Diagram

The implementation is based on the provided class diagram which establishes a document hierarchy:

<img width="1125" height="675" alt="Document Prototype Design Pattern" src="https://github.com/user-attachments/assets/523f41cf-03aa-4f0a-88c2-6b9ef5187b5b" />



### Core Structure:
* **Document (<<interface>>):** The base prototype interface defining `clone()`, `open()`, and `getType()`. It extends the Java `Cloneable` interface.
* **DocumentRegistry:** The central manager that stores private prototypes (`pdfPrototype`, `textDocumentPrototype`, and `spreadsheetPrototype`). It handles the logic of returning clones to the client.
* **Concrete Classes:** * `PdfDocument`: Manages PDF-specific attributes like `fileName`, `author`, and `pageCount`.
    * `TextDocument`: Manages text data such as `filePath`, `encoding`, and `wordCount`.
    * `SpreadsheetDocument`: Manages data for `spreadsheetName`, `rowCount`, and `columnCount`.

## 🛠️ Implementation Logic

The system operates through the following steps:

1.  **Prototype Registration:** The `DocumentRegistry` initializes a default instance of each document type upon creation.
2.  **Cloning Mechanism:** Instead of using the `new` keyword for every document, the system calls the `clone()` method on the existing prototypes stored in the registry.
3.  **Client Customization:** Once a clone is produced, the `ProcessedDocument` class (Main) assigns specific values to the object's attributes before execution.
