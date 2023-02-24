app = angular.module("admin", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
        .when("/product", {
            templateUrl: "../admin/tables-data.html",
            controller: "product-ctrl"
        })
        .when("/authorize", {
            templateUrl: "../admin/charts-chartjs.html",
            controller: "authority-ctrl"
        })
        // .when("/usermanager", {
        //     templateUrl: "account/index.html",
        //     controller: "account-ctrl"
        // })
        // .when("/unauthorized", {
        //     templateUrl: "authority/unauthorized.html",
        //     controller: "authority-ctrl"
        // })
        .otherwise({
            template: "<h1 class='text-center'>FPT Polytechnic Administration</h1>"
        });


});

