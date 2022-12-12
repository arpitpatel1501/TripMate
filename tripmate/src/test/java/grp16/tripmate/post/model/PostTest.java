package grp16.tripmate.post.model;

import grp16.tripmate.post.database.IPostDatabase;
import grp16.tripmate.post.database.PostDatabaseMock;
import grp16.tripmate.post.model.exception.MinAgeGreaterThanMaxAgeException;
import grp16.tripmate.post.model.exception.StartDateAfterEndDateException;
import grp16.tripmate.post.model.exception.StartDateBeforeTodayException;
import grp16.tripmate.post.model.factory.IPostFactory;
import grp16.tripmate.post.model.factory.PostFactory;
import org.junit.jupiter.api.*;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    IPostFactory factory = PostFactory.getInstance();
    IPostDatabase database = new PostDatabaseMock();
    Post post;

    public PostTest() throws ParseException{
        post = (Post) factory.makeNewPost();
        post.setId(1);
        post.setOwner_id(2);
        post.setTitle("Test Title");
        post.setCapacity(6);
        post.setSource("A");
        post.setDestination("B");
        post.setStartDate("2024-12-01");
        post.setEndDate("2025-12-01");
        post.setMinAge(5);
        post.setMaxAge(10);
        post.setDescription("Test Description");
        post.setHidden(false);
    }

    @Test
    @Order(1)
    void createPost() throws Exception {
        assertTrue(post.createPost(database));
    }

    @Test
    @Order(2)
    void getPostsByUserId() throws Exception {
        post.createPost(database);
        assertEquals(post.getPostsByUserId(database, 2).size(), 1);
    }

    @Test
    @Order(3)
    void getAllPosts() throws Exception {
        post.createPost(database);
        assertEquals(post.getAllPosts(database, 2).size(), 1);
    }

    @Test
    @Order(4)
    void getPostByPostId() throws Exception {
        post.createPost(database);
        Post postFromDb = post.getPostByPostId(database, post.getId());
        assertEquals(post.getId(), postFromDb.getId());
    }

    @Test
    @Order(5)
    void updatePost() throws Exception {
        post.createPost(database);
        post.setTitle("Updated Title");
        post.updatePost(database);
        Post postFromDb = post.getPostByPostId(database, post.getId());
        assertEquals(postFromDb.getTitle(), "Updated Title");
    }

    @Test
    @Order(6)
    void deletePost() throws Exception {
        post.createPost(database);
        post.deletePost(database);
        Post postFromDB = post.getPostByPostId(database, post.getId());
        assertEquals(postFromDB, null);
    }

    @Test
    @Order(7)
    void hidePost() throws Exception {
        post.createPost(database);
        post.hidePost(database);
        Post postFromDb = post.getPostByPostId(database, post.getId());
        assertTrue(postFromDb.isHidden());
    }

    @Test
    @Order(8)
    void validatePostStartDateBeforeToday() throws Exception {
        post.setStartDate("2022-12-01");
        post.createPost(database);
        assertThrows(StartDateBeforeTodayException.class, () -> post.validatePost(factory.makePostValidator()));
    }

    @Test
    @Order(9)
    void validatePostStartDateAfterEndDate() throws Exception {
        post.setStartDate("2040-12-01");
        post.createPost(database);
        assertThrows(StartDateAfterEndDateException.class, () -> post.validatePost(factory.makePostValidator()));
    }

    @Test
    @Order(10)
    void validatePostMinAgeGreaterThanMaxAge() throws Exception {
        post.setMinAge(15);
        post.createPost(database);
        assertThrows(MinAgeGreaterThanMaxAgeException.class, () -> post.validatePost(factory.makePostValidator()));
    }
}