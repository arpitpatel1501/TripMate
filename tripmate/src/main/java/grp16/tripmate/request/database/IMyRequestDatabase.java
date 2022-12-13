package grp16.tripmate.request.database;

import grp16.tripmate.request.model.IMyRequestFactory;
import grp16.tripmate.request.model.MyRequest;

import java.util.List;

public interface IMyRequestDatabase {

    List<MyRequest> getMyRequestByUserId(IMyRequestFactory myRequestFactory, int userId);
}
