app.controller("dashboard-ctrl", function ($scope, $http) {
    $scope.topsale = [];

    $scope.initialize = function () {
        $http.get("/api/products/topsale").then(resp => {
            $scope.topsale = resp.data;
            })
    }

    $scope.initialize();


})