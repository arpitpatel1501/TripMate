package grp16.tripmate.user.model;

import grp16.tripmate.user.database.IUserDatabase;
import grp16.tripmate.user.model.encoder.IPasswordEncoder;

public interface IUser {

    boolean validateUser(IUserDatabase userDatabase, IPasswordEncoder passwordEncoder) throws Exception;

    boolean createUser(IUserDatabase userDatabase) throws Exception;

    User getUserById(IUserDatabase userDatabase, int loggedInUserId) throws Exception;
}