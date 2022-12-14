package grp16.tripmate.joinRequest.model;

import grp16.tripmate.joinRequest.persistence.IMyRequestPersistence;
import grp16.tripmate.joinRequest.model.factory.IMyRequestFactory;

import java.util.List;

public interface IMyRequest {
    List<MyRequest> getMyRequestByUserId(IMyRequestFactory requestFactory, IMyRequestPersistence database, int userId);
}
