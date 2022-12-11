package grp16.tripmate.forgetPassword.database;

import grp16.tripmate.user.model.User;
import grp16.tripmate.user.model.UserDbColumnNames;

import java.sql.SQLOutput;

public class ForgetPasswordQueryBuilder implements IForgetPasswordQueryBuilder {

    private static IForgetPasswordQueryBuilder instance = null;

    public static IForgetPasswordQueryBuilder getInstance() {
        if (instance == null) {
            instance = new ForgetPasswordQueryBuilder();
        }
        return instance;
    }

    @Override
    public String checkUserExist(String email) {
        String query = "select * from " +
                UserDbColumnNames.tableName +
                " where " +
                UserDbColumnNames.username + " = " + "\"" + email + "\"";
        System.out.println(query);
        return query;
    }
    @Override
    public String changeUserPassword(String email, String password) {
        String query = "update " +
                UserDbColumnNames.tableName + " set " +
                UserDbColumnNames.password + " = '" + password + "'" +
                " where " +
                UserDbColumnNames.username + " = '" + email + "'";
        System.out.println();
        return query;
    }
}
