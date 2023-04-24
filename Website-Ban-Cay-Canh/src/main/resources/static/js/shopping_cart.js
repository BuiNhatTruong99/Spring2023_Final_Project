const app = angular.module('shoppingCart', ['ngSanitize']);
app.controller('shoppingCart-ctrl', function ($scope, $http) {
    $scope.products = [];
    $scope.form = {};

    $scope.cates = function () {
        $http.get('/api/category').then(resp => {
            $scope.cates = resp.data;
        })
    };
    $scope.cates();

    $scope.cart = {
        items: [],
        add(id) {
            let quantity = document.querySelector("#quantity");
            let quantity2 = quantity ? parseInt(quantity.value) : 1;
            let product_size = document.querySelector('input[name="size"]:checked');
            let product_size2 = product_size ? product_size.value : '';
            let arr = product_size2.split(",");
            let idSize = arr[0] ? arr[0] : '';
            let priceSize = arr[1] ? arr[1] : 0;
            let item = this.items.find(item => item.id === id && item.idSize === idSize);
            if (quantity.value.length == 0) {
                return alert("Mời bạn vui lòng nhập số lượng!");
            }
            if (item) {
                item.qty += quantity2;
                this.saveToLocalStorage()
                alert("+" + quantity2 + " sản phẩm [" + item.name + "] vào giỏ hàng!");
            } else {
                $http.get(`/api/dto/products/${id}`).then(resp => {
                    resp.data.data.idSize = idSize;
                    resp.data.data.priceSize = Math.floor(priceSize);
                    resp.data.data.qty = quantity2;
                    this.items.push(resp.data.data);
                    this.saveToLocalStorage()
                    alert("Thêm " + quantity2 + " sản phẩm [" + resp.data.data.name + "] vào giỏ hàng thành công!");
                });
            }
        },
        remove(id, idSize) {
            let index = this.items.findIndex(item => item.id === id && item.idSize === idSize);
            if (index !== -1) {
                this.items.splice(index, 1);
                this.saveToLocalStorage()
            }
        },

        clear() {
            this.items = [];
            this.saveToLocalStorage()
        },

        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },

        get amount() {
            return this.items.map(item => item.qty * item.price + item.qty * item.priceSize)
                .reduce((total, qty) => total += qty, 0);
        },

        // save cart to localstorage
        saveToLocalStorage() {
            let json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },

        // read cart from localstoge
        loadFromLocalStorage() {
            let json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        },


    }
    $scope.cart.loadFromLocalStorage();

    var salePd = document.getElementById("saleP");
    if (salePd == null || salePd == undefined) {
        $scope.CouponSale = 0;
    } else {
        var paragraphValue = parseFloat(salePd.textContent);
        $scope.CouponSale = paragraphValue;
    }




    //payment

    $scope.formprofile = {};
    $scope.userid = [];
    $scope.profile = {};
    $scope.payment = {};

    // $scope.orderstatus = 1;
    $scope.initialize = function () {

        $http.get('/api/payment').then(resp => {
            $scope.payment = resp.data;
        })
        // $http.get('/api/orderStatus').then(resp => {
        //     $scope.orderstatus = resp.data;
        // })

    }

    $('#cart__button').ready(function () {
        var user_id = document.getElementById('user_id').value;
        console.log(user_id);
        if (user_id == null) {
            user_id = 0;
        } else {
            user_id = document.getElementById('user_id').value;
            $scope.userid = user_id;
        }

        $http.get(`/profile/${user_id}`).then(resp => {
            $scope.profile = resp.data;
            console.log($scope.profile)
            $scope.formprofile = angular.copy($scope.profile);
        })
    })

    $scope.initialize();


    $scope.order = {};
    $scope.shipmoney = 25000;
    $scope.clickship = function () {
        var selectedShip = parseInt(document.querySelector('input[name="ship"]:checked').value);
        $scope.shipmoney = selectedShip;
    }



    $scope.create = function () {
        var saleP = document.getElementById("saleP");
        var saleText = saleP.textContent;
        var saleValue = saleText.replace("%", "");
        saleP.textContent = saleValue;

        $scope.order.phone = document.getElementById('phone').value;
        $scope.order.address = document.getElementById('address').value;
        $scope.order.coupon = saleValue;
        $scope.order.ship = parseInt(document.querySelector('input[name="ship"]:checked').value);

        $scope.order.total = document.getElementById('totalmoney').textContent;
        // $scope.order.status.id = $scope.orderstatus = 1;
        // $scope.order.profile_id =  document.getElementById('user_id').value;

        var itemorder = angular.copy($scope.order);


        $http.post(`/api/order`, itemorder).then(resp => {
            $scope.order.push(resp.data);
            $scope.message = "Mua thành công";
            console.log($scope.order)
        }).catch(error => {
            $scope.message = "Mua thất bại";
            console.log("Error", error);
        })
    }
});

function getCurrentURL() {
    return window.location.pathname;
}

const url = getCurrentURL();
console.log(url);

app.filter('vndFilter', function () {
    return function (x) {
        x = x.toLocaleString('it-IT', { style: 'currency', currency: 'VND' });
        return x.toString().split('.').join(',');
    };
});
