package grp16.tripmate.user.database;

import grp16.tripmate.db.execute.DatabaseExecution;
import grp16.tripmate.db.execute.IDatabaseExecution;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.IPostsQueryGenerator;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.encoder.PasswordEncoder;
import grp16.tripmate.user.model.IUser;
import grp16.tripmate.user.model.User;
import grp16.tripmate.user.model.UserDbColumnNames;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserDatabase implements IUserDatabase {

    private final ILogger logger = new MyLoggerAdapter(this);
    private final IUserQueryBuilder queryGenerator;
    private final IDatabaseExecution databaseExecution;

    public UserDatabase() {
        queryGenerator = UserQueryBuilder.getInstance();
        databaseExecution = new DatabaseExecution();
    }

    @Override
    public boolean validateUser(User user) throws Exception {
        String query = queryGenerator.getUserByUsername(user.getUsername());
        List<User> users = listToUsers(databaseExecution.executeSelectQuery(query));
        User userFromDb = users.get(0);
        boolean isValidUser = userFromDb != null &&
                userFromDb.getUsername().equals(user.getUsername()) &&
                userFromDb.getPassword().equals(PasswordEncoder.encodeString(user.getPassword()));
        if (isValidUser) {
            logger.info("Current User: " + userFromDb);
            SessionManager.Instance().setValue(UserDbColumnNames.id, userFromDb.getId());
        }
        return isValidUser;
    }

    @Override
    public boolean createUser(User user) {
        String query = queryGenerator.createUser(user);
        return databaseExecution.executeUpdateQuery(query);
    }

    @Override
    public User getLoggedInUser() throws Exception {
        int currentUserId = (int) SessionManager.Instance().getValue(UserDbColumnNames.id);
        logger.info("Current User ID: " + currentUserId);
        String query = queryGenerator.getUserByUserID(currentUserId);
        List<User> users = listToUsers(databaseExecution.executeSelectQuery(query));
        return users.get(0);
    }

    @Override
    public boolean changeUserDetails(User user) throws Exception {
        user.setId((Integer) SessionManager.Instance().getValue(UserDbColumnNames.id));
        String query = queryGenerator.changeUserDetails(user);
        return databaseExecution.executeUpdateQuery(query);
    }

    @Override
    public User getUserById(int userid) throws Exception {
        String query = queryGenerator.getUserByUserID(userid);
        List<User> users = listToUsers(databaseExecution.executeSelectQuery(query));
        return users.get(0);
    }

    private List<User> listToUsers(List<Map<String, Object>> results) throws SQLException, NoSuchAlgorithmException, ParseException {
        List<User> users = new ArrayList<>();
        for (Map<String, Object> result : results) {
            User user = new User();
            user.setUsername((String) result.get(UserDbColumnNames.username));
            user.setPassword((String) result.get(UserDbColumnNames.password));
            user.setId((Integer) result.get(UserDbColumnNames.id));
            user.setFirstname((String) result.get(UserDbColumnNames.firstname));
            user.setLastname((String) result.get(UserDbColumnNames.lastname));
            user.setBirthDate((Date) result.get(UserDbColumnNames.birthDate));
            user.setGender((String) result.get(UserDbColumnNames.gender));
            users.add(user);
        }
        return users;
    }

    private String localDateTimeToString(LocalDateTime ldt) {
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return ldt.format(customFormat);
    }
}
