package grp16.tripmate.user.model;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.user.model.factory.IUserFactory;
import grp16.tripmate.user.model.factory.UserFactory;
import grp16.tripmate.user.persistence.UserPersistenceMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
class UserTest {
    private final ILogger logger = new MyLoggerAdapter(this);

    private final IUserFactory userFactory;

    private User user;

    private final UserPersistenceMock userPersistence;

    UserTest() throws NoSuchAlgorithmException, ParseException {
        userFactory = UserFactory.getInstance();
        createTestUser();
        userPersistence = new UserPersistenceMock();
    }

    private void createTestUser() throws NoSuchAlgorithmException, ParseException {
        user = (User) userFactory.makeNewUser();
        user.setUsername("uname");
        user.setPassword("password");
        user.setId(1);
        user.setLastname("lastname");
        user.setFirstname("firstname");
        user.setBirthDate("1999-01-11");
        user.setGender("Male");
    }

    @Test
    void getIdTest() {
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    void setIdTest() {
        user.setId(2);
        Assertions.assertEquals(2, user.getId());
    }

    @Test
    void getFirstnameTest() {
        Assertions.assertEquals("firstname", user.getFirstname());
    }

    @Test
    void setFirstnameTest() {
        user.setFirstname("first name 2");
        Assertions.assertEquals("first name 2", user.getFirstname());
    }

    @Test
    void getLastnameTest() {
        Assertions.assertEquals("lastname", user.getLastname());
    }

    @Test
    void setLastnameTest() {
        user.setLastname("last name 2");
        Assertions.assertEquals("last name 2", user.getLastname());
    }

    @Test
    void getBirthDateTest() throws ParseException {
        Assertions.assertEquals(new SimpleDateFormat("dd-MM-yyyy").parse("11-01-1999"), user.getBirthDate());
    }

    @Test
    void setBirthDateTest() throws ParseException {
        user.setBirthDate("12-01-1999");
        Assertions.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("12-01-1999"), user.getBirthDate());
    }

    @Test
    void getGenderTest() {
        Assertions.assertEquals("Male", user.getGender());
    }

    @Test
    void setGenderTest() {
        user.setGender("Female");
        Assertions.assertEquals("Female", user.getGender());
    }

    @Test
    void getUsernameTest() {
        Assertions.assertEquals("uname", user.getUsername());
    }

    @Test
    void setUsernameTest() {
        user.setUsername("username 2");
        Assertions.assertEquals("username 2", user.getUsername());
    }

    @Test
    void getPasswordTest() throws NoSuchAlgorithmException {
        Assertions.assertEquals(userFactory.makePasswordEncoder().encodeString("password"), user.getPassword());
    }

    @Test
    void setPasswordTest() throws NoSuchAlgorithmException {
        user.setPassword("password 2");
        Assertions.assertEquals(userFactory.makePasswordEncoder().encodeString("password 2"), user.getPassword());
    }

    @Test
    void testToStringTest() {
        logger.info(user.toString());
        Assertions.assertEquals(user.toString(), user.toString());
    }

    @Test
    void createUserPositiveTest() {
        Assertions.assertTrue(userPersistence.insertUser(user));
    }

    @Test
    void createUserNegativeTest() {
        Assertions.assertFalse(userPersistence.insertUser(user));
    }

    @Test
    void validateUserPositiveTest() throws NoSuchAlgorithmException {
        userPersistence.insertUser(user);
        Assertions.assertEquals(user, userPersistence.getUserByUsername("uname"));
    }

    @Test
    void validateUserNegativeTest() throws NoSuchAlgorithmException {
        Assertions.assertNull(userPersistence.getUserByUsername("sharshil1299@gmail.com"));
    }

    @Test
    void dateToSQLDateTest() {
        String SQLBirthDate = user.dateToSQLDate(user.getBirthDate());
        Assertions.assertEquals("1999-01-11", SQLBirthDate);
    }

    @Test
    void changeUserDetailsPositiveTest() throws Exception {
        Assertions.assertTrue(userPersistence.updateUser(user));
    }

    @Test
    void changeUserDetailsNegativeTest() throws Exception {
        user.setId(4);
        Assertions.assertFalse(userPersistence.updateUser(user));
    }
}