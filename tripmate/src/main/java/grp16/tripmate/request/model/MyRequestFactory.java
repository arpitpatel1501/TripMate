package grp16.tripmate.request.model;

public class MyRequestFactory implements IMyRequestFactory{
    private static IMyRequestFactory instance = null;

    public static IMyRequestFactory getInstance() {
        if(instance == null) {
            instance = new MyRequestFactory();
        }
        return instance;
    }

    @Override
    public IMyRequest createMyRequest() {
        return new MyRequest();
    }
}
