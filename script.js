
$('.slider').owlCarousel({
    loop: true,
    margin: 0,
    nav: true,
    autoplay: true,
    autoplayTimeout: 4000,
    animateOut: 'fadeOut',
    responsive: {
        0: {
            items: 1
        },
        600: {
            items: 1
        },
        1000: {
            items: 1
        }
    }
})
$('.owl-loaded').owlCarousel({
    loop: true,
    margin: 10,
    dots: false,
    nav: true,
    autoplay: true,
    autoplayTimeout: 2000,
    responsive: {
        0: {
            items: 1
        },
        600: {
            items: 3
        },
        1000: {
            items: 3
        }
    }
})
$('.owl-same-products').owlCarousel({
    loop: true,
    margin: 10,
    dots: false,
    nav: true,
    responsive: {
        0: {
            items: 1
        },
        600: {
            items: 4
        },
        1000: {
            items: 4
        }
    }
})

$(document).ready(function () {
    $(window).scroll(function () {
        if ($(this).scrollTop()) {
            $('.sticky-header').addClass('sticky');
            $('.scrolls').addClass('scroll-down');
        } else {
            $('.sticky-header').removeClass('sticky');
            $('.scrolls').removeClass('scroll-down');

        }
    })
})

$('.shop-btn').click(function () {
    $('nav .offcanvas_main_menu .shop-show').toggleClass("show");
})
$('.blog-btn').click(function () {
    $('nav .offcanvas_main_menu .blog-show').toggleClass("show");
})
$('.pages-btn').click(function () {
    $('nav .offcanvas_main_menu .pages-show').toggleClass("show");
})
$('.shop-lay-btn').click(function () {
    $('nav .offcanvas_main_menu .lay-show').toggleClass("show");
})
$('.shop-other-btn').click(function () {
    $('nav .offcanvas_main_menu .other-show').toggleClass("show");
})
$('.shop-pro-btn').click(function () {
    $('nav .offcanvas_main_menu .pro-show').toggleClass("show");
})

$('.mini_cart_wrapper').click(function () {
    $('.mini_cart').addClass("active");
})

$('.mini_cart_close').click(function () {
    $('.mini_cart').toggleClass("active");
})


const button = document.querySelector('.cart-alert')



function myf() {
    setInterval(function () {
        location.reload()
    }, 3000)
}


var popViews = document.querySelectorAll('.popup-view');
var popupBtns = document.querySelectorAll('.popup-btn');
var closeBtns = document.querySelectorAll('.close-btn');

var popup = function (popClick) {
    popViews[popClick].classList.add('active');
}

popupBtns.forEach((popupBtns, i) => {
    popupBtns.addEventListener("click", () => {
        popup(i);
    })
})

closeBtns.forEach((closeBtns) => {
    closeBtns.addEventListener("click", () => {
        popViews.forEach((popViews) => {
            popViews.classList.remove('active');
        })
    })
})

window.addEventListener('scroll', reveal);
function reveal() {
    var reveals = document.querySelectorAll('.reveal');
    for (var i = 0; i < reveals.length; i++) {
        var windowheight = window.innerHeight;
        var revealtop = reveals[i].getBoundingClientRect().top;
        var revealpoint = 120;

        if (revealtop < windowheight - revealpoint) {
            reveals[i].classList.add('fadeIn');
        } else {
            reveals[i].classList.remove('fadeIn');
        }
    }
}









