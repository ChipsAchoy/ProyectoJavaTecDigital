package tec.proyectomatricula.FileController;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tec.proyectomatricula.modelos.Curso;
import tec.proyectomatricula.modelos.PlanEstudios;

public class PDFCreator {

    public void createPDF(String dest, PlanEstudios planEstudios) {
        try (PDDocument document = new PDDocument()) {
            // Crear una página con una altura mucho mayor
            PDRectangle customPageSize = new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight() * 3); // 10 veces la altura de una página A4
            PDPage page = new PDPage(customPageSize);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                contentStream.newLineAtOffset(100, customPageSize.getHeight() - 50);
                contentStream.showText("Escuela o Area: " + planEstudios.getEscArea().getName());
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.newLineAtOffset(100, customPageSize.getHeight() - 100);
                contentStream.showText("Codigo Plan: " + planEstudios.getCode());
                contentStream.endText();

                float y = customPageSize.getHeight() - 130;
                for (int i = 0; i < planEstudios.getBloques().size(); i++) {
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                    contentStream.newLineAtOffset(0, y-i*50);
                    contentStream.showText(planEstudios.getBloques().get(i) + "                         Requisitos                      Correquistos");
                    for (int j = 0; j < planEstudios.getBloqueCursos().get(planEstudios.getBloques().get(i)).size(); j++) {

                        contentStream.setFont(PDType1Font.HELVETICA, 8);
                        Curso curso = planEstudios.getBloqueCursos().get(planEstudios.getBloques().get(i)).get(j);
                        contentStream.newLineAtOffset(0, -15);
                        String requisito = "", correquisito = "", tabsStr = "";
                        int tabs = 0;
                        
                        if (!curso.getRequisitos().isEmpty()){
                            for (int x=0; x<curso.getRequisitos().size(); x++){
                                requisito += Integer.toString(curso.getRequisitos().get(x).getCode())+"  ";
                                tabs += 2;
                            }
                        }
                        
                        if (!curso.getCorrequisitos().isEmpty()){
                            for (int x=0; x<curso.getCorrequisitos().size(); x++){
                                requisito += Integer.toString(curso.getCorrequisitos().get(x).getCode())+" ";
                            }
                        }
                        
                        tabs = 8-tabs;
                        
                        for (int x=0; x<tabs; x++){
                            
                            tabsStr += "    ";
                            
                        }
                        
                        contentStream.showText(Integer.toString(curso.getCode())+"                          "+requisito+tabs+correquisito);

                    }
                    contentStream.endText();

                }
                contentStream.close();
                document.save(dest);
            } catch (IOException e) {
                Logger.getLogger(PDFCreator.class.getName()).log(Level.SEVERE, null, e);
            }
        } catch (IOException ex) {
            Logger.getLogger(PDFCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
