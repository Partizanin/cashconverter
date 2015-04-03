package servlet;

import launch.ClassLoader;
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
    private static launch.ClassLoader cl = new ClassLoader();


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
            case "USD":
                return USDCourse(operation);
            case "EUR":
                return EURCourse(operation);
            case "RUB":
                return RUBCourse(operation);
            case "PLN":
                return PLNCourse(operation);
            default:
                /*uah*/
                return UAHCourse(operation);
        }
    }


    public static JSONObject UAHCourse(String transactionValue) {
        JSONObject obj = new JSONObject();


        try {

            obj.put("exchange1", cl.getCourseByIdAndOperation("UAHUSD", transactionValue));
            obj.put("exchange2", cl.getCourseByIdAndOperation("UAHRUB", transactionValue));
            obj.put("exchange3", cl.getCourseByIdAndOperation("UAHEUR", transactionValue));
            obj.put("exchange4", cl.getCourseByIdAndOperation("UAHPLN", transactionValue));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;

    }

    public static JSONObject USDCourse(String transactionValue) {
        JSONObject obj = new JSONObject();

        try {

            obj.put("exchange1", cl.getCourseByIdAndOperation("USDUAH", transactionValue));
            obj.put("exchange2", cl.getCourseByIdAndOperation("USDRUB", transactionValue));
            obj.put("exchange3", cl.getCourseByIdAndOperation("USDEUR", transactionValue));
            obj.put("exchange4", cl.getCourseByIdAndOperation("USDPLN", transactionValue));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;

    }

    public static JSONObject EURCourse(String transactionValue) {
        JSONObject obj = new JSONObject();

        try {

            obj.put("exchange1", cl.getCourseByIdAndOperation("EURUAH", transactionValue));
            obj.put("exchange2", cl.getCourseByIdAndOperation("EURRUB", transactionValue));
            obj.put("exchange3", cl.getCourseByIdAndOperation("EURUSD", transactionValue));
            obj.put("exchange4", cl.getCourseByIdAndOperation("EURPLN", transactionValue));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;

    }

    public static JSONObject RUBCourse(String transactionValue) {
        JSONObject obj = new JSONObject();

        try {

            obj.put("exchange1", cl.getCourseByIdAndOperation("RUBUAH", transactionValue));
            obj.put("exchange2", cl.getCourseByIdAndOperation("RUBUSD", transactionValue));
            obj.put("exchange3", cl.getCourseByIdAndOperation("RUBEUR", transactionValue));
            obj.put("exchange4", cl.getCourseByIdAndOperation("RUBPLN", transactionValue));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;

    }

    public static JSONObject PLNCourse(String transactionValue) {
        JSONObject obj = new JSONObject();

        try {

            obj.put("exchange1", cl.getCourseByIdAndOperation("PLNUSD", transactionValue));
            obj.put("exchange2", cl.getCourseByIdAndOperation("PLNRUB", transactionValue));
            obj.put("exchange3", cl.getCourseByIdAndOperation("PLNEUR", transactionValue));
            obj.put("exchange4", cl.getCourseByIdAndOperation("PLNUAH", transactionValue));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;

    }

}
