package launch;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import java.util.Set;
=======
>>>>>>> f22dc58137c88b20b01febf6162ff54b82423d57

/**
 * Created with Intellij IDEA.
 * Project name: cashTestConverter.
 * User: Partizanin.
 * Date: 29.03.2015.
 * Time:  17:57.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class ClassLoader {

    public static void main(String[] args) {
        ClassLoader cl = new ClassLoader();

<<<<<<< HEAD

=======
        for (InnerExchange uah : cl.getExchangeById("UAH").getExchanges()) {

            System.out.println(uah);
        }
>>>>>>> f22dc58137c88b20b01febf6162ff54b82423d57
    }

    public ClassLoader() {
        createExchangeList();
    }

    private SiteFilter sf = new SiteFilter();

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

<<<<<<< HEAD
=======

>>>>>>> f22dc58137c88b20b01febf6162ff54b82423d57
    private  void createExchangeList() {

        List<InnerExchange> innerExchanges = createInnerExchangeList();
        for (String exchangeId : sf.getIdsForExchange()) {
            Exchange exchange = new Exchange(exchangeId);


            for (InnerExchange innerExchange : innerExchanges) {

                if (innerExchange.getId().substring(0, 3).equals(exchangeId)) {
                    exchange.addExchanges(innerExchange);
                }
            }

            exchangeList.add(exchange);
        }
    }

<<<<<<< HEAD
=======

>>>>>>> f22dc58137c88b20b01febf6162ff54b82423d57
    public String getCourseByIdAndOperation(String exchangeId, String transactionValue) {
        String id = exchangeId.substring(0, 3);
        for (Exchange exchange : exchangeList) {

            if (exchange.getId().equals(id)) {
                return exchange.getCourseByIdAndOperation(exchangeId, transactionValue);
            }
        }
        return "null";
    }

<<<<<<< HEAD
=======

>>>>>>> f22dc58137c88b20b01febf6162ff54b82423d57
    public Exchange getExchangeById(String id) {

        for (Exchange exchange : exchangeList) {
            if (exchange.getId().equals(id)) {
                return exchange;
            }
        }

        return new Exchange("Null");
    }

<<<<<<< HEAD
    public Set<String> getOptions() {

        return sf.getIdsForExchange();
    }

=======

    /*Проверяет существующие курсы по id*/
>>>>>>> f22dc58137c88b20b01febf6162ff54b82423d57
    private boolean containOfId(String id) {

        for (Exchange exchange : exchangeList) {
            if (exchange.getId().equals(id)) {
                return true;
            }

        }

        return false;
    }

}
