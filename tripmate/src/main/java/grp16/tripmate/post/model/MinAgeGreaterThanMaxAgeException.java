package grp16.tripmate.post.model;

public class MinAgeGreaterThanMaxAgeException extends Exception{
    @Override
    public String getMessage() {
        String message = "Min Age cannot be greater than Max Age";
        return message;
    }
}
