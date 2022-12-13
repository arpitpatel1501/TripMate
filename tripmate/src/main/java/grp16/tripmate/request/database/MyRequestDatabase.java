package grp16.tripmate.request.database;

import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.postrequest.model.PostRequestStatus;
import grp16.tripmate.request.model.IMyRequestFactory;
import grp16.tripmate.request.model.MyRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyRequestDatabase implements IMyRequestDatabase {
    IDatabaseExecutor databaseExecutor;
    IMyRequestQueryGenerator queryGenerator;

    public MyRequestDatabase(IDatabaseExecutor databaseExecutor, IMyRequestQueryGenerator queryGenerator) {
        this.databaseExecutor = databaseExecutor;
        this.queryGenerator = queryGenerator;
    }

    @Override
    public List<MyRequest> getMyRequestByUserId(IMyRequestFactory myRequestFactory, int userId) {
        String query = queryGenerator.getMyRequestByUserId(userId);
        return listToMyRequests(databaseExecutor.executeSelectQuery(query), myRequestFactory);
    }

    private List<MyRequest> listToMyRequests(List<Map<String, Object>> results, IMyRequestFactory requestFactory) {
        List<MyRequest> myRequests = new ArrayList<>();

        for (Map<String, Object> result : results) {
            MyRequest myRequest = (MyRequest) requestFactory.makeMyRequest();
            String status = (String) result.get("status");
            //TODO: do something about this
            if (status.equals("pending")) {
                myRequest.setStatus(PostRequestStatus.PENDING);
            } else if (status.equals("approved")) {
                myRequest.setStatus(PostRequestStatus.ACCEPT);
            } else {
                myRequest.setStatus(PostRequestStatus.DECLINE);
            }
            myRequest.setPostTitle((String) result.get("postTitle"));
            myRequest.setFirstNameCreator((String) result.get("firstNameCreator"));
            myRequest.setLastNameCreator((String) result.get("lastNameCreator"));

            myRequests.add(myRequest);
        }
        return myRequests;
    }
}
