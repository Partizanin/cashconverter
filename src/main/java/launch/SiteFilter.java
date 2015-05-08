package launch;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Partizanin on 07.05.2015.
 */
public abstract class SiteFilter {

    protected abstract String returnAskValueBySourceAndOperation(String source, String operation);

    protected abstract String getId(String source);

    protected abstract ArrayList<String> getAllCurrency();

    protected abstract Set<String> getIdsForExchange();

    protected abstract String getCurrencyById(String startPoint);
}
