package servlet;

import classes.SiteFilter;
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
    private static SiteFilter.UAH uah = new SiteFilter.UAH();
    private static SiteFilter.USD usd = new SiteFilter.USD();
    private static SiteFilter.EUR eur = new SiteFilter.EUR();
    private static SiteFilter.RUB rub = new SiteFilter.RUB();
    private static SiteFilter.PLN pln = new SiteFilter.PLN();


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

        JSONObject sendObject = setNewRateToJsonObject(operation,exchange);

        writer.println(sendObject);
        writer.flush();
    }

    public static JSONObject setNewRateToJsonObject(String operation,String exchange){
        if (operation.equals("buy"))

            switch (exchange) {
            case "usd":
                return USDCourseBuy();
            case "eur":
                return EURCourseBuy();
            case "rub":
                return RUBCourseBuy();
            case "pln":
                return PNLCourseBuy();

            default:
                /*uah*/
                return UAHCourseBuy();

        }else { /*sell operation*/

            switch (exchange) {
                case "usd":
                    return USDCourseSell();
                case "eur":
                    return EURCourseSell();
                case "rub":
                    return RUBCourseSell();
                case "pln":
                    return PNLCourseSell();
                default:
                /*uah*/
                    return UAHCourseSell();
            }
        }

    }

    public static JSONObject UAHCourseBuy() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("exchange1", uah.getBuyUSD());
            obj.put("exchange2", uah.getBuyRUB());
            obj.put("exchange3", uah.getBuyEUR());
            obj.put("exchange4", uah.getBuyPLN());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static JSONObject UAHCourseSell() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("exchange1", uah.getSellUSD());
            obj.put("exchange2", uah.getSellRUB());
            obj.put("exchange3", uah.getSellEUR());
            obj.put("exchange4", uah.getSellPLN());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static JSONObject PNLCourseBuy() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("exchange1", pln.getBuyUSD());
            obj.put("exchange2", pln.getBuyRUB());
            obj.put("exchange3", pln.getBuyEUR());
            obj.put("exchange4", pln.getBuyUAH());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static JSONObject PNLCourseSell() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("exchange1", pln.getSellUSD());
            obj.put("exchange2", pln.getSellRUB());
            obj.put("exchange3", pln.getSellEUR());
            obj.put("exchange4", pln.getSellUAH());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static JSONObject USDCourseBuy() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("exchange1", usd.getBuyUAH());
            obj.put("exchange2", usd.getBuyRUB());
            obj.put("exchange3", usd.getBuyEUR());
            obj.put("exchange4", usd.getBuyPLN());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return obj;
    }

    public static JSONObject USDCourseSell() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("exchange1", usd.getSellUAH());
            obj.put("exchange2", usd.getSellRUB());
            obj.put("exchange3", usd.getSellEUR());
            obj.put("exchange4", usd.getSellPLN());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static JSONObject EURCourseBuy() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("exchange1", eur.getBuyUAH());
            obj.put("exchange2", eur.getBuyRUB());
            obj.put("exchange3", eur.getBuyUSD());
            obj.put("exchange4", eur.getBuyPLN());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return obj;
    }

    public static JSONObject EURCourseSell() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("exchange1", eur.getSellUAH());
            obj.put("exchange2", eur.getSellRUB());
            obj.put("exchange3", eur.getSellUSD());
            obj.put("exchange4", eur.getSellPLN());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static JSONObject RUBCourseBuy() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("exchange1", rub.getBuyUAH());
            obj.put("exchange2", rub.getBuyUSD());
            obj.put("exchange3", rub.getBuyEUR());
            obj.put("exchange4", rub.getBuyPLN());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return obj;
    }

    public static JSONObject RUBCourseSell() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("exchange1", rub.getSellUAH());
            obj.put("exchange2", rub.getSellUSD());
            obj.put("exchange3", rub.getSellEUR());
            obj.put("exchange4", rub.getSellPLN());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

}
