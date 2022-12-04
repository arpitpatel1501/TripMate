package grp16.tripmate.user.database;

public class UserQueryBuilder implements IUserQueryBuilder {
    private static IUserQueryBuilder instance;


    public UserQueryBuilder() {
        //Required empty constructor
    }

    public static IUserQueryBuilder getInstance() {
        if (instance == null) {
            instance = new UserQueryBuilder();
        }
        return instance;
    }


    @Override
    public String getUserByUsername(String username) {
        return "SELECT `User`.`id`,\n" +
                "    `User`.`firstname`,\n" +
                "    `User`.`lastname`,\n" +
                "    `User`.`email`,\n" +
                "    `User`.`password`,\n" +
                "    `User`.`birthdate`,\n" +
                "    `User`.`gender`\n" +
                "FROM `User` where email = " + username;
    }

    @Override
    public String getUserByUserID(int userid) {
        return "SELECT `User`.`id`,\n" +
                "    `User`.`firstname`,\n" +
                "    `User`.`lastname`,\n" +
                "    `User`.`email`,\n" +
                "    `User`.`password`,\n" +
                "    `User`.`birthdate`,\n" +
                "    `User`.`gender`\n" +
                "FROM `User` where id = " + userid;
    }
}


