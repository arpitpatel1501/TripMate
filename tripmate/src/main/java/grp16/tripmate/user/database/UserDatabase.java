package grp16.tripmate.user.database;

import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.user.model.User;

import java.util.Map;

public class UserDatabase implements IUserDatabase {
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
    public Map<String, Object> getUserById(int userid) {
        String query = queryGenerator.getUserByUserID(userid);
        return databaseExecution.executeSelectQuery(query).get(0);
    }

    @Override
    public Map<String, Object> getUserByUsername(String username) {
        String query = queryGenerator.getUserByUsername(username);
        return databaseExecution.executeSelectQuery(query).get(0);
    }
}