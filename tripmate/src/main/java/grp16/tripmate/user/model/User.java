package grp16.tripmate.user.model;

import grp16.tripmate.user.database.IUserDatabase;
import grp16.tripmate.user.database.UserDatabase;
import grp16.tripmate.user.encoder.PasswordEncoder;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements IUser {
    private final ILogger logger = new MyLoggerAdapter(this);

    private String username;
    private String password;
    private int id;
    private String firstname;
    private String lastname;
    private Date birthDate;
    private String gender;


    private final IUserDatabase userDatabase;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) throws ParseException {
        this.birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        password = PasswordEncoder.encodeString(password);
        this.password = password;
    }

    public User() {
        userDatabase = new UserDatabase();
    }

    public User(IUserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", id=" + id + ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + ", birthDate=" + birthDate + ", gender='" + gender + '\'' + '}';
    }

    public boolean validateUser() throws Exception {
        return userDatabase.validateUser(this);
    }

    @Override
    public boolean createUser() throws Exception {
        return userDatabase.createUser(this);
    }

    @Override
    public User getLoggedInUser() throws Exception {
        return userDatabase.getLoggedInUser();
    }

    public String dateToSQLDate(Date date) {
        if (date != null) {
            // Ref: https://theopentutorials.com/examples/java/util/date/how-to-convert-java-util-date-to-mysql-date-format/
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return formatter.format(date);
        }
        return "";
    }

    public boolean changeUserDetails() throws Exception {
        return userDatabase.changeUserDetails(this);
    }

    @Override
    public User getUserById(int userid) throws Exception {
        return userDatabase.getUserById(userid);
    }
}
