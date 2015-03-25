package servlet;

import launch.SiteFilter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with Intellij IDEA.
 * Project name: proff16.
 * User: Partizanin.
 * Date: 05.12.2014.
 * Time:  15:30.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
@WebServlet(name = "ConventerServlet", urlPatterns = "/ConventerServlet")

public class ConventerServlet extends HttpServlet {
    private static SiteFilter sf = new SiteFilter();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();

        JSONObject catchObject = new JSONObject();

        String operation = "";
        String exchange = "";

        try {
            catchObject = new JSONObject(request.getParameter("jsonData"));
            operation = catchObject.getString("operationCall");
            exchange = catchObject.getString("exchange");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject sendObject = setNewRateToJsonObject(operation, exchange);

        writer.println(sendObject);
        writer.flush();
    }

    public static JSONObject setNewRateToJsonObject(String operation, String exchange) {

            switch (exchange) {
                case "usd":
                    return USDCourse(operation);
                case "eur":
                    return EURCourse(operation);
                case "rub":
                    return RUBCourse(operation);
                case "pln":
                    return PLNCourse(operation);
                default:
                /*uah*/
                    return UAHCourse(operation);
        }
    }


    public static JSONObject UAHCourse(String transactionValue) {
        JSONObject obj = new JSONObject();

        try {

            obj.put("exchange1", sf.getCourse("UAHUSD", transactionValue));
            obj.put("exchange2", sf.getCourse("UAHRUB", transactionValue));
            obj.put("exchange3", sf.getCourse("UAHEUR", transactionValue));
            obj.put("exchange4", sf.getCourse("UAHPLN", transactionValue));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;

    }

    public static JSONObject USDCourse(String transactionValue) {
        JSONObject obj = new JSONObject();

        try {

            obj.put("exchange1", sf.getCourse("USDUAH", transactionValue));
            obj.put("exchange2", sf.getCourse("USDRUB", transactionValue));
            obj.put("exchange3", sf.getCourse("USDEUR", transactionValue));
            obj.put("exchange4", sf.getCourse("USDPLN", transactionValue));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;

    }

    public static JSONObject EURCourse(String transactionValue) {
        JSONObject obj = new JSONObject();

        try {

            obj.put("exchange1", sf.getCourse("EURUAH", transactionValue));
            obj.put("exchange2", sf.getCourse("EURRUB", transactionValue));
            obj.put("exchange3", sf.getCourse("EURUSD", transactionValue));
            obj.put("exchange4", sf.getCourse("EURPLN", transactionValue));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;

    }

    public static JSONObject RUBCourse(String transactionValue) {
        JSONObject obj = new JSONObject();

        try {

            obj.put("exchange1", sf.getCourse("RUBUAH", transactionValue));
            obj.put("exchange2", sf.getCourse("RUBUSD", transactionValue));
            obj.put("exchange3", sf.getCourse("RUBEUR", transactionValue));
            obj.put("exchange4", sf.getCourse("RUBPLN", transactionValue));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;

    }

    public static JSONObject PLNCourse(String transactionValue) {
        JSONObject obj = new JSONObject();

        try {

            obj.put("exchange1", sf.getCourse("PLNUSD", transactionValue));
            obj.put("exchange2", sf.getCourse("PLNRUB", transactionValue));
            obj.put("exchange3", sf.getCourse("PLNEUR", transactionValue));
            obj.put("exchange4", sf.getCourse("PLNUAH", transactionValue));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;

    }

}
