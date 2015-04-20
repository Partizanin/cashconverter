/**
 * Created by Partizanin on 08.04.2015.
 */
$(window).load(function () {


    $.ajax({
        url: '../js/jsonData.json', //Change this path to your JSON file.
        type: "post",
        dataType: "json",
        success: function(data,textStatus,jqXHR) {

            drawTable(data);
        }

    });

    function drawRow(rowData) {

        var row = $("<li/>");
        var input1 = rowData.input1;
        var input2 = rowData.input2;
        var lable = rowData.lable;
        $("#personDataTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it

        row.append(
            $('<input>',{
                type: 'text',
                readonly: true,
                value: input1.values,
                onblur:"if (this.value == '') {this.value = '0.00'; }",
                id:input1.id
            }),
            $('<label>',{
                "id": lable.id,
                "text": lable.values
            }),
            $('<input>',{
                "readonly": true,
                "id": input2.id,
                "value": input2.values
            })
        );

    }

    function drawTable(data) {

        data = data.DATA.fields;

        for(var i in data) {
            drawRow(data[i]);
        }
    }
});
