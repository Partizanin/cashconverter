package launch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Partizanin on 07.05.2015.
 */

public class SiteFilterNBU  {

    public static void main(String[] args) {

        SiteFilterNBU sf = new SiteFilterNBU();

        System.out.println("sf.getCurrencyById(\"USD\") = " + sf.getCurrencyById("USD"));
    }

    private StringBuilder siteSource = new StringBuilder(new SiteDownload().getSource("NBU"));

    public SiteFilterNBU() {
    }


    protected String returnAskValueBySourceAndOperation(String source, String operation) {
        StringBuilder returnValue = new StringBuilder();

        int start = 0;
        int finish = 0;

        if (operation.equals("buy")) {

            start = source.indexOf("<rateBuy>") + 4;
            finish = source.indexOf("</rateBuy>");


        } else { /*operation sell*/

            start = source.indexOf("<rateSale>") + 4;
            finish = source.indexOf("</rateSale>");
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


        int start = source.indexOf("<codeAlpha>") + 10;

        int end = source.indexOf("</codeAlpha>", start);


        id.append(source, start, end);


        return String.valueOf(id);
    }


    protected ArrayList<String> getAllCurrency() {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder source = new StringBuilder(siteSource);

        StringBuilder currency = new StringBuilder();

        while (source.indexOf("<chapter>") != -1) {

            int start = source.indexOf("<chapter>") - 1;

            int end = source.indexOf("</chapter>", start);

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


    protected Set<String> getIdsForExchange() {
        Set<String> iDs = new HashSet<>();

        for (String s : getAllCurrency()) {

            String id = getId(s).substring(0, 3);
            iDs.add(id);

        }

        return iDs;
    }

    protected String getCurrencyById(String id) {

        StringBuilder result = new StringBuilder(siteSource);

        StringBuilder test = new StringBuilder();

        StringBuilder currencyValue = new StringBuilder();

        int start = result.indexOf("</item>");

        int end = result.indexOf("</item>", start);


        while (result.length() > 0) {

            test.append(result, start, end);

            if (test.toString().contains(id)) {

                currencyValue.append(result, start, end);
                break;
            } else {

                result.delete(end, result.length());

                start = result.indexOf("</item>");

                end = result.indexOf("</item>", start);

            }
        }
        return String.valueOf(currencyValue);

    }
}
