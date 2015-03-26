package launch;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * Project name: Partizanin
 * User: Partizanin
 * Date: 28.08.2014
 * Time:  22:00
 * To change this template use File|Setting|File Templates.
 */
public class SiteFilter {

    public SiteFilter() {
        createExchangeList();
    }

    private static DecimalFormat df = new DecimalFormat("#.####");

    private static StringBuilder siteSource = new StringBuilder(new SiteDownload().getSource());

    private static List<Exchange> exchangeList = new ArrayList<>();

    private Exchange getExchangeById(String id) {

        for (Exchange exchange : exchangeList) {
            if (exchange.getId().equals(id)) {
                return exchange;
            }
        }

        return new Exchange("Null","0",0,0);
    }

    public String getCourse(String exchangeId,String transactionValue) {

        return String.valueOf(getExchangeById(exchangeId).getCourse(transactionValue));
    }

    private static void createExchangeList() {

        List<String> list = getAllCurrency();

        for (String s : list) {

            Exchange exchange = new Exchange();
            exchange.setName(getName(s));
            exchange.setBuyCourse(Double.parseDouble(df.format(Double.parseDouble(returnAskValueFromMethod(s, "buy")))));
            exchange.setId(getId(s));
            exchange.setSellCourse(Double.parseDouble(df.format(Double.parseDouble(returnAskValueFromMethod(s, "sell")))));

            exchangeList.add(exchange);
        }

        /*createNewExchange();*/

    }

    private static String getValues() {
        StringBuilder siteSourceCode = siteSource;

        StringBuilder result = new StringBuilder();

        int start = siteSourceCode.indexOf("<results>");

        int end = siteSourceCode.indexOf("</results>");

        for (int i = start; i < siteSourceCode.length(); i++) {

            if (i > start && i <= end) {

                result.append(siteSourceCode.charAt(i));
            }
        }

        return String.valueOf(result);
    }

    private static String returnAskValueFromMethod(String source, String operation) {
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

    private static String getName(String source) {

        StringBuilder returnName = new StringBuilder(getId(source));

        returnName.insert(3, "  ");
        returnName.insert(4, "to");


        return String.valueOf(returnName);

    }

    private static String getId(String source) {


        StringBuilder id = new StringBuilder();


        int start = source.indexOf("<rate id=") + 10;

        int end = source.indexOf('"' + ">", start);


        id.append(source, start, end);


        return String.valueOf(id);

    }
    /*Достает xml исходник все х курсов*/
    private static ArrayList<String> getAllCurrency() {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder source = new StringBuilder(getValues());

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
    private static String getCurrency(String startPoint) {

        StringBuilder result = new StringBuilder(getValues());

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

    private static void createNewExchange() {

        for (Exchange exchange : exchangeList) {
            Exchange newExchange = new Exchange();

            char[] chars = exchange.getId().toCharArray();
            StringBuilder id = new StringBuilder();
            id.append(chars[3]);
            id.append(chars[4]);
            id.append(chars[5]);
            id.append(chars[0]);
            id.append(chars[1]);
            id.append(chars[2]);


        }
    }
    /*Проверяет существующие курсы по id*/
    private static boolean containOfId(String id) {

        for (Exchange exchange : exchangeList) {
            if (exchange.getId().equals(id)) {
                return true;
            }

        }

        return false;
    }

}
