package ma.zs.carriere.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.zs.carriere.zynerator.security.bean.User;
import ma.zs.carriere.zynerator.security.service.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl  implements EmailService{
    @Autowired
    private UserService userService;
    @Lazy
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    @Value("${activation-url}")
    private String activationUrl;

    @Override
    public void sendEmail(
            String to,
            EmailTemplateName emailTemplate,
            String confirmationUrl,
            String password,
            String username,
            String subject
    ) throws MessagingException {
        String templateName;
        if (emailTemplate == null) {
            templateName = "resetPasswordEmail";
        } else {
            templateName = emailTemplate.name();
        }
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );
        Map<String, Object> properties = new HashMap<>();
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("password", password);
        properties.put("username", username);

        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom("hrsystemweb123@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        String template = templateEngine.process(templateName, context);

        helper.setText(template, true);

        mailSender.send(mimeMessage);
    }
    @Override
    public void sendValidationEmail(String email) throws MessagingException {
        var newToken = generateAndSaveActivationToken(email);
        var user = userService.findByEmail(email);
        String username = user.getUsername();
                sendEmail(email,
                EmailTemplateName.resetPasswordEmail,
                activationUrl,
                newToken,
                username,
                "reset password"
        );
    }

    @Override
    public String generateActivationCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }

    @Override
    public String generateAndSaveActivationToken(String email) {
        User user = userService.findByEmail(email);
        String generatedToken = generateActivationCode(8);
        String tokenCrypter = bCryptPasswordEncoder.encode(generatedToken);
        user.setPassword(tokenCrypter);
        userService.update(user);
        return generatedToken;
    }
}
