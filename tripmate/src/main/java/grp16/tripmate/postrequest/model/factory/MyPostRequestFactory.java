package grp16.tripmate.postrequest.model.factory;

import grp16.tripmate.postrequest.model.IMyPostRequest;
import grp16.tripmate.postrequest.model.MyPostRequest;

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
