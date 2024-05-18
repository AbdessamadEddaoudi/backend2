package ma.zs.carriere.ws.facade.admin.PasswordResetRequest;

import jakarta.mail.MessagingException;
import ma.zs.carriere.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/passwordReset")
public class PasswordResetRequestRestAdmin {

    @Autowired
    private EmailService emailService;

    @PostMapping("/email/{email}")
    public void sendValidationEmail(@PathVariable String email) throws MessagingException {
        emailService.sendValidationEmail(email);
    }
}
