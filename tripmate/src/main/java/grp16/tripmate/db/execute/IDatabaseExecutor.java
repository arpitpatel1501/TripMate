package grp16.tripmate.db.execute;

import java.util.List;
import java.util.Map;

public interface IDatabaseExecutor {
    List<Map<String, Object>> executeSelectQuery(String query);

    boolean executeUpdateQuery(String query);

    boolean executeDeleteQuery(String query);

    boolean executeInsertQuery(String query);
}
