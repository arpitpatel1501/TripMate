package grp16.tripmate.post.database;

import java.sql.ResultSet;
import java.sql.Statement;

public interface IMyPostRequestDB {
    public String getPostRequestByUserId(int userid);
}
