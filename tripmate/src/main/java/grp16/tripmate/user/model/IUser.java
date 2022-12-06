package grp16.tripmate.user.model;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public interface IUser {

    boolean validateUser() throws Exception;

    List<User> resultSetToUsers(ResultSet rs) throws SQLException, NoSuchAlgorithmException, ParseException;

    boolean createUser() throws Exception;

    User getLoggedInUser() throws Exception;
}
