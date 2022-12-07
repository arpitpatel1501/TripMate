package grp16.tripmate.post.model;


import java.util.Date;

public class PostFactory implements IPostFactory {

    private static IPostFactory instance;

    private PostFactory() {
        //Required private empty constructor
    }

    public static PostFactory getInstance() {
        if (instance == null) {
            instance = new PostFactory();
        }
        return (PostFactory) instance;
    }

    @Override
    public IPost getNewPost() {
        Post post = new Post();
        post.setStartDate(new Date());
        post.setEndDate(new Date());
        return new Post();
    }
}
