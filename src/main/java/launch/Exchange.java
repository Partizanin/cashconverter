package launch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * Project id: Partizanin.
 * User: Partizanin.
 * Date: 09.03.2015.
 * Time:  18:51.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class Exchange {

    private String id;

    private List<InnerExchange> exchanges = new ArrayList<>();

    public Exchange() {
    }

    public Exchange(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "id='" + id + '\'' +
                ", exchanges=" + exchanges +
                '}';
    }

    public List<InnerExchange> getExchanges() {
        return exchanges;
    }

    public List<InnerExchange> getExchangesByBankName(String bankName) {
        ArrayList<InnerExchange> result = new ArrayList<>();

        for (InnerExchange exchange : exchanges) {
            if (exchange.getBankName().equals(bankName)) {
                result.add(exchange);
            }
        }

        return result;
    }

    public void addExchanges(InnerExchange exchanges) {
        this.exchanges.add(exchanges);
    }

    public String getCourseByIdAndOperation(String exchangeId, String transactionValue) {

        for (InnerExchange exchange : exchanges) {

            if (exchange.getId().equals(exchangeId)) {
                return String.valueOf(exchange.getCourseByTransactionValue(transactionValue));
            }
        }

        return null;
    }
}
