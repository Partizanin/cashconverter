/**
    * Created with Intellij IDEA.
    * Project name: cashTestConverter.
    * User: Partizanin.
    * Date: 16.12.2014.
    * Time:  22:31.
    * To change this template use File|Setting|Editor|File and Code Templates.
    */

$(document).ready(function () {

    var buyButton = $("#buy");
    buyButton.addClass("disabled");
    buyButton.attr("disabled", true);
    $("#inputValue").focus();
    callServeToChangeExchange("UAH", "buy");
});

function loader(action) {

    if (action == "show") {

        $("#loader-wrapper").show();
    } else {/*hide*/
        $("#loader-wrapper").hide();
    }
}

function isNumberKey(evt) {
    var charCode = ((evt.which) ? evt.which : event.keyCode);
    return !(charCode > 31 && (charCode != 46 && charCode != 44 && (charCode < 48 || charCode > 57)));

}

function disableButtonOnClick(buttonValue) {

    var value = "";
    var buyButton = $("#buy");
    var sellButton = $("#sell");
    if (buttonValue == "Купить") {

        buyButton.addClass("disabled");
        buyButton.attr("disabled", true);
        sellButton.attr("disabled", false);
        sellButton.removeClass("disabled");

        value = "buy";
    } else {
        buyButton.attr("disabled", false);
        sellButton.attr("disabled", true);
        sellButton.addClass("disabled");
        buyButton.removeClass("disabled");

        value = "sell";
    }


    return value;
}

function howButtonActive() {

    if (document.getElementById("buy").disabled) {
        return "buy";
    } else {
        return "sell";
    }

}

function changeExchange(exchange) {
    var operation = howButtonActive();

    changeShowLable(exchange);

    if (validation()) {
        callServeToChangeExchange(exchange, operation);
    }

}

function changeShowLable(exchange) {

    if (exchange == "USD") {

        $("label[id = value1]").text("UAH");
        $("label[id = value2]").text("RUB");
        $("label[id = value3]").text("EUR");
        $("label[id = value4]").text("PLN");

    } else
    if (exchange == "EUR") {

        $("label[id = value1]").text("UAH");
        $("label[id = value2]").text("RUB");
        $("label[id = value3]").text("USD");
        $("label[id = value4]").text("PLN");

    } else
    if (exchange == "RUB") {

        $("label[id = value1]").text("UAH");
        $("label[id = value2]").text("USD");
        $("label[id = value3]").text("EUR");
        $("label[id = value4]").text("PLN");

    } else
    if (exchange == "PLN") {

        $("label[id = value1]").text("USD");
        $("label[id = value2]").text("RUB");
        $("label[id = value3]").text("EUR");
        $("label[id = value4]").text("UAH");

    } else {
        /*UAH*/
        $("label[id = value1]").text("USD");
        $("label[id = value2]").text("RUB");
        $("label[id = value3]").text("EUR");
        $("label[id = value4]").text("PLN");
    }
}

function changeOperation(changeValue) {

    changeValue = disableButtonOnClick(changeValue);
    changeExchange($("#selectExchange").val(), changeValue);
}

function callServeToChangeExchange(exchange, operation) {

    var myData = {"operationCall": operation, "exchange": exchange};

    loader("show");

    $.ajax({
        type: "GET",
        url: "/ConventerServlet",
        data: {jsonData: JSON.stringify(myData)},
        dataType: "json",

        //if received a response from the server
        success: function (data) {
            document.getElementById("exchange1").value = data.exchange1;
            document.getElementById("exchange2").value = data.exchange2;
            document.getElementById("exchange3").value = data.exchange3;
            document.getElementById("exchange4").value = data.exchange4;
            count($("#inputValue").val());
            /*loader("hide")*/
        },

        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        },

        complete: function () {
            loader("hide")
        }

    });

}

function validation() {

    var booll = ""/*parserFloat()*/;

    var input = document.getElementById("inputValue"),
        inputButton = document.getElementById("inputButton");

    if (!input.checkValidity()) {

        inputButton.click();

        booll = false;
    }else{
        booll = true;
    }

    if (booll) {
        count($("#inputValue").val());
    } else {
        setDefaultValues();
    }

    /*changeIcon(booll); dont work*/

    return booll;

}

function count(inputValue) {

    if (inputValue != "" && inputValue != "0.00" && inputValue != "0,00") {

        $("#conventUSD").val(formatOutput((($("#exchange1").val() * inputValue).toFixed(4))));
        $("#conventEUR").val(formatOutput((($("#exchange3").val() * inputValue).toFixed(4))));
        $("#conventRUB").val(formatOutput((($("#exchange2").val() * inputValue).toFixed(4))));
        $("#conventUAH").val(formatOutput((($("#exchange4").val() * inputValue).toFixed(4))));

    } else {
        setDefaultValues();
    }
    $("#inputValue").focus();
}

function setDefaultValues() {
    var defaultValue = "0.00";
    $("#conventUSD").val(defaultValue);
    $("#conventEUR").val(defaultValue);
    $("#conventRUB").val(defaultValue);
    $("#conventUAH").val(defaultValue);
}

function formatOutput(intputNumm){
    var parts = intputNumm.toString().split(".");
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    return parts.join(".");

}



