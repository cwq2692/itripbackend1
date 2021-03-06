/**
 * Created by zhzbin on 2016/11/24.
 */
var tavern = {
    init: function (e) {
        var sef = this;
        sef.accordion = function (e) {
            var num = null;
            var speed = 600;
            var accor = e + " .box li";
            $(accor).each(function (index, el) {
                num = index + 1;
                $(el).css("background-image", "url(../images/img/tavern/tese/travel-accordion-" + num + ".jpg)");
            });
            $(accor).hover(function () {
                /*当前的那个盒子变大变600， 兄弟 变小变100*/
                $(this).stop().animate({"width": 400, "opacity": 1}, speed).siblings()
                    .stop().animate({"width": 149, "opacity": 0.3}, speed);
            }, function () {
                /*当鼠标离开的时候，所有的里都变成200*/
                $(accor).stop().animate({"width": 195, "opacity": 1}, speed);
            });
        }
        sef.banner = function (e) {

// 首页轮播图
            var num = 0;
            var timer = null;
            var i_circle = $(e + ' .lb li');
            var i_banner = $('.banner_list li');
            var i=1;
            $("a",i_banner).each(function (e) {
                this.style.background="url(../images/img/tavern/banner/"+i+".jpg)";
                i++;
            });
// 自动轮播
            function autoPlay() {
                timer = setInterval(function () {
                    num++;
                    if (num == i_circle.length + 1) {
                        num = 0;
                    }
                    i_circle.eq(num).addClass('cur').siblings().removeClass('cur');
                    i_banner.eq(num).fadeIn(600).siblings().fadeOut();
                }, 3000);
            }

//调用自动轮播
            autoPlay();
// 经过小圆点切换
            i_circle.hover(function () {
                clearInterval(timer);
                $(this).addClass('cur').siblings().removeClass('cur');
                num = $(this).index();
                i_banner.eq(num).fadeIn(600).siblings().fadeOut();
            }, function () {
                autoPlay();
            });
// 鼠标经过banner图事件
            i_banner.hover(function () {
                clearInterval(timer);
            }, function () {
                autoPlay();
            });
        }
        sef.travelTabs = function doubleTab(aEle, bEle, cls) {
            $(aEle).click(function () {
                $(this).addClass(cls).siblings().removeClass(cls);
                var num = $(this).index();
                $(bEle).eq(num).addClass('i_show').siblings().removeClass('i_show');
            });
        }
        sef.tavernList = './tavern_list.html';
        sef.tavernMessage = '../hotelDetail.html';

        $('html,body').stop().animate({'scrollTop': 0});
        function change(numy) {
            $('.points-list-menu .' + numy).addClass('act').siblings().removeClass('act');
        }

        var h1 = $('.travel-overseas-body').offset().top;
        var h2 = $('.travel-churchyard-body').offset().top;
        var h3 = $('.travel-rim-body').offset().top;
        var h4 = $('.travel-local-body').offset().top;
        var map = {
            'travel-overseas': h1,
            'travel-churchyard': h2,
            'travel-rim': h3,
            'travel-local': h4
        };
        $('.points-list-menu div[name=levelgroup]').click(function () {
            $(document).off();
            var a = map[this.className];
            change(this.className);
            $('html,body').stop().animate({'scrollTop': a},500,function (e) {
                $(document).scroll(scrollLeve);
            });
        });
        function scrollLeve(e) {
            var iScroll = $(document).scrollTop();
            if (iScroll + 200 >= h4) {
                change("travel-local");
            } else if (iScroll + 100 >= h3) {
                change("travel-rim");
            } else if (iScroll + 100 >= h2) {
                change("travel-churchyard");
            } else if (iScroll + 100 >= h1) {
                $('.points-list-menu').fadeIn();
                change("travel-overseas");
            } else {
                $('.points-list-menu').fadeOut();
            }
        }

        $(document).scroll(scrollLeve);
        sef.bindEvent();
    }, bindEvent: function (e) {
        var sef = this;
        $(".points-list .header-list-right").on("click", function (e) {
            if (!$(this).hasClass("tavern-gray")) {
                window.location = sef.tavernList;
            }
        });
        $(".points-list-body .column").on("click", function (e) {
            if (!$(this).hasClass("column-gray")) {
                window.location = sef.tavernMessage;//tavernList;
            }
        });
        $(".travel-accordion li").on("click", function (e) {
            window.location = sef.tavernMessage;//tavernList;
        });
        $(".banner-border li a").on("click", function (e) {
            window.location = sef.tavernMessage;//tavernList;
        });
    }, refresh: function (e) {
        alert("刷新函数是否正常使用？")
    }
};