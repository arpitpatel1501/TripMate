package grp16.tripmate.post.model;

import grp16.tripmate.user.model.IUserFactory;
import grp16.tripmate.user.model.UserFactory;

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
        return new Post();
    }
}
