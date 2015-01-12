package launch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with Intellij IDEA.
 * Project name: proff16
 * User: Partizanin
 * Date: 28.08.2014
 * Time:  21:30
 * To change this template use File|Setting|File Templates.
 */
public class SiteDownload {

    public static void main(String[] args) {
        URL url;
        InputStream is = null;
        BufferedReader br = null;
        String line = "";
        /*LocalTime time = LocalTime.now(ZoneId.systemDefault());*/
        try {
            url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22UAHEUR%22%2C%22UAHUSD%22%2C%22UAHRUB%22%2C%22USDRUB%22%2C%22USDEUR%22%2C%22USDUAH%22%2C%22EURRUB%22%2C%22EURUSD%22%2C%22EURUAH%22%2C%22RUBUSD%22%2C%22RUBEUR%22%2C%22RUBUAH%22)&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
          /*  System.out.println("set url " + time);
            System.out.println("start open stream " + time);
          */  is = url.openStream();
            /*System.out.println("stream is opened " + time);
            System.out.println("start bufferReader " + time);
            */br = new BufferedReader(new InputStreamReader(is));
            /*System.out.println("bufferReader is started " + time);
            System.out.println("start read br " + time);
*/
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public String getSource() {
        HttpURLConnection con = null;
        String line = "";
        try {
            URL url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(\"UAHEUR\"%2C\"UAHUSD\"%2C\"UAHRUB\"%2C\"UAHPLN\"%2C\"USDEUR\"%2C\"USDUAH\"%2C\"USDRUB\"%2C\"USDPLN\"%2C\"EURUSD\"%2C\"EURUAH\"%2C\"EURRUB\"%2C\"EURPLN\"%2C\"RUBUAH\"%2C\"RUBEUR\"%2C\"RUBUSD\"%2C\"RUBPLN\"%2C\"PLNRUB\"%2C\"PLNEUR\"%2C\"PLNUSD\"%2C\"PLNUAH\")&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));//"Cp1251"
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");

            }
            line += sb;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert con != null;
            con.disconnect();
        }
        return line;
    }
}
