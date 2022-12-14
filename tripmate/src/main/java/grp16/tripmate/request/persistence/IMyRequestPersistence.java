package grp16.tripmate.request.persistence;

import grp16.tripmate.request.model.factory.IMyRequestFactory;
import grp16.tripmate.request.model.MyRequest;

import java.util.List;

public interface IMyRequestPersistence {

    List<MyRequest> getMyRequestByUserId(IMyRequestFactory myRequestFactory, int userId);
}
