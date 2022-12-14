package grp16.tripmate.request.persistence;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;

public class MyRequestQueryGenerator implements IMyRequestQueryGenerator {
    private static MyRequestQueryGenerator instance;
    private final ILogger logger = new MyLoggerAdapter(this);

    private MyRequestQueryGenerator() {
    }

    public static MyRequestQueryGenerator getInstance() {
        if (instance == null) {
            instance = new MyRequestQueryGenerator();
        }
        return instance;
    }

    @Override
    public String getMyRequestByUserId(int userid) {
        //TODO: do something about this static query
        String query = "SELECT p.title as postTitle, status, post_owner.firstname as firstNameCreator, post_owner.lastname lastNameCreator " +
                "FROM " + MyRequestDBColumnNames.TABLE_NAME + " pr " +
                "JOIN Post p on pr.Post_id = p.id " +
                "JOIN User u on pr.request_owner = u.id " +
                "JOIN User post_owner on post_owner.id = p.created_by " +
                "WHERE u.id = " + userid + ";";
        logger.info(query);
        return query;
    }
}