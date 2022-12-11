package grp16.tripmate.post.model;

import grp16.tripmate.notification.INotificationObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class PostSubject {
    private final List<INotificationObserver> observerList;

    public PostSubject() {
        observerList = new ArrayList<>();
    }

    public void attach(INotificationObserver observer) {
        observerList.add(observer);
    }

    public void detach(INotificationObserver observer) {
        observerList.remove(observer);
    }
    public void notifyObservers() {
        for(int i = 0 ; i<observerList.size() ; i++) {
            observerList.get(i).update();
        }
    }
}
