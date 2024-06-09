package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.model.DeiEmailService;
import pt.ipp.isep.dei.esoft.project.domain.model.GmailEmailService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeiServiceTest {

    @Test
    public void sendEmail() {
        DeiEmailService emailService = new DeiEmailService();
        boolean success = emailService.sendEmail("teste@teste.pt", "ola",0);
        assertTrue(success);
    }

    @Test
    public void nullEmailSendEmail() {
        GmailEmailService emailService = new GmailEmailService();
        boolean success = emailService.sendEmail(null, "ola",0);
        assertFalse(success);
    }

    @Test
    public void nullMsgSendEmail() {
        GmailEmailService emailService = new GmailEmailService();
        boolean success = emailService.sendEmail("teste@teste.pt", null,0);
        assertFalse(success);
    }
    @Test
    public void nullParametreSendEmail() {
        GmailEmailService emailService = new GmailEmailService();
        boolean success = emailService.sendEmail(null, null,0);
        assertFalse(success);
    }
}
