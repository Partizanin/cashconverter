package launch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with Intellij IDEA.
 * Project name: Partizanin
 * User: Partizanin
 * Date: 28.08.2014
 * Time:  22:00
 * To change this template use File|Setting|File Templates.
 */
public class SiteFilterGoogle {

    private StringBuilder siteSource = new StringBuilder(new SiteDownload().getSource("Google"));

    SiteFilterGoogle() {
    }

    String returnAskValueBySourceAndOperation(String source, String operation) {
        int beginIndex = source.indexOf("\"sellValue\":") + 12;
        int endIndex = beginIndex + 4;


        if (operation.equals("buy")) {

            beginIndex = source.indexOf("\"buyValue\":") + 11;
            endIndex = beginIndex + 4;
        }
        return source.substring(beginIndex, endIndex);
    }

    String getId(String source) {

        return source.substring(9, 15);

    }

    Set<String> getIdsForExchange() {
        Set<String> iDs = new HashSet<>();

        for (String s : getAllCurrency()) {

            String id = getId(s).substring(0, 3);

            iDs.add(id);
        }

        return iDs;
    }

    /*Достает xml исходник все х курсов*/
    ArrayList<String> getAllCurrency() {
        ArrayList<String> list = new ArrayList<>();

        StringBuilder siteSource = this.siteSource;


        int startIndex = siteSource.indexOf("{\"name\"");
        int endIndex = siteSource.indexOf("}", startIndex) + 1;

        while (startIndex != -1) {
            String substring = siteSource.substring(startIndex, endIndex);
            list.add(substring);

            siteSource = new StringBuilder(siteSource.substring(endIndex, siteSource.length()));


            startIndex = siteSource.indexOf("{\"name\"");
            endIndex = siteSource.indexOf("}", startIndex) + 1;

        }

        return list;
    }

    /*Достает xml исходник всего курса*/
    protected String getCurrencyById(String startPoint) {
        return "";
    }

}
