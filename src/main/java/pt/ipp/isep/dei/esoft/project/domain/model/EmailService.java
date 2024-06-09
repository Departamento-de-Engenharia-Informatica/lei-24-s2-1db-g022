package pt.ipp.isep.dei.esoft.project.domain.model;

/**
 * Represents an email service that can send emails.
 *
 * @author Group22
 */
public interface EmailService {

    /**
     * Sends an email message to the specified email address.
     *
     * @param email The recipient's email address.
     * @param msg   The message content.
     * @return {@code true} if the email was sent successfully; {@code false} otherwise.
     */
    boolean sendEmail(String email, String msg,int delay);
}
