package ma.zs.carriere.email;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendEmail(
            String to,
            EmailTemplateName emailTemplate,
            String confirmationUrl,
            String password,
            String username,
            String subject
    ) throws MessagingException;

    void sendValidationEmail(String email) throws MessagingException;

    String generateActivationCode(int length);

    String generateAndSaveActivationToken(String email);
}
