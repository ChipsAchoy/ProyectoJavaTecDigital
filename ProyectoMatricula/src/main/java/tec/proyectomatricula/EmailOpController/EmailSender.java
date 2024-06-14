/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.proyectomatricula.EmailOpController;
import okhttp3.*;
import okio.ByteString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailSender {

    public void emailSend(String emailString, String contentString, File pdfFile) {
        String apiKey = "317a7b4b8ae14a4ec5f024ac33620e17";
        String apiSecret = "5a3f7c6d6822e3261d3c057beb5d3a16";
        String fromEmail = "antoca299@gmail.com";
        String fromName = "Proyecto Trivia";
        String toEmail = emailString;
        String toName = "Estimado usuario";
        String subject = "Trivia";
        String htmlContent = "<html><body><p>" + contentString + "</p></body></html>";

        OkHttpClient client = new OkHttpClient();

        // Convert the PDF file to a Base64 encoded string
        String pdfBase64 = "";
        try {
            byte[] pdfBytes = Files.readAllBytes(pdfFile.toPath());
            pdfBase64 = ByteString.of(pdfBytes).base64();
        } catch (IOException e) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, e);
            return;
        }

        String json = "{"
                + "\"Messages\":[{"
                + "\"From\":{"
                + "\"Email\":\"" + fromEmail + "\","
                + "\"Name\":\"" + fromName + "\""
                + "},"
                + "\"To\":[{"
                + "\"Email\":\"" + toEmail + "\","
                + "\"Name\":\"" + toName + "\""
                + "}],"
                + "\"Subject\":\"" + subject + "\","
                + "\"HTMLPart\":\"" + htmlContent + "\","
                + "\"Attachments\":[{"
                + "\"ContentType\":\"application/pdf\","
                + "\"Filename\":\"attachment.pdf\","
                + "\"Base64Content\":\"" + pdfBase64 + "\""
                + "}]"
                + "}]"
                + "}";

        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));

        String credential = Credentials.basic(apiKey, apiSecret);

        Request request = new Request.Builder()
                .url("https://api.mailjet.com/v3.1/send")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", credential)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                System.out.println("Unexpected code " + response);
            }

            System.out.println(response.body().string());
        } catch (IOException ex) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
