package launch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with Intellij IDEA.
 * Project name: cashTestConverter.
 * Date: 07.05.2015.
 * Time: 20:43.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */

public class SiteFilterBanks {

    private StringBuilder siteSource = new StringBuilder(new SiteDownload().getSource("NBU"));

    SiteFilterBanks() {
    }

    String returnAskValueBySourceAndOperation(String source, String operation) {

        int beginIndex = source.indexOf("<rateSale>") + 10;
        int endIndex = source.indexOf("</rateSale>");

        if (operation.equals("buy")) {/**/

            beginIndex = source.indexOf("<rateBuy>") + 9;
            endIndex = source.indexOf("</rateBuy>");

        }


        return source.substring(beginIndex, endIndex);

    }

    String getId(String source) {

        int beginIndex = source.indexOf("<codeAlpha>") + 11;

        int endIndex = source.indexOf("</codeAlpha>", beginIndex);

        return source.substring(beginIndex, endIndex);
    }

    ArrayList<String> getAllCurrency() {
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

    Set<String> getIdsForExchange() {
        Set<String> iDs = new HashSet<>();

        for (String s : getAllCurrency()) {

            String id = getId(s).substring(0, 3);
            iDs.add(id);

        }

        return iDs;
    }

    protected String getCurrencyById(String id) {

        StringBuilder result = new StringBuilder(siteSource);

        StringBuilder temp = new StringBuilder();

        StringBuilder currencyValue = new StringBuilder();

        int start = result.indexOf("<item>");

        int end = result.indexOf("</item>", start);
        while (result.length() > 0) {

            temp.append(result, start, end);

            if (temp.toString().contains(id)) {

                currencyValue.append(result, start, end);
                break;
            } else {
                temp.setLength(0);

                result.delete(start, end);

                start = result.indexOf("<item>");

                end = result.indexOf("</item>", start);

            }
        }
        return String.valueOf(currencyValue);

    }

    String returnBankName(String source) {

        int start = source.indexOf("<bankName>") + 10;
        int end = source.indexOf("</bankName>");

        return source.substring(start, end);
    }
}
