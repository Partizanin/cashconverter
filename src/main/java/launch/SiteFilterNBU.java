package launch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Partizanin on 07.05.2015.
 */

public class SiteFilterNBU {

    public static void main(String[] args) {

        SiteFilterNBU sf = new SiteFilterNBU();

        System.out.println(sf.returnAskValueBySourceAndOperation(sf.getCurrencyById("USD"),"buy"));
    }

    private StringBuilder siteSource = new StringBuilder(new SiteDownload().getSource("NBU"));

    public SiteFilterNBU() {
    }


    protected String returnAskValueBySourceAndOperation(String source, String operation) {

        int beginIndex = source.indexOf("<rate>") + 6;
        int endIndex = source.indexOf("</rate>");

        if (!operation.equals("buy")) {

            return "1";
        }


        return source.substring(beginIndex, endIndex);

    }


    protected String getId(String source) {

        int beginIndex = source.indexOf("<char3>") + 7;

        int endIndex = source.indexOf("</char3>", beginIndex);

        return source.substring(beginIndex, endIndex);
    }


    protected ArrayList<String> getAllCurrency() {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder source = new StringBuilder(siteSource);

        StringBuilder currency = new StringBuilder();

        while (source.indexOf("<item>") != -1) {

            int start = source.indexOf("<item>");

            int end = source.indexOf("</item>", start);

            currency.append(source, start, end);

            list.add(String.valueOf(currency));

            source.delete(0, end);

            currency.setLength(0);

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

        int start = result.indexOf("<item>");

        int end = result.indexOf("</item>", start);
        while (result.length() > 0) {

            test.append(result, start, end);

            if (test.toString().contains(id)) {

                currencyValue.append(result, start, end);
                break;
            } else {
                test.setLength(0);

                result.delete(start, end);

                start = result.indexOf("<item>");

                end = result.indexOf("</item>", start);

            }
        }
        return String.valueOf(currencyValue);

    }
}
