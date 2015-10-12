/**
 * Created by zzt on 10/9/15.
 */

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