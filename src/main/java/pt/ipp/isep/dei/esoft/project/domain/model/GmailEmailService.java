package pt.ipp.isep.dei.esoft.project.domain.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GmailEmailService implements EmailService {

    @Override
    public boolean sendEmail(String email, String msg) {
        Properties properties = new Properties();

        try {
            InputStream in = new FileInputStream("src\\main\\resources\\config.properties");
            properties.load(in);
            in.close();

            System.out.println("\n-------Sending Email [GMAIL]-------");
            Thread.sleep(2000);
            System.out.println("From: " + properties.getProperty("Company.email"));
            System.out.println("Sending email to " + email);
            System.out.println("Mensage: " + msg);
            System.out.println("-----------------------------------");

        } catch (InterruptedException | IOException e) {
            return false;
        }

        return true;
    }
}
