package grp16.tripmate.request.database;

import grp16.tripmate.request.model.factory.IMyRequestFactory;
import grp16.tripmate.request.model.MyRequest;

import java.util.List;

public interface IMyRequestDatabase {

    List<MyRequest> getMyRequestByUserId(IMyRequestFactory myRequestFactory, int userId);
}
