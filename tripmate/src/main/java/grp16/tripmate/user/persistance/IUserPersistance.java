package grp16.tripmate.user.persistance;

import grp16.tripmate.user.model.User;

public interface IUserPersistance {
    User getUser() throws Exception;
}
