package grp16.tripmate.user.model;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.user.encoder.PasswordEncoder;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private final ILogger logger = new MyLoggerAdapter(this);

    private IUserFactory userFactory;

    private User user;

    UserTest() throws NoSuchAlgorithmException, ParseException {
        userFactory = UserFactory.getInstance();
        createTestUser();
    }

    private void createTestUser() throws NoSuchAlgorithmException, ParseException {
        user = (User) userFactory.getNewUser();
        user.setUsername("uname");
        user.setPassword("password");
        user.setId(1);
        user.setLastname("lastname");
        user.setFirstname("firstname");
        user.setBirthDate("1999-01-11");
        user.setGender("Male");
    }


    @Test
    void getId() {
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    void setId() {
        user.setId(2);
        Assertions.assertEquals(2, user.getId());
    }

    @Test
    void getFirstname() {
        Assertions.assertEquals("firstname", user.getFirstname());
    }

    @Test
    void setFirstname() {
        user.setFirstname("first name 2");
        Assertions.assertEquals("first name 2", user.getFirstname());
    }

    @Test
    void getLastname() {
        Assertions.assertEquals("lastname", user.getLastname());
    }

    @Test
    void setLastname() {
        user.setLastname("last anem 2");
        Assertions.assertEquals("last anem 2", user.getLastname());
    }

    @Test
    void getBirthDate() throws ParseException {
        Assertions.assertEquals(new SimpleDateFormat("dd-MM-yyyy").parse("11-01-1999"), user.getBirthDate());
    }

    @Test
    void setBirthDate() throws ParseException {
        user.setBirthDate("12-01-1999");
        Assertions.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("12-01-1999"), user.getBirthDate());
    }

    @Test
    void getGender() {
        Assertions.assertEquals("Male", user.getGender());
    }

    @Test
    void setGender() {
        user.setGender("Female");
        Assertions.assertEquals("Female", user.getGender());
    }

    @Test
    void getUsername() {
        Assertions.assertEquals("uname", user.getUsername());
    }

    @Test
    void setUsername() {
        user.setUsername("username 2");
        Assertions.assertEquals("username 2", user.getUsername());
    }

    @Test
    void getPassword() throws NoSuchAlgorithmException {
        Assertions.assertEquals(PasswordEncoder.encodeString("password"), user.getPassword());
    }

    @Test
    void setPassword() throws NoSuchAlgorithmException {
        user.setPassword("password 2");
        Assertions.assertEquals(PasswordEncoder.encodeString("password 2"), user.getPassword());

    }

    @Test
    void testToString() {
        logger.info(user.toString());
        Assertions.assertEquals(user.toString(), user.toString());
    }

    @Test
    void validateUser() {
    }

    @Test
    void createUser() {
    }

    @Test
    void getLoggedInUser() {
    }

    @Test
    void dateToSQLDate() {
        String SQLBirthDate = user.dateToSQLDate(user.getBirthDate());
        Assertions.assertEquals("1999-01-11", SQLBirthDate);
    }

    @Test
    void changeUserDetails() {
    }

    @Test
    void getUserById() {
    }
}