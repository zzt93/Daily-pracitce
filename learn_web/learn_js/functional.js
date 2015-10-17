/**
 * Created by zzt on 10/14/15.
 */

/*
 The my object is a container of secrets that are
 shared by the constructors in the
 inheritance chain

 var constructor = function (spec, my) {
    var that, other private instance variables;
    my = my || {};
    //Add shared variables and functions to my
    that = a new object;
    //Add privileged methods to that
    return that;
 };
 */

var mammal = function (spec) {
    var that = {};

    that.get_name = function () {
        return spec.name;
    };
    that.says = function () {
        return spec.saying || '';
    };

    return that;
};

var myMammal = mammal({name: 'test'});

var cat = function (spec) {
    spec.saying = spec.saying || 'memo';
    var that = mammal(spec);

    that.get_name = function () {
        return that.says() + ' ' + spec.name +
            ' ' + that.says();
    };

    return that;
};