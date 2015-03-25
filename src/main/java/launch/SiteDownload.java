package launch;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created with Intellij IDEA.
 * Project name: proff16
 * User: Partizanin
 * Date: 28.08.2014
 * Time:  21:30
 * To change this template use File|Setting|File Templates.
 */
public class SiteDownload {

    public String getSource() {
        StringBuilder source = new StringBuilder();
        URL url;
        InputStream is = null;
        BufferedReader br = null;
        String line = "";
        try {
            url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(\"UAHEUR\"%2C\"UAHUSD\"%2C\"UAHRUB\"%2C\"UAHPLN\"%2C\"USDEUR\"%2C\"USDUAH\"%2C\"USDRUB\"%2C\"USDPLN\"%2C\"EURUSD\"%2C\"EURUAH\"%2C\"EURRUB\"%2C\"EURPLN\"%2C\"RUBUAH\"%2C\"RUBEUR\"%2C\"RUBUSD\"%2C\"RUBPLN\"%2C\"PLNRUB\"%2C\"PLNEUR\"%2C\"PLNUSD\"%2C\"PLNUAH\")&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null){
                source.append(line);
            }

        } catch (IOException e) {
            System.err.println(e.getClass());
            try {
                source.append(readFromFile());
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } finally {
            if (is != null) {
                try {
                    is.close();
                    if (br != null) {
                        writeToFile(source.toString());
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return source.toString();
    }

    private static void writeToFile(String source){
        PrintWriter pw = null;
        try {
             pw = new PrintWriter("source.xml");
            pw.print(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    private static String readFromFile() throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get("source.xml"));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
