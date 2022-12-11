package grp16.tripmate.forgetPassword.Controller;

import grp16.tripmate.forgetPassword.Model.ForgetPasswordFactory;
import grp16.tripmate.forgetPassword.Model.IForgetPassword;
import grp16.tripmate.notification.EmailVerificationFactory;
import grp16.tripmate.notification.IVerification;
import grp16.tripmate.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class ForgetPasswordController {
    private IVerification iVerification = null;
    private User user;
    @PostMapping("/forget_password")
    public String sendResetPasswordCode(Model model, @ModelAttribute User user) throws Exception {
        iVerification = EmailVerificationFactory.getInstance().createVerificationMethod();
        iVerification.sendUniqueCode(user.getUsername(), "Your reset password code is: ", "User reset password for Tripmate");

        this.user = user;

        return "/forget_password";
    }

    @PostMapping("/reset_password")
    public String userVerificationCode(Model model, HttpServletRequest request) {

        String code = request.getParameter("code");

        if (this.iVerification.verifyCode(code)) {

        } else {
            return "redirect:/error";
        }
        return "redirect:/error";
    }

}
