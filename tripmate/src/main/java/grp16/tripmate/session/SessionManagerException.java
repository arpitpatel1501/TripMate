package grp16.tripmate.session;

public class SessionManagerException extends Exception {

    private String errorMsg;

    public SessionManagerException(String message) {
        super(message);
        errorMsg = message;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "SessionManagerException{" +
                "errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
