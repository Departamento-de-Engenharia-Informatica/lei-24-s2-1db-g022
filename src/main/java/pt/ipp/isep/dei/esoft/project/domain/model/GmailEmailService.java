package pt.ipp.isep.dei.esoft.project.domain.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Represents a Gmail email service implementation of the EmailService interface.
 */
public class GmailEmailService implements EmailService {

    /**
     * Sends an email using Gmail.
     *
     * @param email The recipient's email address.
     * @param msg   The message to be sent.
     * @return true if the email was sent successfully, false otherwise.
     */
    @Override
    public boolean sendEmail(String email, String msg, int delay) {
        Properties properties = new Properties();

        try {
            if (email == null || email.isEmpty() || msg == null || msg.isEmpty()) {
                return false;
            }
            InputStream in = new FileInputStream("src\\main\\resources\\config.properties");
            properties.load(in);
            in.close();

            System.out.println("\n-------Sending Email [GMAIL]-------");
            Thread.sleep(delay);
            System.out.println("From: " + properties.getProperty("Company.email"));
            System.out.println("Sending email to " + email);
            System.out.println("Message: " + msg);
            System.out.println("-----------------------------------");

        } catch (InterruptedException | IOException e) {
            return false;
        }

        return true;
    }
}
