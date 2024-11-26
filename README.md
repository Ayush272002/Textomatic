# Textomatic

**Textomatic** is a simple tool that converts plain text files into well-formatted PDF documents. This project allows you to format text based on commands within the input file, such as changing fonts, sizes, adding indentation, bold, italics, and more. It's built using Java and iText library to generate PDFs from text inputs.

## Features

- **Text Formatting:** Supports font sizes, bold, italics, and regular text formatting.
- **Paragraph Handling:** Allows you to define paragraphs with different indentations and fill options (justified or normal).
- **Pagination:** Automatically adds page numbers to the document.
- **Customization:** Customize text content and formatting commands through an input text file.
- **Multiple Pages:** Automatically handles pagination for large documents.

## Commands

The input text file can include special commands to control how the text is formatted:

- **.large** – Increases the font size.
- **.normal** – Resets the font size to normal.
- **.paragraph** – Starts a new paragraph.
- **.bold** – Sets the font to bold.
- **.italic** – Sets the font to italic.
- **.regular** – Resets the font to regular.
- **.indent <number>** – Indents the paragraph by the specified number.
- **.fill** – Justifies the text (fills the paragraph).
- **.nofill** – Sets the default text alignment (left-aligned).
  
### Example Input:

```
.large
My PDF Document
.normal
.paragraph
This is my first pdf document, and it is formatted well.
.fill
Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
.nofill
.indent 4
This is a new paragraph with indentation.
```

### Example Output:

The generated PDF will include the content formatted as per the commands, with appropriate font sizes, styles, indentation, and page numbers.

## Requirements

- **Java 8 or higher** – Make sure you have Java 8 or later installed.
- **iText 5.5.13** – iText is used to generate the PDF from the formatted text.

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Ayush272002/Textomatic.git
   ```

2. **Navigate to the project directory:**
   ```bash
   cd Textomatic
   ```

3. **Compile and run the project:**
   - If you're using **Maven**, you can compile and run the project by using the following commands:
     ```bash
     mvn clean install
     mvn exec:java
     ```
   - Alternatively, if you're using an IDE like **IntelliJ IDEA** or **Eclipse**, you can import the project and run the `Main` class directly.

4. **Input File:**
   Place your input text file (e.g., `input.txt`) in the project directory, or specify the path to the file in the code.

5. **Output File:**
   The generated PDF file will be saved as `output.pdf` in the project directory.

## Usage

- The program reads the input text file line by line.
- It parses and applies commands to format the text according to the specified rules.
- The output is a PDF document that reflects the formatting and pagination instructions from the input file.

## Example Command Line Execution:

```bash
java -jar Textomatic.jar
```

This will take the content from `input.txt`, apply formatting based on the commands inside the file, and generate a PDF file `output.pdf` in the working directory.


## Contributing

1. Fork this repository.
2. Create your feature branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request.

## License

This project is licensed under the MIT License – see the [LICENSE.md](LICENSE.md) file for details.

---