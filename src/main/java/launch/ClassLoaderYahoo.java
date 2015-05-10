package launch;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with Intellij IDEA.
 * Project name: cashTestConverter.
 * User: Partizanin.
 * Date: 29.03.2015.
 * Time:  17:57.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class ClassLoaderYahoo {

    public static void main(String[] args) {

    }

    public ClassLoaderYahoo() {
        createExchangeList();
    }

    private SiteFilterYahoo sf = new SiteFilterYahoo();

    private List<Exchange> exchangeList = new ArrayList<Exchange>();

    private static DecimalFormat df = new DecimalFormat("#.####");

    private List<InnerExchange> createInnerExchangeList() {

        List<InnerExchange> innerExchanges = new ArrayList<InnerExchange>();
        List<String> list = sf.getAllCurrency();

        for (String s : list) {

            InnerExchange exchange = new InnerExchange();

            exchange.setId(sf.getId(s));

            String askVelue = sf.returnAskValueBySourceAndOperation(s, "buy");
            double parsedValue = Double.parseDouble(askVelue);
            String formatValue = df.format(parsedValue);
            exchange.setBuyCourse(Double.parseDouble(formatValue));
            exchange.setSellCourse(Double.parseDouble(df.format(Double.parseDouble(sf.returnAskValueBySourceAndOperation(s, "sell")))));

            innerExchanges.add(exchange);
        }

        return innerExchanges;

    }

    private  void createExchangeList() {

        for (String exchangeId : sf.getIdsForExchange()) {
            Exchange exchange = new Exchange(exchangeId);


            for (InnerExchange innerExchange : createInnerExchangeList()) {

                if (innerExchange.getId().substring(0, 3).equals(exchangeId)) {
                    exchange.addExchanges(innerExchange);
                }
            }

            exchangeList.add(exchange);
        }
    }

    public String getCourseByIdAndOperation(String exchangeId, String transactionValue) {
        String id = exchangeId.substring(0, 3);
        for (Exchange exchange : exchangeList) {

            if (exchange.getId().equals(id)) {
                return exchange.getCourseByIdAndOperation(exchangeId, transactionValue);
            }
        }
        return "null";
    }

    public Exchange getExchangeById(String id) {

        for (Exchange exchange : exchangeList) {
            if (exchange.getId().equals(id)) {
                return exchange;
            }
        }

        return new Exchange("Null");
    }

    public Set<String> getOptionsValute() {

        return sf.getIdsForExchange();
    }


    private boolean containOfId(String id) {

        for (Exchange exchange : exchangeList) {
            if (exchange.getId().equals(id)) {
                return true;
            }

        }

        return false;
    }

}
