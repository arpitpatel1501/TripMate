package grp16.tripmate.request.model;

import grp16.tripmate.request.persistence.IMyRequestPersistence;
import grp16.tripmate.request.model.factory.IMyRequestFactory;

import java.util.List;

public interface IMyRequest {
    List<MyRequest> getMyRequestByUserId(IMyRequestFactory requestFactory, IMyRequestPersistence database, int userId);
}
