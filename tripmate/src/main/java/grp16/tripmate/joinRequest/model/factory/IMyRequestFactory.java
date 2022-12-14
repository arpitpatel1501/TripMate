package grp16.tripmate.joinRequest.model.factory;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.joinRequest.persistence.IMyRequestPersistence;
import grp16.tripmate.joinRequest.model.IMyRequest;

public interface IMyRequestFactory {
    IMyRequest makeMyRequest();

    ILogger makeLogger(Object classObj);

    IMyRequestPersistence makeMyRequestDatabase();
}
