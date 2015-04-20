package launch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with Intellij IDEA.
<<<<<<< HEAD
 * Project name: Partizanin
=======
 * Project name: proff16
>>>>>>> f22dc58137c88b20b01febf6162ff54b82423d57
 * User: Partizanin
 * Date: 28.08.2014
 * Time:  22:00
 * To change this template use File|Setting|File Templates.
 */
public class SiteFilter {

    public SiteFilter() {

    }

    public static void main(String[] args) {
        SiteFilter sf = new SiteFilter();

        for (String s : sf.getIdsForExchange()) {
            System.out.println(s);
        }

    }


    private StringBuilder siteSource = new StringBuilder(new SiteDownload().getSource());


    protected String returnAskValueBySourceAndOperation(String source, String operation) {
        StringBuilder returnValue = new StringBuilder();

        int start = 0;
        int finish = 0;

        if (operation.equals("buy")) {

            start = source.indexOf("<Ask>") + 4;
            finish = source.indexOf("</Ask>");


        } else { /*operation sell*/

            start = source.indexOf("<Bid>") + 4;
            finish = source.indexOf("</Bid>");
        }

        for (int i = start; i < source.length(); i++) {

            if (i > start && i < finish) {

                returnValue.append(source.charAt(i));
            }
        }

        return String.valueOf(returnValue);
    }

    protected String getId(String source) {


        StringBuilder id = new StringBuilder();


        int start = source.indexOf("<rate id=") + 10;

        int end = source.indexOf('"' + ">", start);


        id.append(source, start, end);


        return String.valueOf(id);

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

            int start = source.indexOf("<rate id=" + '"') - 1;

            int end = source.indexOf("</rate>", start);

            for (int i = start; i < source.length(); i++) {

                if (i > start && i < end) {

                    currency.append(source.charAt(i));

                } else if (i == end) {
                    list.add(String.valueOf(currency));

                    source.delete(0, i);

                    currency.setLength(0);
                    break;
                }
            }

        }

        return list;
    }

    /*Достает xml исходник всего курса*/
    private String getCurrencyById(String startPoint) {

        StringBuilder result = new StringBuilder(siteSource);

        StringBuilder currencyValue = new StringBuilder();

        int start = result.indexOf("<rate id=" + '"' + startPoint + '"' + ">");

        int end = result.indexOf("</rate>", start);


        for (int i = start; i < result.length(); i++) {

            if (i > start && i < end) {

                currencyValue.append(result.charAt(i));

            }
        }

        return String.valueOf(currencyValue);

    }
}
