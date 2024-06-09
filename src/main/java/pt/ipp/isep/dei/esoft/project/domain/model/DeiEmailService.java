package pt.ipp.isep.dei.esoft.project.domain.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Implementation of the EmailService interface for sending emails using the DEI email service.
 *
 * @author Group22
 *
 */
public class DeiEmailService implements EmailService {

    /**
     * Sends an email using the DEI email service.
     *
     * @param email The email address of the recipient.
     * @param msg   The message content of the email.
     * @return {@code true} if the email was sent successfully; {@code false} otherwise.
     */
    @Override
    public boolean sendEmail(String email, String msg) {
        Properties properties = new Properties();


        try {
            InputStream in = new FileInputStream("src\\main\\resources\\config.properties");
            properties.load(in);
            in.close();

            System.out.println("\n-------Sending Email [DEI]-------");
            Thread.sleep(2000);
            System.out.println("From: " + properties.getProperty("Company.email"));
            System.out.println("Sending email to " + email);
            System.out.println("Mensage: " + msg);
            System.out.println("---------------------------------");

        } catch (InterruptedException | IOException e) {
            return false;
        }

        return true;
    }
}
