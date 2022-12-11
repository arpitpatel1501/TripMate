package grp16.tripmate.user.database;

import grp16.tripmate.db.execute.IDatabaseExecution;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.encoder.IPasswordEncoder;
import grp16.tripmate.user.model.User;
import grp16.tripmate.user.model.UserDbColumnNames;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserDatabase implements IUserDatabase {

    private final ILogger logger = new MyLoggerAdapter(this);
    private final IUserQueryBuilder queryGenerator;
    private final IDatabaseExecution databaseExecution;

    private final IPasswordEncoder passwordEncoder;

    public UserDatabase(IUserQueryBuilder queryGenerator, IDatabaseExecution databaseExecution, IPasswordEncoder passwordEncoder) {
        this.queryGenerator = queryGenerator;
        this.databaseExecution = databaseExecution;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean validateUser(User user) throws Exception {
        String query = queryGenerator.getUserByUsername(user.getUsername());
        List<User> users = listToUsers(databaseExecution.executeSelectQuery(query));
        User userFromDb = users.get(0);
        boolean isValidUser = userFromDb != null &&
                userFromDb.getUsername().equals(user.getUsername()) &&
                userFromDb.getPassword().equals(passwordEncoder.encodeString(user.getPassword()));
        if (isValidUser) {
            logger.info("Current User: " + userFromDb);
            SessionManager.Instance().setValue(UserDbColumnNames.ID, userFromDb.getId());
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
        int currentUserId = (int) SessionManager.Instance().getValue(UserDbColumnNames.ID);
        logger.info("Current User ID: " + currentUserId);
        String query = queryGenerator.getUserByUserID(currentUserId);
        List<User> users = listToUsers(databaseExecution.executeSelectQuery(query));
        return users.get(0);
    }

    @Override
    public boolean changeUserDetails(User user) throws Exception {
        user.setId((Integer) SessionManager.Instance().getValue(UserDbColumnNames.ID));
        String query = queryGenerator.changeUserDetails(user);
        return databaseExecution.executeUpdateQuery(query);
    }

    @Override
    public User getUserById(int userid) throws Exception {
        String query = queryGenerator.getUserByUserID(userid);
        List<User> users = listToUsers(databaseExecution.executeSelectQuery(query));
        return users.get(0);
    }

    private List<User> listToUsers(List<Map<String, Object>> results) throws NoSuchAlgorithmException {
        List<User> users = new ArrayList<>();
        for (Map<String, Object> result : results) {
            User user = new User();
            user.setUsername((String) result.get(UserDbColumnNames.USERNAME));
            user.setPassword((String) result.get(UserDbColumnNames.PASSWORD));
            user.setId((Integer) result.get(UserDbColumnNames.ID));
            user.setFirstname((String) result.get(UserDbColumnNames.FIRSTNAME));
            user.setLastname((String) result.get(UserDbColumnNames.LASTNAME));
            user.setBirthDate((Date) result.get(UserDbColumnNames.BIRTHDATE));
            user.setGender((String) result.get(UserDbColumnNames.GENDER));
            users.add(user);
        }
        return users;
    }
}
