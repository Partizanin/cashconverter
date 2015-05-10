package servlet;

import launch.ClassLoaderBanks;
import launch.ClassLoaderYahoo;
import launch.Exchange;
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

    private ClassLoaderYahoo clY = new ClassLoaderYahoo();

    private ClassLoaderBanks clB = new ClassLoaderBanks();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();


        JSONObject catchObject = new JSONObject();
        JSONObject sendObject = new JSONObject();
        String requestValue = "";


        try {
            catchObject = new JSONObject(request.getParameter("jsonData"));
            requestValue = catchObject.getString("operationCall");

            sendObject = setNewRateToJsonObject(requestValue); //fixme : add to constructor new variable

        } catch (JSONException e) {
            e.printStackTrace();
        }

        writer.println(sendObject);
        writer.flush();

        System.out.println(writer.toString());
    }

    private JSONObject setNewRateToJsonObject(String request) {
//improve test this new method with nested switch case!!! set better name for input parameter

        switch (request) {
            case "load":
                return onLoad();
            default:
                return Course(request);
        }
    }

    private JSONObject onLoad() {
        JSONObject obj = new JSONObject();


        try {
            Exchange exchange = clY.getExchangeById("UAH");
            obj.put("id", exchange.getId());
            obj.put("optionsValute", clY.getOptionsValute());
            obj.put("optionsCourse", clB.getOptionsCourse());
            obj.put("rows", exchange.getExchanges());
        } catch (JSONException e) {
            e.printStackTrace();
         }

        return obj;
    }

    private JSONObject Course(String exchangeValue) {
        JSONObject obj = new JSONObject();


        String[] requvest = exchangeValue.split("/");
        String valuta = requvest[0];
        String course = requvest[1];

        Exchange exchange = clY.getExchangeById(valuta);

        if (course.equals("NBU")) {

            exchange = clB.getExchangeById(valuta);
        }


        try {
            obj.put("id", exchange.getId());
            obj.put("rows", exchange.getExchanges());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
