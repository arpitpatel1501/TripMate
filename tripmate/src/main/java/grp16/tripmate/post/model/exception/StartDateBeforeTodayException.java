package grp16.tripmate.post.model.exception;

public class StartDateBeforeTodayException extends Exception{
    @Override
    public String getMessage() {
        String message = "Start Date cannot be before today";
        return message;
    }
}
