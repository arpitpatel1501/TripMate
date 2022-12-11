package grp16.tripmate.post.model.exception;

public class StartDateAfterEndDateException extends Exception{

    @Override
    public String getMessage() {
        String message = "Start Date cannot be later than End Date";
        return message;
    }
}
