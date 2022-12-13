package grp16.tripmate.forgetPassword.Model.factory;

import grp16.tripmate.forgetPassword.Model.ForgetPassword;
import grp16.tripmate.forgetPassword.Model.IForgetPassword;

public class ForgetPasswordFactory implements IForgetPasswordFactory{
    private static IForgetPasswordFactory instance = null;
    private ForgetPasswordFactory() {

    }

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
