package grp16.tripmate.forgetPassword.Controller;

import grp16.tripmate.forgetPassword.Model.factory.ForgetPasswordFactory;
import grp16.tripmate.forgetPassword.Model.IForgetPassword;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.notification.model.IVerification;
import grp16.tripmate.notification.model.factory.NotificationFactory;
import grp16.tripmate.user.encoder.PasswordEncoder;
import grp16.tripmate.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class ForgetPasswordController {

    private final ILogger logger = new MyLoggerAdapter(this);
    private IVerification iVerification = null;
    private String email = null;
    private User user;

    @GetMapping("/forget_password")
    public String forgetPassword(Model model) throws Exception {
        model.addAttribute("title", "Reset password");
        model.addAttribute("email", this.email);

        return "/forget_password";
    }

    @PostMapping("/forget_password")
    public String sendResetPasswordCode(Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("title", "Reset password");
        model.addAttribute("email", "");
        IForgetPassword iForgetPassword = ForgetPasswordFactory.getInstance().createForgetPassword();
        try {
            if (iForgetPassword.checkUserExist(request.getParameter("email"))) {
                System.out.println("---- user exist ----");
            } else {
                model.addAttribute("error", "User Not exists");
                logger.info("User Not exists");
                return "redirect:/error";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("---- user exist ----");
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return "redirect:/error";
        }

        this.email = request.getParameter("email");

        iVerification = NotificationFactory.getInstance().createVerificationMethod();
        iVerification.sendUniqueCode(this.email,
                "Your reset password code is: ",
                "User reset password for Tripmate");

        return "redirect:/forget_password";
    }

    @PostMapping("/reset_password")
    public String userVerificationCode(Model model, HttpServletRequest request) {
        model.addAttribute("email", this.email);
        String code = request.getParameter("code");
        IForgetPassword iForgetPassword = ForgetPasswordFactory.getInstance().createForgetPassword();

        if (this.iVerification.verifyCode(code)) {
            System.out.println("--- code match ---");
            return "new_password";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/new_password")
    public String resetPassword(Model model) throws Exception {
        email = ForgetPasswordFactory.getInstance().createForgetPassword().getEmail();

        model.addAttribute("title", "New password");
        model.addAttribute("email", email);

        return "/new_password";
    }

    @PostMapping("/new_password")
    public String setNewPassword(Model model, HttpServletRequest request) throws Exception {
        IForgetPassword iForgetPassword = ForgetPasswordFactory.getInstance().createForgetPassword();
//        email = iForgetPassword.getEmail();
        model.addAttribute("email", email);
        String password = request.getParameter("password");
        if (iForgetPassword.changeUserPassword(email, PasswordEncoder.getInstance().encodeString(password))) {
            NotificationFactory.getInstance().createEmailNotification().sendNotification(email,
                    "Password Updated",
                    "Password Reset successfully");

            return "redirect:/login";
        } else {
            return "redirect:/error";
        }
    }
}
