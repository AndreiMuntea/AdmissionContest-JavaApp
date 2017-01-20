package DatabaseManager.DatabaseDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 2017-01-04.
 */
public class Query {
    private String query;
    private List<String> queryArguments;

    public Query() {
        query = "";
        queryArguments = new ArrayList<String>();
    }

    public Query(String query, List<String> queryArguments) {
        this.query = query;
        this.queryArguments = queryArguments;
    }

    public void addArgument(String argument) {
        queryArguments.add(argument);
    }

    public void addStatement(String statement){query += statement;}

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getQueryArguments() {
        return queryArguments;
    }

    public void setQueryArguments(List<String> queryArguments) {
        this.queryArguments = queryArguments;
    }

    public void addQuery(Query other, String separator)
    {
        if (other == null) return;
        this.query += other.getQuery() + separator;
        this.queryArguments.addAll(other.getQueryArguments());
    }
}
