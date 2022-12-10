package grp16.tripmate.postrequest.model;

public class MyPostRequestFactory implements IMyPostRequestFactory{

    private static IMyPostRequestFactory instance = null;

    public static IMyPostRequestFactory getInstance() {
        if (instance == null) {
            instance = new MyPostRequestFactory();
        }
        return instance;

    }
    public IMyPostRequest createMyPostRequest() throws Exception {
        return new MyPostRequest();
    }
}
