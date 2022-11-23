package grp16.tripmate.user.model;

import grp16.tripmate.user.persistance.IUserPersistance;

public class UserValidation {
    private IUserPersistance persistance;

    public UserValidation(IUserPersistance persistance) {
        this.persistance = persistance;
    }

    public boolean validateUser(User user){

        return true;
    }
}
