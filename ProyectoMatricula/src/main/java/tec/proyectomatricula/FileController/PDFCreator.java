package tec.proyectomatricula.FileController;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tec.proyectomatricula.modelos.Curso;
import tec.proyectomatricula.modelos.PlanEstudios;

/**
 *
 * @author INTEL
 */
public class PDFCreator {

    private String HELVETICA_path = "Fonts/Helvetica-Bold.ttf";
    private String HELVETICA_BOLD_path = "Fonts/Helvetica-Bold.ttf";

    /**
     *
     * @param dest
     * @param planEstudios
     */
    public void createPDF(String dest, PlanEstudios planEstudios) {

        try (PDDocument document = new PDDocument()) {
            PDType0Font helveticaFont = PDType0Font.load(document, new File(HELVETICA_path));
            PDType0Font helveticaBoldFont = PDType0Font.load(document, new File(HELVETICA_BOLD_path));

            // Crear una página con una altura mayor
            PDRectangle customPageSize = new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight() * 3);
            PDPage page = new PDPage(customPageSize);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            try {
                // Encabezado del documento
                contentStream.setFont(helveticaBoldFont, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, customPageSize.getHeight() - 50);
                contentStream.showText("Escuela /o Área: " + planEstudios.getEscArea().getName());
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(helveticaBoldFont, 12);
                contentStream.newLineAtOffset(50, customPageSize.getHeight() - 70);
                contentStream.showText("Número de plan: " + planEstudios.getCode());
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.setFont(helveticaBoldFont, 12);
                contentStream.newLineAtOffset(50, customPageSize.getHeight() - 90);
                contentStream.showText("Vigencia: " + planEstudios.getDateValid());
                contentStream.endText();

                float y = customPageSize.getHeight() - 100;

                for (int i = 0; i < planEstudios.getBloques().size(); i++) {
                    String bloque = planEstudios.getBloques().get(i);
                    System.out.println("Processing block: " + bloque);  // Debugging line

                    // Título del bloque
                    contentStream.beginText();
                    contentStream.setFont(helveticaBoldFont, 10);
                    contentStream.newLineAtOffset(50, y - 20);
                    contentStream.showText("Bloque " + bloque);
                    contentStream.endText();

                    // Títulos de las columnas
                    contentStream.beginText();
                    contentStream.setFont(helveticaBoldFont, 10);
                    contentStream.newLineAtOffset(70, y - 40);
                    contentStream.showText("Curso");
                    contentStream.newLineAtOffset(200, 0);
                    contentStream.showText("Requisitos");
                    contentStream.newLineAtOffset(200, 0);
                    contentStream.showText("Correquisitos");
                    contentStream.endText();

                    y -= 60;

                    if (planEstudios.getBloqueCursos().containsKey(bloque)) {
                        for (Curso curso : planEstudios.getBloqueCursos().get(bloque)) {
                            contentStream.beginText();
                            contentStream.setFont(helveticaFont, 10);
                            contentStream.newLineAtOffset(70, y);

                            // Escribir el curso
                            contentStream.showText(curso.getName() + ":" + curso.getEscArea().getCode()+curso.getCode());

                            // Escribir los requisitos
                            contentStream.newLineAtOffset(200, 0);
                            String requisitos = curso.getRequisitos().stream()
                                    .map(r -> r.getName() + ":" + r.getCode())
                                    .reduce((r1, r2) -> r1 + " " + r2).orElse("");
                            contentStream.showText(requisitos);

                            // Escribir los correquisitos
                            contentStream.newLineAtOffset(200, 0);
                            String correquisitos = curso.getCorrequisitos().stream()
                                    .map(c -> c.getName() + ":" + c.getEscArea().getCode()+c.getCode())
                                    .reduce((c1, c2) -> c1 + " " + c2).orElse("");
                            contentStream.showText(correquisitos);

                            contentStream.endText();
                            y -= 20; // Espaciado entre cursos

                            System.out.println("Added course: " + curso.getName());  // Debugging line

                            // Verificar si es necesario agregar una nueva página
                            if (y < 50) {
                                contentStream.close();
                                page = new PDPage(customPageSize);
                                document.addPage(page);
                                contentStream = new PDPageContentStream(document, page);
                                y = customPageSize.getHeight() - 50;
                            }
                        }
                    } else {
                        System.out.println("No courses found for block: " + bloque);  // Debugging line
                    }

                    y -= 20; // Espaciado entre bloques
                }

            } catch (IOException e) {
                Logger.getLogger(PDFCreator.class.getName()).log(Level.SEVERE, "Error writing PDF content", e);
            } finally {
                contentStream.close();
            }

            // Verificar si el directorio existe y crearlo si no
            File file = new File(dest);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (!parentDir.mkdirs()) {
                    throw new IOException("Failed to create directory: " + parentDir);
                }
            }

            // Guardar el documento
            document.save(dest);
            System.out.println("PDF saved successfully at: " + dest);

        } catch (IOException ex) {
            Logger.getLogger(PDFCreator.class.getName()).log(Level.SEVERE, "Error creating PDF document", ex);
        }
    }
}
