
var create = function (o) {
    var F = function () {};
    F.prototype = o;
    return new F();
};

var father = {
    first_name: 'Tony',
    secont_name: 'ale'
};

var son = create(father);
son.first_name = 'tom';
delete son.first_name;

var myelement = document.getElementById("intro");
var x = document.getElementsByTagName("nav");
var intro = document.getElementsByClassName("nav.sidenav");

var herf = location.href;

document.cookie = "username=Tom; expires=";