package grp16.tripmate.request.model;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.request.database.IMyRequestDatabase;

public interface IMyRequestFactory {
    IMyRequest makeMyRequest();

    ILogger makeLogger(Object classObj);

    IMyRequestDatabase makeMyRequestDatabase();
}
