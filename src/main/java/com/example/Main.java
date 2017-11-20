/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import launch.ClassLoaderBanks;
import launch.ClassLoaderGoogle;
import launch.Exchange;
import launch.InnerExchange;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@SpringBootApplication
public class Main {

    private ClassLoaderGoogle clG = new ClassLoaderGoogle();

    private ClassLoaderBanks clB = new ClassLoaderBanks();

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/ConventerServlet")
    @ResponseBody
    String Controller(@RequestParam("jsonData") JSONObject data) {
        JSONObject sendObject = new JSONObject();
        String requestValue = "";


        try {
            requestValue = data.getString("operationCall");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        sendObject = setNewRateToJsonObject(requestValue);


        return sendObject.toString();
    }

    private JSONObject setNewRateToJsonObject(String request) {
//improve test this new method with nested switch case!!! set better name for input parameter

        switch (request) {
            case "load":
                return onLoad();
            default:
                return courses(request);
        }
    }


    private JSONObject onLoad() {
        JSONObject obj = new JSONObject();


        try {
            Exchange exchange = clG.getExchangeById("UAH");
            obj.put("id", exchange.getId());
            obj.put("optionsValute", clG.getOptionsValute());
            obj.put("optionsCourse", clB.getOptionsCourse());
            obj.put("rows", packList(exchange.getExchanges()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    private JSONObject courses(String exchangeValue) {
        JSONObject obj = new JSONObject();


        String[] request = exchangeValue.split("/");
        String valuta = request[0];
        String course = request[1];

        Exchange exchange = new Exchange();

        if (course.equals("Google")) {
            exchange = clG.getExchangeById(valuta);
        } else if (!course.equals("Google") && !course.equals("Yahoo")) {
            valuta = "UAH";
            exchange = clB.getExchangeById(valuta);

        }
        try {

            obj.put("id", exchange.getId());

            ArrayList<String> valutes = new ArrayList<>(1);
            if (course.equals("Google")) {
                obj.put("rows", packList(exchange.getExchanges()));
                obj.put("optionsValute", clG.getOptionsValute());

            } else if (!course.equals("Google") && !course.equals("Yahoo")) {

                valutes.add("UAH");
                obj.put("rows", packList(exchange.getExchangesByBankName(course)));
                obj.put("optionsValute", valutes);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private JSONArray packList(List<InnerExchange> exchanges) {
        JSONArray jsonArray = new JSONArray();
        for (InnerExchange exchange : exchanges) {
            jsonArray.put(new JSONObject(exchange));
        }
        return jsonArray;
    }
}
