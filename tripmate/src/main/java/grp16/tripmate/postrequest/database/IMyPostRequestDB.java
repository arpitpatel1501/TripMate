package grp16.tripmate.postrequest.database;

public interface IMyPostRequestDB {
    String getPostRequestByUserId(int userid);

    String createJoinRequest(int post_id, int user_id);

}
