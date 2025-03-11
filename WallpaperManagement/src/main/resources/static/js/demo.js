//背景图片效果

var index = 0;
var timer = null;
var $loginbg = $('#login_wallpaper div');
timer = setInterval(auto, 8000)
auto()
function auto() {
    index++
    index = index > 2 ? 0 : index;
    $loginbg.eq(index).animate({ "opacity": "1" }, 8000).siblings().animate({ "opacity": "0" }, 8000)
}