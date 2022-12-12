package grp16.tripmate.user.database;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.user.model.User;

public class UserQueryGenerator implements IUserQueryGenerator {
    private static IUserQueryGenerator instance;

    private final ILogger logger = new MyLoggerAdapter(this);

    private UserQueryGenerator() {
        //Required private empty constructor
    }

    public static IUserQueryGenerator getInstance() {
        if (instance == null) {
            instance = new UserQueryGenerator();
        }
        return instance;
    }


    @Override
    public String getUserByUsername(String username) {
        return "SELECT `" + UserDbColumnNames.ID + "`," +
                "    `" + UserDbColumnNames.FIRSTNAME + "`," +
                "    `" + UserDbColumnNames.LASTNAME + "`," +
                "    `" + UserDbColumnNames.USERNAME + "`," +
                "    `" + UserDbColumnNames.PASSWORD + "`," +
                "    `" + UserDbColumnNames.BIRTHDATE + "`," +
                "    `" + UserDbColumnNames.GENDER + "` " +
                "FROM `" + UserDbColumnNames.TABLE_NAME + "` where " + UserDbColumnNames.USERNAME + " = \"" + username + "\"";
    }

    @Override
    public String getUserByUserID(int userid) {
        String query = "SELECT `" + UserDbColumnNames.ID + "`," +
                "    `" + UserDbColumnNames.FIRSTNAME + "`," +
                "    `" + UserDbColumnNames.LASTNAME + "`," +
                "    `" + UserDbColumnNames.USERNAME + "`," +
                "    `" + UserDbColumnNames.PASSWORD + "`," +
                "    `" + UserDbColumnNames.BIRTHDATE + "`," +
                "    `" + UserDbColumnNames.GENDER + "`" +
                "FROM `" + UserDbColumnNames.TABLE_NAME + "` where " + UserDbColumnNames.ID + " = " + userid;
        logger.info(query);
        return query;
    }

    @Override
    public String createUser(User user) {
        return "INSERT INTO `User`" +
                "(" + UserDbColumnNames.ID + "," +
                UserDbColumnNames.FIRSTNAME + "," +
                UserDbColumnNames.LASTNAME + "," +
                UserDbColumnNames.USERNAME + "," +
                UserDbColumnNames.PASSWORD + "," +
                UserDbColumnNames.BIRTHDATE + "," +
                UserDbColumnNames.GENDER + ") " +
                "VALUES" +
                " (\"" + user.getId() + "\"," +
                "\"" + user.getFirstname() + "\"," +
                "\"" + user.getLastname() + "\"," +
                "\"" + user.getUsername() + "\"," +
                "\"" + user.getPassword() + "\"," +
                "\"" + user.dateToSQLDate(user.getBirthDate()) + "\"," +
                "\"" + user.getGender() + "\");";
    }

    @Override
    public String changeUserDetails(User user) {
        String query = "update " +
                UserDbColumnNames.TABLE_NAME + " set " +
                UserDbColumnNames.PASSWORD + " = '" + user.getPassword() + "'" + "," +
                UserDbColumnNames.GENDER + " = '" + user.getGender() + "'" + "," +
                UserDbColumnNames.FIRSTNAME + " = '" + user.getFirstname() + "'" + "," +
                UserDbColumnNames.LASTNAME + " = '" + user.getLastname() + "'" +
                " where " +
                UserDbColumnNames.ID + " = " + user.getId();
        logger.info(query);
        return query;
    }
}


