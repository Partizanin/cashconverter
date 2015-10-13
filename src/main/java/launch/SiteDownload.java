package launch;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created with Intellij IDEA.
 * Project name: Partizanin
 * User: Partizanin
 * Date: 28.08.2014
 * Time:  21:30
 * To change this template use File|Setting|File Templates.
 */
public class SiteDownload {

    public String getSource(String name) {
        StringBuilder source = new StringBuilder();
        URL url;
        InputStream is = null;
        BufferedReader br = null;
        String line = "";
        try {

            if (name.equals("yahoo")) {
                url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(\"UAHEUR\"%2C\"UAHUSD\"%2C\"UAHRUB\"%2C\"UAHPLN\"%2C\"USDEUR\"%2C\"USDUAH\"%2C\"USDRUB\"%2C\"USDPLN\"%2C\"EURUSD\"%2C\"EURUAH\"%2C\"EURRUB\"%2C\"EURPLN\"%2C\"RUBUAH\"%2C\"RUBEUR\"%2C\"RUBUSD\"%2C\"RUBPLN\"%2C\"PLNRUB\"%2C\"PLNEUR\"%2C\"PLNUSD\"%2C\"PLNUAH\")&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
            }else {
                url = new URL("http://bank-ua.com/export/exchange_rate_cash.xml");
            }
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            while ((line = br.readLine()) != null) {
                source.append(line);
            }

        } catch (IOException e) {
            System.err.println(e.getClass());
            try {
                source.append(readFromFile(name));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } finally {
            if (is != null) {
                try {
                    is.close();
                    if (br != null) {
                     //   writeToFile(source.toString(),name);
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        if (name.equals("yahoo")) {

            return getValues(source);
        }else {
            return String.valueOf(source);
        }
    }

    public String getPath() throws UnsupportedEncodingException {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String result = "";

        int finish = path.indexOf("cashConverter") + 17;
        result = path.substring(0, finish);
        return result;
    }

    private void writeToFile(String source, String name) throws IOException {
        PrintWriter pw = null;
        if (name.equals("yahoo")) {
            name = "yahooSource.xml";
        }else {
            name = "nbuSource.xml";
        }
        String path = getPath();

        File file = new File(path  + name);
        if (!file.exists() || file.isDirectory()) {
            try {
                file.createNewFile();
            } catch (IOException  e) {
                e.printStackTrace();
            }
        }
        try {
             pw = new PrintWriter(file);
            pw.print(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    private static String readFromFile(String name) throws IOException {
        if (name.equals("yahoo")) {
            name = "yahooSource.xml";
        }else {
            name = "bankSource.xml";
        }
        byte[] encoded = Files.readAllBytes(Paths.get(name));
        return new String(encoded, StandardCharsets.UTF_8);
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
}
