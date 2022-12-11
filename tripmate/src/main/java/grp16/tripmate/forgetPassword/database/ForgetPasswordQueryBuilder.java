package grp16.tripmate.forgetPassword.database;

import grp16.tripmate.user.model.User;
import grp16.tripmate.user.model.UserDbColumnNames;

public class ForgetPasswordQueryBuilder implements IForgetPasswordQueryBuilder {

    private static IForgetPasswordQueryBuilder instance = null;

    public static IForgetPasswordQueryBuilder getInstance() {
        if (instance == null) {
            instance = new ForgetPasswordQueryBuilder();
        }
        return instance;
    }
    public String changeUserPassword(User user) {
        String query = "update " +
                UserDbColumnNames.tableName + " set " +
                UserDbColumnNames.password + " = '" + user.getPassword() + "'" + "," +
                UserDbColumnNames.gender + " = '" + user.getGender() + "'" + "," +
                UserDbColumnNames.firstname + " = '" + user.getFirstname() + "'" + "," +
                UserDbColumnNames.lastname + " = '" + user.getLastname() + "'" +
                " where " +
                UserDbColumnNames.id + " = " + user.getId();
        return query;
    }
}
