var app = angular.module("admin-app", ['ngRoute'])

app.config(function ($routeProvider) {
    $routeProvider
        .when("/dashboard", {
            templateUrl: "../admin/dashboard.html",
        })
        .when("/account", {
            templateUrl: "../admin/accounts/manager-accounts.html",
            controller: "accounts-ctrl"
        })
        .when("/order", {
            templateUrl: "../admin/tables-data.html",
            controller: "order-ctrl"

        })
        .when("/order", {
            templateUrl: "../admin/tables-data.html",
            controller: "order-ctrl"

        })

        .otherwise({
            templateUrl: "../admin/dashboard.html"
        })
})
