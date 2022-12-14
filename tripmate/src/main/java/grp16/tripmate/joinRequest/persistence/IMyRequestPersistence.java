package grp16.tripmate.joinRequest.persistence;

import grp16.tripmate.joinRequest.model.factory.IMyRequestFactory;
import grp16.tripmate.joinRequest.model.MyRequest;

import java.util.List;

public interface IMyRequestPersistence {

    List<MyRequest> getMyRequestByUserId(IMyRequestFactory myRequestFactory, int userId);
}
