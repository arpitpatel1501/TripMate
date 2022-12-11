package grp16.tripmate.forgetPassword.Model;

public class ForgetPasswordFactory implements IForgetPasswordFactory{
    private static IForgetPasswordFactory instance = null;

    public static IForgetPasswordFactory getInstance() {
        if (instance == null) {
            instance = new ForgetPasswordFactory();
        }
        return instance;
    }

    public IForgetPassword createForgetPassword() {
        return new ForgetPassword();
    }
}
