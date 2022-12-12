package grp16.tripmate.user.database;

import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.encoder.IPasswordEncoder;
import grp16.tripmate.user.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserDatabase implements IUserDatabase {

    private final ILogger logger = new MyLoggerAdapter(this);
    private final IUserQueryGenerator queryGenerator;
    private final IDatabaseExecutor databaseExecution;


    public UserDatabase(IUserQueryGenerator queryGenerator, IDatabaseExecutor databaseExecution) {
        this.queryGenerator = queryGenerator;
        this.databaseExecution = databaseExecution;
    }

    @Override
    public boolean insertUser(User user) {
        String query = queryGenerator.createUser(user);
        return databaseExecution.executeInsertQuery(query);
    }

    @Override
    public boolean updateUser(User user) {
        String query = queryGenerator.changeUserDetails(user);
        return databaseExecution.executeUpdateQuery(query);
    }

    @Override
    public User getUserById(int userid) throws Exception {
        String query = queryGenerator.getUserByUserID(userid);
        logger.info(query);
        List<User> users = listToUsers(databaseExecution.executeSelectQuery(query));
        return users.get(0);
    }

    @Override
    public User getUserByUsername(String username) throws NoSuchAlgorithmException {
        String query = queryGenerator.getUserByUsername(username);
        logger.info(query);
        List<User> users = listToUsers(databaseExecution.executeSelectQuery(query));
        return users.get(0);
    }

    private List<User> listToUsers(List<Map<String, Object>> results) throws NoSuchAlgorithmException {
        List<User> users = new ArrayList<>();
        for (Map<String, Object> result : results) {
            User user = new User();
            user.setUsername((String) result.get(UserDbColumnNames.USERNAME));
            user.setPasswordWithOutEncoding((String) result.get(UserDbColumnNames.PASSWORD));
            user.setId((Integer) result.get(UserDbColumnNames.ID));
            user.setFirstname((String) result.get(UserDbColumnNames.FIRSTNAME));
            user.setLastname((String) result.get(UserDbColumnNames.LASTNAME));
            user.setBirthDateAsDate((Date) result.get(UserDbColumnNames.BIRTHDATE));
            user.setGender((String) result.get(UserDbColumnNames.GENDER));
            users.add(user);
        }
        return users;
    }
}
