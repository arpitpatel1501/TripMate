package grp16.tripmate.user.model;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.user.encoder.PasswordEncoder;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.database.IUserQueryBuilder;
import grp16.tripmate.user.database.UserQueryBuilder;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements IUser {
    private final ILogger logger = new MyLoggerAdapter(this);

    private String username;
    private String password;

    private int id;

    private String firstname;
    private String lastname;
    private Date birthDate;
    private String gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) throws ParseException {
        this.birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private final IUserQueryBuilder queryBuilder;
    private final IDatabaseConnection dbConnection;

    public User() {
        this.queryBuilder = new UserQueryBuilder();
        this.dbConnection = new DatabaseConnection();

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        password = PasswordEncoder.encodeString(password);
        this.password = password;
    }


    public boolean validateUser() throws Exception {
        Connection connection = dbConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
        ResultSet userRS = statement.executeQuery(queryBuilder.getUserByUsername(this.getUsername()));
        User userFromDb = resultSetToUsers(userRS).get(0);
        connection.close();
        boolean isValidUser = userFromDb != null && userFromDb.getUsername().equals(this.getUsername()) && userFromDb.getPassword().equals(PasswordEncoder.encodeString(this.getPassword()));
        if (isValidUser) {
            logger.info("Current User: " + userFromDb);
            SessionManager.Instance().setValue(UserDbColumnNames.id, userFromDb.getId());
        }
        return isValidUser;
    }

    public List<User> resultSetToUsers(ResultSet rs) throws SQLException, NoSuchAlgorithmException, ParseException {
        List<User> results = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString(UserDbColumnNames.username));
            user.setPassword(rs.getString(UserDbColumnNames.password));
            user.setId(rs.getInt(UserDbColumnNames.id));
            user.setFirstname(rs.getString(UserDbColumnNames.firstname));
            user.setLastname(rs.getString(UserDbColumnNames.lastname));
            user.setBirthDate(String.valueOf(rs.getDate(UserDbColumnNames.birthDate)));
            user.setGender(rs.getString(UserDbColumnNames.gender));
            results.add(user);
        }
        return results;
    }

    @Override
    public boolean createUser() throws Exception {
        Connection connection = dbConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
        String query = queryBuilder.createUser(this);
        logger.info(query);
        int rowUpdate = statement.executeUpdate(query);
        connection.close();
        return rowUpdate == 1;
    }

    @Override
    public User getLoggedInUser() throws Exception {
        int currentUserId = (int) SessionManager.Instance().getValue(UserDbColumnNames.id);
        logger.info("Current User ID: " + currentUserId);
        String query = queryBuilder.getUserByUserID(currentUserId);
        Connection connection = dbConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
        ResultSet userRS = statement.executeQuery(query);
        User userFromDb = resultSetToUsers(userRS).get(0);
        connection.close();
        return userFromDb;
    }

    public String dateToSQLDate(Date date) {
        if (date != null) {
            // Ref: https://theopentutorials.com/examples/java/util/date/how-to-convert-java-util-date-to-mysql-date-format/
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            String sqlDate = formatter.format(date);
            return sqlDate;
        }
        return "";
    }

    public void changeUserDetails() throws Exception {
        Connection connection = dbConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
        this.setId((Integer) SessionManager.Instance().getValue(UserDbColumnNames.id));
        String query = queryBuilder.changeUserDetails(this);
        logger.info(query);
        int rowUpdate = statement.executeUpdate(query);
        connection.close();
    }


    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", id=" + id + ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + ", birthDate=" + birthDate + ", gender='" + gender + '\'' + '}';
    }
}
