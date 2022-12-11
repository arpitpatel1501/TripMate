package grp16.tripmate.user.model;

public class EmailVerificationFactory implements IVerificationFactory{

    private static IVerificationFactory instance = null;
    public static IVerificationFactory getInstance() {
        if (instance == null) {
            instance = new EmailVerificationFactory();
        }
        return instance;
    }
    @Override
    public IVerification createVerificationMethod() throws Exception {
        return new EmailVerification();
    }
}
