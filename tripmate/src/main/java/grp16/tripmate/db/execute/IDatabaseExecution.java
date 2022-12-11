package grp16.tripmate.db.execute;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface IDatabaseExecution {
    List<Map<String, Object>> executeSelectQuery(String query);

    boolean executeUpdateQuery(String query);

    boolean executeDeleteQuery(String query);

    boolean executeInsertQuery(String query);
}
