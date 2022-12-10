package grp16.tripmate.postrequest.model;

public class MyPostRequestFactory implements IMyPostRequestFactory{

    private IMyPostRequestFactory instance = null;

    public IMyPostRequestFactory getInstance() {
        if (instance == null) {
            instance = new MyPostRequestFactory();
        }
        return instance;

    }
    public IMyPostRequest createMyPostRequest() throws Exception {
        return new MyPostRequest();
    }
}
