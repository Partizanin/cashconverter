package launch;

import java.text.DecimalFormat;

/**
 * Created with Intellij IDEA.
 * Project name: proff16
 * User: Partizanin
 * Date: 28.08.2014
 * Time:  22:00
 * To change this template use File|Setting|File Templates.
 */
public class SiteFilter {


    private static String siteSource = new SiteDownload().getSource();

    private static DecimalFormat df = new DecimalFormat("#.####");


    public static class UAH {

        private static String buyUSD = buyUSD();
        private static String sellUSD = sellUSD();

        private static String buyRUB = buyRUB();
        private static String sellRUB = sellRUB();

        private static String buyEUR = buyEUR();
        private static String sellEUR = sellEUR();

        private static String buyPLN = buyPLN();
        private static String sellPLN = sellPLN();


        private static String sellRUB() {

            String source = getRUB();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyRUB() {

            String source = getRUB();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellUSD() {

            String source = getUSD();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyUSD() {

            String source = getUSD();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellEUR() {

            String source = getEUR();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyEUR() {

            String source = getEUR();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellPLN() {

            String source = getPLN();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyPLN() {

            String source = getPLN();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }


        private static String getUSD() {
            return getCurrency("UAHUSD");
        }

        private static String getEUR() {
            return getCurrency("UAHEUR");
        }

        private static String getRUB() {
            return getCurrency("UAHRUB");
        }

        private static String getPLN() {
            return getCurrency("UAHPLN");
        }


        public String getBuyPLN() {
            return buyPLN;
        }

        public String getSellPLN() {
            return sellPLN;
        }

        public String getBuyUSD() {
            return buyUSD;
        }

        public String getSellUSD() {
            return sellUSD;
        }

        public String getBuyRUB() {
            return buyRUB;
        }

        public String getSellRUB() {
            return sellRUB;
        }

        public String getBuyEUR() {
            return buyEUR;
        }

        public String getSellEUR() {
            return sellEUR;
        }
    }

    public static class USD {

        private static String buyUAH = buyUAH();
        private static String sellUAH = sellUAH();

        private static String buyRUB = buyRUB();
        private static String sellRUB = sellRUB();

        private static String buyEUR = buyEUR();
        private static String sellEUR = sellEUR();

        private static String buyPLN = buyPLN();
        private static String sellPLN = sellPLN();


        private static String sellRUB() {

            String source = getRUB();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyRUB() {

            String source = getRUB();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellUAH() {

            String source = getUAH();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyUAH() {

            String source = getUAH();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellEUR() {

            String source = getEUR();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyEUR() {

            String source = getEUR();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellPLN() {

            String source = getPLN();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyPLN() {

            String source = getPLN();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }


        private static String getUAH() {
            return getCurrency("USDUAH");
        }

        private static String getEUR() {
            return getCurrency("USDEUR");
        }

        private static String getRUB() {
            return getCurrency("USDRUB");
        }

        private static String getPLN() {
            return getCurrency("USDPLN");
        }

        public String getBuyPLN() {
            return buyPLN;
        }

        public String getSellPLN() {
            return sellPLN;
        }

        public String getBuyUAH() {
            return buyUAH;
        }

        public String getSellUAH() {
            return sellUAH;
        }

        public String getBuyRUB() {
            return buyRUB;
        }

        public String getSellRUB() {
            return sellRUB;
        }

        public String getBuyEUR() {
            return buyEUR;
        }

        public String getSellEUR() {
            return sellEUR;
        }
    }

    public static class EUR {

        private static String buyUSD = buyUSD();
        private static String sellUSD = sellUSD();

        private static String buyRUB = buyRUB();
        private static String sellRUB = sellRUB();

        private static String buyUAH = buyUAH();
        private static String sellUAH = sellUAH();

        private static String buyPLN = buyPLN();
        private static String sellPLN = sellPLN();

        private static String sellRUB() {

            String source = getRUB();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyRUB() {

            String source = getRUB();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellUSD() {

            String source = getUSD();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyUSD() {

            String source = getUSD();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellUAH() {

            String source = getUAH();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyUAH() {

            String source = getUAH();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellPLN() {

            String source = getPLN();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyPLN() {

            String source = getPLN();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String getUSD() {
            return getCurrency("EURUSD");
        }

        private static String getUAH() {
            return getCurrency("EURUAH");
        }

        private static String getRUB() {
            return getCurrency("EURRUB");
        }

        private static String getPLN() {
            return getCurrency("EURPLN");
        }

        public String getBuyPLN() {
            return buyPLN;
        }

        public String getSellPLN() {
            return sellPLN;
        }

        public String getBuyUSD() {
            return buyUSD;
        }

        public String getSellUSD() {
            return sellUSD;
        }

        public String getBuyRUB() {
            return buyRUB;
        }

        public String getSellRUB() {
            return sellRUB;
        }

        public String getBuyUAH() {
            return buyUAH;
        }

        public String getSellUAH() {
            return sellUAH;
        }
    }

    public static class RUB {

        private static String buyUSD = buyUSD();
        private static String sellUSD = sellUSD();

        private static String buyUAH = buyUAH();
        private static String sellUAH = sellUAH();

        private static String buyEUR = buyEUR();
        private static String sellEUR = sellEUR();

        private static String buyPLN = buyPLN();
        private static String sellPLN = sellPLN();

        private static String sellUAH() {

            String source = getUAH();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyUAH() {

            String source = getUAH();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellUSD() {

            String source = getUSD();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyUSD() {

            String source = getUSD();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellEUR() {

            String source = getEUR();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyEUR() {

            String source = getEUR();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellPLN() {

            String source = getPLN();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyPLN() {
            String source = getPLN();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }


        private static String getUSD() {
            return getCurrency("RUBUSD");
        }

        private static String getEUR() {
            return getCurrency("RUBEUR");
        }

        private static String getUAH() {
            return getCurrency("RUBUAH");
        }

        private static String getPLN() {

            return getCurrency("RUBPLN");
        }


        public String getBuyPLN() {
            return buyPLN;
        }

        public String getSellPLN() {
            return sellPLN;
        }

        public String getBuyUSD() {
            return buyUSD;
        }

        public String getSellUSD() {
            return sellUSD;
        }

        public String getBuyUAH() {
            return buyUAH;
        }

        public String getSellUAH() {
            return sellUAH;
        }

        public String getBuyEUR() {
            return buyEUR;
        }

        public String getSellEUR() {
            return sellEUR;
        }
    }

    public static class PLN {

        private static String buyUSD = buyUSD();
        private static String sellUSD = sellUSD();

        private static String buyUAH = buyUAH();
        private static String sellUAH = sellUAH();

        private static String buyEUR = buyEUR();
        private static String sellEUR = sellEUR();

        private static String buyRUB = buyRUB();
        private static String sellRUB = sellRUB();

        private static String buyUAH() {

            String source = getUAH();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellUAH() {

            String source = getUAH();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyRUB() {

            String source = getRUB();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellRUB() {

            String source = getRUB();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyUSD() {

            String source = getUSD();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellUSD() {

            String source = getUSD();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String buyEUR() {

            String source = getEUR();

            String result = returnAskValueFromMethod(source, "buy");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }

        private static String sellEUR() {

            String source = getEUR();

            String result = returnAskValueFromMethod(source, "sell");

            return String.valueOf(df.format(Double.parseDouble(result)));
        }


        private static String getUSD() {
            return getCurrency("PLNUSD");
        }

        private static String getEUR() {
            return getCurrency("PLNEUR");
        }

        private static String getUAH() {
            return getCurrency("PLNUAH");
        }

        private static String getRUB() {
            return getCurrency("PLNRUB");
        }


        public String getSellRUB() {
            return sellRUB;
        }

        public String getBuyRUB() {
            return buyRUB;
        }

        public String getBuyUSD() {
            return buyUSD;
        }

        public String getSellUSD() {
            return sellUSD;
        }

        public String getBuyUAH() {
            return buyUAH;
        }

        public String getSellUAH() {
            return sellUAH;
        }

        public String getBuyEUR() {
            return buyEUR;
        }

        public String getSellEUR() {
            return sellEUR;
        }
    }

    private static String getValues() {
        String siteSourceCode = siteSource;

        String result = " ";

        int start = siteSourceCode.indexOf("<results>");

        int end = siteSourceCode.indexOf("</results>");

        for (int i = start; i < siteSourceCode.length(); i++) {

            if (i > start && i <= end) {

                result += siteSourceCode.charAt(i);
            }
        }

        return result;
    }

    private static String returnAskValueFromMethod(String source, String operation) {
        String returnValue = "";

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

                returnValue += source.charAt(i);
            }
        }

        return returnValue;
    }

    private static String getCurrency(String startPoint) {

        String result = getValues();

        String currencyValue = "";

        int start = result.indexOf("<rate id=" + '"' + startPoint + '"' + ">");

        int end = result.indexOf("</rate>", start);


        for (int i = start; i < result.length(); i++) {

            if (i > start && i < end) {

                currencyValue += result.charAt(i);

            }
        }

        return currencyValue;

    }
}
