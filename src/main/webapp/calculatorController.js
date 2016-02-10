var calculatorApp = angular.module('CalculatorApp', []).
controller('CalculatorController', function ($scope, $http) {
    $scope.operator = "+";
    $scope.result = "res";

    $scope.add = function () {
        $http.get('./rest/calc/add/' + $scope.x + '/' + $scope.y).success(function (data) {
            $scope.result = data;
        });
    };
    $scope.subtract = function () {
        $http.get('./rest/calc/subtract/' + $scope.x + '/' + $scope.y).success(function (data) {
            $scope.result = data;
        });
    };
    $scope.multiply = function () {
        $http.get('./rest/calc/multiply/' + $scope.x + '/' + $scope.y).success(function (data) {
            $scope.result = data;
        });
    };
    $scope.divide = function () {
        $http.get('./rest/calc/divide/' + $scope.x + '/' + $scope.y).success(function (data) {
            $scope.result = data;
        });
    };
});
