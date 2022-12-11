package grp16.tripmate.post.model;

import java.text.ParseException;
import java.util.Date;

public class PostValidator {
    public void isStartDateBeforeEndDate(Post post) throws ParseException, StartDateAfterEndDateException {
        Date startDate = post.getJavaDate(post.getStartDate());
        Date endDate = post.getJavaDate(post.getEndDate());
        if(startDate.after(endDate)){
            throw new StartDateAfterEndDateException();
        };
    }

    public void isMinAgeLessThanMaxAge(Post post) throws MinAgeGreaterThanMaxAgeException {
        int minAge = post.getMinAge();
        int maxAge = post.getMaxAge();
        if(minAge > maxAge){
            throw new MinAgeGreaterThanMaxAgeException();
        };
    }
}
