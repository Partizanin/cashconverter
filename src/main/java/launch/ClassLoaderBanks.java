package launch;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
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
public class ClassLoaderBanks {

    public static void main(String[] args) {
        ClassLoaderBanks cl = new ClassLoaderBanks();

        for (Exchange innerExchange : cl.exchangeList) {
            System.out.println(innerExchange);
        }
    }

    public ClassLoaderBanks() {
        createExchangeList();
    }

    private SiteFilterBanks sf = new SiteFilterBanks();

    private List<Exchange> exchangeList = new ArrayList<Exchange>();

    private static DecimalFormat df = new DecimalFormat("#.####");

    private List<InnerExchange> createInnerExchangeList() {

        List<InnerExchange> innerExchanges = new ArrayList<InnerExchange>();
        List<String> list = sf.getAllCurrency();

        for (String s : list) {

            InnerExchange exchange = new InnerExchange();



            String askVelue = sf.returnAskValueBySourceAndOperation(s, "buy");
            String bankName = sf.returnBankName(s);
            double parsedValue = Double.parseDouble(askVelue);
            String formatValue = df.format(parsedValue);

            exchange.setBankName(bankName);
            exchange.setId("UAH" + sf.getId(s));
            exchange.setBuyCourse(Double.parseDouble(formatValue));
            exchange.setSellCourse(Double.parseDouble(df.format(Double.parseDouble(sf.returnAskValueBySourceAndOperation(s, "sell")))));

            innerExchanges.add(exchange);
        }

        return innerExchanges;

    }

    private  void createExchangeList() {

        List<InnerExchange> innerExchanges = createInnerExchangeList();
            Exchange exchange = new Exchange("UAH");


            for (InnerExchange innerExchange : innerExchanges) {

                    exchange.addExchanges(innerExchange);

            }

            exchangeList.add(exchange);

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

        return new Exchange("UAH");
    }

    public Set<String> getOptionsValute() {

        return sf.getIdsForExchange();
    }

    public Set<String> getOptionsCourse() {
        Set<String> stringSet = new HashSet<>();
        stringSet.add("Yahoo");/*Добавляем курс яху потому что его в списке нету*/

        for (Exchange exchange : exchangeList) {
            for (InnerExchange innerExchange : exchange.getExchanges()) {

                stringSet.add(innerExchange.getBankName());
            }
        }
        return stringSet;
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
