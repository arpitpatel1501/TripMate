package grp16.tripmate.user.database;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.user.model.User;
import grp16.tripmate.user.model.UserDbColumnNames;

public class UserQueryBuilder implements IUserQueryBuilder {
    private static IUserQueryBuilder instance;

    private final ILogger logger = new MyLoggerAdapter(this);

    private UserQueryBuilder() {
        //Required private empty constructor
    }

    public static IUserQueryBuilder getInstance() {
        if (instance == null) {
            instance = new UserQueryBuilder();
        }
        return instance;
    }


    @Override
    public String getUserByUsername(String username) {
        return "SELECT `" + UserDbColumnNames.id + "`," +
                "    `" + UserDbColumnNames.firstname + "`," +
                "    `" + UserDbColumnNames.lastname + "`," +
                "    `" + UserDbColumnNames.username + "`," +
                "    `" + UserDbColumnNames.password + "`," +
                "    `" + UserDbColumnNames.birthDate + "`," +
                "    `" + UserDbColumnNames.gender + "` " +
                "FROM `" + UserDbColumnNames.tableName + "` where " + UserDbColumnNames.username + " = \"" + username + "\"";
    }

    @Override
    public String getUserByUserID(int userid) {
        String query = "SELECT `" + UserDbColumnNames.id + "`," +
                "    `" + UserDbColumnNames.firstname + "`," +
                "    `" + UserDbColumnNames.lastname + "`," +
                "    `" + UserDbColumnNames.username + "`," +
                "    `" + UserDbColumnNames.password + "`," +
                "    `" + UserDbColumnNames.birthDate + "`," +
                "    `" + UserDbColumnNames.gender + "`" +
                "FROM `" + UserDbColumnNames.tableName + "` where " + UserDbColumnNames.id + " = " + userid;
        logger.info(query);
        return query;
    }

    @Override
    public String createUser(User user) {
        return "INSERT INTO `User`" +
                "(" + UserDbColumnNames.id + "," +
                UserDbColumnNames.firstname + "," +
                UserDbColumnNames.lastname + "," +
                UserDbColumnNames.username + "," +
                UserDbColumnNames.password + "," +
                UserDbColumnNames.birthDate + "," +
                UserDbColumnNames.gender + ") " +
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
                UserDbColumnNames.tableName + " set " +
                UserDbColumnNames.password + " = '" + user.getPassword() + "'" + "," +
                UserDbColumnNames.gender + " = '" + user.getGender() + "'" + "," +
                UserDbColumnNames.firstname + " = '" + user.getFirstname() + "'" + "," +
                UserDbColumnNames.lastname + " = '" + user.getLastname() + "'" +
                " where " +
                UserDbColumnNames.id + " = " + user.getId();
        logger.info(query);
        return query;
    }
}


