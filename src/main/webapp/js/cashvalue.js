/**
 * Created by Partizanin on 16.12.2014.
 */

$(document).ready(function () {

    $("#buy").addClass("disabled");
    $("#buy").attr("disabled", true);
    $("#inputValue").focus();
    callServeToChangeExchange("uah", "buy");
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
    if (buttonValue == "Купить") {

        $("#buy").addClass("disabled");
        $("#buy").attr("disabled", true);
        $("#sell").attr("disabled", false);
        $("#sell").removeClass("disabled");

        value = "buy";
    } else {
        $("#buy").attr("disabled", false);
        $("#sell").attr("disabled", true);
        $("#sell").addClass("disabled");
        $("#buy").removeClass("disabled");

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

    if (exchange == "usd") {

        $("label[id = value1]").text("UAH");
        $("label[id = value2]").text("RUB");
        $("label[id = value3]").text("EUR");
        $("label[id = value4]").text("PLN");

    } else
    if (exchange == "eur") {

        $("label[id = value1]").text("UAH");
        $("label[id = value2]").text("RUB");
        $("label[id = value3]").text("USD");
        $("label[id = value4]").text("PLN");

    } else
    if (exchange == "rub") {

        $("label[id = value1]").text("UAH");
        $("label[id = value2]").text("USD");
        $("label[id = value3]").text("EUR");
        $("label[id = value4]").text("PLN");

    } else
    if (exchange == "pln") {

        $("label[id = value1]").text("USD");
        $("label[id = value2]").text("RUB");
        $("label[id = value3]").text("EUR");
        $("label[id = value4]").text("UAH");

    } else {
        /*uah*/
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
            loader("hide")
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

function blure(){

    if (validation()) {

        $('#inputValue').removeClass('validationValid');
    }else{

        $('#inputValue').removeClass('validationInvalid');
    }
}


function changeIcon(bool) {

    if (bool) {

        $('#inputValue').removeClass('inputValueInvalid');

        $("#inputValue").addClass("inputValueValid");

        console.log("bool in method: " + bool);
    } else if (!bool) {

        $('#inputValue').removeClass('inputValueValid');

        $('#inputValue').addClass('inputValueInvalid');

        console.log("bool in method: " + bool);
    }
}


function count(inputValue) {

    if (inputValue != "" && inputValue != "0.00" && inputValue != "0,00") {

        $("#conventUSD").val(($("#exchange1").val() * inputValue).toFixed(4));
        $("#conventEUR").val(($("#exchange3").val() * inputValue).toFixed(4));
        $("#conventRUB").val(($("#exchange2").val() * inputValue).toFixed(4));
        $("#conventUAH").val(($("#exchange4").val() * inputValue).toFixed(4));

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



