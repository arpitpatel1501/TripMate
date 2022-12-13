package grp16.tripmate.notification.model;

public class NotificationException extends Exception{
    private final String errorMessage;
    private final Throwable error;

    public NotificationException(String errorMessage, Throwable error) {
        super(errorMessage, error);
        this.errorMessage = errorMessage;
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Throwable getError() {
        return error;
    }

    @Override
    public String toString() {
        return "NotificationException{" +
                "errorMessage='" + errorMessage + '\'' +
                ", error=" + error +
                '}';
    }
}
