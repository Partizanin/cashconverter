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
public class SiteFilterYahoo {

    private StringBuilder siteSource = new StringBuilder(new SiteDownload().getSource("yahoo"));

    public SiteFilterYahoo() {

    }


    public static void main(String[] args) {
        SiteFilterYahoo sf = new SiteFilterYahoo();
        for (String s : sf.getAllCurrency()) {
            System.out.println(s);
        }

    }


    protected String returnAskValueBySourceAndOperation(String source, String operation) {

        int beginIndex = source.indexOf("<Bid>") + 5;
        int endIndex = source.indexOf("</Bid>");

        if (operation.equals("buy")) {

            beginIndex = source.indexOf("<Ask>") + 5;
            endIndex = source.indexOf("</Ask>");
        }

        return source.substring(beginIndex, endIndex);
    }

    protected String getId(String source) {

        int beginIndex = source.indexOf("<rate id=") + 10;

        int endIndex = source.indexOf('"' + ">", beginIndex);


        return source.substring(beginIndex,endIndex);

    }

    protected Set<String> getIdsForExchange() {
        Set<String> iDs = new HashSet<>();

        for (String s : getAllCurrency()) {

            String id = getId(s).substring(0, 3);

            iDs.add(id);
        }

        return iDs;
    }

    /*Достает xml исходник все х курсов*/
    protected ArrayList<String> getAllCurrency() {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder source = new StringBuilder(siteSource);

        StringBuilder currency = new StringBuilder();

        while (source.indexOf("<rate id=" + '"') != -1) {

            int start = source.indexOf("<rate id=" + '"');

            int end = source.indexOf("</rate>", start);

            currency.append(source, start, end);

            list.add(String.valueOf(currency));

            source.delete(0, end);

            currency.setLength(0);
        }

        return list;
    }

    /*Достает xml исходник всего курса*/

    protected String getCurrencyById(String startPoint) {
        int start = siteSource.indexOf("<rate id=" + '"' + startPoint + '"' + ">");

        int end = siteSource.indexOf("</rate>", start);

        return siteSource.substring(start, end);

    }

}
