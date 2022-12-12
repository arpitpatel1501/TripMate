package grp16.tripmate.user.model.factory;

import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.user.database.IUserDatabase;
import grp16.tripmate.user.database.IUserQueryGenerator;
import grp16.tripmate.user.encoder.IPasswordEncoder;
import grp16.tripmate.user.model.IUser;

public interface IUserFactory {

    IUser makeNewUser();

    IUserDatabase makeUserDatabase();

    IUserQueryGenerator makeUserQueryBuilder();

    IDatabaseExecutor makeNewDatabaseExecutor();

    IPasswordEncoder makePasswordEncoder();
}
