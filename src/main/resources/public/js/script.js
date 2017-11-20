/**
 * Created by Partizanin on 20.09.2016.
 */

var app = angular.module("converterApp", []);


app.controller("converterController", function ($scope, $http, $q) {
    /*start create function*/


    $scope.callToServer = function (request) {
        $scope.showLoader();

        var deferred = $q.defer();

        var myData = {"operationCall": request};

        $http({
            url: '/ConventerServlet',
            method: 'GET',
            params: {jsonData: JSON.stringify(myData)}
        }).success(function (data) {

            $scope.showLoader();

            deferred.resolve(data);

        });

        return deferred.promise;
    };

    $scope.showLoader = function () {
        $scope.loaderStatus = !$scope.loaderStatus;
    };

    $scope.buttonClick = function () {

        $scope.buttonDisable = !$scope.buttonDisable;

        console.log("buttonClick " + $scope.buttonDisable);

    };


    $scope.selectExchange = function () {

        $scope.changeCourse($scope.selectedExchangeValue + "/" + $scope.selectCourseValue);
        console.log("selectedExchange: " + $scope.selectedExchangeValue);
    };

    $scope.selectCourse = function () {

        $scope.changeCourse($scope.selectedExchangeValue + "/" + $scope.selectCourseValue);


        console.log("selectCourse: " + $scope.selectCourseValue);
    };

    $scope.changeCourse = function (request) {

        $scope.callToServer(request).then(function (data) {
            $scope.rows = data.rows;
            if (data.id) {
                $scope.selectedExchangeValue = data.id;
            }
            /** @namespace data.optionsCourse */
            if (data.optionsCourse) {
                $scope.optionsCourses = data.optionsCourse;
            }
            /** @namespace data.optionsValute */
            if (data.optionsValute) {
                $scope.optionsValutes = data.optionsValute;
            }
        });

    };

    /*end create function*/

    /*start initialize variable*/

    $scope.inputValue = 1;
    $scope.selectedExchangeValue = "UAH";
    $scope.selectCourseValue = "Google";
    $scope.buttonDisable = true;
    $scope.rows = {};
    $scope.optionsCourses = {};
    $scope.optionsValutes = {};

    /*end initialize variable*/

    /*start working area*/

    $scope.changeCourse("load");

    /*end working area*/


});

