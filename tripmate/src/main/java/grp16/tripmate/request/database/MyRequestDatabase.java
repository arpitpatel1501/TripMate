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
            System.out.println(result);
            System.out.println("title: " + result.get("title"));
            System.out.println("firstname: " + result.get("firstname"));
            System.out.println("lastname: " + result.get("lastname"));

            MyRequest myRequest = (MyRequest) requestFactory.makeMyRequest();
            String status = (String) result.get("status");
            if (status.equals("PENDING")) {
                myRequest.setStatus(PostRequestStatus.PENDING);
            } else if (status.equals("ACCEPT")) {
                myRequest.setStatus(PostRequestStatus.ACCEPT);
            } else if (status.equals("DECLINE")){
                myRequest.setStatus(PostRequestStatus.DECLINE);
            }
//            myRequest.setStatus(status);
            myRequest.setPostTitle((String) result.get("title"));
            myRequest.setFirstNameCreator((String) result.get("firstname"));
            myRequest.setLastNameCreator((String) result.get("lastname"));

            myRequests.add(myRequest);
        }
        return myRequests;
    }
}
