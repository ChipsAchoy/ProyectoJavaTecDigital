package tec.proyectomatricula.FileController;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tec.proyectomatricula.modelos.Curso;
import tec.proyectomatricula.modelos.PlanEstudios;

public class PDFCreator {

    private String HELVETICA_path = "Fonts/Helvetica-Bold.ttf";
    private String HELVETICA_BOLD_path = "Fonts/Helvetica-Bold.ttf";

    public void createPDF(String dest, PlanEstudios planEstudios) {

        
        try (PDDocument document = new PDDocument()) {
            PDType0Font HelveticaFont = PDType0Font.load(document, new File(HELVETICA_path));
            PDType0Font HelveticaBoldFont = PDType0Font.load(document, new File(HELVETICA_BOLD_path));
            
            // Crear una página con una altura mucho mayor
            PDRectangle customPageSize = new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight() * 3); // 3 veces la altura de una página A4
            PDPage page = new PDPage(customPageSize);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(HelveticaBoldFont, 10);
                contentStream.beginText();
                //contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                contentStream.newLineAtOffset(100, customPageSize.getHeight() - 50);
                contentStream.showText("Escuela o Area: " + planEstudios.getEscArea().getName());
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(HelveticaBoldFont, 8);
                contentStream.newLineAtOffset(100, customPageSize.getHeight() - 100);
                contentStream.showText("Codigo Plan: " + planEstudios.getCode());
                contentStream.endText();

                float y = customPageSize.getHeight() - 130;
                for (int i = 0; i < planEstudios.getBloques().size(); i++) {
                    contentStream.beginText();
                    contentStream.setFont(HelveticaBoldFont, 8);
                    contentStream.newLineAtOffset(120, y - i * 50);
                    contentStream.showText(planEstudios.getBloques().get(i) + "                         Requisitos                      Correquistos");
                    for (int j = 0; j < planEstudios.getBloqueCursos().get(planEstudios.getBloques().get(i)).size(); j++) {

                        contentStream.setFont(HelveticaFont, 8);
                        Curso curso = planEstudios.getBloqueCursos().get(planEstudios.getBloques().get(i)).get(j);
                        contentStream.newLineAtOffset(0, -15);
                        String requisito = "", correquisito = "", tabsStr = "";
                        int tabs = 0;

                        if (!curso.getRequisitos().isEmpty()) {
                            for (int x = 0; x < curso.getRequisitos().size(); x++) {
                                requisito += Integer.toString(curso.getRequisitos().get(x).getCode()) + "  ";
                                tabs += 2;
                            }
                        }

                        if (!curso.getCorrequisitos().isEmpty()) {
                            for (int x = 0; x < curso.getCorrequisitos().size(); x++) {
                                correquisito += Integer.toString(curso.getCorrequisitos().get(x).getCode()) + " ";
                            }
                        }

                        tabs = 8 - tabs;

                        for (int x = 0; x < tabs; x++) {
                            tabsStr += "    ";
                        }

                        contentStream.showText(Integer.toString(curso.getCode()) + "                          " + requisito + tabsStr + correquisito);
                    }
                    contentStream.endText();
                }

                contentStream.close();

                // Check if the directory exists, create it if it doesn't
                File file = new File(dest);
                File parentDir = file.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    if (!parentDir.mkdirs()) {
                        throw new IOException("Failed to create directory: " + parentDir);
                    }
                }

                // Save the document
                document.save(dest);
                System.out.println("PDF saved successfully at: " + dest);

            } catch (IOException e) {
                Logger.getLogger(PDFCreator.class.getName()).log(Level.SEVERE, "Error writing PDF content", e);
            }
        } catch (IOException ex) {
            Logger.getLogger(PDFCreator.class.getName()).log(Level.SEVERE, "Error creating PDF document", ex);
        }
    }
}
