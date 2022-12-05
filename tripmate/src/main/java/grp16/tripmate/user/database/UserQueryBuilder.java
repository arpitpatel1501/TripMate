package grp16.tripmate.user.database;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.user.model.User;
import grp16.tripmate.user.model.UserDbColumnNames;

public class UserQueryBuilder implements IUserQueryBuilder {
    private static IUserQueryBuilder instance;

    private final ILogger logger = new MyLoggerAdapter(this);

    public UserQueryBuilder() {
        //Required empty constructor
    }

    public static IUserQueryBuilder getInstance() {
        if (instance == null) {
            instance = new UserQueryBuilder();
        }
        return instance;
    }


    @Override
    public String getUserByUsername(String username) {
        return "SELECT `User`.`id`," +
                "    `User`.`firstname`," +
                "    `User`.`lastname`," +
                "    `User`.`email`," +
                "    `User`.`password`," +
                "    `User`.`birthdate`," +
                "    `User`.`gender`" +
                "FROM `User` where email = \"" + username + "\"";
    }

    @Override
    public String getUserByUserID(int userid) {
        String query = "SELECT `User`.`id`," +
                "    `User`.`firstname`," +
                "    `User`.`lastname`," +
                "    `User`.`email`," +
                "    `User`.`password`," +
                "    `User`.`birthdate`," +
                "    `User`.`gender`" +
                "FROM `User` where id = " + userid;
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
    public String changePassword(User user) {
        String query = "update " +
                UserDbColumnNames.tableName + " set " +
                UserDbColumnNames.password + " = '" +
                user.getPassword() + "'" +
                " where " +
                UserDbColumnNames.id + " = " + user.getId();
        return query;
    }
}


