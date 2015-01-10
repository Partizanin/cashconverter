/**
 * Created by Partizanin on 16.12.2014.
 */

$(document).ready(function () {

    $('#buy').addClass('disabled');
    $("#buy").attr('disabled', true);
    callServeToChangeExchange("uah", "buy");
});

function isNumberKey(evt) {
    var charCode = ((evt.which) ? evt.which : event.keyCode);
    return !(charCode > 31 && (charCode != 46 && charCode != 44 && (charCode < 48 || charCode > 57)));

}

function disableButtonOnClick(buttonValue) {

    var value = "";
    if (buttonValue == "Купить") {
        $('#buy').addClass('disabled');
        $("#buy").attr('disabled', true);
        $("#sell").attr('disabled', false);
        $('#sell').removeClass('disabled');

        value = "buy";
    } else {
        $("#buy").attr('disabled', false);
        $("#sell").attr('disabled', true);
        $('#sell').addClass('disabled');
        $('#buy').removeClass('disabled');

        value = "sell";
    }


    return value;
}

function changeExchange(exchange) {
    var operation = "sell";
    if (document.getElementById('buy').disabled) {
        operation = "buy";
    }
    changeShowLable(exchange);
    callServeToChangeExchange(exchange, operation);

}

function changeShowLable(exchange) {

    if (exchange == "usd") {

        $('#value1').val("UAH");
        $('#value2').val("RUB");
        $('#value3').val("EUR");
        $('#value4').val("PLN");

    } else if (exchange == "eur") {

        $('#value1').val("UAH");
        $('#value2').val("RUB");
        $('#value3').val("USD");
        $('#value4').val("PLN");

    } else if (exchange == "rub") {

        $('#value1').val("UAH");
        $('#value2').val("USD");
        $('#value3').val("EUR");
        $('#value4').val("PLN");

    }  else if(exchange == "pln"){


        $('#value1').val("USD");
        $('#value2').val("RUB");
        $('#value3').val("EUR");
        $('#value4').val("UAH");

    } else {
        /*uah*/
        $('#value1').val("USD");
        $('#value2').val("RUB");
        $('#value3').val("EUR");
        $('#value4').val("PLN");
    }
}

function changeOperation(changeValue) {

    changeValue = disableButtonOnClick(changeValue);
    changeExchange($('#selectExchange').val(), changeValue);
}

function callServeToChangeExchange(exchange, operation) {

    var myData = {"operationCall": operation, "exchange": exchange};

    $.ajax({
        type: "GET",
        url: "/ConventerServlet",
        data: {jsonData: JSON.stringify(myData)},
        dataType: "json",

        //if received a response from the server
        success: function (data) {
            document.getElementById('exchange1').value = data.exchange1;
            document.getElementById('exchange2').value = data.exchange2;
            document.getElementById('exchange3').value = data.exchange3;
            document.getElementById('exchange4').value = data.exchange4;
            count($("#inputValue").val());
        }
    });

}

function count(inputValue) {

    if (inputValue != "" && inputValue != "0.00") {

        $('#conventUSD').val(($("#exchange1").val() * inputValue).toFixed(4));
        $('#conventEUR').val(($("#exchange3").val() * inputValue).toFixed(4));
        $('#conventRUB').val(($("#exchange2").val() * inputValue).toFixed(4));
        $('#conventUAH').val(($("#exchange4").val() * inputValue).toFixed(4));

    } else {
        var defaultValue = "0.00";
        $('#conventUSD').val(defaultValue);
        $('#conventEUR').val(defaultValue);
        $('#conventRUB').val(defaultValue);
        $('#conventUAH').val(defaultValue);

    }

}