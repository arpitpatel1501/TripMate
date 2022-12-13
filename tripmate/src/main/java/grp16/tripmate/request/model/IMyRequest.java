package grp16.tripmate.request.model;

import grp16.tripmate.request.database.IMyRequestDatabase;
import grp16.tripmate.request.model.factory.IMyRequestFactory;

import java.util.List;

public interface IMyRequest {
    List<MyRequest> getMyRequestByUserId(IMyRequestFactory requestFactory, IMyRequestDatabase database, int userId);
}
