package launch;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with Intellij IDEA.
 * Project name: Partizanin
 * User: Partizanin
 * Date: 28.08.2014
 * Time:  21:30
 * To change this template use File|Setting|File Templates.
 */
class SiteDownload {

    private static String readFromFile(String name) throws IOException {
        if (name.equals("yahoo")) {
            name = "yahooSource.xml";
        } else {
            name = "bankSource.xml";
        }
        byte[] encoded = Files.readAllBytes(Paths.get(name));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    private String getUrlSource(String url) {
        StringBuilder sb = new StringBuilder("");
        URLConnection conn;
        BufferedReader br;
        try {
            conn = new URL(url).openConnection();
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 YaBrowser/17.3.1.840 Yowser/2.5 Safari/537.36");
            br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String[] getGoogleSource() {
        ArrayList<String> googleUrl = getGoogleUrl();
        String[] result = new String[googleUrl.size()];
        for (int i = 0; i < googleUrl.size(); i++) {
            String url = googleUrl.get(i);
            String urlSource = "";

            urlSource = getUrlSource(url);

            result[i] = urlSource;
        }
        return result;
    }

    private ArrayList<String> getGoogleUrl() {
        ArrayList<String> exchanges = new ArrayList<>();
        ArrayList<String> links = new ArrayList<>();
/*rub uah eur usd pln */

        exchanges.add("rub");
        exchanges.add("uah");
        exchanges.add("eur");
        exchanges.add("usd");
        exchanges.add("pln");

        for (Iterator<String> iterator = exchanges.iterator(); iterator.hasNext(); ) {
            String next = iterator.next();
            iterator.remove();


            for (String exchange : exchanges) {
                String sLink = "https://www.google.com.ua/search?q=%20to%20";
                StringBuilder link = new StringBuilder(sLink);
                link.insert(43, exchange);
                link.insert(35, next);
                links.add(link.toString());

                link = new StringBuilder(sLink);

                link.insert(43, next);
                link.insert(35, exchange);
                links.add(link.toString());

            }


        }

        return links;
    }

    private String getGoogleExchanges() {
        StringBuilder sb = new StringBuilder("{");
        String[] googleSource = getGoogleSource();


        sb.append("\"exchanges\": [");


        for (int i = 0; i < googleSource.length; i++) {
            String exchangeNameFromSource = getExchangeNameFromSource(googleSource[i]).toUpperCase();
            String exchangeValueFromSource = getExchangeValueFromSource(googleSource[i]);
            sb.append("{");
            sb.append("\"name\":").append("\"").append(exchangeNameFromSource).append("\"").append(",");
            sb.append("\"sellValue\":").append(getExchangeValueFromSource(googleSource[i])).append(",");
            sb.append("\"buyValue\":").append(exchangeValueFromSource);
            if (i < googleSource.length - 1) {
                sb.append("},");
            } else {
                sb.append("}");
            }
        }
        sb.append("]");

        sb.append("}");

        return sb.toString();
    }

    private String getExchangeValueFromSource(String source) {
        String result = "";
        int startIndexOfValue = source.indexOf("class=\"vk_ans vk_bk\">") + 21;
        int endIndexOfValue = startIndexOfValue + 8;
        result = source.substring(startIndexOfValue, endIndexOfValue);
        if (result.contains("itemsco")) {
            String className = "class=\"vk_ans vk_bk curtgt\"";

            int beginIndex = source.indexOf(className) + 40;
            result = source.substring(beginIndex, beginIndex + 200);

            int beginIndex1 = result.indexOf("<span style=\"word-break:break-all\">") + 35;
            result = result.substring(beginIndex1, beginIndex1 + 5);
        }


        return result;
    }

    private String getExchangeNameFromSource(String source) {
        String result = "";

        int startIndexOfExchangeName = source.indexOf("<input class=\"lst lst-tbb sbibps\" id=\"lst-ib\"");
        int endIndexOfExchangeName = startIndexOfExchangeName + 150;

        result = source.substring(startIndexOfExchangeName, endIndexOfExchangeName);

        startIndexOfExchangeName = result.indexOf("value=\"") + 7;
        endIndexOfExchangeName = startIndexOfExchangeName + 10;

        result = result.substring(startIndexOfExchangeName, endIndexOfExchangeName);

        result = result.replace("to", "");
        result = result.replaceAll(" ", "");
        return result;
    }

    private StringBuilder getSource2(URL url) {
        StringBuilder source = new StringBuilder();
        InputStream is = null;
        BufferedReader br = null;
        String line = "";
        try {

            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            while ((line = br.readLine()) != null) {
                source.append(line);
            }

        } catch (IOException e) {
            System.err.println(e);
        /*    try {
                source.append(readFromFile(name));
            } catch (IOException e1) {
                e1.printStackTrace();
            }*/
            // source.append(readFromFile());

        } finally {
            if (is != null) {
                try {
                    is.close();
                    if (br != null) {
                        // writeToFile(source.toString());
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return source;
    }

    private String getPath() throws UnsupportedEncodingException {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String result = "";

        int finish = path.indexOf("cashTestConverter") + 17;
        result = path.substring(0, finish);
        return result;
    }

    private String getValues(StringBuilder siteSourceCode) {

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

    private void writeToFile(String source, String name) throws IOException {
        PrintWriter pw = null;

        name = "nbuSource.xml";

        String path = getPath();

        File file = new File(path + "\\" + name);
        if (!file.exists() || file.isDirectory()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            pw = new PrintWriter(file);
            pw.print(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    String getSource(String name) {
        URL url = null;
        try {
            switch (name) {
                case "NBU":
                    url = new URL("http://bank-ua.com/export/exchange_rate_cash.xml");
                    break;
                case "Google":
                    url = new URL("http://bank-ua.com/export/exchange_rate_cash.xml");
                    break;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        StringBuilder source = new StringBuilder("");

        switch (name) {
            case "NBU":
                source = getSource2(url);
                return String.valueOf(source);
            case "Google":
                return getGoogleExchanges();
        }

        return String.valueOf(source);
    }
}
