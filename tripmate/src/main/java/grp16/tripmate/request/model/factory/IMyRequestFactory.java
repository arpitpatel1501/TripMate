package grp16.tripmate.request.model.factory;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.request.database.IMyRequestDatabase;
import grp16.tripmate.request.model.IMyRequest;

public interface IMyRequestFactory {
    IMyRequest makeMyRequest();

    ILogger makeLogger(Object classObj);

    IMyRequestDatabase makeMyRequestDatabase();
}
