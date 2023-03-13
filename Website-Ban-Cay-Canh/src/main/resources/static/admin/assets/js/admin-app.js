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
        .when("/product", {
            templateUrl: "../admin/products/manager-products.html",
            controller: "products-ctrl"
        })
        .when("/coupon", {
            templateUrl: "../admin/coupon/manager-coupon.html",
            controller: "coupon-ctrl"
        })
        .otherwise({
            templateUrl: "../admin/dashboard.html"
        })
})
