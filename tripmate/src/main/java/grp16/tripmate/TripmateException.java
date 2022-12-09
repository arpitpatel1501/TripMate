package grp16.tripmate;

public class TripmateException extends Exception{
    private final String errorMessage;
    private final Throwable error;

    public TripmateException(String errorMessage, Throwable error) {
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
        return "Exception{" +
                "errorMessage='" + errorMessage + '\'' +
                ", error=" + error +
                '}';
    }
}
