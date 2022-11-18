package grp16.tripmate.post.model;

import grp16.tripmate.user.model.User;

public class PostRequest {
    private int id;
    private PostRequestStatus status;
    private Post post;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PostRequestStatus getStatus() {
        return status;
    }

    public void setStatus(PostRequestStatus status) {
        this.status = status;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


