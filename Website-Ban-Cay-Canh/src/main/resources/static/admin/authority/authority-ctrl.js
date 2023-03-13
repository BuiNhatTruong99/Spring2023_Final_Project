
app.controller("authority-ctrl", function($scope, $http) {
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities=[];
    $scope.accounts = [];
    $scope.init = function() {
        $http.get("/api/roles").then(resp =>{
			$scope.roles = resp.data;
		})

        $http.get("/rest/accounts?admin=true").then(resp => {
            $scope.admins = resp.data;
        })
        $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            $location.path("/unauthorized");
        })
    }

    $scope.init();

})
