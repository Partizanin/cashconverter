/**
 * Created with Intellij IDEA.
 * Project name: cashTestConverter.
 * User: Partizanin.
 * Date: 29.03.2015.
 * Time:  15:04.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */


$(window).load(function fill() {
    loader("show");
    callToServer("load").then(function (data) {

        var jsonData = data;

        for (var i = 0; i < jsonData.rows.length; i++) {

            drawRows(jsonData.rows[i]);

        }

        /** @namespace jsonData.optionsValute */
        for (var i = 0; i < jsonData.optionsValute.length; i++) {

            drawOptions(jsonData.optionsValute[i],"selectExchange");

        }
        $("select#selectExchange").val(jsonData.id);

        /** @namespace jsonData.optionsCourse */
        for(var i = 0; i < jsonData.optionsCourse.length; i++) {
            drawOptions(jsonData.optionsCourse[i],"selectCourse");
        }
        $("select#selectCourse").val("Yahoo");
        function drawOptions(optionValue,id) {
            $("#" + id).append(
                $("<option></option>")
                    .attr("value", optionValue).text(optionValue)
            );

        }

        function drawRows(rows) {

            var data = rows;


            var row = $("<li/>");

            $("#personDataTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it

            /** @namespace data.buyCourse */
            row.append(
                $('<input>', {
                    type: 'text',
                    readonly: true,
                    value: "0.00",
                    onblur: "if (this.value == '') {this.value = '0.00'; }",
                    id: "input1" + data.id
                }),
                $('<label>', {
                    "id": data.id,
                    "text": data.id.substring(3, data.id.length)
                }),
                $('<input>', {
                    "readonly": true,
                    "id": "input2" + data.id,
                    "value": data.buyCourse
                })
            );
        }
    });
    buttonOperation("buy", "disabled");
    loader("show");
});

function callToServer(request) {
    loader("show");
    var myData = {"operationCall": request};
    var defer = $.Deferred();
    $.ajax({
        type: "GET",
        url: "/ConventerServlet",
        data: {jsonData: JSON.stringify(myData)},
        contentType:"application/json; charset=utf-8",
        dataType: "json",

        //if received a response from the server
        success: function (jsonData) {

        },

        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        },

        complete: function () {
            loader("hide");
        }

    }).done(function (data) {
        defer.resolve(data);
    }).fail(function (xhr, status, errorThrown) {
        alert("Sorry, there was a problem!");
        console.log("Error: " + errorThrown);
        console.log("Status: " + status);
        console.dir(xhr);
    });

    return defer.promise();
}

function changedListener(object) {
    var pressedButton = getPressedButton();
    if (object.type == "select-one" && object.id == "selectExchange") {

        changContent(object.value, pressedButton, "changeCourse").then(function () {
            count();
        });


    } else if (object.type == "button") {


        changContent($("#selectExchange").val(), object.id, "changeOperation").then(function(){
            count();//fixme fix bug (after change currency if you click button, value not changed, value changed after second click)
         });


        buttonOperation(object.id, "disabled");


    } else if (object.type == "text") {

        validation();

    }

    function validation() {

        var booll = ""/*parserFloat()*/;

        var input = document.getElementById("inputValue"),
            inputButton = document.getElementById("inputButton");

        if (!input.checkValidity()) {

            inputButton.click();

            booll = false;
        } else {
            booll = true;
        }

        if (booll) {
            count();
        } else {
            /* setDefaultValues();*/
        }

        /*changeIcon(booll); dont work*/

        return booll;

    }


    var selectedValue= $( "#selectExchange" ).val();

    console.log("ChangeListener:" + selectedValue + " " + pressedButton );
}

function changContent(exchange, operation, action) {
    var defer = $.Deferred();


    if (action == "changeCourse") {

      changRows(exchange, operation).done(function(){
          defer.resolve();
      });

    } else {
        changOperation(exchange, operation).done(function(){
            defer.resolve();
        });

    }

    function changOperation(exchange, operation) {
        var defer = $.Deferred();
        callToServer(exchange + "/YAHOO").then(function (data) {

            /** @namespace rows[index].sellCourse */

            var rows = data.rows;

            for (var i = 0; i < rows.length; i++) {

                $('#personDataTable').find('li').each(function (index) {
//todo edith if method and remove duplicate code
                    if (operation === "buy") {
                        $(this).find('input:last').val(rows[index].buyCourse);
                    } else {
                        $(this).find('input:last').val(rows[index].sellCourse);
                    }

                });


            }

        }).done(function(){
            defer.resolve();
        });
        return defer.promise();
    }


    function changRows(exchange, operation) {
        var defer = $.Deferred();
        callToServer(exchange + "/YAHOO").then(function (data) {

            /** @namespace rows[index].sellCourse */

            var rows = data.rows;

            for (var i = 0; i < rows.length; i++) {

                $('#personDataTable').find('li').each(function (index) {
//todo edith if method and remove duplicate code
                    if (operation === "buy") {
                        $(this).find('input:last').val(rows[index].buyCourse);
                    } else {
                        $(this).find('input:last').val(rows[index].sellCourse);
                    }
                    $(this).find('label').text(rows[index].id.substr(3, 5));
                });


            }

        }).done(function () {
            defer.resolve();
        });

        return defer.promise();
    }

    return defer.promise();
}

function buttonOperation(buttonId, operation) {

    var anotherId = function () {

        if (buttonId == "sell") {
            return "buy";
        } else {
            return "sell";
        }
    };

    var button = $("#" + buttonId);

    if (operation == "disabled") {

        button.addClass(operation).attr(operation, true);
        $("#" + anotherId()).removeClass("disabled").removeAttr("disabled");
        //если операция disabled то мы добавляем клас disabled а в другой кнопке убираем disabled

    } else {

        // и наче мы убераем класс disabled а в другую добавляем
        button.removeClass("disabled").removeAttr("disabled");


        $("#" + anotherId()).addClass(operation).attr(operation, true);

    }

    $("#inputValue").focus();

}

function getPressedButton(){

    return $( "#buy" ).is(":disabled") ? "buy"  : "sell"

}

function count() {
    loader("show");
    var inputValue = $("#inputValue").val();
    $('#personDataTable').find('li').each(function () {
//todo edith if method and remove duplicate code

        var lastValue = $(this).find('input:last').val();

        var result = ((lastValue * inputValue).toFixed(4));
        $(this).find('input:first').val(formatOutput(result));
    });

    function formatOutput(inputNumber) {
        var parts = inputNumber.toString().split(".");
        parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, " ");
        return parts.join(".");

    }
    loader("hide");
}

function isNumberKey(evt) {
    var charCode = ((evt.which) ? evt.which : event.keyCode);
    return !(charCode > 31 && (charCode != 46 && charCode != 44 && (charCode < 48 || charCode > 57)));

}

function loader(action) {

    if (action == "show") {

        $("#loader-wrapper").show();
    } else {/*hide*/
        $("#loader-wrapper").hide();
    }
}
