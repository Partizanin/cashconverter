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

    private static SiteDownload sd = new SiteDownload();

    private static String siteSource = sd.getSource();

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

            String line = getRUB();

            int start = line.indexOf("<Bid>") + 4;
            int finish = line.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < line.length(); i++) {
                if (i > start && i < finish) {
                    temp += line.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String buyRUB() {
            String source = getRUB();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String sellUSD() {
            String source = getUSD();


            int start = source.indexOf("<Bid>") + 4;
            int finish = source.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String buyUSD() {
            String source = getUSD();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String sellEUR() {
            String source = getEUR();


            int start = source.indexOf("<Bid>") + 4;
            int finish = source.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));

        }

        private static String buyEUR() {
            String source = getEUR();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");


            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));

        }

        private static String sellPLN() {
            String source = getPLN();


            int start = source.indexOf("<Bid>") + 4;
            int finish = source.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));

        }

        private static String buyPLN() {
            String source = getPLN();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");


            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }
            return String.valueOf(df.format(Double.parseDouble(temp)));

        }



        private static String getUSD() {
            String source = getValues();
            String USD = "";

            int start = source.indexOf("<rate id=\"UAHUSD\">");
            int end = source.indexOf("</rate>", start);

            for (int i = start; i < source.length(); i++) {

                if (i > start && i < end) {


                    USD += source.charAt(i);
                }
            }
            return USD;
        }

        private static String getEUR() {
            String result = getValues();
            String EUR = "";

            int start = result.indexOf("<rate id=\"UAHEUR\">");
            int end = result.indexOf("</rate>", start);

            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    EUR += result.charAt(i);
                }
            }
            return EUR;
        }

        private static String getRUB() {
            String result = getValues();
            String RUB = "";

            int start = result.indexOf("<rate id=\"UAHRUB\">");
            int end = result.indexOf("</rate>", start);


            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    RUB += result.charAt(i);
                }
            }


            return RUB;
        }

        private static String getPLN() {
            String result = getValues();
            String PLN = "";

            int start = result.indexOf("<rate id=\"UAHPLN\">");
            int end = result.indexOf("</rate>", start);


            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    PLN += result.charAt(i);
                }
            }


            return PLN;
        }


        public  String getBuyPLN() {
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

            String line = getRUB();

            int start = line.indexOf("<Bid>") + 4;
            int finish = line.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < line.length(); i++) {
                if (i > start && i < finish) {
                    temp += line.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String buyRUB() {
            String source = getRUB();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String sellUAH() {
            String source = getUAH();


            int start = source.indexOf("<Bid>") + 4;
            int finish = source.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String buyUAH() {
            String source = getUAH();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String sellEUR() {
            String source = getEUR();


            int start = source.indexOf("<Bid>") + 4;
            int finish = source.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));

        }

        private static String buyEUR() {
            String source = getEUR();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");


            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }
            return String.valueOf(df.format(Double.parseDouble(temp)));

        }

        private static String sellPLN() {
            String source = getPLN();


            int start = source.indexOf("<Bid>") + 4;
            int finish = source.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));

        }

        private static String buyPLN() {
            String source = getPLN();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");


            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));

        }


        private static String getUAH() {
            String result = getValues();
            String USD = "";

            int start = result.indexOf("<rate id=\"USDUAH\">");
            int end = result.indexOf("</rate>", start);

            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    USD += result.charAt(i);
                }
            }
            return USD;
        }

        private static String getEUR() {
            String result = getValues();
            String EUR = "";

            int start = result.indexOf("<rate id=\"USDEUR\">");
            int end = result.indexOf("</rate>", start);

            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    EUR += result.charAt(i);
                }
            }
            return EUR;
        }

        private static String getRUB() {
            String result = getValues();
            String RUB = "";

            int start = result.indexOf("<rate id=\"USDRUB\">");
            int end = result.indexOf("</rate>", start);


            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    RUB += result.charAt(i);
                }
            }


            return RUB;
        }

        private static String getPLN() {
            String result = getValues();
            String PLN = "";

            int start = result.indexOf("<rate id=\"USDPLN\">");
            int end = result.indexOf("</rate>", start);


            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    PLN += result.charAt(i);
                }
            }


            return PLN;
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

            String line = getRUB();

            int start = line.indexOf("<Bid>") + 4;
            int finish = line.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < line.length(); i++) {
                if (i > start && i < finish) {
                    temp += line.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String buyRUB() {
            String source = getRUB();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String sellUSD() {
            String source = getUSD();


            int start = source.indexOf("<Bid>") + 4;
            int finish = source.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String buyUSD() {
            String source = getUSD();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String sellUAH() {
            String source = getUAH();


            int start = source.indexOf("<Bid>") + 4;
            int finish = source.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));

        }

        private static String buyUAH() {
            String source = getUAH();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");


            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));

        }

        private static String sellPLN() {
            String source = getPLN();


            int start = source.indexOf("<Bid>") + 4;
            int finish = source.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));

        }

        private static String buyPLN() {
            String source = getPLN();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");


            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));

        }



        private static String getUSD() {
            String result = getValues();
            String USD = "";

            int start = result.indexOf("<rate id=\"EURUSD\">");
            int end = result.indexOf("</rate>", start);

            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    USD += result.charAt(i);
                }
            }
            return USD;
        }

        private static String getUAH() {
            String result = getValues();
            String EUR = "";

            int start = result.indexOf("<rate id=\"EURUAH\">");
            int end = result.indexOf("</rate>", start);

            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    EUR += result.charAt(i);
                }
            }
            return EUR;
        }

        private static String getRUB() {
            String result = getValues();
            String RUB = "";

            int start = result.indexOf("<rate id=\"EURRUB\">");
            int end = result.indexOf("</rate>", start);


            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    RUB += result.charAt(i);
                }
            }


            return RUB;
        }

        private static String getPLN() {
            String result = getValues();
            String PLN = "";

            int start = result.indexOf("<rate id=\"EURPLN\">");
            int end = result.indexOf("</rate>", start);


            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    PLN += result.charAt(i);
                }
            }


            return PLN;
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

            String line = getUAH();

            int start = line.indexOf("<Bid>") + 4;
            int finish = line.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < line.length(); i++) {
                if (i > start && i < finish) {
                    temp += line.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String buyUAH() {
            String source = getUAH();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String sellUSD() {
            String source = getUSD();


            int start = source.indexOf("<Bid>") + 4;
            int finish = source.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String buyUSD() {
            String source = getUSD();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String sellEUR() {
            String source = getEUR();


            int start = source.indexOf("<Bid>") + 4;
            int finish = source.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));

        }

        private static String buyEUR() {
            String source = getEUR();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");


            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));

        }

        private static String sellPLN() {
            String source = getPLN();


            int start = source.indexOf("<Bid>") + 4;
            int finish = source.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));

        }

        private static String buyPLN() {
            String source = getPLN();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");


            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));

        }


        private static String getUSD() {
            String result = getValues();
            String USD = "";

            int start = result.indexOf("<rate id=\"RUBUSD\">");
            int end = result.indexOf("</rate>", start);

            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    USD += result.charAt(i);
                }
            }
            return USD;
        }

        private static String getEUR() {
            String result = getValues();
            String EUR = "";

            int start = result.indexOf("<rate id=\"RUBEUR\">");
            int end = result.indexOf("</rate>", start);

            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    EUR += result.charAt(i);
                }
            }
            return EUR;
        }

        private static String getUAH() {
            String result = getValues();
            String RUB = "";

            int start = result.indexOf("<rate id=\"RUBUAH\">");
            int end = result.indexOf("</rate>", start);


            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    RUB += result.charAt(i);
                }
            }


            return RUB;
        }

        private static String getPLN() {
            String result = getValues();
            String PLN = "";

            int start = result.indexOf("<rate id=\"RUBPLN\">");
            int end = result.indexOf("</rate>", start);


            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    PLN += result.charAt(i);
                }
            }


            return PLN;
        }


        public  String getBuyPLN() {
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

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));
        }
        private static String sellUAH() {

            String line = getUAH();

            int start = line.indexOf("<Bid>") + 4;
            int finish = line.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < line.length(); i++) {
                if (i > start && i < finish) {
                    temp += line.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String buyRUB() {
            String source = getRUB();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));
        }
        private static String sellRUB() {

            String line = getRUB();

            int start = line.indexOf("<Bid>") + 4;
            int finish = line.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < line.length(); i++) {
                if (i > start && i < finish) {
                    temp += line.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String buyUSD() {
            String source = getUSD();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));
        }
        private static String sellUSD() {
            String source = getUSD();


            int start = source.indexOf("<Bid>") + 4;
            int finish = source.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));
        }

        private static String buyEUR() {
            String source = getEUR();

            int start = source.indexOf("<Ask>") + 4;
            int finish = source.indexOf("</Ask>");


            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }

            return String.valueOf(df.format(Double.parseDouble(temp)));

        }
        private static String sellEUR() {
            String source = getEUR();


            int start = source.indexOf("<Bid>") + 4;
            int finish = source.indexOf("</Bid>");

            String temp = "";

            for (int i = start; i < source.length(); i++) {
                if (i > start && i < finish) {
                    temp += source.charAt(i);
                }
            }


            return String.valueOf(df.format(Double.parseDouble(temp)));

        }


        private static String getUSD() {
            String result = getValues();
            String USD = "";

            int start = result.indexOf("<rate id=\"PLNUSD\">");
            int end = result.indexOf("</rate>", start);

            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    USD += result.charAt(i);
                }
            }
            return USD;
        }

        private static String getEUR() {
            String result = getValues();
            String EUR = "";

            int start = result.indexOf("<rate id=\"PLNEUR\">");
            int end = result.indexOf("</rate>", start);

            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    EUR += result.charAt(i);
                }
            }
            return EUR;
        }

        private static String getUAH() {
            String result = getValues();
            String RUB = "";

            int start = result.indexOf("<rate id=\"PLNUAH\">");
            int end = result.indexOf("</rate>", start);


            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    RUB += result.charAt(i);
                }
            }


            return RUB;
        }

        private static String getRUB() {
            String result = getValues();
            String RUB = "";

            int start = result.indexOf("<rate id=\"PLNRUB\">");
            int end = result.indexOf("</rate>", start);


            for (int i = start; i < result.length(); i++) {

                if (i > start && i < end) {


                    RUB += result.charAt(i);
                }
            }


            return RUB;
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

        public  String getSellRUB() {
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

    private static String returnAskValueMethod(String source, String operation){
        String returnValue = "";

        int start = 0;
        int finish = 0 ;

        if (operation.equals("buy")) {

            start = source.indexOf("<Ask>") + 4;
            finish = source.indexOf("</Ask>");


        }else { /*operation sell*/

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
}
