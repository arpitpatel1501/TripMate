package grp16.tripmate.user.model;

import grp16.tripmate.db.execute.IDatabaseExecution;
import grp16.tripmate.user.database.IUserDatabase;
import grp16.tripmate.user.database.IUserQueryBuilder;
import grp16.tripmate.user.encoder.IPasswordEncoder;

public interface IUserFactory {

    IUser getNewUser();

    IUserDatabase getUserDatabase();

    IUserQueryBuilder getUserQueryBuilder();

    IDatabaseExecution getNewDatabaseExecutor();

    IPasswordEncoder getPasswordEncoder();
}
